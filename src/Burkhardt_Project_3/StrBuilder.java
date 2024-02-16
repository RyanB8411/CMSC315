package Burkhardt_Project_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class StrBuilder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean  run = true;
        while (run == true){
            System.out.println("Enter a binary tree:");
            String input = scanner.nextLine();
            int amountOfTabs = 0;
            StringBuilder output = new StringBuilder();
            char prevChar = '\0'; // initialize previous character to null
            for (char c : input.toCharArray()) {
                if (c == '(') {
                    if (prevChar == '\0'){//If it is the start of the expression
                    }
                else{
                    output.append("\n");
                    for (int i=0; i< amountOfTabs; i++){
                        output.append("  ");
                    }
                }
                    amountOfTabs++;
                } else if (c == ')') {
                    if (prevChar != '*' || prevChar != ')') { // only add newline if previous character was not a ')'
                    }else{
                        output.append("\n");
                    }

                    amountOfTabs--;
                    for (int i=0; i< amountOfTabs; i++){
                        output.append("  ");
                    }
                } else if (c == '*') {
                } else if (c == ' ') {
                } else {
                    int value = Character.getNumericValue(c);
                    output.append(value).append("");
                }
                prevChar = c; // update previous character
            }
            System.out.println(output);
            List<Integer> values = new ArrayList<>();
            for (String s : output.toString().split("\\s+")) {
                if (!s.isEmpty()) {
                    values.add(Integer.parseInt(s));
                }
            }
            System.out.println(values);
            while (true) {
                System.out.println("More Trees? Y or N");
                String userInput = scanner.nextLine();
                if (userInput.equalsIgnoreCase("N")) {
                    run = false;
                    break;
                }else if (userInput.equalsIgnoreCase("Y")) {
                    run = true;
                    break;
                }else {
                    continue;
                }
            }
        }scanner.close();
    }
}