package Pieces;
import BoardGame.*;

import static java.lang.Math.*;

public class Queen extends Piece{
    /**
     * Constructor
     * @param color The color of the piece
     */
    public Queen(Color color){
        super(color);
        type = Type.queen;
    }

    /**
     * Copy constructor
     * @param p The Queen object
     */
    public Queen(Queen p){
        super(p);
        System.out.println("Queen copying");
        type = Type.queen;
    }

    /**
     * Return a deep copy of the bishop object
     * @return deep copy of caller
     */
    public Queen deepCopy(){
        return new Queen(this);
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
        System.out.println("checking if queen can move");
        if(!super.checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;

        return (checkVertialHorizontal(x1,y1,x2,y2) && checkThroughVertialHorizontal(x1,y1,x2,y2,bg))
                ||(checkDiagonal(x1,y1,x2,y2) && checkThroughDiagonal(x1,y1,x2,y2,bg));
    }
}
