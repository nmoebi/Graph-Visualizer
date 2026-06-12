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
            int index = bestColoringsCounter % bestColorings.size();
            Coloring bestColoring = bestColorings.get(index);
            System.out.println(bestColorings.size() + " colorings found: showing #" + (index+1) + "/" + bestColorings.size() + ", " + bestColoring.getMaxColor() + " colors used, sum = " + bestColoring.getCurrentSum());
            colorGraph(bestColoring);
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
        currentColoring.saveColoring(new ArrayList<>(graph.getVertices()));
        return currentColoring;
    }

    public void saveBestColoring(Coloring coloring) {
        bestColorings.add(coloring);
    }

    public int getBestColoringSum() {
        if(bestColorings.isEmpty())
            return Integer.MAX_VALUE;
        else
            return bestColorings.getFirst().getCurrentSum();
    }

    public int getBestMaxColor() {
        if(!bestColorings.isEmpty())
            return bestColorings.getFirst().getMaxColor();

        else 
            return -1;
    }

    public ArrayList<Coloring> getBestColorings() {
        return bestColorings;
    }
}