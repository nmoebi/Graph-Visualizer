package visualizer.algorithm.algorithms;

import java.util.ArrayList;
import visualizer.algorithm.AlgoManager;
import visualizer.algorithm.ColoringAlgorithm;
import visualizer.engine.states.EngineState;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.graph.coloring.Coloring;

public class SumColoringAlgorithm extends ColoringAlgorithm{
    
    int sumOfColorAmounts;

    public SumColoringAlgorithm(AlgoManager algoManager, Graph graph, int sleepDuration) {
        super(algoManager, graph, sleepDuration);
    }

    @Override
    public void resetAlgoData() {
        
        super.resetAlgoData();

        sumOfColorAmounts = 0;
        for(Vertex vertex : sortedVertices) {
            sumOfColorAmounts += vertex.getColorAmount();
        }
    }

    @Override
    public void acceptColoring(Coloring currentColoring) {
        int bestColoringSum =  coloringManager.getBestColoringSum();
        int currentColoringSum = currentColoring.getCurrentSum();

        if(currentColoringSum <= bestColoringSum) {
            
            if (currentColoringSum < bestColoringSum) {
                coloringManager.clearBestColorings();
            }
            coloringManager.saveBestColoring(currentColoring);
        }
    }

    @Override
    public void runAlgo() {
        color(0, 0);
    }

    @SuppressWarnings("")
    private void color(int i, int currentSum) {

        if(algoManager.getEngineState() != EngineState.RUNNING_ALGO  || Thread.currentThread().isInterrupted()) {
            System.out.println("Algo interrupted");
            return;
        }

        if(i < n) {
            Vertex v = sortedVertices.get(i);

            int minSum = coloringManager.getBestColoringSum();
            int maxColor = (sumOfColorAmounts == n ? v.getDegree()+1 : sumOfColorAmounts);

            for(int x=1; x <= maxColor; x++) {
                
                int nextSum = currentSum + x + v.getColorAmount()-1;
                
                if(nextSum > minSum) {
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
                }

                if(isLegal(v)) {
                    color(i+1, nextSum);
                }
            }
            v.setDefaultColors();
        }
        else {
            acceptColoring(coloringManager.getCurrentColoring());
        }        
    }
}