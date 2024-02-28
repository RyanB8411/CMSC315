package Burkhardt_Project_4;

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

    // Initialize our two Lists to store values
    private List<Vertex> vertexList = new ArrayList<>();
    private List<Edge> EdgeList = new ArrayList<>();

    // Create the Pane
    public P4Pane() {

        // Set preffered size to 500*500 and center it. Initialize a new Graph
        setPrefSize(500, 500);
        Graph graph = new Graph();

        // Create Buttons and TextFields we wil be using
        Button addEdgeButton = new Button("Add Edge");
        Label vertex1Label = new Label("Vertex 1:");
        TextField vertex1TextField = new TextField();
        vertex1TextField.setPrefSize(30, 20); // Set the preferred size of the first text field
        Label vertex2Label = new Label("Vertex 2:");
        TextField vertex2TextField = new TextField();
        vertex2TextField.setPrefSize(30, 20); // Set the preferred size of the first text field
        Button isConnectedButton = new Button("Is Connected?");
        Button hasCyclesButton = new Button("Has  Cycles?");
        Button dfsButton = new Button("Depth First Search");
        Button bfsButton = new Button("Breadth First Search");
        TextField messageDisplay = new TextField();
        messageDisplay.setPrefSize(423, 20);
        messageDisplay.setEditable(false);

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
                if(event.getY() > 60 && event.getY() < 415){
                    if (vertexList.size() < 26){
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

        // Add action listener to the 'Add Edge' button
        addEdgeButton.setOnAction(event -> {
            if (!vertex1TextField.getText().isEmpty() && !vertex2TextField.getText().isEmpty()) {
                Vertex vertex1 = graph.getVertex(vertex1TextField.getText().toUpperCase().charAt(0));
                Vertex vertex2 = graph.getVertex(vertex2TextField.getText().toUpperCase().charAt(0));
                if (vertexList.contains(vertex1) && vertexList.contains(vertex2)) {
                    graph.addEdge(vertex1, vertex2);
                    Edge edge = graph.addEdge(vertex1, vertex2);
                    EdgeList.add(edge);
                    drawEdges(vertex1, vertex2);
                } else {
                    messageDisplay.setText("One or both of the vertices are not in the vertex list.");
                    messageDisplay.setStyle("-fx-text-inner-color: red;");
                }
            }
        });
        isConnectedButton.setOnAction(event -> {
            if (graph.isConnected()){
                messageDisplay.setText("The graph is Connected");
                messageDisplay.setStyle("-fx-text-inner-color: green;");
            }
            else{
                messageDisplay.setText("The Graph is not Connected.");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }
        });
        hasCyclesButton.setOnAction(event -> {
            if (graph.checkCycle()){
                messageDisplay.setText("The graph contains a cycle");
                messageDisplay.setStyle("-fx-text-inner-color: green;");
            }
            else{
                messageDisplay.setText("The graph doesn't have cycles");
                messageDisplay.setStyle("-fx-text-inner-color: red;");
            }
        });
        dfsButton.setOnAction(event -> {
            if (!EdgeList.isEmpty()){
                messageDisplay.setText(graph.depthFirstSearch());
            }
        });
        bfsButton.setOnAction(event -> {
            if (!EdgeList.isEmpty()){
                messageDisplay.setText(graph.breadthFirstSearch());
            }
        });
    }
    // Will draw the Vertices
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
    private void drawEdges(Vertex v1, Vertex v2) {
        if (!EdgeList.isEmpty()) {
            Line edgeLine = new Line(v1.getXCoordinate(), v1.getYCoordinate(), v2.getXCoordinate(), v2.getYCoordinate());
            edgeLine.setStroke(Color.BLACK);
            edgeLine.setStrokeWidth(2);
            getChildren().add(edgeLine);
        }
    }
}
