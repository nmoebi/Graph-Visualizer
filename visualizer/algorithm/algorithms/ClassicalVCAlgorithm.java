package visualizer.algorithm.algorithms;

import java.util.ArrayList;
import visualizer.algorithm.AlgoManager;
import visualizer.algorithm.ColoringAlgorithm;
import visualizer.engine.states.EngineState;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.graph.coloring.Coloring;

public class ClassicalVCAlgorithm extends ColoringAlgorithm {

    private int bestMaxColor;

    public ClassicalVCAlgorithm(AlgoManager algoManager, Graph graph, int sleepDuration) {
        super(algoManager, graph, sleepDuration);
    }

    @Override
    public void resetAlgoData() {
        
        super.resetAlgoData();
        bestMaxColor = graph.getMaxDegree()+1;
    }

    @Override
    public void acceptColoring(Coloring currentColoring) {
        int currentMaxColor = currentColoring.getMaxColor();

        if(currentMaxColor <= bestMaxColor) {
            
            if (currentMaxColor < bestMaxColor) {
                bestMaxColor = currentMaxColor;
                coloringManager.clearBestColorings();
            }
            coloringManager.saveBestColoring(currentColoring);
        }
    }

    @Override
    public void runAlgo() {
        
        greedyColor();
        color(0);     
    }

    @SuppressWarnings("")
    private void color(int i) {

        if(algoManager.getEngineState() != EngineState.RUNNING_ALGO || Thread.currentThread().isInterrupted()) {
            System.out.println("Algo interrupted");
            return;
        }

        if(i < n) {
            Vertex v = sortedVertices.get(i);

            for(int x=1; x <= bestMaxColor; x++) {
                
                if (x + v.getColorAmount() - 1 > bestMaxColor) {
                    break; 
                }       

                ArrayList<Integer> colors = new ArrayList<>(v.getColorAmount());
                for (int y = 0; y < v.getColorAmount(); y++) {
                    colors.add(x+y);   
                }

                v.setColors(colors);
                
                try {
                    Thread.sleep(sleepDuration);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println(e.getMessage());
                    return;
                }

                if(isLegal(v)) {
                    color(i+1);
                }
            }
            v.setDefaultColors();
        }
        else {
            acceptColoring(coloringManager.getCurrentColoring());
        }        
    }

    private void greedyColor() {
        if(algoManager.getEngineState() != EngineState.RUNNING_ALGO || Thread.currentThread().isInterrupted()) {
            return;
        }

        for(Vertex v : sortedVertices) {
            int x = 1;
            while(true) {
                ArrayList<Integer> colors = new ArrayList<>(v.getColorAmount());
                for (int y = 0; y < v.getColorAmount(); y++) {
                    colors.add(x+y);   
                }
                v.setColors(colors);
                
                if(isLegal(v)) { 
                    break;
                }
                x++;
            }
            
        }
        acceptColoring(coloringManager.getCurrentColoring());
        bestMaxColor = coloringManager.getBestMaxColor();
        
        coloringManager.clearBestColorings();
        for(Vertex v : sortedVertices)
            v.setDefaultColors();
    }
}