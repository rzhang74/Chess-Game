package VC;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Stack;

import javax.swing.*;
import javax.swing.JOptionPane;

import BoardGame.*;
import Operation.Operation;
import Pieces.*;

import static java.lang.Math.abs;

public class Controller {
    private BoardGame bg;
    private View view;
    private JButton currentlySelected;

    /**
     * Constructor for the controller
     * @param bg the Boardgame model object
     * @param v the view for the application
     */
    public Controller(BoardGame bg, View v){
        this.bg = bg;
        this.view = v;
        initController();
    }

    /**
     * Add corresponding listeners to buttons and menu of the GUI view
     */
    public void initController(){
        view.getStart().addActionListener(e -> startGame());
        view.getRestart().addActionListener(e -> restartGame());
        view.getForfeit().addActionListener(e -> forfeit());
        view.getUndo().addActionListener(e -> undo());
        view.getSwitchmode().addActionListener(e->switchMode());
        JButton[][] cells = view.getButtons();
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                cells[i][j].addActionListener(e -> movePiece(e));
            }
        }
    }

    /**
     * Deserialize the string to get position of the board cell
     * @param s a serialized string containing info of the board cell location
     * @return Return a int array with first value being x coordinate and second value being y coordinate
     */
    private int [] getPosition(String s){
        int [] ret = {-1,-1};
        ret[0] = Character.getNumericValue(s.charAt(0));
        ret[1] = Character.getNumericValue(s.charAt(1));
        return ret;
    }

    /**
     * Handle the event for mainly selecting a piece and move to other location(mainly the boardgame side)
     * @param bg the BoardGame obejct
     * @param x1 piece's original x coordinate
     * @param y1 piece's original y coordinate
     * @param x2 piece's destination x coordinate
     * @param y2 piece's destination y coordinate
     */
    public void oneGameLoop(BoardGame bg, int x1, int y1, int x2, int y2){
        int wid = bg.getWidth(), hgt = bg.getHeight();
        if(x1==x2 && y1==y2) return;
        if(bg.getWinner() != null) {
            JOptionPane.showMessageDialog(null,"Winner has already been decided"); return;
        }

        if(x1 < 0 || y1 < 0 || x1 >= wid || y1 >= hgt){
            JOptionPane.showMessageDialog(null,"You must select from valid position"); return;
        }
        if(x2 < 0 || y2 < 0 || x2 >= wid || y2 >= hgt){
            JOptionPane.showMessageDialog(null,"You must go to valid position"); return;
        }
        if(!bg.checkPiece(x1,y1)) {
            JOptionPane.showMessageDialog(null,"You must select your piece"); return;
        }
        if((bg.getPiece(x1,y1).getColor() == Piece.Color.white && bg.getPlayer() == BoardGame.Player.player_black)
                || (bg.getPiece(x1,y1).getColor() == Piece.Color.black && bg.getPlayer() == BoardGame.Player.player_white)){
            JOptionPane.showMessageDialog(null,"You must select your piece"); return;
        }

        Piece p = bg.getPiece(x1,y1);
        if(p.canMove(x1,y1,x2,y2,bg)) {
            if(bg.checkKing(x2, y2)) {
                bg.setWinner(bg.getPlayer());
                updateScore(bg.getPlayer());
                JOptionPane.showMessageDialog(null,"Winner appears");
            }
            //bg.saveBoard(bg.getBoard()); //(deprecated, use stack for storing operation now)
            Operation op;
            if(p.getType() == Piece.Type.pawn && ((Pawn)p).getFirstTurn()) {
                op = new Operation(x1, y1, bg.getPiece(x1, y1), x2, y2, bg.getPiece(x2, y2), true);
                ((Pawn)p).setFirstTurn(false);
            }else
                op = new Operation(x1,y1,bg.getPiece(x1,y1),x2,y2,bg.getPiece(x2,y2),false);
            bg.getHistory().push(op);
            p.move(x1,y1,x2,y2,bg);
        }else{
            JOptionPane.showMessageDialog(null,"Cannot move to this position"); return;
        }

        bg.switchTurn();
        view.changeLabelColor(bg.getPlayer());
        view.render(bg.getBoard());

        int ret = bg.checkEnd();
        if(ret==1){
            JOptionPane.showMessageDialog(null,"Draw!");
            restartGame();
        }else if(ret==2) {
            JOptionPane.showMessageDialog(null, "White wins!");
            bg.setWinner(BoardGame.Player.player_white);
            updateScore(bg.getWinner());
        }else if(ret==3){
            JOptionPane.showMessageDialog(null, "black wins!");
            bg.setWinner(BoardGame.Player.player_black);
            updateScore(bg.getWinner());
        }
    }

    /**
     * Mark the moveable positions given a piece at location(x1,y1)
     * @param bg the boardgame
     * @param x1 x coordinate
     * @param y1 y coordinate
     */
    private void makeRange(BoardGame bg, int x1, int y1){
        Piece p = bg.getPiece(x1,y1);
        if(p==null) return;
        else{
            JButton[][] cells = view.getCells();
            for(int i = 0; i < bg.getWidth(); i++){
                for(int j = 0; j < bg.getHeight(); j++){
                    if(p.canMove(x1,y1,i,j,bg)) {
                        cells[i][j].setBorder(BorderFactory.createLineBorder(Color.blue));
                        if (abs(i - j) % 2 == 0)
                            cells[i][j].setBackground(new Color(254, 206, 159));
                        else
                            cells[i][j].setBackground(new Color(211, 139, 65));
                        cells[i][j].setOpaque(true);
                    }
                }
            }
        }
    }

    /**
     * Unmark the range produced by makeRange function
     * @param bg the boardgame
     */
    private void deleteRange(BoardGame bg){
        JButton[][] cells = view.getCells();
        for(int i = 0; i < bg.getWidth(); i++){
            for(int j = 0; j < bg.getHeight(); j++){
                if (abs(i-j)%2==0)
                    cells[i][j].setBackground(new Color(254, 206, 159));
                else
                    cells[i][j].setBackground(new Color(211, 139, 65));
                cells[i][j].setBorder(null);
                cells[i][j].setOpaque(true);
            }
        }
    }

    /**
     * Handle the event for mainly selecting a piece and move to other location(mainly the view side)
     * @param e event
     */
    private void movePiece(ActionEvent e){
        JButton cell = (JButton) e.getSource();
        int [] position = getPosition(e.getActionCommand());
        if(currentlySelected == null){
            currentlySelected = cell;
            makeRange(bg, position[0],position[1]);
            return;
        }
        deleteRange(bg);
        int [] old_position = getPosition(currentlySelected.getActionCommand());
        System.out.println("button " + position[0] + " , " + position[1] + " clicked");
        int x1 = old_position[0], y1 = old_position[1], x2 = position[0], y2 = position[1];

        oneGameLoop(bg, x1, y1, x2, y2);
        currentlySelected = null;
    }

    /**
     * Reinitialize the board game configuartion, including clear steps history
     */
    private void boardReconfiguration(){
        this.bg.getHistory().clear();
        this.bg.initBoard();
        this.bg.setPlayer(BoardGame.Player.player_white);
        this.bg.setWinner(null);
        this.view.changeLabelColor(BoardGame.Player.player_white);
        this.view.removeAllButtons();
        this.view.initializeButtons();
    }

    /**
     * Update the score for the chessboard game, winner has 1 more point
     * @param player the player who wins the game
     */
    private void updateScore(BoardGame.Player player){
        if(player == null) return;
        else if(player == BoardGame.Player.player_black) this.view.changeScore(true);
        else this.view.changeScore(false);
    }

    /**
     * Start Game event for clicking start
     */
    private void startGame(){
        System.out.println("Game start");
        //updateScore(bg.getWinner());
        boardReconfiguration();
        if(view.isCustomized()){
            view.addDifferntModeButtons();
            bg.addDifferentModeBoard();
        }
    }

    /**
     * Restart Game event for clicking restart
     */
    private void restartGame(){
        System.out.println("Game restart");
        boardReconfiguration();
        if(view.isCustomized()){
            view.addDifferntModeButtons();
            bg.addDifferentModeBoard();
        }
    }

    /**
     * Forfeit event for clicking forfeit
     */
    private void forfeit(){
        System.out.println("player forfeit");
        if(bg.getPlayer() == BoardGame.Player.player_white)
            updateScore(BoardGame.Player.player_black);
        else
            updateScore(BoardGame.Player.player_white);
        boardReconfiguration();
    }

    /**
     * Undo event for clicking undo. Can do multple undo steps
     */
    private void undo(){
        System.out.println("undoing");
        Stack<Operation> history = bg.getHistory();
        if(history.empty()){
            JOptionPane.showMessageDialog(null,"No previous operation");
            return;
        }

        BoardGame.Player player = bg.getOpponent(bg.getPlayer());
        bg.setPlayer(player);
        view.changeLabelColor(player);

        Operation op = history.pop();
        bg.undoOperation(op);
        view.render(bg.getBoard());

        //(deprecated, use stack for storing operation now)
//        if(bg.checkIdentical(bg.getBoard(), bg.getPre())){
//            JOptionPane.showMessageDialog(null,"The game has just started or redo haa already been done.");
//            return;
//        }
//        BoardGame.Player player = bg.getOpponent(bg.getPlayer());
//        bg.setPlayer(player);
//        bg.setBoard(bg.getPre());
//        view.changeLabelColor(player);
//        view.render(bg.getBoard());
    }

    /**
     * Switch Game mode, restart the game and turn normal chess board game to chess game with customized pieces and other way around
     */
    private void switchMode(){
        boardReconfiguration();
        view.resetScore();
        if(!view.isCustomized()){
            view.setCustomized(true);
            view.addDifferntModeButtons();
            bg.addDifferentModeBoard();
        }else{
            view.setCustomized(false);
        }
    }


}
