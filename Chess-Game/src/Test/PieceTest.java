package Test;

import Pieces.*;
import BoardGame.*;

import junit.framework.*;
import junit.extensions.*;
import java.lang.AssertionError;
import java.io.*;

public class PieceTest extends TestCase {

    public static Test suite() {
        TestSuite testSuite = new TestSuite(BoardTest.class);
        return testSuite;
    }

    public void testMovePawn() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new Pawn(Piece.Color.white));
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,3, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,3, bg), true);

        bg.setPiece(1,7, new Pawn(Piece.Color.black));
        assertEquals(bg.getPiece(1,7).canMove(1,7,3,7, bg), true);
        assertEquals(bg.getPiece(1,7).canMove(1,7,3,7, bg), false);
        assertEquals(bg.getPiece(1,7).canMove(1,7,2,7, bg), true);

        bg.setPiece(2,6, new Pawn(Piece.Color.white));
        assertEquals(bg.getPiece(2,6).canMove(2,6,1,7, bg), true);

        bg.setPiece(4,4, new Pawn(Piece.Color.black));
        assertEquals(bg.getPiece(4,4).canMove(4,4,3,3, bg), false);

    }
    public void testMoveKing() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new King(Piece.Color.white));
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,5, bg), false);
    }
    public void testMoveKnight() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(4,3, new Knight(Piece.Color.black));
        assertEquals(bg.getPiece(4,3).canMove(4,3,2,4, bg), true);
        assertEquals(bg.getPiece(4,3).canMove(4,3,3,5, bg), true);
        assertEquals(bg.getPiece(4,3).canMove(4,3,5,5, bg), true);
        assertEquals(bg.getPiece(4,3).canMove(4,3,6,4, bg), true);
        assertEquals(bg.getPiece(4,3).canMove(4,3,5,1, bg), true);
        assertEquals(bg.getPiece(4,3).canMove(4,3,6,2, bg), true);
        assertEquals(bg.getPiece(4,3).canMove(4,3,3,1, bg), true);
        assertEquals(bg.getPiece(4,3).canMove(4,3,2,2, bg), true);

    }
    public void testMoveRook() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new Rook(Piece.Color.black));
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,0, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,3, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,6, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,7, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,0,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,3, bg), true);


        bg.setPiece(3,5, new Rook(Piece.Color.white));
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,6, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,7, bg), false);
    }
    public void testMoveBishop() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new Bishop(Piece.Color.white));
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,0,0, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,6, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,7,7, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,0,6, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,0, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,1, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,5, bg), false);
        bg.setPiece(5,1, new Bishop(Piece.Color.black));
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,0, bg), false);
        bg.setPiece(4,4, new Bishop(Piece.Color.black));
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,5, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,6, bg), false);
    }
    public void testMoveQueen() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new Queen(Piece.Color.white));
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,0, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,3, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,6, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,7, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,0,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,3, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,0,0, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,6, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,7,7, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,1,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,0,6, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,0, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,1, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,2,5, bg), false);


        bg.setPiece(3,5, new Rook(Piece.Color.black));
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,6, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,7, bg), false);
        bg.setPiece(5,1, new Bishop(Piece.Color.black));
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,2, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,1, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,0, bg), false);
        bg.setPiece(4,4, new Bishop(Piece.Color.black));
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,5, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,6,6, bg), false);
    }
    public void testCheckKing() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new King(Piece.Color.black));
        assertEquals(bg.checkKing(3,3), true);
        bg.setPiece(4,4, new King(Piece.Color.white));
        assertEquals(bg.checkKing(4,4), false);
    }
    public void testFirstPlayer() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        assertEquals(bg.getPlayer(), BoardGame.Player.player_white);
    }
    public void testMoveSpike() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new Spike(Piece.Color.black));
        bg.setPiece(3,5, new Pawn(Piece.Color.white));
        bg.setPiece(3,4, new Pawn(Piece.Color.white));
        bg.setPiece(4,4, new Pawn(Piece.Color.white));

        assertEquals(bg.getPiece(3,3).canMove(3,3,3,5, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,3,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,4,4, bg), true);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,4, bg), false);
        assertEquals(bg.getPiece(3,3).canMove(3,3,5,5, bg), false);

        bg.getPiece(3,3).move(3,3,3,5, bg);
        assertEquals(bg.getPiece(3,3), null);
        assertEquals(bg.getPiece(3,5).getType(), Piece.Type.spike);

    }
    public void testMoveDestroyer() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new Pawn(Piece.Color.white));
        bg.setPiece(3,4, new Knight(Piece.Color.white));
        bg.setPiece(3,5, new Knight(Piece.Color.white));
        bg.setPiece(2,3, new Pawn(Piece.Color.white));
        bg.setPiece(2,4, new King(Piece.Color.white));
        bg.setPiece(2,5, new Queen(Piece.Color.white));
        bg.setPiece(1,3, new Bishop(Piece.Color.white));
        bg.setPiece(1,4, new Pawn(Piece.Color.white));
        bg.setPiece(1,5, new Knight(Piece.Color.white));

        bg.setPiece(5,4, new Destroyer(Piece.Color.black));
        assertEquals(bg.getPiece(5,4).canMove(5,4,4,4, bg), true);
        //see if piece gets eaten
        bg.getPiece(5,4).move(5,4,4,4, bg);
        for(int j = 3; j <= 5; j++){
            for(int i = 3; i >= 0; i--){
                if(bg.getPiece(i,j)!=null)
                    System.out.println(bg.getPiece(i,j).getType().toString());
                assertEquals(bg.getPiece(i,j), null);
            }
        }


        bg = new BoardGame(8,8, false);
        bg.setPiece(3,3, new Spike(Piece.Color.white));
        bg.setPiece(3,4, new Spike(Piece.Color.white));
        bg.setPiece(3,5, new Spike(Piece.Color.white));
        bg.setPiece(2,3, new Pawn(Piece.Color.white));
        bg.setPiece(2,4, new King(Piece.Color.white));
        bg.setPiece(2,5, new Queen(Piece.Color.white));
        bg.setPiece(1,3, new Bishop(Piece.Color.white));
        bg.setPiece(1,4, new Pawn(Piece.Color.white));
        bg.setPiece(1,5, new Knight(Piece.Color.white));

        bg.setPiece(5,4, new Destroyer(Piece.Color.black));
        assertEquals(bg.getPiece(5,4).canMove(5,4,4,4, bg), true);
        //see if piece gets eaten
        bg.getPiece(5,4).move(5,4,4,4, bg);
        for(int j = 3; j <= 5; j++){
            for(int i = 3; i > 0; i--){
                if(bg.getPiece(i,j)!=null)
                    System.out.println(bg.getPiece(i,j).getType().toString());
                else
                    System.out.println(i + " " + j + " is gone.");
                assertEquals(bg.checkPiece(i,j), true);
            }
        }
    }
    public void testCopyConstructor() throws Exception{
        Piece b1 = new Bishop(Piece.Color.white);
        Piece b2 = b1.deepCopy();
        System.out.println(b2.getType().toString());
    }



}
