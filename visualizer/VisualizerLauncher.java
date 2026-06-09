package visualizer;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import visualizer.core.logic.VisualizerEngine;
import visualizer.core.logic.VisualizerInputManager;
import visualizer.core.logic.VisualizerRenderManager;
import visualizer.core.logic.VisualizerStateManager;
import visualizer.core.swing.ControlPanel;
import visualizer.core.swing.VisualizerPanel;
import visualizer.engine.VisEngine;
import visualizer.engine.config.Config;

public class VisualizerLauncher {
    public static void main(String[] args) {
        final int panelWidth = 1600;
        final VisualizerInputManager inputManager = new VisualizerInputManager();
        final Config config = new Config(panelWidth, 9*panelWidth/16);
        final VisEngine engine = new VisualizerEngine(inputManager, new VisualizerRenderManager(), new VisualizerStateManager(), config);
        final VisualizerPanel drawPanel = new VisualizerPanel(engine, config);
            inputManager.installMouseListener(drawPanel);

        final ControlPanel controlPanel = new ControlPanel(engine, config);

        initialiseJFrame(drawPanel, controlPanel);
    }

    private static void initialiseJFrame(final VisualizerPanel panel, final ControlPanel controlPanel) {
        final JFrame frame = new JFrame("Graph Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}