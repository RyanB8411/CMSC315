/**
 * UMGC CMSC 315
 * Week 3 Discussion
 * Class that checks the maximum length of a substring inside a user inputted string
 * @author Ryan Burkhardt
 * Date: 22Jan2024
 * Java 21
*/

//Due to folders we must package in my folder
package Non_Projects;

//Import our scanner
import java.util.Scanner;

//Create week3 class
public class Week3 {

    public static void main(String[] args) {

        Scanner user_Scanner = new Scanner(System.in);

        // Prompt user for input
        System.out.print("Enter a string: ");
        String userInput = user_Scanner.nextLine();

        // Call the method from the users input and display the result
        String result = getMaxConsecutiveSubstring(userInput);
        System.out.println("Maximum consecutive increasingly ordered substring is " + result);
    }

    //Create the method to find the max consecutive substring by taking in a string and returning the substring
    public static String getMaxConsecutiveSubstring(String inputStr) {
        //Initialize the varibles to track the current and maximum substrings
        String currentSubstring = String.valueOf(inputStr.charAt(0));
        String maxSubstring = String.valueOf(inputStr.charAt(0));

        //Run a for loop to run through each character in the input string
        for (int i = 1; i < inputStr.length(); i++) {
            
            //Get the current character
            char currentChar = inputStr.charAt(i);

            //Check to see if the character is greater than or equal to the current substring and append it to the current substring
            if (currentChar >= currentSubstring.charAt(currentSubstring.length() - 1)) {
                currentSubstring += currentChar;

            //Or start a new substring with the current character
            } else {
                currentSubstring = String.valueOf(currentChar);
            }

            //Check to see if the current substring is greater than the maximum substring and if it is set it equal to max
            if (currentSubstring.length() > maxSubstring.length()) {
                maxSubstring = currentSubstring;
            }
        }

        //Retun the max substring
        return maxSubstring;
    }
}