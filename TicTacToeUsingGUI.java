import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeUsingGUI extends JFrame {
    private static final int SIZE = 3;
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private char currentPlayer = 'X';
    private boolean gameOver = false;

    public TicTacToeUsingGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(SIZE, SIZE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    private class ButtonClickListener implements ActionListener {
        private int row;
        private int col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!gameOver && buttons[row][col].getText().equals("-")) {
                buttons[row][col].setText(String.valueOf(currentPlayer));
                if (checkWin()) {
                    gameOver = true;
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!");
                } else if (checkTie()) {
                    gameOver = true;
                    JOptionPane.showMessageDialog(null, "The game is a tie!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            }
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < SIZE; i++) {
            if (buttons[i][0].getText().charAt(0) == currentPlayer &&
                    buttons[i][1].getText().charAt(0) == currentPlayer &&
                    buttons[i][2].getText().charAt(0) == currentPlayer) {
                return true;
            }
            if (buttons[0][i].getText().charAt(0) == currentPlayer &&
                    buttons[1][i].getText().charAt(0) == currentPlayer &&
                    buttons[2][i].getText().charAt(0) == currentPlayer) {
                return true;
            }
        }
        if (buttons[0][0].getText().charAt(0) == currentPlayer &&
                buttons[1][1].getText().charAt(0) == currentPlayer &&
                buttons[2][2].getText().charAt(0) == currentPlayer) {
            return true;
        }
        if (buttons[0][2].getText().charAt(0) == currentPlayer &&
                buttons[1][1].getText().charAt(0) == currentPlayer &&
                buttons[2][0].getText().charAt(0) == currentPlayer) {
            return true;
        }
        return false;
    }

    private boolean checkTie() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (buttons[i][j].getText().equals("-")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToeUsingGUI();
    }
}
