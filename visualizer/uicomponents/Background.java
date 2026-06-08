package visualizer.uicomponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import visualizer.engine.config.Config;
import visualizer.engine.render.RenderLayer;
import visualizer.engine.render.Renderable;

public class Background implements Renderable {
    private final int width;
    private final int height;

    private final int maxSizeValue;
    private final int gridSize = 50;

    public Background(final Config config) {
        this.width = config.getWidth();
        this.height = config.getHeight();
        this.maxSizeValue = Math.max(width, height);
    }

    @Override
    public void render(final Graphics2D graphics) {
        graphics.setColor(Color.lightGray);
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