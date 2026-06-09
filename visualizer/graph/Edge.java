package visualizer.graph;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import visualizer.engine.config.Colors;
import visualizer.engine.render.*;

public final class Edge implements Renderable {
    private final Vertex vertex1;
    private final Vertex vertex2;

    public Edge(Vertex vertex1, Vertex vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    //%%%%%%%%%% Renderable %%%%%%%%%%

    @Override
    public void render(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(4));
        graphics.setColor(Colors.BLACK);
        
        graphics.drawLine(
            vertex1.getXPosition(), vertex1.getYPosition(),
            vertex2.getXPosition(), vertex2.getYPosition()
        );
    }

    @Override 
    public RenderLayer getLayer() {
        return RenderLayer.EDGES;
    }

    //%%%%%%%%%% Getter %%%%%%%%%%
    public Vertex getVertex1() {
        return vertex1;
    }

    public Vertex getVertex2() {
        return vertex2;
    }
}