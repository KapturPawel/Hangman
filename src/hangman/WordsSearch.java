package hangman;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordsSearch {
    private FileReader fileReader = new FileReader();

    private Scanner in;

    private Scanner fileSearching() {
        try {
            in = new Scanner(fileReader.getFileName());
        } catch (FileNotFoundException e) {
            System.out.println("There is no file with words.");
        }

        return in;
    }

    private List<String> searchingForWords(int lengthOfWord) {
        fileSearching();
        List<String> words = new ArrayList<>();
        String word = "";
        while (in.hasNext()) {
            word = in.nextLine();
            if (word.length() == lengthOfWord)
                words.add(word);
        }

        return words;
    }

    protected String choosingWord(int lengthOfWord) {
        List<String> words = searchingForWords(lengthOfWord);
        int listLength = words.size();
        Random random = new Random();
        int wordIndex = 0;
        String word = "";
        try {
            wordIndex = random.nextInt(listLength);
            word = words.get(wordIndex);
        } catch (IllegalArgumentException e) {
            System.out.println("There is no words of that length.");

        }

        return word;
    }

}
