package hangman;

import java.io.File;

public class FileReader implements FileGiver {

    @Override
    public File getFileName() {
        File words = new File("TextFile/words.txt");
        return words;
    }
}

