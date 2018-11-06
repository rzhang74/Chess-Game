package Pieces;
import BoardGame.*;

import static java.lang.Math.*;

public class Destroyer extends Piece {
    /**
     * Constructor
     * @param color The color of the piece
     */
    public Destroyer(Color color){
        super(color);
        type = Type.destroyer;
    }

    /**
     * Copy constructor
     * @param p The Destroyer object
     */
    public Destroyer(Destroyer p){
        super(p);
        System.out.println("destroyer copying");
        type = Type.destroyer;
    }

    /**
     * Return a deep copy of the Destroyer object
     * @return deep copy of caller
     */
    public Destroyer deepCopy(){
        return new Destroyer(this);
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
        System.out.println("checking if destroyer can move");
        if(!checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;

        if(bg.checkPiece(x2,y2)) return false;
        if(x1==x2 && abs(y2-y1)==1) return true;
        if(y1==y2 && abs(x2-x1)==1) return true;
        return false;
    }

    /**
     * move to the target position
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @param bg The corresponding chessboard game
     */
    public void move(int x1, int y1, int x2, int y2, BoardGame bg){
        System.out.println("destroyer moving");
        Piece p = bg.getPiece(x1, y1);
        bg.setPiece(x2, y2, p);
        bg.setPiece(x1, y1, null);
        if(x1-x2==1){
            for(int j = -1; j <=1; j++)
                for(int i = -1; i >= -3; i--)
                    if(!destroy(x2+i, y2+j, bg)) break;
        }else if(x2-x1==1){
            for(int j = -1; j <= 1; j++)
                for(int i = 1; i <= 3; i++)
                    if(!destroy(x2+i, y2+j, bg)) break;
        }else if(y2-y1==1){
            for(int i = -1; i <= 1; i++)
                for(int j = 1; j <= 3; j++)
                    if(!destroy(x2+i, y2+j, bg)) break;
        }else if(y1-y2==1){
            for(int i = -1; i <= 1; i++)
                for(int j = -1; j >= -3; j--)
                    if(!destroy(x2+i, y2+j, bg)) break;
        }
    }

    /**
     * Whether destroyer will be able to continue destruction at a given position
     * @param x_pos given x position
     * @param y_pos given y posistion
     * @param bg the boardgame
     * @return Return false if there is a spike blocking in front of destroyer's destroy path
     */
    public boolean destroy(int x_pos, int y_pos, BoardGame bg){
        if(x_pos < 0 || x_pos >= bg.getWidth() || y_pos < 0 || y_pos >= bg.getHeight()) return false;
        if(bg.checkPiece(x_pos,y_pos) && bg.getPiece(x_pos,y_pos).getType() == Type.spike) {System.out.println("cannot destroy " + bg.getPiece(x_pos,y_pos).getType().toString()); return false;}
        //System.out.println("destroy " + bg.getPiece(x_pos,y_pos).getType().toString());
        bg.setPiece(x_pos,y_pos, null);
        return true;
    }
}
