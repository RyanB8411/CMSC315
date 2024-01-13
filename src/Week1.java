public class Week1 {
    public static void main(String[] args) throws Exception {
        String[] cities = {"Atlanta", "Savannah", "New York", "Dallas"};
        java.util.Arrays.sort(cities, java.util.Comparator.comparing(s -> s.length()));

        String banana = "banana";

        for (String s : cities) {
            System.out.print(s + " ");
        }
        System.out.println("\n");
        for (int i = 0; i < banana.length(); i++){
            char curchar = banana.charAt(i);
            System.out.println(curchar + " ");
        }
        //Streamline above code to fewer steps
        banana.chars().forEach(letter -> System.out.println((char)letter));
    }
}
