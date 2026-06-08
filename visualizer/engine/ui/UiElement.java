package visualizer.engine.ui;

import visualizer.engine.render.RenderLayer;
import visualizer.engine.render.Renderable;

public abstract class UiElement implements Renderable {
    protected int xPosition;
    protected int yPosition;

    public UiElement(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public RenderLayer getLayer() {
        return RenderLayer.USER_INTERFACE;
    }
}
