package Pieces;
import BoardGame.*;

import static java.lang.Math.*;

public class Pawn extends Piece{
    boolean firstTurn = true;

    /**
     * Constructor
     * @param color The color of the piece
     */
    public Pawn(Color color){
        super(color);
        type = Type.pawn;
    }

    /**
     * Getter for whether it is first turn for pawn to move
     * @return Return true if it is first turn for pawn to move, false if otherwise
     */
    public boolean getFirstTurn() {
        return firstTurn;
    }

    /**
     * Setter for the first turn flag
     * @param firstTurn the boolean value that indicates whether it is first turn for pawn to move
     */
    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

    /**
     * Copy constructor
     * @param p The Pawn object
     */
    public Pawn(Pawn p){
        super(p);
        System.out.println("Pawn copying");
        type = Type.pawn;
    }

    /**
     * Return a deep copy of the Pawn object
     * @return deep copy of caller
     */
    public Pawn deepCopy(){
        return new Pawn(this);
    }

    /**
     * Return true if it's valid to move to the target position or eat the target at that position
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @param bg The corresponding chessboard game
     * @return Return true if it's valid to move to the target position or eat the target at that position
     */
    public boolean canMove(int x1, int y1, int x2, int y2, BoardGame bg){
        System.out.println("checking if Pawn can move");

        if(!super.checkCanMoveGeneral(x1,y1,x2,y2,bg)) return false;
        if(y1 != y2) return canEat(x1,y1,x2,y2,bg);

        int bigger, smaller;
        if(bg.getPiece(x1,y1).getColor() == Color.black){
            bigger = x2; smaller = x1;
        }else{
            bigger = x1; smaller = x2;
        }

        if(firstTurn){
            if(bigger-smaller!=1 && bigger-smaller!=2) return false;
            for(int i = smaller; i <= bigger; i++){
                if(i == x1) continue;
                if(bg.checkPiece(i, y1)) return false;
            }
            return true;
        }

        return (bigger-smaller==1 && !bg.checkPiece(x2,y2)); //|| canEat(x1,y1,x2,y2,bg);
    }

    /**
     * move to the target position or eat the target at that position
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @param bg The corresponding chessboard game
     */
    public void move(int x1, int y1, int x2, int y2, BoardGame bg){
        System.out.println("pawn moving");
        if(bg.getPiece(x2,y2) == null) {
            Piece p = bg.getPiece(x1, y1);
            bg.setPiece(x2, y2, p);
            bg.setPiece(x1, y1, null);
        }else{
            eat(x1,y1,x2,y2,bg);
        }
    }

    /**
     * Return true if it's valid to eat the target at that position
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @param bg The corresponding chessboard game
     * @return Return true if it's valid to eat the target at that position
     */
    public boolean canEat(int x1, int y1, int x2, int y2, BoardGame bg){
        System.out.println("checking if Pawn can eat");

        if(!bg.checkPiece(x1, y1)) return false;
        int wid = bg.getWidth(), hgt = bg.getHeight();
        if(x2 < 0 || y2 < 0 || x2 >= wid || y2 >= hgt) return false;
        //if(bg.getPiece(x1,y1).getColor() == bg.getPiece(x2,y2).getColor()) return false;

        if(abs(y2-y1)!=1 || !bg.checkPiece(x2,y2)) return false;
        if(bg.getPiece(x1,y1).getColor() == Color.black)
            return x2-x1==1;
        else
            return x1-x2==1;
    }

    /**
     * eat the target at that position
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @param bg The corresponding chessboard game
     */
    public void eat(int x1, int y1, int x2, int y2, BoardGame bg){
        System.out.println("Pawn is doing eat");
        if(firstTurn) firstTurn = false;
        int wid = bg.getWidth(), hgt = bg.getHeight();
        if(x2 < 0 || y2 < 0 || x2 >= wid || y2 >= hgt) return;

        bg.setPiece(x2,y2,null);
    }
}
