package Pieces;
import BoardGame.*;

import static java.lang.Math.*;


public class Spike extends Piece {
    /**
     * Constructor
     * @param color The color of the piece
     */
    public Spike(Color color){
        super(color);
        type = Type.spike;
    }

    /**
     * Copy constructor
     * @param p The Spike object
     */
    public Spike(Spike p){
        super(p);
        System.out.println("Spike copying");
        type = Type.spike;
    }

    /**
     * Return a deep copy of the Spike object
     * @return deep copy of caller
     */
    public Spike deepCopy(){
        return new Spike(this);
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
        System.out.println("checking if spike can move");
        if(!checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;
        if(pow((x1-x2),2) + pow((y1-y2),2) <= 4) return true;
        return false;
    }
}
