/**
 * UMGC CMSC 315
 * Project 4
 * Class that will create the pane and have all methods to display the vertices
 * and edges. includes the 5 buttons using the set action method to call the'
 * vertex, edge, and graph classes. Will allow the user to add vertices with mouse
 * and add edges with buttons. This class will also allow the user to check and
 * see if the graph is connected, has cycles, perform a depth first search and
 * also a breadth first search by calling in the methods from graph.
 * @author Ryan Burkhardt
 * Date: 26Feb2024
 * Java 21
*/

package Burkhardt_Project_4;

//Import Necessary Extensions
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class P4Pane extends Pane {

    // Initialize our two Lists to store vertices and edges
    private List<Vertex> vertexList = new ArrayList<>();
    private List<Edge> edgeList = new ArrayList<>();

    // Create the Pane object
    public P4Pane() {

        // Set preffered size to 500*500. Initialize a new Graph
        setPrefSize(500, 500);
        Graph graph = new Graph();

        // Create Buttons for the program
        Button addEdgeButton = new Button("Add Edge");
        Button isConnectedButton = new Button("Is Connected?");
        Button hasCyclesButton = new Button("Has  Cycles?");
        Button dfsButton = new Button("Depth First Search");
        Button bfsButton = new Button("Breadth First Search");

        //Create the labels for the program
        Label vertex1Label = new Label("Vertex 1:");
        Label vertex2Label = new Label("Vertex 2:");

        //Create the TextFields to take input from user and display messages
        TextField vertex1TextField = new TextField();
        vertex1TextField.setPrefSize(30, 20); // Set the preferred size of the first text field
        TextField vertex2TextField = new TextField();
        vertex2TextField.setPrefSize(30, 20); // Set the preferred size of the first text field
        TextField messageDisplay = new TextField();
        messageDisplay.setPrefSize(423, 20);
        messageDisplay.setEditable(false);//Set it so the user cannot edit it

        // Create a HBox to hold the button and labels
        HBox topBox = new HBox(5);
        topBox.getChildren().addAll(addEdgeButton, vertex1Label, vertex1TextField, vertex2Label, vertex2TextField);
        topBox.setLayoutX(120);
        topBox.setLayoutY(10);
        topBox.setAlignment(Pos.CENTER);
        HBox lowerBox1 = new HBox(5);
        lowerBox1.getChildren().addAll(isConnectedButton, hasCyclesButton, dfsButton, bfsButton);
        lowerBox1.setLayoutX(40);
        lowerBox1.setLayoutY(420);
        HBox lowerBox2 = new HBox(5);
        lowerBox2.setLayoutX(40);
        lowerBox2.setLayoutY(450);
        lowerBox2.getChildren().add(messageDisplay);

        // Add the HBox to the Pane
        getChildren().addAll(topBox, lowerBox1, lowerBox2);

        // Set the mouse clicks to add a new vertex
        setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                if(event.getY() > 60 && event.getY() < 415){//make the upper and lower portions of the screen unclickable
                    if (vertexList.size() < 26){//Only allow A-Z
                        Vertex vertex = graph.addVertex(event.getX(), event.getY());
                        vertexList.add(vertex);
                        drawVertex(vertex);
                        messageDisplay.setText("Successfully added: " + vertex.toString());
                        messageDisplay.setStyle("-fx-text-inner-color: green");
                    }else{
                        messageDisplay.setText("Only up to 26 vertices can be added.");
                        messageDisplay.setStyle("-fx-text-inner-color: red");
                    }
                }
            }
        });

        // Add the button method to add an edge to the graph with methods to check and
        // see if the user has entered valid input and textFields aren't empty
        addEdgeButton.setOnAction(event -> {
            if (!vertex1TextField.getText().isEmpty() && !vertex2TextField.getText().isEmpty()) {
                Vertex vertex1 = graph.getVertex(vertex1TextField.getText().toUpperCase().charAt(0));
                Vertex vertex2 = graph.getVertex(vertex2TextField.getText().toUpperCase().charAt(0));
                if (vertexList.contains(vertex1) && vertexList.contains(vertex2)) {
                    graph.addEdge(vertex1, vertex2);
                    Edge edge = graph.addEdge(vertex1, vertex2);
                    edgeList.add(edge);
                    messageDisplay.setText("Edge created from " + edge.toString() + " successfully!");
                    messageDisplay.setStyle("-fx-text-inner-color: green;");
                    drawEdges(vertex1, vertex2);
                } else {
                    messageDisplay.setText("One or both of the vertices are not in the vertex list.");
                    messageDisplay.setStyle("-fx-text-inner-color: red;");
                }
            }else {
                messageDisplay.setText("One or both of the vertices were left blank.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }
        });

        // Button to check if the graph is connected and display message to user
        isConnectedButton.setOnAction(event -> {
            if(!vertexList.isEmpty()){
                if(!edgeList.isEmpty()){
                    if (graph.isConnected()){
                        messageDisplay.setText("The graph is Connected");
                        messageDisplay.setStyle("-fx-text-inner-color: green;");
                    }
                    else{
                        messageDisplay.setText("The graph is not Connected.");
                        messageDisplay.setStyle("-fx-text-inner-color: red;");
                    }
                }
                else{
                    messageDisplay.setText("The graph contains no edges.");
                    messageDisplay.setStyle("-fx-text-inner-color: red;");
                }
            }
            else{
                messageDisplay.setText("The graph is empty.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }
        });

        //Button to check if the graph contains cycles and display message to user
        hasCyclesButton.setOnAction(event -> {
            if(!vertexList.isEmpty()){
                if(!edgeList.isEmpty()){
                    if (graph.checkCycle()){
                        messageDisplay.setText("The graph contains a cycle");
                        messageDisplay.setStyle("-fx-text-inner-color: green;");
                    }else{
                        messageDisplay.setText("The graph doesn't have cycles");
                        messageDisplay.setStyle("-fx-text-inner-color: red;");
                    }
                }else{
                    messageDisplay.setText("The graph contains no edges.");
                    messageDisplay.setStyle("-fx-text-inner-color: red;");
                }
            }else{
                messageDisplay.setText("The graph is empty.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }
        });

        //Button to display the dfs in the message window
        dfsButton.setOnAction(event -> {
            if (vertexList.isEmpty()) {
                messageDisplay.setText("The graph is empty.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }else if (!edgeList.isEmpty()){
                messageDisplay.setText(graph.depthFirstSearch());
            }else{
                messageDisplay.setText("The graph contains no edges.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }
        });

        //Button to display the bfs in the message window
        bfsButton.setOnAction(event -> {
            if (vertexList.isEmpty()) {
                messageDisplay.setText("The graph is empty.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }else if (!edgeList.isEmpty()){
                messageDisplay.setText(graph.breadthFirstSearch());
            }else{
                messageDisplay.setText("The graph contains no edges.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }
        });
    }

    // Will draw the Vertices on the javaFX graph
    private void drawVertex(Vertex vertex) {
        getChildren().add(new Circle(vertex.getXCoordinate(), vertex.getYCoordinate(), 5, Color.BLACK));
        Label vertexLabel = new Label(vertex.getVertexName().toString());
        // Calculate the position of the label above the vertex point
        double labelX = vertex.getXCoordinate()-4;
        double labelY = vertex.getYCoordinate()-25;
        vertexLabel.setLayoutX(labelX);
        vertexLabel.setLayoutY(labelY);
        getChildren().add(vertexLabel);
    }

    //Will draw the Edges on the JavaFX graph
    private void drawEdges(Vertex v1, Vertex v2) {
        if (!edgeList.isEmpty()) {
            Line edgeLine = new Line(v1.getXCoordinate(), v1.getYCoordinate(), v2.getXCoordinate(), v2.getYCoordinate());
            edgeLine.setStroke(Color.BLACK);
            edgeLine.setStrokeWidth(2);
            getChildren().add(edgeLine);
        }
    }
}
