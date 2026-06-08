package visualizer.core;

import java.awt.Graphics2D;
import java.util.*;
import visualizer.engine.render.*;

public class VisualizerRenderManager implements RenderManager {

    private final Map<RenderLayer, List<Renderable>> buckets = new EnumMap<>(RenderLayer.class);

    public VisualizerRenderManager() {
        for(final RenderLayer layer : RenderLayer.values()) {
            buckets.put(layer, new ArrayList<>());
        }
    }

    @Override
    public void registerRenderable(final Renderable renderable) {
        buckets.get(renderable.getLayer()).add(renderable);
    }

    @Override
    public void deregisterRenderable(final Renderable renderable) {
        buckets.get(renderable.getLayer()).remove(renderable);
    }

    @Override
    public void render(final Graphics2D graphics) {
        for (final RenderLayer layer : RenderLayer.values()) {
            for (final Renderable renderable : buckets.get(layer)) {
                renderable.render(graphics);
            }
        }
    }
}