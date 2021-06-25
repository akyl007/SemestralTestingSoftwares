package unitTests;

import application.UI;
import boardgame.Board;
import boardgame.BoardException;
import boardgame.Position;
import chess.*;
import chess.pieces.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class Junit {

    @Rule
    public ExpectedException exception = ExpectedException.none();


    //Board class
    @ParameterizedTest
    @CsvSource({"0,7", "7,0", "7,7", "0,0"})
    @Order(1)
    public void BoardPositionExist_Mocked(String row, String column) {

        Board board = new Board(8, 8);

        int x = Integer.valueOf(row);
        int y = Integer.valueOf(column);


        Position pos = Mockito.mock(Position.class);
        Mockito.when(pos.getRow()).thenReturn(x);
        Mockito.when(pos.getColumn()).thenReturn(y);

        assertTrue(board.positionExists(pos));
    }


    @Test
    @Order(2)
    public void performChessMove_SourcePositionIsEmpty_ThrowException() {

        ChessMatch cm = new ChessMatch();
        ChessPosition source = UI.readChessPositionString("e3");
        ChessPosition target = UI.readChessPositionString("e4");

        exception.expect(ChessException.class);
        exception.expectMessage("There is no piece on source position");


        cm.performChessMove(source, target);

    }


    @Test
    @Order(3)
    public void performChessMove_SourcePositionIsNotYours_ThrowException() {

        ChessMatch cm = new ChessMatch();
        cm.board = new Board(8, 8);
        cm.placeNewPiece('a', 1, new Rook(cm.board, Color.WHITE));
        cm.placeNewPiece('b', 1, new Knight(cm.board, Color.WHITE));
        cm.placeNewPiece('c', 1, new Bishop(cm.board, Color.WHITE));
        cm.placeNewPiece('e', 1, new King(cm.board, Color.WHITE, cm));

        cm.placeNewPiece('a', 8, new Rook(cm.board, Color.BLACK));
        cm.placeNewPiece('b', 8, new Knight(cm.board, Color.BLACK));
        cm.placeNewPiece('c', 8, new Bishop(cm.board, Color.BLACK));
        cm.placeNewPiece('e', 8, new King(cm.board, Color.BLACK, cm));


        ChessPosition source = UI.readChessPositionString("a8");
        ChessPosition target = UI.readChessPositionString("a7");

        exception.expect(ChessException.class);
        exception.expectMessage("The chosen piece is not yours");

        cm.performChessMove(source, target);

    }


    @Test
    @Order(4)
    public void performChessMove_SourcePositionDoesntHavePossibleMoves_ThrowException() {

        ChessMatch cm = new ChessMatch();

        ChessPosition source = UI.readChessPositionString("a1");
        ChessPosition target = UI.readChessPositionString("a3");

        exception.expect(ChessException.class);
        exception.expectMessage("There is no possible moves for the chosen piece");

        cm.performChessMove(source, target);


    }


    @Test
    @Order(5)
    public void performChessMove_CantMoveToTargetPosition_ThrowException() {
        ChessMatch cm = new ChessMatch();

        ChessPosition source = UI.readChessPositionString("e2");
        ChessPosition target = UI.readChessPositionString("e6");

        exception.expect(ChessException.class);
        exception.expectMessage("The chosen piece can't move to target position");

        cm.performChessMove(source, target);
    }


    @Test
    @Order(6)
    public void removePiece_Mocked() {
        ChessMatch cm = new ChessMatch();

        Position pos = Mockito.mock(Position.class);
        Mockito.when(pos.getRow()).thenReturn(6);
        Mockito.when(pos.getColumn()).thenReturn(0);


        cm.board.removePiece(pos);

        assertEquals(cm.board.piece(6, 0), null);

    }

    @Test
    @Order(7)
    public void pawnPossibleMoves_Mocked() {



        Position pos = Mockito.mock(Position.class);
        Position pos1 = Mockito.mock(Position.class);
        Position pos2 = Mockito.mock(Position.class);

        Mockito.when(pos.getRow()).thenReturn(6);
        Mockito.when(pos.getColumn()).thenReturn(0);
        Mockito.when(pos1.getRow()).thenReturn(5);
        Mockito.when(pos1.getColumn()).thenReturn(0);
        Mockito.when(pos2.getRow()).thenReturn(4);
        Mockito.when(pos2.getColumn()).thenReturn(0);

        ChessMatch cm = new ChessMatch();

        assertTrue(cm.board.piece(pos).possibleMove(pos1));
        assertTrue(cm.board.piece(pos).possibleMove(pos2));

    }

    @Test
    @Order(8)
    public void KnightPossibleMoves_Mocked() {

        Position pos = Mockito.mock(Position.class);
        Position pos1 = Mockito.mock(Position.class);
        Position pos2 = Mockito.mock(Position.class);

        Mockito.when(pos.getRow()).thenReturn(7);
        Mockito.when(pos.getColumn()).thenReturn(1);
        Mockito.when(pos1.getRow()).thenReturn(5);
        Mockito.when(pos1.getColumn()).thenReturn(0);
        Mockito.when(pos2.getRow()).thenReturn(5);
        Mockito.when(pos2.getColumn()).thenReturn(2);

        ChessMatch cm = new ChessMatch();

        assertTrue(cm.board.piece(pos).possibleMove(pos1));
        assertTrue(cm.board.piece(pos).possibleMove(pos2));

    }

    @Test
    @Order(9)
    public void ThereIsAPiece_Mocked(){

        ChessMatch cm = new ChessMatch();

        Position position1 = Mockito.mock(Position.class);
        Mockito.when(position1.getRow()).thenReturn(6);
        Mockito.when(position1.getColumn()).thenReturn(0);

        Position position2 = Mockito.mock(Position.class);
        Mockito.when(position2.getRow()).thenReturn(5);
        Mockito.when(position2.getColumn()).thenReturn(5);

        assertTrue(cm.board.thereIsAPiece(position1));
        assertFalse(cm.board.thereIsAPiece(position2));

    }

    @Test
    @Order(10)
    public void PlaceNewFigureOnTheSamePlace_ThrowException(){

        ChessMatch cm = new ChessMatch();
        cm.board = new Board(8,8);

        exception.expect(BoardException.class);
        exception.expectMessage("There is already a piece on position 7, 0");


        cm.placeNewPiece('a', 1, new Rook(cm.board, Color.WHITE));
        cm.placeNewPiece('a', 1, new Knight(cm.board, Color.WHITE));

    }



}