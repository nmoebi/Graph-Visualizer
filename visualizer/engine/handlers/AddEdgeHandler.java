package visualizer.engine.handlers;

import visualizer.engine.VisEngine;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.util.PartialEdge;

public class AddEdgeHandler implements InteractionHandler {
    
    private final VisEngine engine;
    private final Graph graph;

    private int xPressed, yPressed;
    private boolean legalStart = false;

    private final PartialEdge line = new PartialEdge();

    public AddEdgeHandler(VisEngine engine, Graph graph) {        
        this.engine = engine;
        this.graph = graph;
    }

    @Override 
    public void onPress(int xPressed, int yPressed) {
        try {
            legalStart = graph.getVertexByLocation(xPressed, yPressed) instanceof Vertex;
            if(legalStart) {
                this.xPressed = xPressed;
                this.yPressed = yPressed;

                line.setFirsts(xPressed, yPressed);
                line.setLasts(xPressed, yPressed);
                engine.registerObject(line);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Edge must start at a vertex.");
        } 
    }

    @Override
    public void onDrag(int xCurrent, int yCurrent) {
        if(legalStart) {
            line.setLasts(xCurrent, yCurrent);
        }
    }

    @Override
    public void onRelease(int xReleased, int yReleased) {
        if(legalStart) {
            engine.deregisterObject(line);

            try {
                Vertex a = graph.getVertexByLocation(xPressed, yPressed);
                Vertex b = graph.getVertexByLocation(xReleased, yReleased);
                engine.registerObject(graph.newEdge(a, b));
                
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Edge not allowed");
            }
            legalStart = false;
        }
    }
}