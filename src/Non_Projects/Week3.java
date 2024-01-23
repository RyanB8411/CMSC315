import java.util.Scanner;

public class Week3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.next();
        scanner.close();

        int start = 0, end = 0, maxLen = 0, currLen = 1;
        
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) > input.charAt(i - 1))
                currLen++;
            else{
                if (currLen > maxLen) {
                    maxLen = currLen;
                    start = i - maxLen;
                    end = i;
                }
                currLen = 1;
            }
        }
        if (currLen > maxLen) {
            maxLen = currLen;
            start = input.length() - maxLen;
            end = input.length();
        }

        System.out.println("Maximum consecutive increasingly ordered substring: " + input.substring(start, end + 1));
    }
}