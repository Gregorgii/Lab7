package util.workingWithCommand;

import util.*;
import managers.*;
import java.io.*;

public class FileManager {
    private final File file;

    public FileManager(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        } else {
            if (!file.canRead()) {
                throw new FileNotFoundException("Can't read");
            } else if (!file.canWrite()) {
                throw new FileNotFoundException("Can't write");
            }
        }
    }

    public File getFile() {
        return file;
    }

    public void fileWriter(CollectionManager collectionManager) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream)) {
            for (int i = 0; i < collectionManager.getGroupCollection().size(); i++) {
                String string = Parser.studyGroupToStringParser(collectionManager.getGroupCollection().get(i));
                outputStreamWriter.write(string);
            }
        }
    }
}
