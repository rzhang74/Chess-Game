package Pieces;
import BoardGame.*;

import static java.lang.Math.*;


public class Bishop extends Piece {
    /**
     * Constructor
     * @param color The color of the piece
     */
    public Bishop(Color color){
        super(color);
        type = Type.bishop;
    }

    /**
     * Copy constructor
     * @param p The Bishop object
     */
    public Bishop(Bishop p){
        super(p);
        System.out.println("bishop copying");
        type = Type.bishop;
    }

    /**
     * Return a deep copy of the bishop object
     * @return deep copy of caller
     */
    public Bishop deepCopy(){
        return new Bishop(this);
    }

    /**
     * Return true if it's valid to move to the target position
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @param bg The corresponding chessboard game
     * @return Return true if it's valid to move to the target position
     */
    public boolean canMove(int x1, int y1, int x2, int y2, BoardGame bg){
        System.out.println("checking if bishop can move");
        if(!checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;

        return checkDiagonal(x1,y1,x2,y2) && checkThroughDiagonal(x1,y1,x2,y2,bg);
    }

}
