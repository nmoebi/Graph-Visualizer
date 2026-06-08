package visualizer.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import visualizer.engine.render.RenderLayer;
import visualizer.engine.render.Renderable;

public final class Vertex implements Renderable, Comparable<Vertex> {
    
    private final int vertexID;
    private final int vertexSize = 50;
    
    private int xPosition;
    private int yPosition;

    private ArrayList<Integer> colors = new ArrayList<>();

    public Vertex(int vertexID, int xPosition, int yPosition) {
        this.vertexID = vertexID;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public void move(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    //%%%%%%%%%% Renderable %%%%%%%%%%

    @Override
    public void render(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(3));
        
        graphics.setColor(Color.white);
        graphics.fillOval(xPosition-vertexSize/2, yPosition-vertexSize/2, vertexSize, vertexSize);

        graphics.setColor(Color.BLACK);
        graphics.drawOval(xPosition-vertexSize/2, yPosition-vertexSize/2, vertexSize, vertexSize);
    }

    @Override 
    public RenderLayer getLayer() {
        return RenderLayer.VERTICES;
    }

    //%%%%%%%%%% Compareable %%%%%%%%%%

    @Override
    public int compareTo(Vertex other) {
        return Integer.compare(this.getVertexID(), other.getVertexID());
    }

    //%%%%%%%%%% Getter %%%%%%%%%%

    public int getVertexID() {
        return vertexID;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }

    public int getVertexSize() {
        return vertexSize;
    }

    //%%%%%%%%%% Setter %%%%%%%%%%

    public void setColors(ArrayList<Integer> colors) {
        this.colors = colors;
    }
}