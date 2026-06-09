package visualizer.algorithm;

import visualizer.engine.VisEngine;
import visualizer.engine.states.EngineState;
import visualizer.graph.Graph;

public class AlgoManager {
    
    private VisEngine engine;
    private Graph graph;
    private SumColoringAlgorithm sumColoringAlgorithm;
    private int sleepDuration = 0;

    public void setEngine(VisEngine engine) {
        this.engine = engine;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setState(EngineState state) {
        engine.setEngineState(state);
    }

    public EngineState getEngineState() {
        return engine.getEngineState();
    }

    public void revertEngineState() {
        engine.revertEngineState();
    }
    
    public void initialiseAlgorithms() {
        sumColoringAlgorithm = new SumColoringAlgorithm(this, graph, sleepDuration);
    }

    public void runAlgorithm(Algorithm algo) {
        switch (algo) {
            case SUM_COLORING -> sumColoringAlgorithm();
        }
    }

    private void sumColoringAlgorithm() {
        sumColoringAlgorithm.colorGraph();
    }

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }
}