import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuizGame {

    public static void main(String[] args) {
        List<Question> questions = createQuestions(); // Create questions
        int score = takeQuiz(questions);
        displayResult(score, questions);
    }

    private static List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        // Add your questions, options, and answers here (example):
        questions.add(new Question(
                "What is the capital of France?",
                List.of("London", "Paris", "Berlin", "Rome"),
                "B"
        ));
        questions.add(new Question(// Add more questions...
                "...", List.of("...", "...", "...", "..."), "..."));
        return questions;
    }

    private static int takeQuiz(List<Question> questions) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        for (Question question : questions) {
            System.out.println(question.getText());
            displayOptions(question.getOptions());
            char answer = getAnswer(scanner);
            if (question.isCorrectAnswer(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + question.getCorrectAnswer());
            }
        }
        return score;
    }

    private static void displayOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            char optionChar = (char) ('A' + i);
            System.out.println(optionChar + ". " + options.get(i));
        }
    }

    private static char getAnswer(Scanner scanner) {
        System.out.print("Enter your answer (A-D): ");
        try {
            String input = scanner.nextLine().toUpperCase();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                return input.charAt(0);
            } else {
                System.out.println("Invalid input. Please enter a single letter (A-D).");
                return getAnswer(scanner); // Recursively get valid input
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a single letter (A-D).");
            return getAnswer(scanner); // Recursively get valid input
        } finally {
            // Consider adding a timeout mechanism here if needed (advanced)
        }
    }

    private static void displayResult(int score, List<Question> questions) {
        System.out.println("** Quiz Results **");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("** Answers **");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println((i + 1) + ". " + question.getText());
            System.out.println("  Your answer: " + (char) (i + 'A')); // Convert index to option char
            System.out.println("  Correct answer: " + question.getCorrectAnswer());
        }
    }
}

class Question {
    private final String text;
    private final List<String> options;
    private final String correctAnswer;

    public Question(String text, List<String> options, String correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrectAnswer(char answer) {
        return Character.toUpperCase() == correctAnswer;
    }
}


