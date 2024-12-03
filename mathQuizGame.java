import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class mathQuizGame extends JFrame implements ActionListener {
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            mathQuizGame quiz = new mathQuizGame();
            quiz.setVisible(true);
        });
    }

    static Random numbers = new Random();
    static JTextField answerField;
    static JLabel questionLabel;
    static JLabel scoreLabel;
    static JButton submitButton;
    static JButton startButton; 
    static JLabel questionNumber;
    static JLabel welcome;
    static int correctAnswer = 0;
    static int score = 0;
    static int questionCount = 0;

    public mathQuizGame() {
        setTitle("Random Math Quiz Game");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        getContentPane().setBackground(new Color(0xffe59a));
        Font font = new Font("Impact", Font.PLAIN, 30);

        questionLabel = new JLabel("Pertanyaan akan muncul disini");
        questionLabel.setFont(font);

        answerField = new JTextField(10);
        answerField.setFont(font);
        answerField.setEnabled(false);

        submitButton = new JButton("Submit");
        submitButton.setFont(font);
        submitButton.setEnabled(false);

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(font);

        questionNumber = new JLabel("Question: 1"); 
        questionNumber.setFont(font);

        welcome = new JLabel("Welcome to the Random Questions Math Game");
        welcome.setFont(font);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton = new JButton("Start");
        startButton.setFont(font);
        startButton.addActionListener(e -> startGame(submitButton));
        startButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        submitButton.addActionListener(this);

        answerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    submitButton.doClick();
                }
            }
        });

        add(welcome);
        add(startButton);
        add(questionLabel);
        add(answerField);
        add(submitButton);
        add(scoreLabel);
        add(questionNumber);

        questionLabel.setVisible(false);
        answerField.setVisible(false);
        submitButton.setVisible(false);
        scoreLabel.setVisible(false);
        questionNumber.setVisible(false);
    }

    private void startGame(JButton submitButton) {
        startButton.setVisible(false);
        welcome.setVisible(false);
        
        questionLabel.setVisible(true);
        answerField.setVisible(true);
        submitButton.setVisible(true);
        scoreLabel.setVisible(true);
        
        answerField.setEnabled(true);
        submitButton.setEnabled(true);
        questionNumber.setVisible(true);
        generateQuestion(); 
    }

    private void generateQuestion() {
        if (questionCount < 10) {
            int num1 = numbers.nextInt(20) + 1;
            int num2 = numbers.nextInt(20) + 1;
            int operator = numbers.nextInt(3);
            String operatorSymbol = "";

            switch (operator) {
                case 0:
                    operatorSymbol = "+";
                    correctAnswer = num1 + num2;
                    break;
                case 1:
                    operatorSymbol = "-";
                    correctAnswer = num1 - num2;
                    break;
                case 2:
                    operatorSymbol = "*";
                    correctAnswer = num1 * num2;
                    break;
            }

            questionLabel.setText(num1 + " " + operatorSymbol + " " + num2 + " = ");
            answerField.setText("");
            questionNumber.setText("Question: " + (questionCount + 1));
        } else {
            questionLabel.setText("Quiz Over! Your score: " + score);
            answerField.setEnabled(false);

            questionNumber.setVisible(false); 
            answerField.setVisible(false);
            submitButton.setVisible(false);
            scoreLabel.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (questionCount < 10) {
            int userAnswer;
            try {
                userAnswer = Integer.parseInt(answerField.getText());
                if (userAnswer == correctAnswer) {
                    score++;
                    JOptionPane.showMessageDialog(this, "Correct!");
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong! The answer was " + correctAnswer);
                }
                questionCount++;
                scoreLabel.setText("Score: " + score);
                questionNumber.setText("Question: " + (questionCount + 1));
                generateQuestion();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");
            }
        }
    }

    
}