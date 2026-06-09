package visualizer.core;

import javax.swing.JFrame;
import visualizer.engine.VisEngine;
import visualizer.engine.config.Config;

public class VisualizerLauncher {
    public static void main(String[] args) {
        final int panelWidth = 1600;
        final VisualizerInputManager inputManager = new VisualizerInputManager();
        final Config config = new Config(panelWidth, 9*panelWidth/16);
        final VisEngine engine = new VisualizerEngine(inputManager, new VisualizerRenderManager(), new VisualizerStateManager(), config);
        final VisualizerPanel panel = new VisualizerPanel(engine, config);
        inputManager.installMouseListener(panel);

        initialiseJFrame(panel);
    }

    private static void initialiseJFrame(final VisualizerPanel panel) {
        final JFrame frame = new JFrame("Graph Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}