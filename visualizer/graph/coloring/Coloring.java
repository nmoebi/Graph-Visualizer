package visualizer.graph.coloring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import visualizer.graph.Vertex;

public final class Coloring {
    private final HashMap<Integer, ArrayList<Integer>> coloringMap = new HashMap<>();

    public void saveColoring(ArrayList<Vertex> vertices) {
        for(Vertex vertex : vertices) {
            coloringMap.put(vertex.getVertexID(), new ArrayList<>(vertex.getColors()));
        }
    }

    public void loadColoring(ArrayList<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            vertex.setColors(coloringMap.get(vertex.getVertexID()));
        }
    }

    public void saveColors(Vertex vertex) {
        coloringMap.put(vertex.getVertexID(), new ArrayList<>(vertex.getColors()));
    }

    public ArrayList<Integer> getColors(Vertex vertex) {
        return coloringMap.get(vertex.getVertexID());
    }

    public int getColoringSum() {
        int sum = 0;
        for(ArrayList<Integer> colors : coloringMap.values()) {
            if(colors != null && !colors.isEmpty()) {
                sum += Collections.max(colors);
            }
        }
        return sum;
    }
} 