// /**
//  * UMGC CMSC 315
//  * Project 4
//  * Class that will handle the creation and testing of project 4 data and methods no GUI
//  * @author Ryan Burkhardt
//  * Date: 23Feb2024
//  * Java 21
// */

// package Burkhardt_Project_4;

// import java.util.*;
// import javafx.scene.layout.Pane;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.shape.Line;

// // public class Project4Test{
// //     public static void main(String[] args){
// //         Graph testGraph = new Graph();
// //         Vertex vert1 = testGraph.addVertex(4.0,1.0);
// //         Vertex vert2 = testGraph.addVertex(5.0,2.0);
// //         Vertex vert3 = testGraph.addVertex(10.0,8.0);
// //         Vertex vert4 = testGraph.addVertex(12.0,1.0);
// //         Vertex vert5 = testGraph.addVertex(5.0,7.8);
// //         Vertex vert6 = testGraph.addVertex(10.0,1.0);
        
        

// //         testGraph.addEdge(vert1, vert2);
// //         testGraph.addEdge(vert2, vert3);
// //         testGraph.addEdge(vert3, vert4);
// //         testGraph.addEdge(vert4, vert5);
// //         testGraph.addEdge(vert5, vert6);
// //         testGraph.addEdge(vert6, vert1);

// //         testGraph.depthFirstSearch();
// //         testGraph.breadthFirstSearch();

// //         System.out.print(testGraph.isConnected()+"\n\n"); 
// //         System.out.print(testGraph.checkCycle()+"\n");

// //         System.out.println(testGraph.printAllVerts());
        
// //         System.out.println(testGraph.printAllEdges());

// //     }
// // }
// class Graph {

//     private Map<Character, Vertex> vertices = new HashMap<>();
//     private Map<Integer, Edge> edges = new HashMap<>();
//     private char[] names = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
//             'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
//     private int vertIndex = 0;
//     private int edgeIndex = 0;

//     // Construct the empty graph
//     public Graph() {
//     }

//     // method to add a vertex
//     public Vertex addVertex(Double x, Double y) {
//         Vertex v = new Vertex(names[vertIndex], x, y);
//         vertices.put(names[vertIndex], v);
//         vertIndex += 1;
//         return v;
//     }

//     // method to add an edge
//     public void addEdge(Vertex vert1, Vertex vert2) {
//         edges.put(edgeIndex, new Edge(vert1, vert2));
//         edgeIndex += 1;
//     }

//     ArrayList<Edge> getConnectedEdges(Vertex vertex) {
//         ArrayList<Edge> result = new ArrayList<>();
//         for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
//             if (edge.getValue().v1 == vertex || edge.getValue().v2 == vertex)
//                 result.add(edge.getValue());
//         }
//         return result;
//     }

//     ArrayList<Vertex> getConnectedVertices(Vertex vertex) {
//         ArrayList<Vertex> result = new ArrayList<>();
//         for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
//             if (edge.getValue().v1 == vertex)
//                 result.add(edge.getValue().v2);
//             else if (edge.getValue().v2 == vertex)
//                 result.add(edge.getValue().v1);
//         }
//         return result;
//     }

//     private boolean checkCycle(Vertex vertex, Vertex original) {
//         ArrayList<Edge> connections = getConnectedEdges(vertex);
//         for (Edge con : connections) {
//             if (!visited.contains(con)) {
//                 System.out.println(vertex.getVertexName());
//                 visited.add(con);
//                 if (con.v1 == original || con.v2 == original)
//                     return true;
//                 Vertex conVert = con.v1;
//                 if (conVert == vertex) // We want the other vertex, not the current one
//                     conVert = con.v2;
//                 if (checkCycle(conVert, original))
//                     return true;
//             }
//         }
//         return false;
//     }

//     // method that checks whether the graph has cycles
//     ArrayList<Edge> visited;

//     public boolean checkCycle() {
//         for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
//             visited = new ArrayList<>();
//             visited.add(edge.getValue());
//             if (checkCycle(edge.getValue().v2, edge.getValue().v1))
//                 return true;
//         }
//         return false;
//     }

//     ArrayList<Vertex> visitedVert;
//     public Boolean isConnected(){
//         visitedVert = new ArrayList<>();
//         Vertex startPoint = vertices.get('A');
//         visitedVert.add(startPoint);
//         for (int i = 0; i < visitedVert.size(); i++){
//             for(Vertex vertex : getConnectedVertices(visitedVert.get(i))){
//                 if (!visitedVert.contains(vertex)){
//                     visitedVert.add(vertex);
//                 }
//             }

//         }
//         return visitedVert.size() == vertices.size();
//     }

//     // method returns a list of vertices resulting from a depth-first graph search
//     public void depthFirstSearch() {
//         Stack<Vertex> dfsStack = new Stack<>();
//         Set<Vertex> visited = new HashSet<>();
    
//         dfsStack.push(vertices.get('A')); // Starting from vertex 'A'
//         visited.add(vertices.get('A'));
    
//         while (!dfsStack.isEmpty()) {
//             Vertex currentVertex = dfsStack.pop();
//             System.out.println("Visited: " + currentVertex.getVertexName());
    
//             // Get connected vertices and add them to the stack if not visited
//             for (Vertex neighbor : getConnectedVertices(currentVertex)) {
//                 if (!visited.contains(neighbor)) {
//                     dfsStack.push(neighbor);
//                     visited.add(neighbor);
//                 }
//             }
//         }
//     }

//     // method returns a list of vertices resulting from a breadth-first search
//     public void breadthFirstSearch() {
//         Queue<Vertex> bfsQueue = new LinkedList<>();
//         Set<Vertex> visited = new HashSet<>();
    
//         bfsQueue.offer(vertices.get('A')); // Starting from vertex 'A'
//         visited.add(vertices.get('A'));
    
//         while (!bfsQueue.isEmpty()) {
//             Vertex currentVertex = bfsQueue.poll();
//             System.out.println("Visited: " + currentVertex.getVertexName());
    
//             // Get connected vertices and add them to the queue if not visited
//             for (Vertex neighbor : getConnectedVertices(currentVertex)) {
//                 if (!visited.contains(neighbor)) {
//                     bfsQueue.offer(neighbor);
//                     visited.add(neighbor);
//                 }
//             }
//         }
//     }

//     public String printAllVerts(){
//         String str = "";
//         for (int i=0; i < vertices.size(); i++){
//             str += vertices.get(names[i]).toString() + "\n";
//         }
//         return str;
//     }
//     public String printAllEdges(){
//         String str = "";
//         for (int i=0; i < edges.size(); i++){
//             str += edges.get(i).toString() + "\n";
//         }
//         return str;
//     }
// }
// class Edge {
//     Vertex v1;
//     Vertex v2;

//     Edge(Vertex v1, Vertex v2) {
//         this.v1 = v1;
//         this.v2 = v2;
//     }

//     public Vertex getEdgeStart(){
//         return v1;
//     }

//     public Vertex getEdgeEnd(){
//         return v2;
//     }

//     public String toString() {
//         return ("Edge is from " + v1 + " to " + v2);
//     }
// }
// final class Vertex {
//     private Character name;
//     private Double x;
//     private Double y;

//     //Constructor for vertex
//     public Vertex(Character name, Double x, Double y) {
//         this.name = name;
//         this.x = x;
//         this.y = y;
//     }
//     //toString method to print the Vertex
//     public String toString(){
//         return ("Vertex: "+ name +" at ("+ x +", "+ y + ")");
//     }
//     //Getter for character name
//     public Character getVertexName() {
//         return name;
//     }
//     //getter for x coordinate
//     public Double getXCoordinate(){
//         return x;
//     }
//     //getter for y coordinate
//     public Double getYCoordinate(){
//         return y;
//     }
// }


// // ####################FAILED METHODS##########################//
//     // private Boolean isConnected(Vertex v1) {//to me this is making sense but it is not working.
//     //     ArrayList<Vertex> connected = getConnectedVertices(v1);
//     //     for (Vertex con : connected) {
//     //         if (!visitedVert.contains(con)) {//If the list doesnt contain the vertex we are visiting
//     //             visitedVert.add(con);//it will add it
//     //             System.out.println("Each Pass" + visitedVert.size()+" "+ vertices.size());
//     //         if (visitedVert.size() == vertices.size()) { // Check if all vertices have been visitedif on the last recursion end
//     //             return true;
//     //         }
//     //         isConnected(con);
//     //     }
//     //     }
//     //     return false;
//     // }
    
//     //method that checks if the graph is connected
//     // public Boolean isConnected() {
//     //     visitedVert = new ArrayList<>(); // Clear visitedVert for each vertex
//     //     System.out.println(visitedVert.size()+" "+ vertices.size());
//     //     for (Vertex vertex : vertices.values()) {
//     //         System.out.println(isConnected(vertex));
//     //         if (!isConnected(vertex)) {
//     //             System.out.println("False" + visitedVert.size()+" "+ vertices.size());

//     //             return false;
//     //         }
//     //     }
//     //     System.out.println("True" + visitedVert.size()+" "+ vertices.size());

//     //     return true;
//     // }