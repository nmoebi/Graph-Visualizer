package visualizer.algorithm;

import visualizer.algorithm.algorithms.ClassicalVCAlgorithm;
import visualizer.algorithm.algorithms.SumColoringAlgorithm;
import visualizer.engine.VisEngine;
import visualizer.engine.states.EngineState;
import visualizer.graph.Graph;

public class AlgoManager {
    
    private VisEngine engine;
    private Graph graph;
    private ClassicalVCAlgorithm classicalVCAlgorithm;
    private SumColoringAlgorithm sumColoringAlgorithm;
    private int sleepDuration = 10;

    ColoringAlgo activeAlgorithm;

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
    
    public boolean stopAlgo() {
        return activeAlgorithm.stop();
    }

    public void initialiseAlgorithms() {
        sumColoringAlgorithm = new SumColoringAlgorithm(this, graph, sleepDuration);
        classicalVCAlgorithm = new ClassicalVCAlgorithm(this, graph, sleepDuration);
    }

    public void runAlgorithm(Algorithm algo) {
        switch (algo) {
            case SUM_COLORING -> activeAlgorithm = sumColoringAlgorithm;
            case CLASSICAL_VC -> activeAlgorithm = classicalVCAlgorithm;
        }
        activeAlgorithm.run();
    }

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }
}