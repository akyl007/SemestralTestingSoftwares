package boardgame;

import application.UI;
import chess.ChessMatch;
import chess.ChessPosition;
import static org.junit.Assert.*;

import boardgame.BoardException;
import boardgame.Position;
import chess.Color;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BoardTest {
    @Test
    public void removePiece(){
        /**
         * Kontrola odstranění figurek z desky
         */

        ChessPosition mockChessPosition = Mockito.mock(ChessPosition.class);
        Mockito.when(mockChessPosition.toPosition()).thenReturn(UI.readChessPositionString("b2").toPosition());

        ChessMatch cm = new ChessMatch();
        ChessPosition chessPosition = UI.readChessPositionString("b2");
        Position pos = chessPosition.toPosition();


        cm.board.removePiece(pos);
        UI.printBoard(cm.getPieces());
        assertFalse(cm.board.thereIsAPiece(pos));
    }

    @Test
    public void positionExistsTest(){
        /**
         * Kontrola funkce position exists
         */
        ChessMatch cm = new ChessMatch();
        ChessPosition chessPosition = UI.readChessPositionString("a2");
        Position pos = chessPosition.toPosition();
        assertTrue(cm.board.positionExists(pos));

    }

    @Test
    public void newPieceTest(){


    }
}
