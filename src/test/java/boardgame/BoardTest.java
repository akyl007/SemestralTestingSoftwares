package boardgame;

import application.UI;
import chess.ChessMatch;
import chess.ChessPosition;
import static org.junit.Assert.*;

import boardgame.BoardException;
import boardgame.Position;
import chess.Color;
<<<<<<< HEAD
=======
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
>>>>>>> 690985ae65bd967d751cbfb686005dc51eab3706
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BoardTest {
    @Test
<<<<<<< HEAD
    public void removePiece(){
=======
    public void removePieceTest(){
>>>>>>> 690985ae65bd967d751cbfb686005dc51eab3706
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
<<<<<<< HEAD

    @Test
    public void newPieceTest(){

=======
    @Test
    public void placePieceTest(){
        //Create Knight on d4
        ChessMatch cm = new ChessMatch();
        cm.placeNewPiece('d', 4, new Knight(cm.board, Color.BLACK));

        //get position d4 to get piece
        ChessPosition cp = UI.readChessPositionString("d4");
        Position pos = cp.toPosition();
        Piece king = cm.board.piece(pos);
        UI.printBoard(cm.getPieces());

        assertTrue(king instanceof Knight);
>>>>>>> 690985ae65bd967d751cbfb686005dc51eab3706

    }
}
