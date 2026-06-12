package visualizer.algorithm.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.SwingUtilities;
import visualizer.algorithm.AlgoManager;
import visualizer.algorithm.ColoringAlgorithm;
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

    private int sumOfColorAmounts;
    private int sleepDuration;
    private int n; //Size of sortedVertices

    public SumColoringAlgorithm(AlgoManager algoManager, Graph graph, int sleepDuration) {
        this.algoManager = algoManager;
        this.graph = graph;
        this.sleepDuration = sleepDuration;
        
        coloringManager = graph.getColoringManager();
    }

    @Override public void run() {
        algoManager.setState(EngineState.RUNNING_ALGO);
        sumColoringAlgo();
    }

    @Override public boolean stop() {
        algoManager.revertEngineState();
        if(coloringThread != null && coloringThread.isAlive()) {
            coloringThread.interrupt();
        }
        return true;
    }

    private boolean isLegal(Vertex vertex) {
        if(vertex != null) {
            for(int color : vertex.getColors()) {
                for(Vertex neighbor : neighbors.get(vertex)) {
                    if(neighbor.getColors().contains(color)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    private void resetAlgoData() {
        sortedVertices = new ArrayList<>(graph.getVertices());
        sortedVertices.sort((vertex1, vertex2) -> Integer.compare(vertex2.getDegree(), vertex1.getDegree()));
        n = sortedVertices.size();

        neighbors = graph.getNeighborsMap();
        
        coloringManager.clearBestColorings();
        
        sumOfColorAmounts = 0;
        for(Vertex vertex : sortedVertices) {
            vertex.setDefaultColors();
            sumOfColorAmounts += vertex.getColorAmount();
        }
    }

    private void acceptColoring(Coloring currentColoring) {
        int bestColoringSum =  coloringManager.getBestColoringSum();
        int currentColoringSum = currentColoring.getCurrentSum();

        if(currentColoringSum <= bestColoringSum) {
            
            if (currentColoringSum < bestColoringSum) {
                coloringManager.clearBestColorings();
            }
            coloringManager.saveBestColoring(currentColoring);
        }
    }

    private void sumColoringAlgo() {
        resetAlgoData();
        coloringThread = new Thread(() ->  {
            color(0, 0);
            SwingUtilities.invokeLater(() -> {
                if(algoManager.getEngineState() == EngineState.RUNNING_ALGO) {
                    System.out.println("------------------------");
                    System.out.println("SumColoringAlgo finished");
                    System.out.println("-> Showcasing Best Colorings");
                    System.out.println("------------------------");
                }
                    
                    
                coloringManager.setNextBestColoring();
                algoManager.revertEngineState();
            });
        });
        coloringThread.start();
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

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }
}