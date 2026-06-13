package visualizer.engine.input.handlers;

import visualizer.engine.VisEngine;
import visualizer.engine.config.Config;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

public class MoveVertexHandler implements InteractionHandler {
    
    private final Graph graph;
    Vertex vertex;
    private boolean vertexGrabbed = false;
    private boolean moveAllVertices = false;
    
    private final Config config;

    private int lastX;
    private int lastY;

    public MoveVertexHandler(VisEngine visEngine, Graph graph) {
        this.graph = graph;
        this.config = visEngine.getConfig();
    }

    public void moveAllVertices() {
        this.moveAllVertices = true;
    }

    @Override public void onPress(int x, int y) {
        if(moveAllVertices) {
            lastX = x;
            lastY = y;
        }
        else {
            try {
                vertex = graph.getVertexByLocation(x, y);
                vertexGrabbed = true;
            } catch (Exception e) {
                System.out.println("Error: no vertex here");
            }
        }
    }

    @Override public void onDrag(int x, int y) {
        if (moveAllVertices) {
            int diffX = x-lastX;
            int diffY = y-lastY;
            
            for(Vertex v : graph.getVertices()) {

                int newX = v.getXPosition() + diffX;
                int newY = v.getYPosition() + diffY;

                v.setPosition(newX, newY);
            }
            
            lastX = x;
            lastY = y;
        }
        else if(vertexGrabbed) {
            vertex.setPosition(x, y);
        }
    }

    @Override
    public void onRelease(int x, int y) {
        int gridSize = config.getGridSize(); 
        
        if (moveAllVertices) {

            int diffX = x-lastX;
            int diffY = y-lastY; 
            
            for(Vertex v : graph.getVertices()) {    

                int newX = (int) (Math.round((double) (v.getXPosition() + diffX) / gridSize) * gridSize);
                int newY = (int) (Math.round((double) (v.getYPosition() + diffY) / gridSize) * gridSize);
                
                v.setPosition(newX, newY);
            }  
            moveAllVertices = false;
        }
        else if(vertexGrabbed) {
            x = (int) (Math.round((double) x / gridSize) * gridSize);
            y = (int) (Math.round((double) y / gridSize) * gridSize);

            vertex.setPosition(x, y);
            vertexGrabbed = false;
        }
    }
}