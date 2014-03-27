package assign1;

public class QuixoBoard
{   
    public enum Direction { UP, DOWN, LEFT, RIGHT }
    public enum Face { X, O, BLANK }
    
    private Face nextMove = Face.X;
    
    private Face[][] _cubes = new Face[5][5];
    
    public QuixoBoard()
    {
        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                _cubes[row][col] = Face.BLANK;
    }

    public boolean move(int row, int col, Direction direction)
    {
        if (validateMove(row, col, direction) && (getFace(row, col) == nextMove || getFace(row, col) == Face.BLANK))
        {
            switch (direction)
            {
                case UP:
                    shiftCol(row, col, direction);
                    _cubes[0][col] = nextMove;
                    break;

                case DOWN:
                    shiftCol(row, col, direction);
                    _cubes[4][col] = nextMove;
                    break;

                
                case LEFT:
                    shiftRow(row, col, direction);
                    _cubes[row][0] = nextMove;
                    break;
                
                case RIGHT:
                    shiftRow(row, col, direction);
                    _cubes[row][4] = nextMove;
                    break;
            }
            
            nextMove = (nextMove == Face.X) ? Face.O : Face.X;
            return true;
        } 
        return false;
    }

    public boolean validateMove(int row, int col, Direction direction)
    {
        if (row > 0 && row < 4 && col > 0 && col < 4)
            return false;
            
        switch (direction)
        {
            case UP:
                return (row != 0);
            case DOWN:
                return (row != 4);
            case LEFT:
                return (col != 0);
            case RIGHT:
                return (col != 4);
            
            default:
                return false;
        }
    }
    
    public void shiftRow(int row, int col, Direction direction)
    {
        if (direction == Direction.RIGHT)
        {
            for (int i = col; i < 4; i++)
                _cubes[row][i] = _cubes[row][i+1];
        }
        else if (direction == Direction.LEFT)
        {
            for (int i = col; i > 0; i--)
                _cubes[row][i] = _cubes[row][i-1];
        }
    }
    
    public void shiftCol(int row, int col, Direction direction)
    {
        if (direction == Direction.DOWN)
        {
            for (int i = row; i < 4; i++)
                _cubes[i][col] = _cubes[i+1][col];
        }
        else if (direction == Direction.UP)
        {
            for (int i = row; i > 0; i--)
                _cubes[i][col] = _cubes[i-1][col];
        }        
    }
    
    public Face getFace(int row, int col)
    {
        return _cubes[row][col];
    }
    
    public Face gameWonBy()
    {
        if (isHorizontalMatch() != Face.BLANK)
            return isHorizontalMatch();
        
        else if (isVerticalMatch() != Face.BLANK)
            return isVerticalMatch();
        
        else if (isDiagonalMatch() != Face.BLANK)
            return isDiagonalMatch();
        
        else
            return Face.BLANK;                   
    }
    
    public Face isHorizontalMatch()
    {
        boolean match;
        
        for (int row = 0; row < 5; row++)
        {
            match = true;
            
            for (int col = 1; col < 5; col++)
                if (_cubes[row][0] == Face.BLANK || _cubes[row][0] != _cubes[row][col])
                {
                    match = false;
                    break;
                }
            
            if (match == true)
                return _cubes[row][0];
        }
        
        return Face.BLANK;
    }
    
    public Face isVerticalMatch()
    {
        boolean match;
        
        for (int col = 0; col < 5; col++)
        {
            match = true;
            
            for (int row = 1; row < 5; row++)
                if (_cubes[0][col] == Face.BLANK || _cubes[0][col] != _cubes[row][col]) 
                {
                    match = false;
                    break;
                }
            
            if (match == true)
                return _cubes[0][col];
        }
        
        return Face.BLANK; 
    }
    
    public Face isDiagonalMatch()
    {
        boolean match = true;
        
        for (int i = 1; i < 5; i++)
            if (_cubes[0][0] == Face.BLANK || _cubes[0][0] != _cubes[i][i])
            {
                match = false;
                break;
            }
        
        if (match == true)
            return _cubes[0][0];
        
        match = true;
        
        for (int row = 3, col = 1; col < 5; row--, col++)
            if (_cubes[4][0] == Face.BLANK || _cubes[4][0] != _cubes[row][col])
            {
                match = false;
                break;
            }
        
        if (match == true)
            return _cubes[4][0];
            
        return Face.BLANK;
    }
}