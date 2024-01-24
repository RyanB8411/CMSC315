package Burkhardt_Project_1;

/**
 * UMGC CMSC 315
 * Project 1
 * Class that will import a file and run it through a delimiter check
 * @author Ryan Burkhardt
 * Date: 20Jan2023
 * Java 21
*/

//Import necessary extensions
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

//Create our class Delimiter Checker
public class DelimiterCheck {
    //State our variables for the reader, character stack line number and character number
    private BufferedReader reader;
    private Stack<Character> delimiterStack;
    private Stack<Integer> delimiterLocation;
    private int lineNumber;
    private int charNumber;
    private char badChar;

    //Allow the Delimeter check to read in the file name or location indicated by fileName also create the object
    public DelimiterCheck(String fileName) throws IOException {

        //The Reader will than create a new reader for the file inputted
        reader = new BufferedReader(new FileReader(fileName));

        //Create a new stack for the delimiters
        delimiterStack = new Stack<>();
        delimiterLocation = new Stack<>();

        //Index Line and Character Numbers
        lineNumber = 1;
        charNumber = 0;

        //Baseline badChar for a mismatch at end of file
        badChar = 0;
    }

    //Create a character reader method that throws exceptions
    public char getNextCharacter() throws IOException {

        //State our character integer index
        int charInt;

        //Create a while loop for the length of the file and stops when at end indicasted by -1
        while ((charInt = reader.read()) != -1) {

            //Set characters equal to eachother and Index our character number
            char currentChar = (char) charInt;
            charNumber++;

            //If it is a new line we will index our line number and reset our character number
            if (currentChar == '\n') {
                lineNumber++;
                charNumber = 0;

            //If it is a single line comment we will add to index our line number and reset char count while continuing our while loop
            } else if (currentChar == '/' && peekNextCharacter() == '/') {
                reader.readLine();
                lineNumber++;
                charNumber = 0;
                continue;

            //Same as above but for multi-line comments
            } else if (currentChar == '/' && peekNextCharacter() == '*') {
                skipMultiLineComment();
                continue;

            //Same as above but for characters in literals
            } else if (currentChar == '\'' || currentChar == '\"') {
                skipLiteral(currentChar);
                continue;


            //Tests individual code blocks to make sure they are complete without missing a '(' or '['
            } else if (currentChar == ';' & !delimiterStack.empty()) {
                if (delimiterStack.peek() == '{' ) {
                    continue;
                }else {
                    currentChar = delimiterStack.pop();
                    lineNumber = delimiterLocation.pop();
                    charNumber = delimiterLocation.pop();
                    System.out.println("Mismatched delimiter '" + currentChar + "' at " + getCurrentPosition());
                    continue;
                }
            }
            //Returns our current character
            return currentChar;
        }
        if (!delimiterStack.empty()){
            badChar = delimiterStack.pop();
            lineNumber = delimiterLocation.pop();
            charNumber = delimiterLocation.pop();
            System.out.println("Mismatched delimiter '" + badChar + "' at " + getCurrentPosition());            
        }
        //Will indicate the end of our file
        return '\0';
    }

    //String to show what line and character positon we are on.
    public String getCurrentPosition() {
        return "Line: " + lineNumber + ", Char: " + charNumber;
    }

    //This will be our peek method so that we can identify the end of the file, comments, and literals.
    private char peekNextCharacter() throws IOException {
        reader.mark(1);
        int charInt = reader.read();
        reader.reset();
        return (char) charInt;
    }

    //Method to skip multi line comments while keeping the line and character numbers indexed
    private void skipMultiLineComment() throws IOException {
        while (true) {
            int charInt = reader.read();

            //If statement to find the end of the file just incase the comments lead to the end
            if (charInt == -1) {
                return;
            }

            //Character index
            char currentChar = (char) charInt;
            charNumber++;

            //Line index and character reset
            if (currentChar == '\n') {
                lineNumber++;
                charNumber = 0;

            //Ends the multi-line comment by reading in the last '/' and returning to main character reader
            } else if (currentChar == '*' && peekNextCharacter() == '/') {
                reader.read();
                return;
            }
        }
    }

    //Will skip literals.
    private void skipLiteral(char literalType) throws IOException {
        while (true) {
            //For end of the file character will return end of file
            int charInt = reader.read();
            if (charInt == -1) {
                return;
            }

            //if not the end of the file characters will be compared and indexed
            char currentChar = (char) charInt;
            charNumber++;

            //When at a new line line number will be indexed and charnumber will be reset
            if (currentChar == '\n') {
                lineNumber++;
                charNumber = 0;
            //If it is a literal type it will be indexed and return to read the next character
            } else if (currentChar == literalType) {
                return; // End of literal
            }
        }
    }

    //Will be used to peek if there are any other characters
    public boolean hasMoreCharacters() throws IOException {
        return peekNextCharacter() != -1;
    }

    //character deliminator classifications to search for what type of delimiter it is and see if it is unmatched
    public boolean isDelimiterMismatch(char currentChar) {

        //If left delimiter it will be pushed to stack and location will be stored
        if (isLeftDelimiter(currentChar)) {

            delimiterStack.push(currentChar);

            delimiterLocation.push(charNumber);
            delimiterLocation.push(lineNumber);

        //If it is right delimiter it will see if the stack is empty or if it is a matching pair
        } else if (isRightDelimiter(currentChar)) {
            if (delimiterStack.isEmpty() || !isMatchingPair(delimiterStack.peek(), currentChar)) {
                System.out.println("Mismatched delimiter '" + currentChar + "' at " + getCurrentPosition());
                //If it is found to be a mistmatch we will set this to true and print the error message.
                return true;
            }else{
                delimiterStack.pop();
                delimiterLocation.pop(); delimiterLocation.pop();
            }
        }
        //Will set mistmatch to false and continue search
        return false;
    }

    //Assign Left Delimiters
    private boolean isLeftDelimiter(char ch) {
        return ch == '(' || ch == '{' || ch == '[';
    }

    //Assign right delimitres
    private boolean isRightDelimiter(char ch) {
        return ch == ')' || ch == '}' || ch == ']';
    }

    //Assign matching delimiters
    private boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
               (left == '{' && right == '}') ||
               (left == '[' && right == ']');
    }

    //Close the reader
    public void close() throws IOException {
        reader.close();
    }
}