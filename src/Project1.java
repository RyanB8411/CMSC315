/**
 * UMGC CMSC 315
 * Class that displays union, intersection, and difference of two lines of integers
 * @author Ryan Burkhardt
 * Date: 07Jan2023
 * Java 21
*/

//Import necessary extensions
import java.io.IOException;
import java.util.Scanner;

public class Project1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DelimiterCheck fileReader = null;
        boolean validFileName = false;

        while (!validFileName) {
            System.out.println("Enter the Java file name (including path if necessary):");
            String filePath = scanner.nextLine();

            try {
                fileReader = new DelimiterCheck(filePath);
                validFileName = true;
            } catch (IOException e) {
                System.err.println("Error opening the file: " + e.getMessage());
            }
        }

        try {
            while (fileReader.hasMoreCharacters()) {
                char currentChar = fileReader.getNextCharacter();
                if (currentChar == '\0') {
                    break; // End of file
                }

                if (fileReader.isDelimiterMismatch(currentChar)) {
                    break; // Mismatched delimiter found
                }
            }

            System.out.println("Delimiter checking complete.");
        } catch (IOException e){
            System.err.println("Error reading the the file: " + e.getMessage());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                }catch (IOException e) {
                    System.err.println("Error closing the file: " + e.getMessage());
                }
            }
            scanner.close();
        }
    }
}