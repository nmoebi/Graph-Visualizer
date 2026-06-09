package visualizer.engine;

import java.awt.Graphics2D;
import visualizer.algorithm.Algorithm;
import visualizer.engine.config.Config;
import visualizer.engine.states.EngineState;

public interface VisEngine {
    EngineState getEngineState();
    void setEngineState(EngineState state);
    void revertEngineState();
    void registerObject(Object object);
    void deregisterObject(Object object);
    void step();
    void render(Graphics2D graphics);
    void reset();
    Config getConfig();
    void runAlgorithm(Algorithm algorithm);
}
