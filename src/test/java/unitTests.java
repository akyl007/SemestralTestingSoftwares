import application.UI;
import boardgame.Board;
import boardgame.BoardException;
import boardgame.Piece;
import boardgame.Position;
import chess.*;
import chess.pieces.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class unitTests {

   @Rule
   public ExpectedException exception = ExpectedException.none();

   @Test
   @Order(1)
   public void validateSourcePositionTest_PositionIsEmpty_ThrowException(){

      ChessMatch cm = new ChessMatch();
      ChessPosition source = UI.readChessPositionString("e3");
      ChessPosition target = UI.readChessPositionString("e4");



      exception.expect(ChessException.class);
      exception.expectMessage("There is no piece on source position");


      cm.performChessMove(source, target);

   }


   @Test
   @Order(2)
   public void validateSourcePositionTest_NotYours_ThrowException(){

      ChessMatch cm = new ChessMatch();
      cm.board = new Board(8,8);
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
   @Order(3)
   public void validateSourcePositionTest_NoPossibleMoves_ThrowException(){

      ChessMatch cm = new ChessMatch();

      ChessPosition source = UI.readChessPositionString("a1");
      ChessPosition target = UI.readChessPositionString("a3");

      exception.expect(ChessException.class);
      exception.expectMessage("There is no possible moves for the chosen piece");

      cm.performChessMove(source, target);


   }


   @Test
   @Order(4)
   public void validateTargetPosition_CantMove_ThrowException(){
      ChessMatch cm = new ChessMatch();

      ChessPosition source = UI.readChessPositionString("e2");
      ChessPosition target = UI.readChessPositionString("e6");

      exception.expect(ChessException.class);
      exception.expectMessage("The chosen piece can't move to target position");

      cm.performChessMove(source, target);
   }

   @Test
   @Order(5)
   public void PositionExistMocked(){

      ChessPosition chessPosition = Mockito.mock(ChessPosition.class);
      Mockito.when(chessPosition.toPosition()).thenReturn(new Position(6, 0));

      ChessMatch cm = new ChessMatch();
      Position pos = chessPosition.toPosition();
      assertTrue(cm.board.positionExists(pos));

   }

   @Test
   @Order(6)
   public void PlaceNewFiguresMocked(){

      ChessPosition chessPosition1 = Mockito.mock(ChessPosition.class);
      ChessPosition chessPosition2 = Mockito.mock(ChessPosition.class);
      Mockito.when(chessPosition1.toPosition()).thenReturn(new Position(7, 0));
      Mockito.when(chessPosition2.toPosition()).thenReturn(new Position(0, 2));


      ChessMatch cm = new ChessMatch();
      cm.board = new Board(8,8);

      cm.placeNewPiece('a', 1, new Rook(cm.board, Color.WHITE));
      cm.placeNewPiece('c', 8, new Bishop(cm.board, Color.BLACK));

      assertTrue(cm.board.thereIsAPiece(chessPosition1.toPosition()));
      assertTrue(cm.board.thereIsAPiece(chessPosition2.toPosition()));

   }

   @Test
   @Order(7)
   public void PlaceNewFigureOnTheSamePlace(){

      ChessMatch cm = new ChessMatch();
      cm.board = new Board(8,8);

      exception.expect(BoardException.class);
      exception.expectMessage("There is already a piece on position 7, 0");


      cm.placeNewPiece('a', 1, new Rook(cm.board, Color.WHITE));
      cm.placeNewPiece('a', 1, new Knight(cm.board, Color.WHITE));

   }

   @Test
   @Order(8)
   public void KnightPossibleMovesMocked(){

      ChessPosition chessPosition1 = Mockito.mock(ChessPosition.class);
      ChessPosition chessPosition2 = Mockito.mock(ChessPosition.class);
      ChessPosition chessPosition3 = Mockito.mock(ChessPosition.class);
      Mockito.when(chessPosition1.toPosition()).thenReturn(new Position(7, 1));
      Mockito.when(chessPosition2.toPosition()).thenReturn(new Position(5, 0));
      Mockito.when(chessPosition3.toPosition()).thenReturn(new Position(5, 2));

      ChessMatch cm = new ChessMatch();

      Position pos = chessPosition1.toPosition();
      Position pos1 = chessPosition2.toPosition();
      Position pos2 = chessPosition2.toPosition();

      assertTrue(cm.board.piece(pos).possibleMove(pos1));
      assertTrue(cm.board.piece(pos).possibleMove(pos2));


   }

   @Test
   @Order(9)
   public void RemovePieceMocked(){


      ChessPosition chessPosition = Mockito.mock(ChessPosition.class);
      Mockito.when(chessPosition.toPosition()).thenReturn(new Position(7,7));

      ChessMatch cm = new ChessMatch();
      cm.board = new Board(8,8);

      cm.placeNewPiece('h', 1, new Rook(cm.board, Color.WHITE));
      cm.placeNewPiece('e', 1, new King(cm.board, Color.WHITE, cm));

      cm.placeNewPiece('h', 8, new Rook(cm.board, Color.BLACK));
      cm.placeNewPiece('e', 8, new King(cm.board, Color.BLACK, cm));


      Position pos = chessPosition.toPosition();

      cm.board.removePiece(pos);
      assertFalse(cm.board.thereIsAPiece(pos));

   }

   @Test
   @Order(10)
   public void pawnPossibleMovesMocked(){

      ChessPosition chessPosition1 = Mockito.mock(ChessPosition.class);
      ChessPosition chessPosition2 = Mockito.mock(ChessPosition.class);
      ChessPosition chessPosition3 = Mockito.mock(ChessPosition.class);
      Mockito.when(chessPosition1.toPosition()).thenReturn(new Position(6, 0));
      Mockito.when(chessPosition2.toPosition()).thenReturn(new Position(5, 0));
      Mockito.when(chessPosition3.toPosition()).thenReturn(new Position(4, 0));

      Position pos = chessPosition1.toPosition();
      Position pos1 = chessPosition2.toPosition();
      Position pos2 = chessPosition3.toPosition();

      ChessMatch cm = new ChessMatch();

      assertTrue(cm.board.piece(pos).possibleMove(pos1));
      assertTrue(cm.board.piece(pos).possibleMove(pos2));

   }

}
