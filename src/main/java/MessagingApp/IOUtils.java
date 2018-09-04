package MessagingApp;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class IOUtils {

    Scanner scanner;

    public IOUtils() {                  // This method is to overide the static method by presenting it as an object
        scanner = new Scanner(System.in);
    }

    public boolean fileExist(Path fileName) {
        return Files.exists(fileName);
    }

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public boolean fileExists(String fileName) {
        Path filePath = Paths.get(fileName + "txt");
        return fileExist(filePath);
    }

    public String readNextLine() {
        return scanner.nextLine();
    }

}
