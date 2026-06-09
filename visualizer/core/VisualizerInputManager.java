package visualizer.core;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import visualizer.engine.InputManager;
import visualizer.engine.VisEngine;
import visualizer.engine.handlers.AddEdgeHandler;
import visualizer.engine.handlers.AddVertexHandler;
import visualizer.engine.states.EngineState;
import visualizer.graph.Graph;

public class VisualizerInputManager implements InputManager {
    private VisEngine visEngine;
    private Graph graph;

    int xPressed, yPressed;
    int xReleased, yReleased;

    AddVertexHandler addVertexHandler;
    AddEdgeHandler AddEdgeHandler;

    @Override
    public void setEngine(VisEngine visEngine) {
        this.visEngine = visEngine;
    }

    @Override 
    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public void addHandlers() {
        addVertexHandler = new AddVertexHandler(visEngine, graph);
        AddEdgeHandler = new AddEdgeHandler(visEngine, graph);
    }

    @Override 
    public void handleInput() {
        
        switch(visEngine.getEngineState()) {
            case ADDING_VERTICES -> addVertexHandler.handleInput(xPressed, yPressed, xReleased, yReleased);
            case ADDING_EDGES -> AddEdgeHandler.handleInput(xPressed, yPressed, xReleased, yReleased);
        }
    }

    @Override
    public void installMouseListener(java.awt.Component target) {
        target.addMouseListener(new MouseInputAdapter() {
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override 
            public void mousePressed(MouseEvent e) {
                xPressed = e.getX();
                yPressed = e.getY(); 
                System.out.println("Pressed: " + xPressed + ", " + yPressed);
                if(visEngine.getEngineState() == EngineState.ADDING_VERTICES)
                    handleInput();
            }

            @Override 
            public void mouseReleased(MouseEvent e) {
                xReleased = e.getX();
                yReleased = e.getY();
                System.out.println("Released: " + xReleased + ", " + yReleased); 
                if(visEngine.getEngineState() == EngineState.ADDING_EDGES)
                    handleInput();
            }
        });
    }
}