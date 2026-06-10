package visualizer.graph.coloring;

import java.util.ArrayList;
import java.util.HashMap;
import visualizer.graph.Vertex;

public final class Coloring {
    private final HashMap<Integer, ArrayList<Integer>> coloringMap = new HashMap<>();
    private int currentSum = 0;
    private int maxColor = 0;

    public void saveColoring(ArrayList<Vertex> vertices) {
        currentSum = 0;
        maxColor = 0;
        coloringMap.clear();

        for(Vertex vertex : vertices) {
            ArrayList<Integer> colors = new ArrayList<>(vertex.getColors());
            coloringMap.put(vertex.getVertexID(), colors);

            if(!colors.isEmpty()) {
                int maxVertexColor = 0;
                for(int color : colors) {
                    if(color > maxVertexColor) {
                        maxVertexColor = color;
                    }
                }
                currentSum += maxVertexColor;
                if(maxVertexColor > maxColor) {
                    maxColor = maxVertexColor;
                }

            }
        }
    }

    public void loadColoring(ArrayList<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            vertex.setColors(coloringMap.get(vertex.getVertexID()));
        }
    }

    public void saveColors(Vertex vertex) {
        int maxVertexColor = vertex.getColors().getLast();

        if(coloringMap.containsKey(vertex.getVertexID())) {
            currentSum -= maxVertexColor;
            coloringMap.remove(vertex.getVertexID());
        }
        coloringMap.put(vertex.getVertexID(), new ArrayList<>(vertex.getColors()));
        currentSum += maxVertexColor;

        maxColor = getMaxColor();
    }

    public ArrayList<Integer> getColors(Vertex vertex) {
        return coloringMap.get(vertex.getVertexID());
    }

    public int getCurrentSum() {
        return currentSum;
    }

    public int getMaxColor() {
        maxColor = 0;
        for(ArrayList<Integer> colors : coloringMap.values()) {
            int color = colors.getLast();
            if(color > maxColor) {
                maxColor = color;
            }
        }
        return maxColor;
    }
} 