package visualizer.engine.handlers;

import visualizer.graph.Graph;
import visualizer.graph.Vertex;

public class MoveVertexHandler implements InteractionHandler {
    
    private final Graph graph;
    Vertex vertex;
    private boolean vertexGrabbed = false;

    public MoveVertexHandler(Graph graph) {
        this.graph = graph;
    }

    @Override public void onPress(int x, int y) {
        try {
            vertex = graph.getVertexByLocation(x, y);
            vertexGrabbed = true;
        } catch (Exception e) {
            System.out.println("Error: no vertex here");
        }
    }

    @Override public void onDrag(int x, int y) {
        if(vertexGrabbed)
            vertex.setPosition(x, y);
    }

    @Override
    public void onRelease(int x, int y) {
        if(vertexGrabbed) {
            vertex.setPosition(x, y);
            vertexGrabbed = false;
        }
    }
}