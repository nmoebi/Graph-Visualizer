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

public abstract class ColoringAlgorithm implements ColoringAlgo {

    protected final Graph graph;
    protected ArrayList<Vertex> sortedVertices;
    protected HashMap<Vertex, HashSet<Vertex>> neighbors;

    protected final ColoringManager coloringManager;
    protected final AlgoManager algoManager;
    protected Thread coloringThread;

    protected int sleepDuration;
    protected int n; //Size of sortedVertices
   
    public ColoringAlgorithm(AlgoManager algoManager, Graph graph, int sleepDuration) {
        this.algoManager = algoManager;
        this.graph = graph;
        this.sleepDuration = sleepDuration;
        
        coloringManager = graph.getColoringManager();
    }

    @Override
    public abstract void runAlgo();

    @Override
    public abstract void acceptColoring(Coloring currentColoring); 

    @Override
    public void run() {
        algoManager.setState(EngineState.RUNNING_ALGO);
        resetAlgoData();
        coloringThread = new Thread(() ->  {
            runAlgo();
            SwingUtilities.invokeLater(() -> {
                if(algoManager.getEngineState() == EngineState.RUNNING_ALGO) {
                    System.out.println("------------------------");
                    System.out.println("ColoringAlgorithm finished");
                    System.out.println("-> Showcasing Best Colorings");
                    System.out.println("------------------------");
                }
                coloringManager.setNextBestColoring();
                algoManager.revertEngineState();
            });
        });
        coloringThread.start();
    }

    @Override
    public boolean stop() {
        algoManager.revertEngineState();
        if(coloringThread != null && coloringThread.isAlive()) {
            coloringThread.interrupt();
        }
        return true;
    }

    protected boolean isLegal(Vertex vertex) {
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

    @Override
    public void resetAlgoData() {
        sortedVertices = new ArrayList<>(graph.getVertices());
        sortedVertices.sort((vertex1, vertex2) -> Integer.compare(vertex2.getDegree(), vertex1.getDegree()));
        n = sortedVertices.size();

        neighbors = graph.getNeighborsMap();
        
        coloringManager.clearBestColorings();
        
        for(Vertex vertex : sortedVertices) {
            vertex.setDefaultColors();
        }
    }  

    protected void setSleepDuration(int sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    

    

    

    


    
}