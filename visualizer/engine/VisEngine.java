package visualizer.engine;

import java.awt.Graphics2D;

public interface VisEngine {
    void registerObject(Object object);
    void deregisterObject(Object object);
    void step();
    void render(Graphics2D graphics);
}
