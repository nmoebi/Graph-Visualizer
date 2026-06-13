package visualizer.algorithm;

import visualizer.graph.coloring.Coloring;

public interface ColoringAlgo {
    void run();
    boolean stop();
    void runAlgo();
    void acceptColoring(Coloring currentColoring);
    void resetAlgoData();
}