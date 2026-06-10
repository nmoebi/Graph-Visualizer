package visualizer.graph;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import visualizer.engine.config.Colors;
import visualizer.engine.render.RenderLayer;
import visualizer.engine.render.Renderable;

public final class Vertex implements Renderable, Comparable<Vertex> {
    
    private final int vertexID;
    
    private int vertexSize = 50; 
    private int xPosition;
    private int yPosition;
    private int degree;

    private ArrayList<Integer> colors = new ArrayList<>();
    private int colorAmount = 1;

    public Vertex(int vertexID, int xPosition, int yPosition) {
        this.vertexID = vertexID;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.degree = 0;
        setDefaultColors();
    }

    public void move(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override public String toString() {
        return "V" + vertexID;
    }

    public void setDefaultColors() {
        colors.clear();
        for (int i = 0; i < colorAmount; i++) {
            colors.add(0);
        }
    }

    //%%%%%%%%%% Renderable %%%%%%%%%%

    @Override
    public void render(Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(4));
        
        graphics.setColor(Colors.VERTEX_COLORS[colors.getLast() % Colors.VERTEX_COLORS.length]);
        graphics.fillOval(xPosition-vertexSize/2, yPosition-vertexSize/2, vertexSize, vertexSize);

        graphics.setColor(Colors.BLACK);
        graphics.drawOval(xPosition-vertexSize/2, yPosition-vertexSize/2, vertexSize, vertexSize);
        
        if(this.colorAmount == 1) {
            //VertexID beside Vertex
            graphics.setFont(new Font("Arial", Font.BOLD, 30));
            graphics.setColor(Colors.GRAY);
            graphics.drawString(this.toString(), xPosition+vertexSize*3/5, yPosition+vertexSize*4/5);

            //Vertex-Color on Vertex
            if(this.getMaxColor() > 0) {
                graphics.setColor(Colors.BLACK);
                graphics.setFont(new Font("Arial", Font.BOLD, 40));
                graphics.drawString(colors.getFirst().toString() , xPosition-10,  yPosition+13 );
            }   
        }
        else {
            //VertexID on Vertex
            graphics.setFont(new Font("Arial", Font.BOLD, 30));
            graphics.setColor(Colors.BLACK);
            graphics.drawString(this.toString(), xPosition-17,  yPosition+13);

            //Vertex-Colors beside Vertex
            graphics.setColor(Colors.BLACK);
            graphics.setFont(new Font("Arial", Font.BOLD, 40));
            graphics.drawString(getColorsString(), xPosition, yPosition+vertexSize+10);
        }
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

    public int getColorAmount() {
        return colorAmount;
    }

    public int getDegree() {
        return degree;
    }

    public int getMaxColor() {
        return Collections.max(this.colors);
    }

    private String getColorsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
            for(int color : colors) {
                sb.append(color).append(" ");
            }
            sb.replace(sb.length()-1, sb.length(), "");
        sb.append("}");

        return sb.toString();
    }

    //%%%%%%%%%% Setter %%%%%%%%%%

    public void setColors(ArrayList<Integer> colors) {
        this.colors = colors;
    }

    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }

    public void setVertexSize(int vertexSize) {
        this.vertexSize = vertexSize;
    }

    public void setColorAmount(int colorAmount) {
        this.colorAmount = colorAmount;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}