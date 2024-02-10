package Burkhardt_Project_3;

import java.util.Scanner;

public class Project3 {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a string:");
        String input = scanner.nextLine();
        int i = 0;
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c == '(') {
                output.append("\n\t(i+=1)");
                i++;
            } else if (c == ')') {
                i--;
            } else if (c == '*') {
                // do nothing
            } else if (c == ' ') {
                // do nothing
            } else {
                output.append(c);
            }
        }
        System.out.println(output);
        scanner.close();
    }
}