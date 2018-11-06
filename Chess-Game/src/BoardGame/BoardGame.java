package BoardGame;
import Pieces.*;
import Operation.*;

import java.util.Stack;

public class BoardGame {
    public enum Player{
        player_white, player_black
    }
    int width, height;
    Player player;
    Player winner = null;
    Piece board[][];
    Piece pre[][]; //(deprecated, use stack for storing operation now)
    Stack<Operation> history = new Stack<Operation>();

    /**
     * Constructor for making the board game
     * @param width the width of the board
     * @param height the height of the board
     * @param init false if you want an empty board
     * @return return the boardgame object
     */
    public BoardGame(int width, int height, boolean init){
        this.width = width;
        this.height = height;
        this.player = Player.player_white;
        board = new Piece[width][height];

        if(init) {
            initBoard();
        }
        //this.pre = deepCopy(board);
    }

    /**
     * Initialize the chess board for the common game
     */
    public void initBoard(){
        board[0][0] = new Rook(Piece.Color.black);
        board[0][7] = new Rook(Piece.Color.black);
        board[7][0] = new Rook(Piece.Color.white);
        board[7][7] = new Rook(Piece.Color.white);

        board[0][1] = new Knight(Piece.Color.black);
        board[0][6] = new Knight(Piece.Color.black);
        board[7][1] = new Knight(Piece.Color.white);
        board[7][6] = new Knight(Piece.Color.white);

        board[0][2] = new Bishop(Piece.Color.black);
        board[0][5] = new Bishop(Piece.Color.black);
        board[7][2] = new Bishop(Piece.Color.white);
        board[7][5] = new Bishop(Piece.Color.white);

        board[0][3] = new Queen(Piece.Color.black);
        board[7][3] = new Queen(Piece.Color.white);

        board[0][4] = new King(Piece.Color.black);
        board[7][4] = new King(Piece.Color.white);

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Piece.Color.black);
            board[6][i] = new Pawn(Piece.Color.white);
        }

        for (int i = 2; i < 6; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = null;
            }
        }
    }

    /**
     * Give customized chess piece to the board
     */
    public void addDifferentModeBoard(){
        board[0][2] = new Destroyer(Piece.Color.black);
        board[7][5] = new Destroyer(Piece.Color.white);
        board[0][5] = new Spike(Piece.Color.black);
        board[7][2] = new Spike(Piece.Color.white);
    }

    /**
     * Getter for stack that stores operation history
     * @return the current board configuartion
     */
    public Stack<Operation> getHistory() {
        return history;
    }

    /**
     * Getter for board
     * @return the current board configuartion
     */
    public Piece[][] getBoard() {
        return board;
    }

    public void undoOperation(Operation op){
        int x1 = op.getX1(), x2 = op.getX2(), y1 = op.getY1(), y2 = op.getY2();
        Piece p1 = op.getP1(), p2 = op.getP2();
        boolean revert = op.getRevertFirsturn();
        if(revert)
            ((Pawn)p1).setFirstTurn(true);
        board[x1][y1] = p1;
        board[x2][y2] = p2;
    }

    /**
     * Check if two piece board configuration are identical(deprecated, use stack for storing operation now)
     * @param ps1 the first piece board
     * @param ps2 the second piece board
     * @return return true if two boards have the same configuration
     */
    public boolean checkIdentical(Piece[][] ps1, Piece[][] ps2){
        for(int i=0; i < width; i++){
            for(int j=0; j < height; j++){
                if(ps1[i][j]==null&&ps2[i][j]==null) continue;
                if(ps1[i][j]==null&&ps2[i][j]!=null) {System.out.println("c1"); return false;}
                if(ps1[i][j]!=null&&ps2[i][j]==null) {System.out.println("c2"); return false;}
                if(ps1[i][j].getType()!=ps2[i][j].getType()) {System.out.println("c3"); return false;}
            }
        }
        return true;
    }

    /**
     * Make a deep copy of piece configuration(deprecated, use stack for storing operation now)
     * @param pieces
     * @return return deep copy of piece configuration
     */
    public Piece[][] deepCopy(Piece[][] pieces){
        Piece[][] ps = new Piece[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(pieces[i][j] == null)
                    ps[i][j] = null;
                else
                    ps[i][j] = pieces[i][j].deepCopy();
            }
        }
        return ps;
    }

    /**
     * Save the previous board configuartion
     * @param pieces
     */
    public void saveBoard(Piece[][] pieces){
        this.pre = deepCopy(pieces);
    }
    /**
     * get the height of board
     * @return return value of the board's height
     */
    public int getWidth() {
        return width;
    }

    /**
     * get the height of board
     * @return return value of the board's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * get the current player
     * @return Return the player this turn
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * set current player given a player
     * @param player The player next turn
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Check if there is a piece on location (x,y)
     * @param x The piece's row index
     * @param y The piece's column index
     * @return Return true if there is a piece on that location(x,y)
     */
    public boolean checkPiece(int x, int y){
        if(x<0 || y<0 || x>=width || y>=width) return false;
        return this.board[x][y] != null;
    }

    /**
     * set piece on location (x,y)
     * @param x The piece's row index
     * @param y The piece's column index
     * @param p The piece
     */
    public void setPiece(int x, int y, Piece p) {
        if(x<0 || y<0 || x>=width || y>=width) return;
        this.board[x][y] = p;
    }

    /**
     * Get the piece on location (x,y)
     * @param x The piece's row index
     * @param y The piece's column index
     * @return Return the piece if there is a piece on that location(x,y), otherwise null
     */
    public Piece getPiece(int x, int y){
        if(x<0 || y<0 || x>=width || y>=width) return null;
        return this.board[x][y];
    }

    /**
     * Get the winner
     * @return Return the winner if exists, otherwise null
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * set winner given a player
     *
     * @param winner The potential winner
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Setter for current board configuration
     * @param board
     */
    public void setBoard(Piece[][] board) {
        this.board = deepCopy(board);
    }

    /**
     * Getter for Previous board configuration(deprecated, use stack for storing operation now)
     * @return piece array that contains previous board configuartion
     */
    public Piece[][] getPre() {
        return pre;
    }

    /**
     * Setter for Previous board configuration(deprecated, use stack for storing operation now)
     * @param board
     */
    public void setPre(Piece[][] board) {
        this.pre = deepCopy(board);
    }

    /**
     * Check if the opponent's king is at this position
     * @param x2 The piece's row index
     * @param y2 The piece's column index
     * @return Return true if opponent's king is at this position
     */
    public boolean checkKing(int x2, int y2){
        if(board[x2][y2] == null) return false;
        Piece p2 = this.getPiece(x2, y2);
        if((p2.getColor() == Piece.Color.white && this.getPlayer() == Player.player_white)
                || (p2.getColor() == Piece.Color.black && this.getPlayer() == BoardGame.Player.player_black))
            return false;
        if(p2.getType() != null && p2.getType() == Piece.Type.king)
            return true;
        return false;
    }

    /**
     * Return the pieces of a given player
     * @param player player_white or player_black
     * @return Return all the black pieces if player_black or all the white pieces if player_white
     */
    public  Piece[] getPieces(Player player){
        Piece[] pieces = new Piece[16];
        int index = 0;
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(board[i][j]==null) continue;
                if(player == Player.player_black && board[i][j].getColor() == Piece.Color.black)
                    pieces[index++] = board[i][j];
                else if(player == Player.player_white && board[i][j].getColor() == Piece.Color.white)
                    pieces[index++] = board[i][j];
            }
        }
        return pieces;
    }


    /**
     * Return the king position of a given player
     * @param player player_white or player_black
     * @return Return the king position of a given player
     */
    public int[] getKingPos(Player player){
        int [] pos = {-1,-1};
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(board[i][j]==null) continue;
                if(player == Player.player_black && board[i][j].getColor() == Piece.Color.black && board[i][j].getType() == Piece.Type.king){
                    pos[0] = i; pos[1] = j;
                }
                else if(player == Player.player_white && board[i][j].getColor() == Piece.Color.white && board[i][j].getType() == Piece.Type.king) {
                    pos[0] = i; pos[1] = j;
                }
            }
        }
        return pos;
    }

    /**
     * Return the king piece of a given player
     * @param pieces a given player's pieces
     * @return Return the king of pieces of white player or black player
     */
    public Piece getKing(Piece[] pieces){
        for(int i = 0; i < pieces.length; i++)
            if(pieces[i] != null && pieces[i].getType() == Piece.Type.king)
                return pieces[i];
        return null;
    }

    /**
     * Return the oppponent player of a given player
     * @param player a given player
     * @return Return black player if current player is white and white if otherwise
     */
    public Player getOpponent(Player player){
        if (player == Player.player_white)
            return Player.player_black;
        return Player.player_white;
    }

    /**
     * Return color of pieces being played by a given player
     * @param player a given player
     * @return Return black if current player is black and white if otherwise
     */
    public Piece.Color getColorOfPlayer(Player player){
        if(player == Player.player_black)
            return Piece.Color.black;
        return Piece.Color.white;
    }

    /**
     * Return true if given player's king is under check
     * @param x the player's king row position
     * @param y the player's king col position
     * @param player a given player
     * @return Return true if given player's king is under check
     */
    public boolean isUnderCheck(int x, int y, Player player){
        System.out.println("Is under check calling");
        Player opponent = getOpponent(player);

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(board[i][j]==null) continue;
                if(board[i][j].getColor() == getColorOfPlayer(opponent) && board[i][j].canMove(i,j,x,y,this)) {
                    System.out.println("****"+board[i][j].getType().toString() + "at lcoation " + i + ", " + j + "can check king");
                    return true;
                }
            }
        }
        System.out.println("Returning false");
        return false;
    }

    /**
     * Return true if one of the player's pieces except king can make a legal move
     * @param player a given player
     * @param k_i the player's king row position
     * @param k_j the player's king col position
     * @return Return false if none of player's pieces except king can make a legal move, that is,
     * no matter how that piece moves, the king will be checked. Return true if otherwise.
     */
    public boolean checkLegalMovesExceptKing(Player player, int k_i, int k_j){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(board[i][j] == null || board[i][j].getColor() != getColorOfPlayer(player) || board[i][j].getType() == Piece.Type.king)
                    continue;
                for(int x = 0; x < width; x++){
                    for(int y = 0; y < height; y++){
                        if(board[i][j].canMove(i,j,x,y,this)){
                            Piece pij = board[i][j];
                            Piece pxy = board[x][y];
                            board[x][y] = pij;
                            board[i][j] = null;
                            boolean legal = !isUnderCheck(k_i,k_j,player);
                            board[x][y] = pxy;
                            board[i][j] = pij;
                            if(legal) return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Return true if there is no legal move for the player
     * @param player a given player
     * @param k_i the player's king row position
     * @param k_j the player's king col position
     * @return Return true if there is no legal move, that is, no matter the piece of a player moves, the king will be checked.
     */
    public boolean checkIfNoLegalMoves(Player player, int k_i, int k_j) {
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i==0 && j==0){
                    if(checkLegalMovesExceptKing(player,k_i,k_j)) {System.out.println("!!!!!!!!!!!!");return false;}
                    continue;
                }
                int x_pos = k_i+i;
                int y_pos = k_j+j;
                if(board[k_i][k_j].canMove(k_i,k_j,x_pos,y_pos,this)){
                    Piece king = board[k_i][k_j];
                    Piece pxy = board[x_pos][y_pos];
                    board[k_i][k_j] = null;
                    board[x_pos][y_pos] = king;
                    boolean check = isUnderCheck(x_pos,y_pos,player);
                    board[k_i][k_j] = king;
                    board[x_pos][y_pos] = pxy;
                    if(!check) return false;
                }
            }
        }
        return true;
    }

    /**
     * Return true if there's a checkmate situation
     * @param player a given player
     * @return Return true if there's a checkmate situation, that is, the king is being checked,
     * and no matter how the player moves his piece, his king will be checked.
     */
    public boolean isCheckMate(Player player){
        //checkmate = king is under check && no other legal moves
        System.out.println("! checking checkmate");
        int [] king_pos = getKingPos(player);
        if(!isUnderCheck(king_pos[0],king_pos[1],player)) return false;
        return checkIfNoLegalMoves(player, king_pos[0], king_pos[1]);
    }

    /**
     * Return true if there's a stalemate situation
     * @param player a given player
     * @return Return true if there's a stalemate situation, that is,
     * no matter the king is not being checked, but no matter how the player moves his piece, his king will be checked.
     */
    public boolean isStaleMate(Player player){
        //stalemate = king is not under check && no other legal moves
        int [] king_pos = getKingPos(player);
        if(isUnderCheck(king_pos[0],king_pos[1],player)) return false;
        return checkIfNoLegalMoves(player, king_pos[0], king_pos[1]);
    }

    /**
     * Return the value of game condition
     * @return Return 1 if there's a stalemate situation(draw); Return 2 if white wins; Return 3 if black wins;
     * Otherwise the game does not end, return 0;
     */
    public int checkEnd(){
        //if(isStaleMate(Player.player_white) || isStaleMate(Player.player_black)) return 1;
        int[] blackKing = getKingPos(Player.player_black);
        System.out.println("black king pos: " + blackKing[0] + ", " + blackKing[1]);
        int[] whiteKing = getKingPos(Player.player_white);
        System.out.println("white king pos: " + whiteKing[0] + ", " + whiteKing[1]);
        if(blackKing[0]==-1 || isCheckMate(Player.player_black)) return 2;
        if(whiteKing[0]==-1 || isCheckMate(Player.player_white)) return 3;
        return 0;
    }

    /**
     * Make the player switch turn, if it is player_white, player_black will take the turn and player_white will take the
     * turn if otherwise;
     */
    public void switchTurn(){
        if(getPlayer() == BoardGame.Player.player_black)
            setPlayer(BoardGame.Player.player_white);
        else
            setPlayer(BoardGame.Player.player_black);
    }
}