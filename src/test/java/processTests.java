import application.UI;
import boardgame.Board;
import boardgame.Position;
import chess.*;
import chess.pieces.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import static org.junit.Assert.*;

public class processTests {

    @Test
    @Order(1)
    public void checkMateTest1(){
        ChessMatch cm = new ChessMatch();
        /**
         * Použijeme nejrychlejší mat s názvem Mat bláznů
         */
        //Bílý pěšák chodí g2-g3
        UI.printBoard(cm.getPieces());
        assertFalse(cm.getCheckMate());
        ChessPosition source1 = UI.readChessPositionString("g2");
        ChessPosition target1 = UI.readChessPositionString("g4");
        cm.performChessMove(source1, target1);
        //zkontrolujeme na mat
        assertFalse(cm.getCheckMate());
        UI.printBoard(cm.getPieces());

        //Černý pěšák chodí e7-e5
        ChessPosition source2 = UI.readChessPositionString("e7");
        ChessPosition target2 = UI.readChessPositionString("e5");
        cm.performChessMove(source2, target2);
        UI.printBoard(cm.getPieces());

        //Bílý pěšák chodí f2-f3
        ChessPosition source3 = UI.readChessPositionString("f2");
        ChessPosition target3 = UI.readChessPositionString("f3");
        cm.performChessMove(source3, target3);
        UI.printBoard(cm.getPieces());
        assertFalse(cm.getCheckMate());

        //Naposled nečekaná černá královna bije a dává mat
        ChessPosition source4 = UI.readChessPositionString("d8");
        ChessPosition target4 = UI.readChessPositionString("h4");
        cm.performChessMove(source4, target4);
        UI.printBoard(cm.getPieces());
        assertTrue(cm.getCheckMate());
    }
    @Test
    @Order(2)
    public void TestingTurnFunctionality(){
        /**
         * Zkontrolujeme, zda funkce Turn funguje správně
         */
        ChessMatch cm = new ChessMatch();

        assertTrue(cm.getTurn() == 1);

        ChessPosition source = UI.readChessPositionString("e2");
        ChessPosition target = UI.readChessPositionString("e4");
        cm.performChessMove(source, target);
        assertTrue(cm.getTurn() == 2);
        ChessPosition source2 = UI.readChessPositionString("e7");
        ChessPosition target2 = UI.readChessPositionString("e5");
        cm.performChessMove(source2, target2);
        assertTrue(cm.getTurn() == 3);

    }

    @Test
    @Order(3)
    public void getMovedPieceTest(){
        /**
         * Ověřujeme účinnost pohybu figurek
         */
        ChessMatch cm = new ChessMatch();

        ChessPosition source = UI.readChessPositionString("e2");
        ChessPosition target = UI.readChessPositionString("e4");

        cm.performChessMove(source, target);
        ChessPiece movedPiece = (ChessPiece)cm.board.piece(target.toPosition());
        UI.printBoard(cm.getPieces());
        assertTrue(movedPiece instanceof Pawn);
        assertEquals(cm.getTurn(), 2);
    }

    @Test
    @Order(4)
    public void changeTurnFromWhiteToBlack(){
        /**
         * Ověřujeme správnost výměny hráčů
         */
        ChessMatch cm = new ChessMatch();
        assertEquals(cm.getCurrentPlayer(), Color.WHITE);
        ChessPosition source = UI.readChessPositionString("b1");
        ChessPosition target = UI.readChessPositionString("a3");

        cm.performChessMove(source, target);
        UI.printBoard(cm.getPieces());
        assertEquals(cm.getCurrentPlayer(),Color.BLACK);

    }

    @Test
    @Order(5)
    public void gettingCapturedList(){
        ChessMatch cm = new ChessMatch();
        cm.placeNewPiece('f',3,new Knight(cm.board, Color.WHITE));
        cm.placeNewPiece('d', 4, new Pawn(cm.board, Color.BLACK, cm));
        assertEquals(0,cm.capturedPieces.size());

        ChessPosition source = UI.readChessPositionString("f3");
        ChessPosition target = UI.readChessPositionString("d4");
        cm.performChessMove(source,target);

        assertEquals(1,cm.capturedPieces.size());

    }

    @Test
    @Order(6)
    public void getPiecesOnTheBoardTest(){
        /**
         * Pokusíme se vrátit všechny figurky na Boardu
         */
        ChessMatch cm = new ChessMatch();
        cm.board = new Board(8,8);
        cm.placeNewPiece('a', 1, new Rook(cm.board, Color.WHITE));
        cm.placeNewPiece('b', 1, new Knight(cm.board, Color.WHITE));
        cm.placeNewPiece('c', 1, new Bishop(cm.board, Color.WHITE));
        cm.placeNewPiece('d', 1, new Queen(cm.board, Color.WHITE));

        cm.placeNewPiece('a', 8, new Rook(cm.board, Color.BLACK));
        cm.placeNewPiece('b', 8, new Knight(cm.board, Color.BLACK));
        cm.placeNewPiece('c', 8, new Bishop(cm.board, Color.BLACK));
        cm.placeNewPiece('d', 8, new Queen(cm.board, Color.BLACK));
        UI.printBoard(cm.getPieces());
        assertEquals(8,cm.getPieces().length);
    }
    @Test(expected = ChessException.class)
    @Order(7)
    public void performChessMoveExceptionTest(){
        /**
         * Chytneme funkci chůze figurek na chybě
         */
        ChessMatch cm = new ChessMatch();

        ChessPosition source = UI.readChessPositionString("e2");
        ChessPosition target = UI.readChessPositionString("e5");
        cm.performChessMove(source, target);

    }

    @Test(expected = ChessException.class)
    // 1-2
    public void test1(){
        ChessMatch cm = new ChessMatch();
        ChessPosition source = UI.readChessPositionString("e2");
        ChessPosition target = UI.readChessPositionString("e5");
        cm.performChessMove(source, target);
    }

    @Test(expected = ChessException.class)
    //1-3-4
    public void test2() {
        ChessMatch cm = new ChessMatch();
        ChessPosition source = UI.readChessPositionString("a1");
        ChessPosition target = UI.readChessPositionString("a3");
        cm.performChessMove(source, target);
    }

    @Test
    //1-3-5-7
    public void test3(){
        ChessMatch cm = new ChessMatch();
        cm.board = new Board(8, 8);
        cm.placeNewPiece('a', 1, new Rook(cm.board, Color.WHITE));
        cm.placeNewPiece('b', 1, new Knight(cm.board, Color.WHITE));
        cm.placeNewPiece('c', 1, new Bishop(cm.board, Color.WHITE));
        cm.placeNewPiece('d', 1, new Queen(cm.board, Color.WHITE));
        cm.placeNewPiece('e', 1, new King(cm.board, Color.WHITE, cm));
        cm.placeNewPiece('h', 7, new Pawn(cm.board, Color.WHITE, cm));

        cm.placeNewPiece('a', 8, new Rook(cm.board, Color.BLACK));
        cm.placeNewPiece('b', 8, new Knight(cm.board, Color.BLACK));
        cm.placeNewPiece('c', 8, new Bishop(cm.board, Color.BLACK));
        cm.placeNewPiece('d', 8, new Queen(cm.board, Color.BLACK));
        cm.placeNewPiece('e', 8, new King(cm.board, Color.BLACK, cm));
        UI.printBoard(cm.getPieces());

        ChessPosition source = UI.readChessPositionString("h7");
        ChessPosition target = UI.readChessPositionString("h8");
        cm.performChessMove(source, target);
        UI.printBoard(cm.getPieces());
        assertEquals(cm.board.piece(target.toPosition()).toString(), "Q");
    }

    @Test
    //1-3-5-8
    public void test4(){
        ChessMatch cm = new ChessMatch();
        cm.board = new Board(8, 8);
        cm.placeNewPiece('a', 1, new Rook(cm.board, Color.WHITE));
        cm.placeNewPiece('b', 1, new Knight(cm.board, Color.WHITE));
        cm.placeNewPiece('c', 1, new Bishop(cm.board, Color.WHITE));
        cm.placeNewPiece('d', 1, new Queen(cm.board, Color.WHITE));
        cm.placeNewPiece('e', 1, new King(cm.board, Color.WHITE, cm));
        cm.placeNewPiece('h', 1, new Pawn(cm.board, Color.WHITE, cm));

        cm.placeNewPiece('a', 8, new Rook(cm.board, Color.BLACK));
        cm.placeNewPiece('b', 8, new Knight(cm.board, Color.BLACK));
        cm.placeNewPiece('c', 8, new Bishop(cm.board, Color.BLACK));
        cm.placeNewPiece('d', 8, new Queen(cm.board, Color.BLACK));
        cm.placeNewPiece('e', 8, new King(cm.board, Color.BLACK, cm));
        UI.printBoard(cm.getPieces());

        ChessPosition source = UI.readChessPositionString("h1");
        ChessPosition target = UI.readChessPositionString("h2");
        cm.performChessMove(source, target);
        UI.printBoard(cm.getPieces());
        assertEquals(cm.board.piece(target.toPosition()).toString(), "P");


    }






}
