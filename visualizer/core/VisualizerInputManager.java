package visualizer.core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.event.MouseInputAdapter;
import visualizer.engine.InputManager;
import visualizer.engine.VisEngine;
import visualizer.engine.handlers.AddEdgeHandler;
import visualizer.engine.handlers.AddVertexHandler;
import visualizer.engine.handlers.MoveVertexHandler;
import visualizer.graph.Graph;

public class VisualizerInputManager implements InputManager {
    private VisEngine visEngine;
    private Graph graph;

    AddVertexHandler addVertexHandler;
    AddEdgeHandler addEdgeHandler;
    MoveVertexHandler moveVertexHandler;

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
        addEdgeHandler = new AddEdgeHandler(visEngine, graph);
        moveVertexHandler = new MoveVertexHandler(graph);
    }

    @Override
    public void dispatchPress(int x, int y) {
        switch(visEngine.getEngineState()) {
            case ADDING_VERTICES -> addVertexHandler.onPress(x, y);
            case ADDING_EDGES -> addEdgeHandler.onPress(x, y);
            case MOVE_VERTEX -> moveVertexHandler.onPress(x, y);
        }
    }

    @Override
    public void dispatchRelease(int x, int y) {
        switch(visEngine.getEngineState()) {
            case ADDING_EDGES -> addEdgeHandler.onRelease(x, y);
            case MOVE_VERTEX -> moveVertexHandler.onRelease(x, y);
        }
    }

    @Override
    public void dispatchClick(int x, int y) {
        //switch(visEngine.getEngineState()) {}
    }

    @Override
    public void dispatchDrag(int x, int y) {
        switch(visEngine.getEngineState()) {
            case ADDING_EDGES -> addEdgeHandler.onDrag(x, y);
            case MOVE_VERTEX -> moveVertexHandler.onDrag(x, y);
        }
    }

    @Override
    public void installMouseListener(java.awt.Component target) {
        target.addMouseListener(new MouseInputAdapter() {
            
            @Override 
            public void mouseClicked(MouseEvent e) {
                dispatchClick(e.getX(), e.getY());
            }

            @Override 
            public void mousePressed(MouseEvent e) {
                dispatchPress(e.getX(), e.getY());
            }

            @Override 
            public void mouseReleased(MouseEvent e) {
                dispatchRelease(e.getX(), e.getY());
            }
        });

        target.addMouseMotionListener(new MouseMotionAdapter() {
            @Override 
            public void mouseDragged(MouseEvent e) {
                dispatchDrag(e.getX(), e.getY());
            }
        });
    }
}