package visualizer.graph.coloring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import visualizer.graph.Vertex;

public final class Coloring {
    private final HashMap<Integer, ArrayList<Integer>> coloringMap = new HashMap<>();
    private int currentSum = 0;

    public void saveColoring(ArrayList<Vertex> vertices) {
        currentSum = 0;
        coloringMap.clear();
        for(Vertex vertex : vertices) {
            coloringMap.put(vertex.getVertexID(), new ArrayList<>(vertex.getColors()));
            currentSum += Collections.max(vertex.getColors());
        }
    }

    public void loadColoring(ArrayList<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            vertex.setColors(coloringMap.get(vertex.getVertexID()));
        }
    }

    public void saveColors(Vertex vertex) {
        if(coloringMap.containsKey(vertex.getVertexID())) {
            currentSum -= Collections.max(vertex.getColors());
            coloringMap.remove(vertex.getVertexID());
        }
        coloringMap.put(vertex.getVertexID(), new ArrayList<>(vertex.getColors()));
        currentSum += Collections.max(vertex.getColors());
    }

    public ArrayList<Integer> getColors(Vertex vertex) {
        return coloringMap.get(vertex.getVertexID());
    }

    public int getCurrentSum() {
        return currentSum;
    }
} 