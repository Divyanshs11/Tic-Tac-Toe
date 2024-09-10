import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeUsingGUI extends JFrame {
    private static final int SIZE = 3; // 3x3 grid
    private JButton[][] buttons = new JButton[SIZE][SIZE];
    private char currentPlayer = 'X';
    private boolean gameOver = false;
    private int xScore = 0;
    private int oScore = 0;
    private JLabel xScoreLabel;
    private JLabel oScoreLabel;
    private JLabel turnLabel;
    private JButton restartButton;

    public TicTacToeUsingGUI() {
        setTitle("Tic Tac Toe");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeComponents();
        setVisible(true);
    }

    private void initializeComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(SIZE, SIZE)); // 3x3 grid for game board

        // Initialize buttons and add them to the panel
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j] = new JButton("-");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                panel.add(buttons[i][j]);
            }
        }

        // Add score labels and restart button
        xScoreLabel = new JLabel("Player X Score: 0");
        oScoreLabel = new JLabel("Player O Score: 0");
        turnLabel = new JLabel("Player X's Turn");

        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(e -> restartGame());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(4, 1)); // Added row for restart button
        bottomPanel.add(turnLabel);
        bottomPanel.add(xScoreLabel);
        bottomPanel.add(oScoreLabel);
        bottomPanel.add(restartButton);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
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
                    if (currentPlayer == 'X') {
                        xScore++;
                        xScoreLabel.setText("Player X Score: " + xScore);
                    } else {
                        oScore++;
                        oScoreLabel.setText("Player O Score: " + oScore);
                    }
                } else if (checkTie()) {
                    gameOver = true;
                    JOptionPane.showMessageDialog(null, "The game is a tie!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    turnLabel.setText("Player " + currentPlayer + "'s Turn");
                }
            }
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
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

    private void restartGame() {
        currentPlayer = 'X';
        gameOver = false;
        turnLabel.setText("Player X's Turn");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                buttons[i][j].setText("-");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeUsingGUI::new);
    }
}
