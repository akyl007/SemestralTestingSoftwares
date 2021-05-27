package boardgame;

import application.UI;
import chess.ChessMatch;
import chess.ChessPosition;
import static org.junit.Assert.*;

import boardgame.BoardException;
import boardgame.Position;
import chess.Color;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import org.junit.Test;

public class BoardTest {
    @Test
    public void removePieceTest(){
        /**
         * Kontrola odstranění figurek z desky
         */
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

    }
}
