package visualizer.engine.handlers;

import visualizer.engine.VisEngine;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

public class AddEdgeHandler implements InteractionHandler {
    
    private final VisEngine engine;
    private final Graph graph;

    public AddEdgeHandler(VisEngine engine, Graph graph) {
        this.engine = engine;
        this.graph = graph;
    }

    @Override
    public void handleInput(int xPressed, int yPressed, int xReleased, int yReleased) {
        
        Vertex a = graph.getVertexByLocation(xPressed, yPressed);
        Vertex b = graph.getVertexByLocation(xReleased, yReleased);

        engine.registerObject(graph.newEdge(a, b));
    }
}