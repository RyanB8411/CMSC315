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


//Create Class Project1
public class Project1 {
    public static void main(String[] args) {

        //Create new Scanner
        Scanner scanner = new Scanner(System.in);

        //Set our Delimiter check to null and valid file to false
        DelimiterCheck fileReader = null;
        boolean validFileName = false;
        boolean validAnswer = true;

        //Welcoming Message and ask for input
        System.out.println("Hello, Welcome to the Delimiter Checker. Would you like to check a java file for missing delimiters? Please enter 'yes' or 'no':");

        while(validAnswer){

            //Allows user to stay in while loop
            String answer = scanner.nextLine();
            //System.out.println("DEBUG: User entered " + answer);

            //If answer is yes than we will read in the file of choice
            if (answer.equalsIgnoreCase("yes")){
                
                //While we do not find a valid file it will reprompt user
                while (!validFileName) {

                    //Prompt user for input and read in file
                    System.out.println("Enter the Java file name (including path if necessary):");
                    String filePath = scanner.nextLine();

                    //Try creating Delimiter Check object with the given file path if it is correct will be set true encapsulate the file and exit while loop.
                    try {
                        fileReader = new DelimiterCheck(filePath);
                        validFileName = true;

                        //If there is an error it will catch stream errors and close()
                    } catch (IOException e) {
                        System.err.println("Error opening the file: " + e.getMessage());
                    }
                }

                try {
                    //If we have a valid file we will run each character while there is a next character
                    while (fileReader.hasMoreCharacters()) {

                        //gets next character
                        char currentChar = fileReader.getNextCharacter();

                        //If output for character is \0 will indicate the end of the file and break the loop
                        if (currentChar == '\0') {
                            break;
                        }

                        //If a mismatch is found it will print the mismatch and continue until the end of the file.
                        if (fileReader.isDelimiterMismatch(currentChar)) {
                            continue;
                        }
                    }

                    //Indicates to the user that the check is complete
                    System.out.println("Delimiter checking is complete for this file."); 

                    //Will catch a read in file exception
                } catch (IOException e){
                    System.err.println("Error reading the the file: " + e.getMessage());

                    //Lastly will close the file reader
                } finally {
                    if (fileReader != null) {
                        try {
                            fileReader.close();
                        }catch (IOException e) {
                            System.err.println("Error closing the file: " + e.getMessage());
                        }
                    }
                    //Displays question to user to see if they want to enter another file
                    System.out.println("Would you like to check another file? Please enter 'yes' or 'no':");
                    validFileName = false;
                }
    
                //If user answers no system will thank them and exit loop
            }else if (answer.equalsIgnoreCase("no")) {
                System.out.println("Thank you for coming have a wonderful day.");
                validAnswer = false;

                //If user answers anything but yes or no will continue to repromt for valid answer
            }else{
                validAnswer = true;
                System.out.println("Please enter a valid input. Enter 'yes' or 'no' :");
            }

        }
        scanner.close();
    }
}