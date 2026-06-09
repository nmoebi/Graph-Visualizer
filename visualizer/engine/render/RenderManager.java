package visualizer.engine.render;

import java.awt.Graphics2D;

public interface RenderManager {
    void registerRenderable(Renderable renderable);
    void deregisterRenderable(Renderable renderable);
    void render(Graphics2D graphics);
    void reset();
}
