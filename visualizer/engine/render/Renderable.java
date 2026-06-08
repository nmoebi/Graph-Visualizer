package visualizer.engine.render;

import java.awt.Graphics2D;

public interface Renderable {
    void render(Graphics2D graphics);
    RenderLayer getLayer();
}