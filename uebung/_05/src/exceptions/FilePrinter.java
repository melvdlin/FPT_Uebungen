package exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;

public class FilePrinter {

    public static void run() {
        Scanner userInputScanner = new Scanner(System.in);
        String userInput;
        while (true) {
            System.out.print("\nPlease enter the path of the file you wish to read: ");
            userInput = userInputScanner.nextLine();
            if (userInput.equals("quit"))
                break;
            try {
                System.out.println("\n" + Files.readString(Path.of(userInput)));
            } catch (IOException e) {
                System.out.println("Could not read file!");
            } catch (InvalidPathException e) {
                System.out.println("Invalid path!");
            }
        }
    }

    public static void main(String[] args) {
        run();
    }
}
