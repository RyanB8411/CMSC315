/**
 * UMGC CMSC 315
 * Project 4
 * Class that will call the P4Pane class and use it to display a javaFX Scene
 * This will display the graph based on the built in classes.
 * @author Ryan Burkhardt
 * Date: 26Feb2024
 * Java 21
*/

package Burkhardt_Project_4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Project4 extends Application{

    // Override our stage class and add our Pane Class
    @Override
    public void start(Stage proj4Stage) {

        P4Pane project4Pane = new P4Pane();// Create Pane
        Scene scene = new Scene(project4Pane);// Create a scene and place the pane in the stage
        proj4Stage.setTitle("Project 4 - Ryan Burkhardt"); // Set the stage title
        proj4Stage.setScene(scene); // Place the scene in the stage
        proj4Stage.show(); // Display the stage
    }
    public static void main(String[] args) {
        launch(args);
    }
}
