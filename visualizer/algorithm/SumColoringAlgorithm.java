package visualizer.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.SwingUtilities;
import visualizer.engine.states.EngineState;
import visualizer.graph.Graph;
import visualizer.graph.Vertex;
import visualizer.graph.coloring.Coloring;
import visualizer.graph.coloring.ColoringManager;

public class SumColoringAlgorithm implements ColoringAlgorithm {
    
    private final Graph graph;
    private ArrayList<Vertex> sortedVertices;
    private HashMap<Vertex, HashSet<Vertex>> neighbors;

    private final ColoringManager coloringManager;
    private final AlgoManager algoManager;
    private Thread coloringThread;

    private int bestColoringSum;
    private int sumOfColorAmounts;
    private int sleepDuration;
    private int n; //Size of sortedVertices

    public SumColoringAlgorithm(AlgoManager algoManager, Graph graph, int sleepDuration) {
        this.algoManager = algoManager;
        this.graph = graph;
        this.sleepDuration = sleepDuration;
        
        coloringManager = graph.getColoringManager();
    }

    @Override public void colorGraph() {
        sumColoringAlgo();
    }

    private boolean isLegal(Vertex vertex) {
        
        for(int color : vertex.getColors()) {
            for(Vertex neighbor : neighbors.get(vertex)) {
                if(neighbor.getColors().contains(color)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetAlgoData() {
        sortedVertices = new ArrayList<>(graph.getVertices());
        sortedVertices.sort((vertex1, vertex2) -> Integer.compare(vertex2.getDegree(), vertex1.getDegree()));
        n = sortedVertices.size();

        neighbors = graph.getNeighborsMap();

        coloringManager.clearBestColorings();
        bestColoringSum = Integer.MAX_VALUE;
        
        sumOfColorAmounts = 0;
        for(Vertex vertex : sortedVertices) {
            vertex.setDefaultColors();
            sumOfColorAmounts += vertex.getColorAmount();
        }
    }

    private void acceptColoring() {
        Coloring currentColoring = coloringManager.getCurrentColoring();
        int coloringSum = currentColoring.getColoringSum();
        if(coloringSum <= bestColoringSum) {
            
            if (coloringSum < bestColoringSum) {
                coloringManager.clearBestColorings();
                bestColoringSum = coloringSum;
            }
            coloringManager.saveBestColoring(currentColoring);
        }
    }

    private void sumColoringAlgo() {
        resetAlgoData();
        algoManager.setState(EngineState.RUNNING_ALGO);
        coloringThread = new Thread(() ->  {
            color(0, 0);
            SwingUtilities.invokeLater(() -> {
                algoManager.revertEngineState();
                coloringManager.setNextBestColoring();
            });
        });
        coloringThread.start();
    }

    @SuppressWarnings("")
    private void color(int i, int currentSum) {
        if(algoManager.getEngineState() == EngineState.RUNNING_ALGO) {
            if(i < n) {
                Vertex v = sortedVertices.get(i);
                int minSum = bestColoringSum;
                int maxColor = (sumOfColorAmounts == n ? v.getDegree()+2 : sumOfColorAmounts);

                for(int x=1; x < maxColor; x++) {
                    
                    int nextSum = currentSum + x + v.getColorAmount()-1;
                    if(nextSum > minSum) {
                        v.setDefaultColors();
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
                acceptColoring();
            }
        }
        
    }

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }
}