package assign1;

import static assign1.QuixoBoard.Direction.*;
import static assign1.QuixoBoard.Face.*;
import junit.framework.TestCase;

public class QuixoBoardTest extends TestCase
{
    QuixoBoard _board;
    
    protected void setUp()
    {
        _board = new QuixoBoard();
    }
    
    public void testCanary()
    {
        assertTrue(true);
    }
    
    public void testCreateBoard()
    {
        assertEquals(BLANK, _board.gameWonBy());
    }
    
    public void testMoveTopLeftBlankCubeRight()
    {
        assertTrue(_board.move(0, 0, RIGHT));
    }
    
    public void testCheckFaceAfterMovingTopLeftBlankCubeRight()
    {
        _board.move(0, 0, RIGHT);
        assertEquals(X, _board.getFace(0, 4));
        assertEquals(BLANK, _board.getFace(0, 0));
    } 
    
    public void testMoveTopLeftBlankCubeLeft()
    {
        assertFalse(_board.move(0, 0, LEFT));
    }
    
    public void testMoveTopLeftBlankCubeDown()
    {
        assertTrue(_board.move(0, 0, DOWN));
    }
    
    public void testCheckFaceAfterMovingTopLeftBlankCubeDown()
    {
        _board.move(0, 0, DOWN);
        assertEquals(X, _board.getFace(4, 0));
        assertEquals(BLANK, _board.getFace(0, 0));
    }
    
    public void testMoveTopLeftBlankCubeUp()
    {
        assertFalse(_board.move(0, 0, UP));
    }
    
    public void testMoveTopRightBlankCubeLeft()
    {
        assertTrue(_board.move(0, 4, LEFT));
    }
    
    public void testCheckFaceAfterMovingTopRightBlankCubeLeft()
    {
        _board.move(0, 4, LEFT);
        assertEquals(X, _board.getFace(0, 0));
        assertEquals(BLANK, _board.getFace(0, 4));
    }
    
    public void testMoveTopRightBlankCubeUp()
    {
        assertFalse(_board.move(0, 4, UP));
    }
    
    public void testMoveBottomLeftBlankCubeRight()
    {
        assertTrue(_board.move(4, 0, RIGHT));
    }
    
    public void testCheckFaceAfterMovingBottomLeftBlankCubeRight()
    {
        _board.move(4, 0, RIGHT);
        assertEquals(X, _board.getFace(4, 4));
        assertEquals(BLANK, _board.getFace(4, 0));
    }
    
    public void testMoveBottomLeftBlankCubeDown()
    {
        assertFalse(_board.move(4, 0, DOWN));
    }
    
    public void testMoveBottomLeftBlankCubeUp()
    {
        assertTrue(_board.move(4, 0, UP));
    }
    
    public void testCheckFaceAfterMovingBottomLeftBlankCubeUp()
    {
        _board.move(4, 0, UP);
        assertEquals(X, _board.getFace(0, 0));
        assertEquals(BLANK, _board.getFace(4, 0));
    }
    
    public void testMoveBottomRightBlankCubeLeft()
    {
        assertTrue(_board.move(4, 4, LEFT));
    }
    
    public void testCheckFaceAfterMovingBottomRightBlankCubeLeft()
    {
        _board.move(4, 4, LEFT);
        assertEquals(X, _board.getFace(4, 0));
        assertEquals(BLANK, _board.getFace(4, 4));
    }
    
    public void testMoveBottomRightBlankCubeRight()
    {
        assertFalse(_board.move(4, 4, RIGHT));
    }
    
    public void testMoveLeftEdgeCubeRight()
    {
        assertTrue(_board.move(2, 0, RIGHT));
    }
    
    public void testCheckFaceAfterMovingMoveLeftEdgeCubeRight()
    {
        _board.move(2, 0, RIGHT);
        assertEquals(X, _board.getFace(2, 4));
        assertEquals(BLANK, _board.getFace(2, 0));
    }
    
    public void testMoveTopEdgeCubeDown()
    {
        assertTrue(_board.move(0, 2, DOWN));
    }
    
    public void testCheckFaceAfterMovingTopEdgeCubeDown()
    {
        assertTrue(_board.move(0, 2, DOWN));
        assertEquals(X, _board.getFace(4, 2));
        assertEquals(BLANK, _board.getFace(0, 2));
    }
    
    public void testNonEdgeCube()
    {
        assertFalse(_board.move(1, 2, DOWN));
        assertFalse(_board.move(2, 3, UP));
        assertFalse(_board.move(3, 1, RIGHT));
    }
    
    public void testTwoMoves()
    {       
        assertTrue(_board.move(0, 0, RIGHT));
        assertTrue(_board.move(0, 0, RIGHT));
    }
    
    public void testCheckFacesAfterTwoMoves()
    {
        _board.move(0, 0, RIGHT);
        _board.move(0, 0, RIGHT);
        assertEquals(X, _board.getFace(0, 3));
        assertEquals(O, _board.getFace(0, 4));
        assertEquals(BLANK, _board.getFace(0, 0));
    }
    
    public void testAnotherTwoMoves()
    {       
        assertTrue(_board.move(3, 4, LEFT));
        assertTrue(_board.move(0, 2, DOWN));
    }
    
    public void testCheckFacesAfterAnotherTwoMoves()
    {
        _board.move(3, 4, LEFT);
        _board.move(0, 2, DOWN);
        assertEquals(X, _board.getFace(3, 0));
        assertEquals(O, _board.getFace(4, 2));
        assertEquals(BLANK, _board.getFace(3, 4));
        assertEquals(BLANK, _board.getFace(0, 2));
    }

    public void testGameWonByRowMatch()
    {
        _board.move(0, 0, RIGHT);
        _board.move(1, 4, LEFT);
        _board.move(0, 0, RIGHT);
        _board.move(1, 4, LEFT);
        _board.move(0, 0, RIGHT);
        _board.move(1, 4, LEFT);
        _board.move(0, 0, RIGHT);
        _board.move(1, 4, LEFT);
        _board.move(0, 0, RIGHT);
        assertEquals(X, _board.isHorizontalMatch());
    }
    
    public void testGameWonByColMatch()
    {
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        assertEquals(X, _board.isVerticalMatch());
    }
    
    public void testGameWonByDiagonalMatch()
    {
        _board.move(0, 0, RIGHT);
        _board.move(0, 1, DOWN);
        _board.move(1, 0, RIGHT);
        _board.move(1, 0, RIGHT);
        _board.move(2, 0, RIGHT);
        _board.move(2, 0, RIGHT);
        _board.move(2, 0, RIGHT);
        _board.move(4, 0, UP);
        _board.move(3, 4, LEFT);
        _board.move(3, 4, LEFT);
        _board.move(4, 4, LEFT);
        assertEquals(X, _board.isDiagonalMatch());
    }
    
    public void testGameWonBy()
    {
        _board.move(0, 0, RIGHT);
        _board.move(0, 1, DOWN);
        _board.move(1, 0, RIGHT);
        _board.move(1, 0, RIGHT);
        _board.move(2, 0, RIGHT);
        _board.move(2, 0, RIGHT);
        _board.move(2, 0, RIGHT);
        _board.move(4, 0, UP);
        _board.move(3, 4, LEFT);
        _board.move(3, 4, LEFT);
        _board.move(4, 4, LEFT);
        assertEquals(X, _board.gameWonBy());
    }
    
    public void testAnotherGameWonBy()
    {
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        assertEquals(X, _board.gameWonBy());
    }
    
    public void testGameWonByNoWinner()
    {
        _board.move(0, 2, DOWN);
        _board.move(4, 3, UP);
        _board.move(4, 3, UP);
        _board.move(0, 2, DOWN);
        assertEquals(BLANK, _board.gameWonBy());
    }
}