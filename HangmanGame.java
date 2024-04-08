import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// for reading user input of primitive data types:
import java.util.Scanner;

public class HangmanGame {
    
    // an array of possible words the user will have to guess:
    private static final String[] WORDS = {"hangman", "computer", "programming", "variable", "developer", "pineapple", "python"};
    // the amount of max number of attempts based on the avg words length:
    private static final int MAX_INCORRECT_ATTEMPTS = 6;

    public static void main(String[] args) {
        // object to generate random numbers
        Random random = new Random();
        // object to scan user imput 
        Scanner scanner = new Scanner(System.in);
        
        // chooses a random word from the list based on the length of the words array
        String wordToGuess = WORDS[random.nextInt(WORDS.length)].toUpperCase();
        // displays the chosen word in lower dashes to conceal it 
        char[] hiddenWord = new char[wordToGuess.length()];
        for (int i = 0; i < hiddenWord.length; i++) {
            hiddenWord[i] = '_';
        }

    // an empty array list to store guessed letters
        List<Character> guessedLetters = new ArrayList<>();
        
         // sets a counter for attempts to guess the word to zero
        int incorrectAttempts = 0;

        // starting the greeting message and explaining the game 
        System.out.println("Welcome to Hangman!");
        System.out.println("You will have 6 attempts to guess the hidden word");
        System.out.println("Try to guess the word: " + new String(hiddenWord));
        
        /* making sure that the amount of attempts is less than the max attempts
        allowed while scanning the user's input and converting it to uppercase
        for case-sensitivity purposes. the loop will continue until the hidden word 
        still has underscores
            */
        while (contains(hiddenWord, '_') && incorrectAttempts < MAX_INCORRECT_ATTEMPTS) {
            System.out.println("\nIncorrect attempts remaining: " + (MAX_INCORRECT_ATTEMPTS - incorrectAttempts));
            System.out.print("Enter a letter: ");
            String inputStr = scanner.nextLine().toUpperCase();
/*
    this if statement makes sure that the input is indeed a letter and has not been guessed before. 
    
            */
            if (inputStr.length() != 1 || !Character.isLetter(inputStr.charAt(0)) || guessedLetters.contains(inputStr.charAt(0))) {
                System.out.println("Invalid input or letter already guessed. Please enter a valid letter.");
                continue;
            }
// this takes the first user input letter and adds to the array list of guessed letters 
            char input = inputStr.charAt(0);
            guessedLetters.add(input);

            // by default the correctGuess is set to false 
            boolean correctGuess = false;
        /* if the letter is indeed in the hidden word, it's stored in the guessed letters array list 
            and the correctGuess is set to true 
        */
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
/* after the previous loop ends, the program checks if the word has been completely guessed. if not, 
they are notified that there are no attempts left
        */
        if (!contains(hiddenWord, '_')) {
            System.out.println("\nCongratulations! You guessed the word: " + wordToGuess);
        } else {
            System.out.println("\nSorry, you ran out of attempts! The word was: " + wordToGuess);
        }

        scanner.close();
    }

    // method to determine if there are underscores remaining:
    private static boolean contains(char[] array, char target) {
        for (char element : array) {
            if (element == target) {
                return true;
            }
        }
        return false;
    }
}
