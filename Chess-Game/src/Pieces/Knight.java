package Pieces;
import BoardGame.*;

import static java.lang.Math.*;

public class Knight extends Piece{
    /**
     * Constructor
     * @param color The color of the piece
     */
    public Knight(Color color){
        super(color);
        type = Type.knight;
    }

    /**
     * Copy constructor
     * @param p The Knight object
     */
    public Knight(Knight p){
        super(p);
        System.out.println("Knight copying");
        type = Type.knight;
    }

    /**
     * Return a deep copy of the Knight object
     * @return deep copy of caller
     */
    public Knight deepCopy(){
        return new Knight(this);
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
        System.out.println("checking if knight can move");
        if(!super.checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;
        if((abs(x1-x2)==1 && abs(y1-y2)==2) || (abs(x1-x2)==2 && abs(y1-y2)==1)) return true;
        return false;
    }

}
