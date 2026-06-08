package visualizer.core;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import visualizer.engine.InputManager;
import visualizer.engine.VisEngine;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

public class VisualizerInputManager implements InputManager {
    private VisEngine visEngine;
    private Graph graph;

    int xPosition;
    int yPosition;

    @Override
    public void setEngine(VisEngine visEngine) {
        this.visEngine = visEngine;
    }

    @Override 
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override 
    public void handleInput() {
        if(true) {  //add states later
            Vertex v = graph.newVertex(xPosition, yPosition);
            visEngine.registerObject(v);
        }
    }

    @Override
    public void installMouseListener(java.awt.Component target) {
        target.addMouseListener(new MouseInputAdapter() {
            
            @Override 
            public void mousePressed(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
                System.out.println(xPosition + ", " + yPosition); 
                handleInput();
            }
        });
    }
}