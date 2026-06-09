package visualizer.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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

    @Override public String toString() {
        return "V" + vertexID;
    }

    //%%%%%%%%%% Renderable %%%%%%%%%%

    @Override
    public void render(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(4));
        
        graphics.setColor(Color.white);
        graphics.fillOval(xPosition-vertexSize/2, yPosition-vertexSize/2, vertexSize, vertexSize);

        graphics.setColor(Color.black);
        graphics.drawOval(xPosition-vertexSize/2, yPosition-vertexSize/2, vertexSize, vertexSize);

        graphics.setColor(Color.lightGray);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));
        graphics.drawString(this.toString(), xPosition+vertexSize*3/5, yPosition+vertexSize*4/5);
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

    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }
}