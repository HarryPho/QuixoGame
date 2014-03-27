package assign1;

import static assign1.QuixoBoard.*;
import static assign1.QuixoBoard.Direction.*;
import static assign1.QuixoBoard.Face.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuixoBoardGUI extends JFrame
{
    private QuixoBoard board;
    private Direction currentDirection = RIGHT;
    private JButton[][] buttons = new JButton[5][5];

    public QuixoBoardGUI()
    {
        board = new QuixoBoard();
        
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(6, 5));

        setUpDirectionButton(pane);
        setUpCube(pane);
    }

    private void setUpDirectionButton(Container pane)
    {
        ButtonGroup directionButton = new ButtonGroup();
        DirectionListener directionListener = new DirectionListener();

        JLabel direction = new JLabel("    Direction:");

        JRadioButton moveRight = new JRadioButton("RIGHT");
        moveRight.setActionCommand("RIGHT");
        moveRight.addActionListener(directionListener);
        moveRight.setSelected(true);

        JRadioButton moveLeft = new JRadioButton("LEFT");
        moveLeft.setActionCommand("LEFT");
        moveLeft.addActionListener(directionListener);

        JRadioButton moveUp = new JRadioButton("UP");
        moveUp.setActionCommand("UP");
        moveUp.addActionListener(directionListener);

        JRadioButton moveDown = new JRadioButton("DOWN");
        moveDown.setActionCommand("DOWN");
        moveDown.addActionListener(directionListener);

        directionButton.add(moveRight);
        directionButton.add(moveLeft);
        directionButton.add(moveUp);
        directionButton.add(moveDown);
        
        pane.add(direction);
        pane.add(moveRight);
        pane.add(moveLeft);
        pane.add(moveUp);
        pane.add(moveDown);
    }
    
    private void setUpCube(Container pane)
    {
        CubeListener cubeListener = new CubeListener();

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
            {
                buttons[i][j] = new JButton();
                buttons[i][j].setName(i + "_" + j);
                pane.add(buttons[i][j]);
                
                buttons[i][j].addActionListener(cubeListener);
            }
    }
    
    private class DirectionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "RIGHT":
                    currentDirection = RIGHT;
                    break;
                case "LEFT":
                    currentDirection = LEFT;
                    break;
                case "UP":
                    currentDirection = UP;
                    break;
                case "DOWN":
                    currentDirection = DOWN;
                    break;
            }
        }
    }
    
    private class CubeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JButton theButton = (JButton) e.getSource();
            String name = theButton.getName();
            int row = Integer.parseInt("" + name.charAt(0));
            int column = Integer.parseInt("" + name.charAt(2));

            board.move(row, column, currentDirection);

            redrawBoard();
            announceWinner();
        }
    }

    public void redrawBoard()
    {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
            {
                if (board.getFace(i, j) == X)
                    buttons[i][j].setText("X");
                
                else if (board.getFace(i, j) == O)
                    buttons[i][j].setText("O");
            }
    }
    
    public void announceWinner()
    {
        if (board.gameWonBy() == X)
            JOptionPane.showMessageDialog(null, "Game won by X");

        if (board.gameWonBy() == O)
            JOptionPane.showMessageDialog(null, "Game won by O");
    }

    public static void main(String[] args)
    {
        QuixoBoardGUI boardFrame = new QuixoBoardGUI();

        boardFrame.setSize(500, 600);
        boardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        boardFrame.setVisible(true);
    }
}