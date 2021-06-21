import application.UI;
import chess.ChessMatch;
import chess.ChessPosition;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class processTests {

    @Test
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
}
