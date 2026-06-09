package visualizer.engine;

import java.awt.Graphics2D;
import visualizer.engine.states.EngineState;

public interface VisEngine {
    EngineState getEngineState();
    void setEngineState(EngineState state);
    void registerObject(Object object);
    void deregisterObject(Object object);
    void step();
    void render(Graphics2D graphics);
}
