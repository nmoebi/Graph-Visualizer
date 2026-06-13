package visualizer.engine.input.handlers;

import visualizer.engine.VisEngine;
import visualizer.graph.Graph;

public class AddVertexHandler implements InteractionHandler {
    
    private final VisEngine engine;
    private final Graph graph;

    public AddVertexHandler(VisEngine engine, Graph graph) {
        this.engine = engine;
        this.graph = graph;
    }

    @Override public void onPress(int x, int y) {
        int gridSize = engine.getConfig().getGridSize();

        x = (int) (Math.round((double) x / gridSize) * gridSize);
        y = (int) (Math.round((double) y / gridSize) * gridSize);
        
        engine.registerObject(graph.newVertex(x, y));
    }
}