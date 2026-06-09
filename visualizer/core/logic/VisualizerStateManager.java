package visualizer.core.logic;

import visualizer.engine.states.EngineState;
import visualizer.engine.states.StateManager;


public class VisualizerStateManager implements StateManager {
    
    private final EngineState defaultState = EngineState.ADDING_VERTICES;
    private EngineState currentEngineState;
    private EngineState lastEngineState;

    public VisualizerStateManager() {
        setDefaultState();
    }

    private void printState() {
        System.out.println("Engine state changed to " + currentEngineState);
    }

    @Override
    public final void setDefaultState() {
        this.currentEngineState = defaultState;
        printState();
    }

    @Override
    public void setEngineState(EngineState engineState) {
        lastEngineState = currentEngineState;
        this.currentEngineState = engineState;
        printState();
    }

    @Override
    public EngineState getEngineState() {
        return currentEngineState;
    }

    @Override
    public void revertEngineState() {
        currentEngineState = lastEngineState;
        printState();
    }
}