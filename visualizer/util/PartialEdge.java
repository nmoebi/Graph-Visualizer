package visualizer.util;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import visualizer.engine.config.Colors;
import visualizer.engine.render.RenderLayer;
import visualizer.engine.render.Renderable;

public class PartialEdge implements Renderable {

    private int xFirst, yFirst;
    private int xLast, yLast;

    public void setFirsts(int xFirst, int yFirst) {
        this.xFirst = xFirst;
        this.yFirst = yFirst;
    }

    public void setLasts(int xLast, int yLast) {
        this.xLast = xLast;
        this.yLast = yLast;
    }

   

    @Override
    public void render(Graphics2D graphics) {
        graphics.setColor(Colors.GRAY);
        graphics.setStroke(new BasicStroke(3));

        graphics.drawLine(xFirst, yFirst, xLast, yLast);
    }

    @Override
    public RenderLayer getLayer() {
        return RenderLayer.EDGES;
    }
}