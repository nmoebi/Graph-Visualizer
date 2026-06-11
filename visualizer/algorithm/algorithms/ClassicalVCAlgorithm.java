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

public class ClassicalVCAlgorithm implements ColoringAlgorithm {

    private final Graph graph;
    private ArrayList<Vertex> sortedVertices;
    private HashMap<Vertex, HashSet<Vertex>> neighbors;

    private final ColoringManager coloringManager;
    private final AlgoManager algoManager;
    private Thread coloringThread;

    private int sleepDuration;
    private int n; //Size of sortedVertices
    private boolean running = false;

    private int bestMaxColor;

    public ClassicalVCAlgorithm(AlgoManager algoManager, Graph graph, int sleepDuration) {
        this.algoManager = algoManager;
        this.graph = graph;
        this.sleepDuration = sleepDuration;
        
        coloringManager = graph.getColoringManager();
    }

    @Override public void run() {
        running = true;
        classicalVCAlgo();
    }

    @Override public boolean stop() {
        running = false;
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
        bestMaxColor = graph.getMaxDegree()+1;
        
        for(Vertex vertex : sortedVertices) {
            vertex.setDefaultColors();
        }
    }

    private void acceptColoring(Coloring currentColoring) {
        int currentMaxColor = currentColoring.getMaxColor();

        if(currentMaxColor <= bestMaxColor) {
            
            if (currentMaxColor < bestMaxColor) {
                bestMaxColor = currentMaxColor;
                coloringManager.clearBestColorings();
            }
            coloringManager.saveBestColoring(currentColoring);
        }
    }

    private void classicalVCAlgo() {
        resetAlgoData();
        algoManager.setState(EngineState.RUNNING_ALGO);
        coloringThread = new Thread(() ->  {
            greedyColor();
            color(0);
            SwingUtilities.invokeLater(() -> {
                System.out.println("ClassicalVertexColoringAlgorithm finished with " + coloringManager.getBestColorings().size() + " colorings found");
                coloringManager.setNextBestColoring();
                algoManager.revertEngineState();
            });
        });
        coloringThread.start();
    }

    @SuppressWarnings("")
    private void color(int i) {

        if(!running || Thread.currentThread().isInterrupted()) {
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
        if(!running || Thread.currentThread().isInterrupted()) {
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
        coloringManager.clearBestColorings();
        
        for(Vertex v : sortedVertices)
            v.setDefaultColors();
    }

    public void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }
}