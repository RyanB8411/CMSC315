package Burkhardt_Project_4;

public class Project4Test{
    public static void main(String[] args){
        Graph testGraph = new Graph();
        Vertex vert1 = testGraph.addVertex(4.0,1.0);
        Vertex vert2 = testGraph.addVertex(5.0,2.0);
        Vertex vert3 = testGraph.addVertex(10.0,8.0);
        Vertex vert4 = testGraph.addVertex(12.0,1.0);
        Vertex vert5 = testGraph.addVertex(5.0,7.8);
        Vertex vert6 = testGraph.addVertex(10.0,1.0);
        
        

        testGraph.addEdge(vert1, vert2);
        testGraph.addEdge(vert2, vert3);
        testGraph.addEdge(vert3, vert4);
        testGraph.addEdge(vert4, vert5);
        testGraph.addEdge(vert5, vert3);
        testGraph.addEdge(vert4, vert3);

        System.out.print(testGraph.isConnected()+"\n"); 
        System.out.print(testGraph.checkCycle()+"\n");

        System.out.println(testGraph.toStringEdge());
        
        System.out.println(testGraph.toStringVert());

    }
}
