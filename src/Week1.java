public class Week1 {
    public static void main(String[] args) throws Exception {
        String[] cities = {"Atlanta", "Savannah", "New York", "Dallas"};
        java.util.Arrays.sort(cities, java.util.Comparator.comparing(s -> s.length()));

        for (String s : cities) {
            System.out.print(s + " ");
        }

        
    }
}
