package visualizer.engine.ui.uicomponents;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import visualizer.engine.config.Colors;
import visualizer.engine.config.Config;
import visualizer.engine.render.RenderLayer;
import visualizer.engine.render.Renderable;

public class Background implements Renderable {
    private final Config config;

    public Background(final Config config) {
        this.config = config;
        
    }

    @Override
    public void render(final Graphics2D graphics) {
        
        int width = config.getWidth();
        int height = config.getHeight();
        int maxSizeValue = Math.max(width, height);
        int gridSize = config.getGridSize();

        graphics.setColor(Colors.GRAY);
        graphics.setStroke(new BasicStroke(1));

        for (int i = gridSize; i <= maxSizeValue; i+=gridSize) {
            if(i < width) {
                graphics.drawLine(i, 0, i, height);
            }
            if(i < height) {
                graphics.drawLine(0, i, width, i);
            }
        }
    }

    @Override
    public RenderLayer getLayer() {
        return RenderLayer.BACKGROUND;
    }
}