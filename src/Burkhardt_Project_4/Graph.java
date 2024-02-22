package Burkhardt_Project_4;

import java.util.*;


public class Graph{

    private Map<Character, Vertex> vertices = new HashMap<>();
    private Map<Integer, Edge> edges = new HashMap<>();
    private char[] names = new char[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private int vertIndex = 0;
    private int edgeIndex = 0;
    

    //Construct the empty graph
    public Graph(){
    }

    class Edge{
        Vertex v1;
        Vertex v2;
        Edge(Vertex v1, Vertex v2){
        this.v1 = v1;
        this.v2 = v2;
        }
        public String toString(){
            return ("Edge is from "+ v1 + " to " + v2);
        }
    }
    
    //method to add a vertex
    public Vertex addVertex(Double x, Double y){
        Vertex v = new Vertex(names[vertIndex], x, y);
        vertices.put(names[vertIndex], v);
        vertIndex += 1;
        return v;
    }

    //method to add an edge
    public void addEdge(Vertex vert1, Vertex vert2){
        edges.put(edgeIndex, new Edge(vert1, vert2));
        edgeIndex += 1;
    }

    //method that checks whether the graph has cycles
    public void checkCycle(){
        //TODO checkCycles method
    }

    //method that checks if the graph is connected
    public void isConnected(){
        //TODO check to see if graph is connected
    }

    //method returns a list of vertices resulting from a depth-first graph search
    public void depthFirstSearch(){
        //TODO implement DFS
    }

    //method returns a list of vertices resulting from a breadth-first search
    public void breadthFirstSearch(){
        //TODO implement BFS
    }
    public String toStringVert(){
        String str = "";
        for (int i=0; i < vertices.size(); i++){
            str += vertices.get(names[i]).toString() + "\n";
        }
        return str;
    }
    public String toStringEdge(){
        String str = "";
        for (int i=0; i < edges.size(); i++){
            str += edges.get(i).toString() + "\n";
        }
        return str;
    }
}
