package Operation;

import Pieces.*;

public class Operation {
    int x1 = -1, y1 = -1;
    int x2 = -1, y2 = -1;
    Piece p1 = null, p2 = null;
    boolean revertFirsturn = false;

    /**
     * Constructor for move operation of a piece
     * @param x1 piece1 x coordinate
     * @param y1 piece1 y coordinate
     * @param p1 piece1
     * @param x2 piece2 x coordinate
     * @param y2 piece2 y coordinate
     * @param p2 piece2
     * @param r  whether it is first turn of pawn piece
     */
    public Operation(int x1, int y1, Piece p1, int x2, int y2, Piece p2, boolean r){
        this.x1 = x1;
        this.y1 = y1;
        this.p1 = p1;
        this.x2 = x2;
        this.y2 = y2;
        this.p2 = p2;
        this.revertFirsturn = r;
    }

    /**
     * Getter for revertFirsturn variable
     * @return Return whether it is first turn of pawn piece
     */
    public boolean getRevertFirsturn() {
        return revertFirsturn;
    }

    /**
     * Setter for revertFirsturn variable
     * @param revertFirsturn whether it is first turn of pawn piece
     */
    public void setRevertFirsturn(boolean revertFirsturn) {
        this.revertFirsturn = revertFirsturn;
    }

    /**
     * Getter for piece1 x coordinate
     * @return x coordinate of piece1
     */
    public int getX1() {
        return x1;
    }

    /**
     * Setter for piece1 x coordinate
     * @param x1
     */
    public void setX1(int x1) {
        this.x1 = x1;
    }

    /**
     * Getter for piece1 y coordinate
     * @return y coordinate of piece1
     */
    public int getY1() {
        return y1;
    }

    /**
     * Setter for piece1 y coordinate
     * @param y1
     */
    public void setY1(int y1) {
        this.y1 = y1;
    }

    /**
     * Getter for piece2 x coordinate
     * @return x coordinate of piece
     */
    public int getX2() {
        return x2;
    }

    /**
     * Setter for piece1 x coordinate
     * @param x2
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * Getter for piece2 y coordinate
     * @return y coordinate of piece2
     */
    public int getY2() {
        return y2;
    }

    /**
     * Setter for piece2 y coordinate
     * @param y2
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }

    /**
     * Getter for piece 1
     * @return Return piece 1, the piece at the starting location
     */
    public Piece getP1() {
        return p1;
    }

    /**
     * Setter for piece1
     * @param p1 piece 1, the piece at the starting location
     */
    public void setP1(Piece p1) {
        this.p1 = p1;
    }

    /**
     * Getter for piece 2
     * @return Return piece 2, the piece at the starting location
     */
    public Piece getP2() {
        return p2;
    }

    /**
     * Setter for piece2
     * @param p2 piece 2, the piece at the starting location
     */
    public void setP2(Piece p2) {
        this.p2 = p2;
    }
}
