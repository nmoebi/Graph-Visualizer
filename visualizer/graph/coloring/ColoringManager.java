package visualizer.graph.coloring;

import java.util.ArrayList;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;

public class ColoringManager {

    private int bestColoringsCounter = 0;
    private final Graph graph;

    public ColoringManager(Graph graph) {
        this.graph = graph;
    }
    ArrayList<Coloring> bestColorings = new ArrayList<>();

    public void setNextBestColoring() {
        if(!bestColorings.isEmpty()) {
            colorGraph(bestColorings.get(bestColoringsCounter % bestColorings.size()));
            bestColoringsCounter++;
        }
    }

    public void colorGraph(Coloring coloring) {
        for (Vertex vertex : graph.getVertices()) {
            vertex.setColors(coloring.getColors(vertex));
        }
    }

    public void clearBestColorings() {
        bestColorings.clear();
        bestColoringsCounter = 0;
    }

    public Coloring getCurrentColoring() {
        Coloring currentColoring = new Coloring();
        currentColoring.saveColoring(graph.getVertices());
        return currentColoring;
    }

    public void saveBestColoring(Coloring coloring) {
        bestColorings.add(coloring);
    }

    public ArrayList<Coloring> getBestColorings() {
        return bestColorings;
    }
}