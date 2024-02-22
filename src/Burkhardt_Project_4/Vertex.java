/**
 * UMGC CMSC 315
 * @author Ryan Burkhardt
 * 22Feb2024
 * This class includes the constructor for a vertex as well as the three getter methods
 * to return the name or character such as a, b, c etc. and the coordinates for x and y.
 * Java 21
 */

package Burkhardt_Project_4;

//Immutable class for Vertex
public final class Vertex {
    private Character name;
    private Double x;
    private Double y;

    //Constructor for vertex
    public Vertex(Character name, Double x, Double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    //Getter for character name
    public Character getVertexName() {
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
