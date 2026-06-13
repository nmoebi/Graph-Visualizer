package visualizer.engine.input;

import visualizer.engine.VisEngine;
import visualizer.graph.Graph;

public interface InputManager {
    
    void installMouseListener(java.awt.Component target);
    void setEngine(VisEngine visualizerEngine);
    void setGraph(Graph graph);
    void addHandlers();

    void dispatchPress(int x, int y, int button);
    void dispatchRelease(int x, int y);
    void dispatchClick(int x, int y);
    void dispatchDrag(int x, int y);
}