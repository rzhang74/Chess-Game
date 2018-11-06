package VC;
import BoardGame.BoardGame;
import Pieces.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import static java.lang.Math.*;
import javax.imageio.ImageIO;
import java.util.*;


public class View implements ActionListener{
    private JPanel chessboard;
    private JPanel info;
    private int width = 8;
    private int length = 8;
    private JButton[][] cells;
    private JLabel[][] labels;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("Game");
    private JMenuItem start = new JMenuItem("Start");
    private JMenuItem restart = new JMenuItem("Restart");
    private JMenuItem forfeit = new JMenuItem("Forfeit");
    private JMenuItem undo = new JMenuItem("Undo");
    private JMenuItem switchmode = new JMenuItem("Switch Mode");

    String player_w_name = "Jerry";
    String player_b_name = "Tom";
    int [] scores = {0,0};

    private boolean customized = false;

    /**
     * View constructor
     */
    public View(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        JFrame window = new JFrame("Basic ChessBoard Display");
        window.setSize(575/width*length, 500);

        setUpChessBoardPanel();
        setUpinfoPanel();
        initializeButtons();
        setUpMenu(window);

        window.getContentPane().add(chessboard, BorderLayout.CENTER);
        window.getContentPane().add(info, BorderLayout.EAST);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Set up the game info panel(player names, scores, etc)
     */
    private void setUpinfoPanel(){
        this.info = new JPanel(new GridLayout(8,2));
        labels = new JLabel[4][2];
        String [] messages = {"Player black:", player_b_name, "", "Score:", "0 : 0", "", "Player white:", player_w_name};
        this.info.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(Color.BLACK)
        ));
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 2; j++){
                JLabel label = new JLabel(messages[i*2+j],SwingConstants.CENTER);
                label.setFont(new Font("Serif", Font.PLAIN, 14));
                labels[i][j] = label;
                if(i==3&&j==1) labels[i][j].setForeground(Color.green);
                info.add(labels[i][j]);
            }
        }
    }

    /**
     * Set up the chess board GUI panel
     */
    private void setUpChessBoardPanel(){
        this.chessboard = new JPanel(new GridLayout(8,8));
        this.chessboard.setBorder(new CompoundBorder(
                new EmptyBorder(8, 8, 8, 8),
                new LineBorder(Color.BLACK)
        ));
        this.cells = new JButton[width][length];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                this.cells[i][j] = new JButton();
                if (abs(i-j)%2==0)
                    this.cells[i][j].setBackground(new Color(254, 206, 159));
                else
                    this.cells[i][j].setBackground(new Color(211, 139, 65));
                this.cells[i][j].setBorder(null);
                this.cells[i][j].setOpaque(true);
                this.cells[i][j].setActionCommand(""+i+j);
                this.chessboard.add(this.cells[i][j]);
            }
        }
    }

    /**
     * Set up the initial chess configuartion(white pieces, black pieces)
     */
    public void initializeButtons() {
        Image [] b_images = new Image[16];
        String [] b_names = {"b_rook","b_knight","b_bishop","b_queen","b_king","b_bishop","b_knight","b_rook",
                            "b_pawn","b_pawn","b_pawn","b_pawn","b_pawn","b_pawn","b_pawn","b_pawn"};

        Image [] w_images = new Image[16];
        String [] w_names = {"w_rook","w_knight","w_bishop","w_queen","w_king","w_bishop","w_knight","w_rook",
                                    "w_pawn","w_pawn","w_pawn","w_pawn","w_pawn","w_pawn","w_pawn","w_pawn"};

        for(int i = 0; i < 8; i++){
            cells[0][i].setIcon(createIcon(b_names[i]));
            cells[1][i].setIcon(createIcon(b_names[i+8]));
            cells[6][i].setIcon(createIcon(w_names[i+8]));
            cells[7][i].setIcon(createIcon(w_names[i]));
        }
    }

    /**
     * Give customized buttons to the board
     */
    public void addDifferntModeButtons(){
        System.out.println("mode changing");
        cells[0][2].setIcon(createIcon("b_destroyer"));
        cells[7][5].setIcon(createIcon("w_destroyer"));
        cells[0][5].setIcon(createIcon("b_spike"));
        cells[7][2].setIcon(createIcon("w_spike"));
    }

    /**
     * Remove all the chess pieces on board
     */
    public void removeAllButtons(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                cells[i][j].setIcon(null);
                cells[i][j].revalidate();
            }
        }
    }

    /**
     * Change the player's name color
     * @param player player_black or player_white
     */
    public void changeLabelColor(BoardGame.Player player){
        if(player == BoardGame.Player.player_black){
            labels[0][1].setForeground(Color.green);
            labels[3][1].setForeground(Color.black);
        }else{
            labels[0][1].setForeground(Color.black);
            labels[3][1].setForeground(Color.green);
        }
    }

    /**
     * return the cell GUI
     * @return Return the GUI board configuartion
     */
    public JButton[][] getCells() {
        return cells;
    }

    /**
     * Map to piece to image address
     * @param p a chess piece
     * @return Return a string that contains the location of its corresponding image
     */
    private String mapTypetoAddr(Piece p){
        if(p.getColor() == Piece.Color.white)
            return "w_" + p.getType().toString();
        else
            return "b_" + p.getType().toString();
    }

    /**
     * Render the chess board GUI configuartion given a piece array
     * @param pieces piece array that characterizes the chess board
     */
    public void render(Piece[][] pieces){
        removeAllButtons();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < length; j++){
                if(pieces[i][j]!=null){
                    cells[i][j].setIcon(createIcon(mapTypetoAddr(pieces[i][j])));
                    cells[i][j].revalidate();
                }
            }
        }
    }

    /**
     * Update the score given the winner
     * @param isBlack True if the black player wins and false if otherwise
     */
    public void changeScore(boolean isBlack){
        if(isBlack){
            this.scores[0]++;
        }else{
            this.scores[1]++;
        }
        String score_label = "" + this.scores[0] + " : " + this.scores[1];
        labels[2][0].setText(score_label);
        labels[2][0].revalidate();
    }

    /**
     * reset the score to 0:0
     */
    public void resetScore(){
        labels[2][0].setText("0 : 0");
        labels[2][0].revalidate();
    }

    /**
     * Create icon image based on the image name
     * @param image_name the name of image(in folder pieces_imgs)
     * @return the image name's corresponding image icon
     */
    private ImageIcon createIcon(String image_name){
        try {
            String path = "piece_imgs/";
            return new ImageIcon(ImageIO.read(getClass().getResource(path + image_name+".png")).getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH));
        } catch (Exception e) {
            System.out.println("error in reading image");
            return null;
        }
    }

    /**
     * Set up menue of application's window
     * @param window the window of the application
     */
    private void setUpMenu(JFrame window) {
        window.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(start);
        menu.add(restart);
        menu.add(forfeit);
        menu.add(undo);
        menu.add(switchmode);
    }

    /**
     * Setup the action perform when event happens
     * @param e event
     */
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,
                "I was clicked by "+e.getActionCommand(),
                "Title here", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Getter for start menu item
     * @return Return start menu item
     */
    public JMenuItem getStart() {
        return start;
    }

    /**
     * Getter for restart menu item
     * @return Return restart menu item
     */
    public JMenuItem getRestart() {
        return restart;
    }

    /**
     * Getter for forfeit menu item
     * @return Return forfeit menu item
     */
    public JMenuItem getForfeit() {
        return forfeit;
    }

    /**
     * Getter for undo menu item
     * @return Return undo menu item
     */
    public JMenuItem getUndo() {
        return undo;
    }

    /**
     * Getter for GUI board
     * @return Return GUI board with normal chess pieces initialized
     */
    public JButton[][] getButtons() {
        return cells;
    }

    /**
     * Getter for switch mode menu item
     * @return Return switch mode  menu item
     */
    public JMenuItem getSwitchmode() {
        return switchmode;
    }

    /**
     * Find location of the given button(deprecated, you can store string info in the button, which is better)
     * @param cell the given cell button of the board
     * @return Return the location of the button
     */

    /**
     * Getter whether the board is loaded with customized pieces
     * @return Return true if board id customized, otherwise if's just a normal chess board gae
     */
    public boolean isCustomized() {
        return customized;
    }

    /**
     * Setter for whether board is customized
     * @param customized true if board id customized, otherwise if's just a normal chess board gaeFad
     */
    public void setCustomized(boolean customized) {
        this.customized = customized;
    }

    public int[] findButton(JButton cell){
        int[] ret = {-1,-1};
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                if(cells[i][j] == cell)
                    ret[0] = i; ret[1] = j;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        new View();
    }
}
