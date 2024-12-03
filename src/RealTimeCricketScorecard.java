import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RealTimeCricketScorecard {

    private static final int TOTAL_OVERS = 2; // For testing
    private static final int BALLS_PER_OVER = 6;
    private static int team1Score = 0;
    private static int team2Score = 0;
    private static int targetScore;
    private static int wicketsLostTeam1 = 0;
    private static int wicketsLostTeam2 = 0;
    private static int ballsPlayed = 0;
    private static int oversPlayed = 0;
    private static boolean isFirstInnings = true;

    private static JLabel inningLabel;
    private static JLabel scoreLabel;
    private static JLabel targetLabel;
    private static JLabel oversLabel;
    private static JLabel currentRunRateLabel;
    private static JLabel requiredRunRateLabel;
    private static JLabel team1WinPercentageLabel;
    private static JLabel team2WinPercentageLabel;
    private static JTextField inputField;
    private static JTextArea logArea;

    private static String[] overEvents = new String[BALLS_PER_OVER];
    private static ArrayList<String> overSummaries = new ArrayList<>();

    private static void updateUI() {
        if (isFirstInnings) {
            inningLabel.setText("Innings: Team 1");
            scoreLabel.setText("Team 1 Score: " + team1Score + "/" + wicketsLostTeam1);
            currentRunRateLabel.setText("Current Run Rate: " + String.format("%.2f", calculateRunRate(team1Score)));
            targetLabel.setText("");
            requiredRunRateLabel.setText("");
            team1WinPercentageLabel.setText("");
            team2WinPercentageLabel.setText("");
        } else {
            inningLabel.setText("Innings: Team 2");
            scoreLabel.setText("Team 2 Score: " + team2Score + "/" + wicketsLostTeam2);
            currentRunRateLabel.setText("Current Run Rate: " + String.format("%.2f", calculateRunRate(team2Score)));
            requiredRunRateLabel.setText("Required Run Rate: " + String.format("%.2f", calculateRequiredRunRate()));
            targetLabel.setText("Target: " + targetScore);

            // Calculate win percentage for Team 2
            double team2WinPercentage = calculateWinPercentage(team2Score);
            team2WinPercentageLabel.setText("Team 2 Win %: " + String.format("%.2f", team2WinPercentage));

            // For Team 1, calculate win percentage based on how close Team 2 is to the target
            double team1WinPercentage = 100 - team2WinPercentage;
            team1WinPercentageLabel.setText("Team 1 Win %: " + String.format("%.2f", team1WinPercentage));
        }

        oversLabel.setText("Overs: " + oversPlayed + "." + ballsPlayed);
        displayEvents();
    }

    private static void displayEvents() {
        logArea.setText(""); // Clear previous logs
        for (int i = 0; i < overSummaries.size(); i++) {
            logArea.append("Over " + (i + 1) + ": " + overSummaries.get(i) + "\n");
        }
        if (ballsPlayed > 0) {
            logArea.append("Current Over: " + String.join(", ", overEvents).replace("null", "_") + "\n");
        }
    }

    private static double calculateRunRate(int score) {
        int totalBalls = (oversPlayed * BALLS_PER_OVER) + ballsPlayed;
        return totalBalls > 0 ? (double) score / (totalBalls / (double) BALLS_PER_OVER) : 0.0;
    }

    private static double calculateRequiredRunRate() {
        int ballsRemaining = (TOTAL_OVERS * BALLS_PER_OVER) - (oversPlayed * BALLS_PER_OVER + ballsPlayed);
        return ballsRemaining > 0 ? (double) (targetScore - team2Score) / ballsRemaining * BALLS_PER_OVER : 0.0;
    }

    private static double calculateWinPercentage(int teamScore) {
        return (teamScore / (double) targetScore) * 100;
    }

    private static void checkMatchStatus() {
        int ballsRemaining = (TOTAL_OVERS * BALLS_PER_OVER) - (oversPlayed * BALLS_PER_OVER + ballsPlayed);

        // First, update the UI to reflect the current state
        updateUI();

        // Then check and display the match result
        if (team2Score >= targetScore) { // Team 2 Wins or Tie
            if (team2Score > targetScore) {
                displayResult("Team 2 Wins!");
            } else {
                displayResult("Match Tied!");
            }
        } else if (ballsRemaining == 0 || wicketsLostTeam2 == 10) { // Overs completed or All Out
            if (team2Score == targetScore - 1) {
                displayResult("Match Tied!");
            } else if (team2Score < targetScore - 1) {
                displayResult("Team 1 Wins!");
            }
        }
    }

    private static void displayResult(String resultMessage) {
        inputField.setEnabled(false); // Disable input field

        // Display result in the log area
        logArea.append("\n" + resultMessage + "\n");
        logArea.append("\nMatch Over!\n");

        // Display result in the center with bold font
        JLabel resultLabel = new JLabel(resultMessage);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(inputField);
        if (parentFrame != null) {
            JPanel centerPanel = (JPanel) parentFrame.getContentPane().getComponent(0);
            centerPanel.add(resultLabel, BorderLayout.CENTER);
            parentFrame.revalidate();
            parentFrame.repaint();
        }

        // Show popup message
        JOptionPane.showMessageDialog(null, resultMessage, "Match Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private static boolean isValidInput(String input) {
        // Valid inputs are: 1, 2, 3, 4, 6, W (case-insensitive)
        return input.equals("0") || input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("6") || input.equalsIgnoreCase("W");
    }

    private static void handleBall(String input) {
        if (!isValidInput(input)) {
            // Display a popup message for invalid input
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid input (1, 2, 3, 4, 6, W).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return; // Do not proceed further until valid input is provided
        }

        // Proceed with valid input
        if (isFirstInnings) {
            if (input.equalsIgnoreCase("W")) {
                wicketsLostTeam1++;
                overEvents[ballsPlayed] = "W";
            } else {
                int runs = Integer.parseInt(input);
                team1Score += runs;
                overEvents[ballsPlayed] = String.valueOf(runs);
            }
        } else {
            if (input.equalsIgnoreCase("W")) {
                wicketsLostTeam2++;
                overEvents[ballsPlayed] = "W";

                // Decrease Team 2's win percentage and increase Team 1's by 5%
                double team2WinPercentage = calculateWinPercentage(team2Score);
                double team1WinPercentage = 100 - team2WinPercentage;

                // Decrease Team 2's win percentage by 5% and increase Team 1's by 5%
                team1WinPercentage += 5;
                team2WinPercentage -= 5;

                // Ensure percentages stay within the 0-100 range
                if (team1WinPercentage > 100) team1WinPercentage = 100;
                if (team2WinPercentage < 0) team2WinPercentage = 0;

                // Update the win percentage labels
                team1WinPercentageLabel.setText("Team 1 Win %: " + String.format("%.2f", team1WinPercentage));
                team2WinPercentageLabel.setText("Team 2 Win %: " + String.format("%.2f", team2WinPercentage));
            } else {
                int runs = Integer.parseInt(input);
                team2Score += runs;
                overEvents[ballsPlayed] = String.valueOf(runs);
            }
        }

        ballsPlayed++;
        if (ballsPlayed == BALLS_PER_OVER) {
            ballsPlayed = 0;
            oversPlayed++;
            overSummaries.add(String.join(", ", overEvents).replace("null", "_"));
            overEvents = new String[BALLS_PER_OVER]; // Reset for the next over
        }

        if (isFirstInnings && oversPlayed == TOTAL_OVERS && ballsPlayed == 0) {
            isFirstInnings = false;
            oversPlayed = 0;
            ballsPlayed = 0;
            targetScore = team1Score + 1;
            overSummaries.clear(); // Clear events for second innings
            logArea.append("\nEnd of First Innings - Team 1 Score: " + team1Score + "\n");
            logArea.append("Target for Team 2: " + targetScore + "\n\n");
        } else if (!isFirstInnings) {
            checkMatchStatus();
        }

        updateUI();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Real-Time Cricket Scorecard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2)); // Adjusted for additional labels
        inningLabel = new JLabel("Innings: Team 1");
        scoreLabel = new JLabel("Team 1 Score: 0/0");
        oversLabel = new JLabel("Overs: 0.0");
        currentRunRateLabel = new JLabel("Current Run Rate: 0.00");
        requiredRunRateLabel = new JLabel("");
        targetLabel = new JLabel("");
        team1WinPercentageLabel = new JLabel(""); // Team 1 win percentage label
        team2WinPercentageLabel = new JLabel(""); // Team 2 win percentage label

        topPanel.add(inningLabel);
        topPanel.add(scoreLabel);
        topPanel.add(oversLabel);
        topPanel.add(currentRunRateLabel);
        topPanel.add(requiredRunRateLabel);
        topPanel.add(targetLabel);
        topPanel.add(team1WinPercentageLabel); // Add Team 1 win percentage label
        topPanel.add(team2WinPercentageLabel); // Add Team 2 win percentage label

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        inputField = new JTextField();
        JButton enterButton = new JButton("Enter");
        logArea = new JTextArea(15, 50);
        logArea.setEditable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(new JLabel("Enter runs or 'W' for wicket:"), BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(enterButton, BorderLayout.EAST);

        bottomPanel.add(inputPanel, BorderLayout.NORTH);
        bottomPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.CENTER);

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String input = inputField.getText().trim();
                    inputField.setText("");
                    handleBall(input);
                }
            }
        });

        enterButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            inputField.setText("");
            handleBall(input);
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
