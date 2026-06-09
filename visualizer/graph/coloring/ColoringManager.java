package visualizer.graph.coloring;

import java.util.ArrayList;

public class ColoringManager {

    private int bestColoringCounter = 0;
    private int bestColoringSum = Integer.MAX_VALUE;

    Coloring currentColoring = new Coloring();
    ArrayList<Coloring> bestColorings = new ArrayList<>();

    public Coloring getCurrentColoring() {
        return currentColoring;
    }

    public Coloring getNextBestColoring() {
        return bestColorings.get(bestColoringCounter++ % bestColorings.size());
    }

    public void saveBestColoring() {
        if(currentColoring.getColoringSum() <= bestColoringSum) {
            
            if (currentColoring.getColoringSum() < bestColoringSum) {
                bestColorings.clear();
            }

            bestColorings.add(currentColoring);
        }
    }
}