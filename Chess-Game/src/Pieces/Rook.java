package Pieces;
import BoardGame.*;
import static java.lang.Math.*;


public class Rook extends Piece{
    /**
     * Constructor
     * @param color The color of the piece
     */
    public Rook(Color color){
        super(color);
        type = Type.rook;
    }

    /**
     * Copy constructor
     * @param p The Rook object
     */
    public Rook(Rook p){
        super(p);
        System.out.println("Rook copying");
        type = Type.rook;
    }

    /**
     * Return a deep copy of the Rook object
     * @return deep copy of caller
     */
    public Rook deepCopy(){
        return new Rook(this);
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
        System.out.println("checking if rook can move");
        if(!checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;

        return checkVertialHorizontal(x1,y1,x2,y2) && checkThroughVertialHorizontal(x1,y1,x2,y2,bg);
    }

}
