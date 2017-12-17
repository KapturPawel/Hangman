package hangman;

import java.io.File;
import java.io.FileNotFoundException;

public interface FileGiver {
    File getFileName() throws FileNotFoundException;
}

