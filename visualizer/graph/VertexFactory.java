package visualizer.graph;

public final class VertexFactory {
    
    private int vertexCounter = 0;

    public Vertex createVertex(int xPosition, int yPosition) {
        return new Vertex(vertexCounter++, xPosition, yPosition);
    }

    public void reset() {
        vertexCounter = 0;
    }
}