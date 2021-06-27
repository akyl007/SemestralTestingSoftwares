import application.UI;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;



import static org.junit.Assert.*;

public class unit {


    Board board = new Board(8,8);

    @Mock
    ChessMatch chessMatch;
    @BeforeEach
    public void init(){
        board.placePiece(new King(board, Color.WHITE, chessMatch), new Position(6,6));
        board.placePiece(new King(board, Color.BLACK, chessMatch), new Position(1,1));

    }

    @ParameterizedTest
    @CsvSource({"0,7", "7,0", "7,7", "0,0"})
    @Order(1)
    public void BoardPositionExist_Mocked(String row, String column) {

        int x = Integer.valueOf(row);
        int y = Integer.valueOf(column);


        Position pos = Mockito.mock(Position.class);
        Mockito.when(pos.getRow()).thenReturn(x);
        Mockito.when(pos.getColumn()).thenReturn(y);

        assertTrue(board.positionExists(pos));
    }




    @ParameterizedTest
    @CsvSource({"0,0", "0,1", "0,2", "0,3", "0,4", "0,5", "0,6", "0,7"})
    @Order(2)
    public void ThereIsAPiece_EmptyBoard_Mocked(String row, String column){

        int x = Integer.valueOf(row);
        int y = Integer.valueOf(column);

        Position position = Mockito.mock(Position.class);
        Mockito.when(position.getRow()).thenReturn(x);
        Mockito.when(position.getColumn()).thenReturn(y);

        assertFalse(board.thereIsAPiece(position));


    }


    @Test
    @Order(3)
    public void opponentCheckTest(){
        ChessMatch cm = new ChessMatch();
        Color res = cm.opponent(Color.WHITE);
        assertEquals(res, Color.BLACK);
    }


    @ParameterizedTest
    @CsvSource({"a1,a,1","b5,b,5", "c4,c,4", "d6,d,6", "e8,e,8", "f2,f,2", "g3,g,3", "h7,h,7"})
    @Order(4)
    public void readChessPositionString(String readPosition, char column, String row){

        ChessPosition res = UI.readChessPositionString(readPosition);

        int x = Integer.parseInt(row);
        assertEquals(res.getColumn(), column);
        assertEquals(res.getRow(), x);

    }

    @Test
    @Order(5)
    public void RemovePiece(){

        Piece res = board.removePiece(new Position(6, 6));
        Piece expected = new King(board, Color.WHITE, chessMatch);

        assertEquals(true, res.equals(expected));

    }

    @Test
    @Order(6)
    public void NewPiece(){

        ChessMatch cm = new ChessMatch();
        cm.board = board;

        ChessPiece res = cm.newPiece("Q", Color.WHITE);
        ChessPiece expected = new Queen(board, Color.WHITE);

        assertEquals(expected ,res);

    }
    @Test
    @Order(7)
    public void getCurrentPlayerTest(){
        ChessMatch cm = new ChessMatch();
        assertEquals(Color.WHITE,cm.getCurrentPlayer());
    }
    @Test
    @Order(8)
    public void ThereIsAPiece_Mocked(){

        ChessMatch cm = new ChessMatch();

        Position position1 = Mockito.mock(Position.class);
        Mockito.when(position1.getRow()).thenReturn(6);
        Mockito.when(position1.getColumn()).thenReturn(0);

        Position position2 = Mockito.mock(Position.class);
        Mockito.when(position2.getRow()).thenReturn(5);
        Mockito.when(position2.getColumn()).thenReturn(5);

        assertTrue(cm.board.thereIsAPiece(position1));

    }

    @Test
    @Order(9)
    public void pawnPossibleMove_Mocked() {



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

        assertTrue(cm.board.piece(pos).possibleMove(pos2));

    }

    @Test
    @Order(10)
    public void removePiece_Mocked() {

        ChessMatch cm = new ChessMatch();

        Position pos = Mockito.mock(Position.class);
        Mockito.when(pos.getRow()).thenReturn(6);
        Mockito.when(pos.getColumn()).thenReturn(0);


        cm.board.removePiece(pos);

        assertEquals(cm.board.piece(6, 0), null);

    }

}
