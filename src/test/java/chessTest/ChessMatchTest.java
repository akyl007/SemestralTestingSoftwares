package chessTest;

import application.UI;
import boardgame.Board;
import boardgame.Position;
import chess.*;
import chess.pieces.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChessMatchTest {


    @Test
    public void opponentCheckTest(){
        /**
         * Kontrola správnosti funkce oponentCheck
         */
        ChessMatch cm = new ChessMatch();
        assertEquals(cm.opponent(Color.WHITE),Color.BLACK);
        assertEquals(cm.opponent(Color.BLACK),Color.WHITE);

    }
}
