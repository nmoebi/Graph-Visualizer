package visualizer.engine.handlers;

import visualizer.engine.VisEngine;
import visualizer.graph.Graph;

public class AddVertexHandler implements InteractionHandler {
    
    private final VisEngine engine;
    private final Graph graph;

    public AddVertexHandler(VisEngine engine, Graph graph) {
        this.engine = engine;
        this.graph = graph;
    }

    @Override public void onPress(int xPressed, int yPressed) {
        engine.registerObject(graph.newVertex(xPressed, yPressed));
    }
}