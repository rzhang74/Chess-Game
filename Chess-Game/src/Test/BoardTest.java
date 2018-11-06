package Test;

import Pieces.*;
import BoardGame.*;

import junit.framework.*;
import junit.extensions.*;
import java.lang.AssertionError;
import java.io.*;

public class BoardTest extends TestCase{

    public static Test suite() {
        TestSuite testSuite = new TestSuite(BoardTest.class);
        return testSuite;

    }

    public void testCreateBoard() throws Exception{
        BoardGame bg = new BoardGame(8,8, true);
        assertEquals(bg.getWidth(), 8);
        assertEquals(bg.getHeight(), 8);

        for(int j = 0; j < 8; j++){
            for(int i = 0; i < 2; i++){
                assertEquals(bg.getPiece(i,j).getColor(), Piece.Color.black);
            }
            for(int i = 6; i < 8; i++){
                assertEquals(bg.getPiece(i,j).getColor(), Piece.Color.white);
            }
        }

        assertEquals(bg.getPiece(0,0).getType(), Piece.Type.rook);
        assertEquals(bg.getPiece(0,7).getType(), Piece.Type.rook);
        assertEquals(bg.getPiece(7,0).getType(), Piece.Type.rook);
        assertEquals(bg.getPiece(7,7).getType(), Piece.Type.rook);

        assertEquals(bg.getPiece(0,1).getType(), Piece.Type.knight);
        assertEquals(bg.getPiece(0,6).getType(), Piece.Type.knight);
        assertEquals(bg.getPiece(7,1).getType(), Piece.Type.knight);
        assertEquals(bg.getPiece(7,6).getType(), Piece.Type.knight);

        assertEquals(bg.getPiece(0,2).getType(), Piece.Type.bishop);
        assertEquals(bg.getPiece(0,5).getType(), Piece.Type.bishop);
        assertEquals(bg.getPiece(7,2).getType(), Piece.Type.bishop);
        assertEquals(bg.getPiece(7,5).getType(), Piece.Type.bishop);

        assertEquals(bg.getPiece(0,3).getType(), Piece.Type.queen);
        assertEquals(bg.getPiece(7,3).getType(), Piece.Type.queen);

        assertEquals(bg.getPiece(0,4).getType(), Piece.Type.king);
        assertEquals(bg.getPiece(7,4).getType(), Piece.Type.king);

        for(int j = 0; j < 8; j++){
            assertEquals(bg.getPiece(1,j).getType(), Piece.Type.pawn);
            assertEquals(bg.getPiece(6,j).getType(), Piece.Type.pawn);
        }
    }
    public void testPlayer() throws Exception{
        BoardGame bg = new BoardGame(8,8, true);
        assertEquals(bg.getPlayer(), BoardGame.Player.player_white);
        bg.setPlayer(BoardGame.Player.player_black);
        assertEquals(bg.getPlayer(), BoardGame.Player.player_black);
    }
    public void testWinner() throws Exception{
        BoardGame bg = new BoardGame(8,8, true);
        assertEquals(bg.getWinner(), null);
        bg.setWinner(BoardGame.Player.player_black);
        assertEquals(bg.getWinner(), BoardGame.Player.player_black);
        bg.setWinner(BoardGame.Player.player_white);
        assertEquals(bg.getWinner(), BoardGame.Player.player_white);
    }
    public void testPiece() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);

        bg.setPiece(0,0, new King(Piece.Color.black));
        assertEquals(bg.getPiece(0,0).getType(), Piece.Type.king);
        assertEquals(bg.checkPiece(0,0), true);
        assertEquals(bg.checkKing(0,0), true);

    }
    public void testGetKingPos() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);

        bg.setPiece(0,0, new King(Piece.Color.white));
        assertEquals(bg.getKingPos(BoardGame.Player.player_white)[0], 0);
        assertEquals(bg.getKingPos(BoardGame.Player.player_white)[1], 0);

        bg.setPiece(3,4, new King(Piece.Color.black));
        assertEquals(bg.getKingPos(BoardGame.Player.player_black)[0], 3);
        assertEquals(bg.getKingPos(BoardGame.Player.player_black)[1], 4);
    }
    public void testGetOpponent() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        assertEquals(bg.getOpponent(bg.getPlayer()), BoardGame.Player.player_black);
        assertEquals(bg.getOpponent(BoardGame.Player.player_black), BoardGame.Player.player_white);
    }
    public void testGetColorofPlayer() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        assertEquals(bg.getColorOfPlayer(bg.getPlayer()), Piece.Color.white);
        assertEquals(bg.getColorOfPlayer(BoardGame.Player.player_black), Piece.Color.black);
    }
    public void testIsUnderCheck() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(0,0, new King(Piece.Color.white));
        assertEquals(bg.isUnderCheck(0,0, bg.getPlayer()), false);
        bg.setPiece(7,0, new Queen(Piece.Color.black));
        assertEquals(bg.isUnderCheck(0,0, bg.getPlayer()), true);
    }
    public void testCheckmate() throws  Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(2,0, new King(Piece.Color.black));
        bg.setPiece(2,1, new Queen(Piece.Color.white));
        bg.setPiece(2,2, new King(Piece.Color.white));
        assertEquals(bg.isCheckMate(BoardGame.Player.player_black), true);

        BoardGame bg2 = new BoardGame(8,8, false);
        bg2.setPiece(2,0, new King(Piece.Color.black));
        bg2.setPiece(7,0, new Queen(Piece.Color.white));
        bg2.setPiece(2,2, new King(Piece.Color.white));
        assertEquals(bg2.isCheckMate(BoardGame.Player.player_black), true);
    }
    public void testStalemate() throws Exception{
        BoardGame bg = new BoardGame(8,8, false);
        bg.setPiece(0,2, new King(Piece.Color.black));
        bg.setPiece(1,2, new Pawn(Piece.Color.white));
        bg.setPiece(2,2, new King(Piece.Color.white));
        //assertEquals(bg.getPiece(1,2).canMove(1,2,0,2,bg), true);
        assertEquals(bg.isStaleMate(BoardGame.Player.player_black), true);

        BoardGame bg2 = new BoardGame(8,8, false);
        bg2.setPiece(0,7, new King(Piece.Color.black));
        bg2.setPiece(0,6, new Bishop(Piece.Color.black));
        bg2.setPiece(0,3, new Rook(Piece.Color.white));
        bg2.setPiece(3,6, new King(Piece.Color.white));
        //assertEquals(bg.getPiece(1,2).canMove(1,2,0,2,bg), true);
        assertEquals(bg.isStaleMate(BoardGame.Player.player_black), true);

    }
}
