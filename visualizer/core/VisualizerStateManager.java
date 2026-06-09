package visualizer.core;

import visualizer.engine.states.EngineState;
import visualizer.engine.states.StateManager;


public class VisualizerStateManager implements StateManager {
    
    private EngineState engineState;

    public VisualizerStateManager() {
        setDefaultState();
    }

    @Override
    public final void setDefaultState() {
        this.engineState = EngineState.ADDING_VERTICES;
    }

    @Override
    public void setEngineState(EngineState engineState) {
        this.engineState = engineState;
    }

    @Override
    public EngineState getEngineState() {
        return engineState;
    }

}