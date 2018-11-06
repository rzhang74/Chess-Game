package Pieces;
import BoardGame.*;

import static java.lang.Math.*;

public class King extends Piece{
    /**
     * Constructor
     * @param color The color of the piece
     */
    public King(Color color){
        super(color);
        type = Type.king;
    }

    /**
     * Copy constructor
     * @param p The King object
     */
    public King(King p){
        super(p);
        System.out.println("King copying");
        type = Type.king;
    }

    /**
     * Return a deep copy of the King object
     * @return deep copy of caller
     */
    public King deepCopy(){
        return new King(this);
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
        System.out.println("checking if king can move");
        if(!super.checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;
        if(abs(x1-x2)<=1 && abs(y1-y2)<=1) return true;
        return false;
    }
}
