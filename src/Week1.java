//Import required Classes
import java.util.PriorityQueue;
import java.util.Scanner;

//Create our Project 1 Class
public class Week1 {

    //Create our main with String and args
    public static void main(String[] args) {

        //Start by creating two Priority Ques for the integers
        PriorityQueue<Integer> queue1 = new PriorityQueue<>();
        PriorityQueue<Integer> queue2 = new PriorityQueue<>();

        //Next we will prompt the user for their Integers as strings
        //Create the Scanner
        Scanner sc = new Scanner(System.in);

        //Prompt User for 1st list
        System.out.println("Please enter your first lines of integers each seperated by a space:\t");
        String firstList = sc.nextLine();

        //Add our integers into our 1st queue using our private class below
        addIntegersToQueue(firstList, queue1);

        //repeat process for second list
        System.out.println("Please enter your second lines of integers seperated by a space:\t");
        String secondList = sc.nextLine();
        addIntegersToQueue(secondList, queue2);

        //Here we will display required Union, Difference, intersection in increasing order
        //Create our new Priority Queue union using queue 1's elements
        PriorityQueue<Integer> union = new PriorityQueue<>(queue1);

        //add all of queue 2 to queue 1
        union.addAll(queue2);

        //Create our new Priority Queue Difference using queue 1's elements
        PriorityQueue<Integer> difference = new PriorityQueue<>(queue1);

        //Use the removeAll method remove all of queue 2's elements from queue 1
        difference.removeAll(queue2);

        //Lastly create our Priority Queue for our intersection
        PriorityQueue<Integer> intersection = new PriorityQueue<>(queue1);

        //use the retainAll method to remove elements from queue1 and only retain queue2
        intersection.retainAll(queue2);

        //Here we will display our findings for union, difference and intersection
        System.out.print("The union of the two priority queues is\n");
        sortQueue(union);
        System.out.print("\nThe difference between the two priority queues is\n");
        sortQueue(difference);
        System.out.print("\nThe intersection between the two priority queues is\n");
        sortQueue(intersection);
        sc.close();
    }


    //Create a private class to take strings and import them to ints in our queue
    private static void addIntegersToQueue(String strings, PriorityQueue<Integer> queue) {

        //Bring in a scanner for strings
        Scanner sc = new Scanner(strings);

        //Create a while loop to see if the list provided has next element and also add to a queue
        while (sc.hasNext()) {

            //Create an if and else to see if the element is an int or non-int
            if (sc.hasNextInt()){
                //Will add to que if it is an integer
                queue.add(sc.nextInt());
            }else{
                //Will skip if it is non-integer
                sc.next();
            }
        }
        sc.close();
    }

    //We will then need another Priavte class to sort using poll
    private static void sortQueue (PriorityQueue<Integer> queue) {

        //Create a new Priority Queue to sort from the passed in queue
        PriorityQueue<Integer> sortedQueue = new PriorityQueue<>(queue);

        //create the while loop with the not empty as its peramiter(!) this way we do not get a null value or empty brackets
        while (!sortedQueue.isEmpty()) {
            
            //print each integer while queue is not empty
            System.out.print(sortedQueue.poll() + " ");
        

        }
    }
}
