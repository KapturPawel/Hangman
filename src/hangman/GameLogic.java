package hangman;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameLogic {
    protected static boolean playing;
    Subject error = new Subject();
    ErrorsObserver errorsNumber = new ErrorsObserver(error);

    private void playingGame(int lengthOfWord) {
        WordsSearch wordsSearch = new WordsSearch();
        String word = wordsSearch.choosingWord(lengthOfWord);
        StringBuilder playerWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            playerWord.append("_");
        }

        if (playerWord.length() > 0)
            System.out.println("Word was chosen.");

        while (!playerWord.toString().equals(word) && error.getState() >= 0) {
            System.out.println("Your word is: " + playerWord);
            System.out.println("You can do " + error.getState() + " more errors.");
            choosingLetter(word, playerWord);
        }

        if (playerWord.length() > 0) {
            if (playerWord.toString().equals(word)) {
                System.out.println("The word is " + word + "! You win.");
            } else {
                System.out.println("The word is " + word + "! You lose.");
            }
        }

    }

    private void choosingLetter(String word, StringBuilder playerWord) {
        Scanner in = new Scanner(System.in);
        System.out.println("Please select a letter:");
        String letter = in.nextLine();
        if (letter.length() > 1) {
            System.out.println("To many letters! Please try again.");
            choosingLetter(word, playerWord);
        } else if (letter.length() == 0) {
            System.out.println("You didn't choose any letter! Please try again.");
            choosingLetter(word, playerWord);
        } else {
            char c = letter.charAt(0);
            if (Character.isLetter(c)) {
                characterSearch(c, word, playerWord);
            } else {
                System.out.println("Wrong input. Try again.");
                choosingLetter(word, playerWord);
            }
        }
    }

    private StringBuilder characterSearch(char c, String word, StringBuilder playerWord) {
        int numberOfHits = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == c) {
                playerWord = playerWord.replace(i, i + 1, String.valueOf(c));
                numberOfHits++;
            }
        }

        if (numberOfHits == 0) {
            errorsNumber.update();
        }

        return playerWord;
    }

    private void startingGame() {
        int lengthOfWord = choosingLength();
        choosingErrors();
        playingGame(lengthOfWord);
    }

    private int choosingLength() {
        Scanner in = new Scanner(System.in);
        int lengthOfWord = 0;
        System.out.println("Please, choose length of the word!");
        try {
            lengthOfWord = in.nextInt();
            if(lengthOfWord <= 0){
                System.out.println("Wrong input. Try again.");
                choosingLength();
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input. Try again.");
            choosingLength();
        }
        return lengthOfWord;
    }

    private void choosingErrors() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please, choose number of errors!");
        try {
            error.setState(in.nextInt());
            if (error.getState() < 0){
                System.out.println("Wrong input. Try again.");
                choosingErrors();
            }
        } catch (InputMismatchException e) {
            System.out.println("Wrong input. Try again.");
            choosingErrors();
        }
    }

    protected boolean menu() {
        Scanner in = new Scanner(System.in);
        System.out.println("What are you want to do? Write 'p' if you want to play, or 'q' if you want to exit.");
        String decision = in.nextLine();

        switch (decision.toLowerCase()) {
            case "p":
                playing = true;
                startingGame();
                break;
            case "q":
                playing = false;
                break;
            default:
                System.out.println("There is no such command. Please choose again.");
                playing = true;
                break;
        }

        return playing;
    }
}
