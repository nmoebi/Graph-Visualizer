package visualizer.engine.handlers;

import visualizer.engine.VisEngine;
import visualizer.engine.config.Config;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

public class MoveVertexHandler implements InteractionHandler {
    
    private final Graph graph;
    Vertex vertex;
    private boolean vertexGrabbed = false;
    
    private final Config config;

    public MoveVertexHandler(VisEngine visEngine, Graph graph) {
        this.graph = graph;
        this.config = visEngine.getConfig();
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
        int gridSize = config.getGridSize(); 
        if(vertexGrabbed) {
            x = (int) (Math.round((double) x / gridSize) * gridSize);
            y = (int) (Math.round((double) y / gridSize) * gridSize);
            vertex.setPosition(x, y);
            vertexGrabbed = false;
        }
    }
}