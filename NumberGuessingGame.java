/*code by Arnav Kumar
          BSC CS , MITWPU , KOTHRUD
		  TASK 1:  NUMBER GUESSING GAME
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int targetNumber;
    private int attempts = 0;
    private int roundsWon = 0;

    private JLabel instructionLabel;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel roundsWonLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeComponents();
        startNewRound();

        setLayout(new GridLayout(5, 1));
        add(instructionLabel);
        add(guessField);
        add(guessButton);
        add(feedbackLabel);
        add(attemptsLabel);
        add(roundsWonLabel);
    }

    private void initializeComponents() {
        instructionLabel = new JLabel("Guess a number between " + lowerBound + " and " + upperBound);
        guessField = new JTextField();
        guessButton = new JButton("Guess");
        feedbackLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts: " + attempts);
        roundsWonLabel = new JLabel("Rounds Won: " + roundsWon);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
    }

    private void startNewRound() {
        Random random = new Random();
        targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        attempts = 0;
        instructionLabel.setText("Guess a number between " + lowerBound + " and " + upperBound);
        feedbackLabel.setText("");
        attemptsLabel.setText("Attempts: " + attempts);
    }

    private void checkGuess() {
        try {
            int userGuess = Integer.parseInt(guessField.getText());
            attempts++;
            attemptsLabel.setText("Attempts: " + attempts);

            if (userGuess == targetNumber) {
                feedbackLabel.setText("Congratulations! You guessed the number.");
                roundsWon++;
                roundsWonLabel.setText("Rounds Won: " + roundsWon);
                startNewRound();
            } else if (userGuess < targetNumber) {
                feedbackLabel.setText("Too low. Try again.");
            } else {
                feedbackLabel.setText("Too high. Try again.");
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
