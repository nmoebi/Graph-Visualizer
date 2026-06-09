package visualizer.engine.states;

public interface StateManager {
    EngineState getEngineState();
    void setEngineState(EngineState state);
    void setDefaultState();
}