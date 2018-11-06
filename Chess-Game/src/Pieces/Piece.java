package Pieces;
import BoardGame.*;

import static java.lang.Math.*;

abstract public class Piece {
    public enum Color{
        white, black
    }

    public enum Type{
        rook, bishop, knight, king, queen, pawn,destroyer, spike
    }

    Color color;
    Type type;

    /**
     * get the type of piece
     * @return return value of the piece's type
     */
    public Type getType() {
        return type;
    }

    /**
     * set the piece's type
     * @param type (rook, bishop, knight, king, queen, pawn)6 types of piece
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Constructor for initializing the piece
     * @param color the color of the piece
     * @return return the piece object
     */
    public Piece(Color color){
        this.color = color;
    }

    public Piece(Piece p){
        System.out.println("piece copying");
        this.color = p.color;
    }

    public abstract Piece deepCopy();

    /**
     * get the color of piece
     * @return return value of the piece's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * set piece's color given a color
     * @param color the color of the piece
     */
    public void setColor(Color color) {
        this.color = color;
    }

    public boolean checkCanMoveGeneral(int x1, int y1, int x2, int y2, BoardGame bg){
        if(!bg.checkPiece(x1, y1)) return false;
        int wid = bg.getWidth(), hgt = bg.getHeight();
        if(x2 < 0 || y2 < 0 || x2 >= wid || y2 >= hgt) return false;
        if(x1 == x2 && y1 == y2) return false;
        if(bg.checkPiece(x2, y2) && bg.getPiece(x2,y2).color == bg.getPiece(x1,y1).color) return false;
        if(bg.checkPiece(x2, y2) && bg.getPiece(x2,y2).type == Type.spike && bg.checkPiece(x1,y1) && bg.getPiece(x1,y1).type != Type.spike) return false;
        return true;
    }

    /**
     * Given two positions check if two position are on a vertical or horizontal line
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @return Return true if two positions are on a vertical or horizontal line
     */
    public boolean checkVertialHorizontal(int x1, int y1, int x2, int y2){
        return x1 == x2 || y1 == y2;
    }

    /**
     * Given two positions check if two position are on a diagonal line
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @return Return true if two positions are on a diagonal line
     */
    public boolean checkDiagonal(int x1, int y1, int x2, int y2){
        return abs(x1-x2) == abs(y1-y2);
    }

    /**
     * Given two positions are on a vertical or horizontal line, check if there if any piece between them
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @return Return true if there is no piece between the two positions
     */
    public boolean checkThroughVertialHorizontal(int x1, int y1, int x2, int y2, BoardGame bg){
        if(y1 == y2){
            for(int i = min(x1, x2)+1; i < max(x1, x2); i++)
                if(bg.checkPiece(i, y1))
                    return false;
        }else if(x1 == x2) {
            for (int j = min(y1, y2)+1; j < max(y1, y2); j++)
                if (bg.checkPiece(x1, j))
                    return false;
        }
        return true;
    }

    /**
     * Given two positions are on a diagonal line, check if there if any piece between them
     * @param x1 The piece's x coordinate
     * @param y1 The piece's y coordinate
     * @param x2 The target's x coordinate
     * @param y2 The target's y coordinate
     * @return Return true if there is no piece between the two positions
     */
    public boolean checkThroughDiagonal(int x1, int y1, int x2, int y2, BoardGame bg){
        if((x1>x2&&y1>y2) || (x1<x2&&y1<y2)){
            for(int i = min(x1,x2)+1, j = min(y1,y2)+1; i < max(x1,x2) && j < max(y1,y2); i++,j++)
                if(bg.checkPiece(i, j))
                    return false;
        }else{
            for(int i = max(x1,x2)-1, j = min(y1,y2)+1; i > min(x1,x2) && j < max(y1,y2); i--,j++)
                if(bg.checkPiece(i, j))
                    return false;
        }
        return true;
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
        System.out.println("general piece cannot move yet");
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
        System.out.println("piece moving");
        Piece p = bg.getPiece(x1, y1);
        bg.setPiece(x2, y2, p);
        bg.setPiece(x1, y1, null);
    }
}
