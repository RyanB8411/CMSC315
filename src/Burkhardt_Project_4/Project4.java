/**
 * UMGC CMSC 315
 * Project 4
 * Class that will take the users input and create a Binary Search Tree
 * It uses a parse expression to recursively extract the expression from the string
 * After it has the three substrings it creates the  tree nodes with them.
 * It uses classes Binary Search Tree as well SyntaxCheck Classes in its main methods.
 * @author Ryan Burkhardt
 * Date: 23Feb2024
 * Java 21
*/

package Burkhardt_Project_4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Project4 extends Application{

    // Override our stage class and add our Pane Class
    @Override
    public void start(Stage primaryStage) {

        P4Pane project4Pane = new P4Pane();// Create Pane
        // Create a scene and place the pane in the stage
        Scene scene = new Scene(project4Pane);
        primaryStage.setTitle("Project 4 - Ryan Burkhardt"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    public static void main(String[] args) {
        launch(args);
    }
}
