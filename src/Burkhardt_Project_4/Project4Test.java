package Burkhardt_Project_4;

public class Project4Test{
    public static void main(String[] args){
        Graph testGraph = new Graph();
        Vertex vert1 = testGraph.addVertex(0.0,1.0);
        Vertex vert2 = testGraph.addVertex(5.0,0.0);

        testGraph.addEdge(vert1, vert2);
        System.out.println(testGraph.toStringEdge());
        
        System.out.println(testGraph.toStringVert());

    }
}
