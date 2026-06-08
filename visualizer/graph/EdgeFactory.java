package visualizer.graph;

public class EdgeFactory {
    public Edge createEdge(Vertex vertex1, Vertex vertex2) {
        return new Edge(vertex1, vertex2);
    }
}