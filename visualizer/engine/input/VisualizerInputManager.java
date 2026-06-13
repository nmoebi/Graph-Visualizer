package visualizer.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.event.MouseInputAdapter;
import visualizer.engine.VisEngine;
import visualizer.engine.input.handlers.AddEdgeHandler;
import visualizer.engine.input.handlers.AddVertexHandler;
import visualizer.engine.input.handlers.MoveVertexHandler;
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
        moveVertexHandler = new MoveVertexHandler(visEngine, graph);
    }

    @Override
    public void dispatchPress(int x, int y, int button) {
        switch(visEngine.getEngineState()) {
            case ADDING_VERTICES -> {
                if(button == MouseEvent.BUTTON1)
                    addVertexHandler.onPress(x, y);
            }
            case ADDING_EDGES -> {
                if(button == MouseEvent.BUTTON1)
                    addEdgeHandler.onPress(x, y);
            }
            
            case MOVE_VERTEX -> {
                if(button == MouseEvent.BUTTON1) 
                    moveVertexHandler.onPress(x, y);
                
                else if (button == MouseEvent.BUTTON2) {
                    moveVertexHandler.moveAllVertices();
                    moveVertexHandler.onPress(x, y);
                }
            }
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
    public void dispatchClick(int x, int y) { }

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
                dispatchPress(e.getX(), e.getY(), e.getButton());
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