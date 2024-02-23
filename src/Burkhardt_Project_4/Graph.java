package Burkhardt_Project_4;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<Character, Vertex> vertices = new HashMap<>();
    private Map<Integer, Vertex> cycleVerts = new HashMap<>();
    private Map<Integer, Edge> edges = new HashMap<>();
    private char[] names = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    private int vertIndex = 0;
    private int edgeIndex = 0;
    private int cycleIndex = 0;

    // Construct the empty graph
    public Graph() {
    }

    class Edge {
        Vertex v1;
        Vertex v2;

        Edge(Vertex v1, Vertex v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        public String toString() {
            return ("Edge is from " + v1 + " to " + v2);
        }
    }

    // method to add a vertex
    public Vertex addVertex(Double x, Double y) {
        Vertex v = new Vertex(names[vertIndex], x, y);
        vertices.put(names[vertIndex], v);
        vertIndex += 1;
        return v;
    }

    // method to add an edge
    public void addEdge(Vertex vert1, Vertex vert2) {
        edges.put(edgeIndex, new Edge(vert1, vert2));
        edgeIndex += 1;
    }

    ArrayList<Edge> getConnectedEdges(Vertex vertex) {
        ArrayList<Edge> result = new ArrayList<>();
        for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
            if (edge.getValue().v1 == vertex || edge.getValue().v2 == vertex)
                result.add(edge.getValue());
        }
        return result;
    }

    ArrayList<Vertex> getConnectedVertices(Vertex vertex) {
        ArrayList<Vertex> result = new ArrayList<>();
        for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
            if (edge.getValue().v1 == vertex)
                result.add(edge.getValue().v2);
            else if (edge.getValue().v2 == vertex)
                result.add(edge.getValue().v1);
        }
        return result;
    }

    private boolean checkCycle(Vertex vertex, Vertex original) {
        ArrayList<Edge> connections = getConnectedEdges(vertex);
        for (Edge con : connections) {
            if (!visited.contains(con)) {
                System.out.println(
                        vertex.getYCoordinate() + "->" + con.v1.getYCoordinate() + "," + con.v2.getYCoordinate());
                visited.add(con);
                if (con.v1 == original || con.v2 == original)
                    return true;
                Vertex conVert = con.v1;
                if (conVert == vertex) // We want the other vertex, not the current one
                    conVert = con.v2;
                if (checkCycle(conVert, original))
                    return true;
            }
        }
        return false;
    }

    // method that checks whether the graph has cycles
    ArrayList<Edge> visited;

    public boolean checkCycle() {
        for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
            visited = new ArrayList<>();
            visited.add(edge.getValue());
            if (checkCycle(edge.getValue().v2, edge.getValue().v1))
                return true;
        }
        return false;
    }

    private Boolean isConnected(Vertex v1) {
        ArrayList<Vertex> connected = getConnectedVertices(v1);
        for (Vertex con : connected) {
            if (!visitedVert.contains(con)){
                visitedVert.add(con);
                isConnected(con);
            }
        }
        if (visitedVert.size() == vertices.size())
            return true;
        else{
            return false;
        }

    }
    ArrayList<Vertex> visitedVert;
    //method that checks if the graph is connected
    public Boolean isConnected() {
        for (Map.Entry<Integer, Edge> edge : edges.entrySet()) {
            visitedVert = new ArrayList<>();//start with the list
            visitedVert.add(edge.getValue().v1);
            if(isConnected(edge.getValue().v1));
                return true;
        }
        return false;
    }

    // method returns a list of vertices resulting from a depth-first graph search
    public void depthFirstSearch() {
        // TODO implement DFS
    }

    // method returns a list of vertices resulting from a breadth-first search
    public void breadthFirstSearch() {
        // TODO implement BFS
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