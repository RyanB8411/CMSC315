/**
 * UMGC CMSC 315
 * @author Ryan Burkhardt
 * 22Feb2024
 * This class includes the constructor for a vertex as well as the three getter methods
 * to return the name or character such as a, b, c etc. and the coordinates for x and y.
 * lastly it utilizes the toString() method to display the vertex to the user
 * Java 21
 */

 package Burkhardt_Project_4;

 //Immutable class for Vertex
 public final class Vertex {
     private String name;
     private Double x;
     private Double y;
 
     //Constructor for vertex
     public Vertex(String name, Double x, Double y) {
         this.name = name;
         this.x = x;
         this.y = y;
     }
     //toString method to print the Vertex
     public String toString(){
        return String.format("Vertex: %s at (%1.0f, %1.0f)", name, x, y);
    
     }
     //Getter for character name
     public String getVertexName() {
         return name;
     }
     //getter for x coordinate
     public Double getXCoordinate(){
         return x;
     }
     //getter for y coordinate
     public Double getYCoordinate(){
         return y;
     }
 }
 