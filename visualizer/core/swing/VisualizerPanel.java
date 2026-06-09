package visualizer.core.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import visualizer.engine.VisEngine;
import visualizer.engine.config.Config;

public class VisualizerPanel extends JPanel implements ActionListener {

    public static final int FRAMES_PER_SECOND= 60;
    public static final int MS_PER_FRAME     = 1000 / FRAMES_PER_SECOND;

    private final VisEngine engine;
    private final Timer timer;
    
    public VisualizerPanel(final VisEngine engine, final Config config) {
        super();
        this.engine = engine;
        
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(config.getWidth(), config.getHeight()));
        setBackground(java.awt.Color.white);

        timer = new Timer(MS_PER_FRAME, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();   
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        engine.render(graphics2D);
        graphics2D.dispose();
    }
}