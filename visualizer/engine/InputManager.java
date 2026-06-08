package visualizer.engine;

import visualizer.graph.Graph;

public interface InputManager {
    void installMouseListener(java.awt.Component target);
    void handleInput();
    void setEngine(VisEngine visualizerEngine);
    void setGraph(Graph graph);
}