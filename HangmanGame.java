import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {

    private static final String[] WORDS = {"elephant", "computer", "programming", "universe", "chocolate", "rainbow", "alphabet"};
    private static final int MAX_INCORRECT_ATTEMPTS = 6;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        String wordToGuess = WORDS[random.nextInt(WORDS.length)].toUpperCase();
        char[] hiddenWord = new char[wordToGuess.length()];
        for (int i = 0; i < hiddenWord.length; i++) {
            hiddenWord[i] = '_';
        }

        List<Character> guessedLetters = new ArrayList<>();
        int incorrectAttempts = 0;

        System.out.println("Welcome to Hangman!");
        System.out.println("Try to guess the word: " + new String(hiddenWord));

        while (contains(hiddenWord, '_') && incorrectAttempts < MAX_INCORRECT_ATTEMPTS) {
            System.out.println("\nIncorrect attempts remaining: " + (MAX_INCORRECT_ATTEMPTS - incorrectAttempts));
            System.out.print("Enter a letter: ");
            char input = scanner.nextLine().toUpperCase().charAt(0);

            if (!Character.isLetter(input) || guessedLetters.contains(input)) {
                System.out.println("Invalid input or letter already guessed. Please try again.");
                continue;
            }

            guessedLetters.add(input);
            boolean correctGuess = false;

            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == input) {
                    hiddenWord[i] = input;
                    correctGuess = true;
                }
            }

            if (correctGuess) {
                System.out.println("Correct guess! Word so far: " + new String(hiddenWord));
            } else {
                incorrectAttempts++;
                System.out.println("Incorrect guess! Word so far: " + new String(hiddenWord));
            }
        }

        if (!contains(hiddenWord, '_')) {
            System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("\nSorry, you ran out of attempts! The word was: " + wordToGuess);
        }

        scanner.close();
    }

    private static boolean contains(char[] array, char target) {
        for (char element : array) {
            if (element == target) {
                return true;
            }
        }
        return false;
    }
}

// knjbkj 