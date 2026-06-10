package visualizer.core.swing;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import visualizer.algorithm.Algorithm;
import visualizer.engine.VisEngine;
import visualizer.engine.config.Colors;
import visualizer.engine.config.Config;
import visualizer.engine.states.EngineState;

public class ControlPanel extends JPanel implements ActionListener, Colors {

    private final VisEngine engine;

    private JButton addVerticesButton;
    private JButton addEdgesButton;
    private JButton moveVertexButton;
    private JButton resetButton;
    private JButton bestColoringsButton;
    private JButton sumColoringButton;

    
    

    public ControlPanel(final VisEngine engine, Config config) {
        super();
        this.engine = engine;

        setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
        setBackground(Colors.WHITE);
        setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Colors.GRAY));

        addComponents();
    }

    private void addComponents() {
        addVerticesButton();
        addEdgesButton();
        moveVertexButton();
        resetButton(); 
        bestColoringsButton();
        sumColoringButton();

        setBackgrounds(addVerticesButton);      
    }

    private void setBackgrounds(Component activeComponent) {
        for(Component component : this.getComponents()) {
            component.setBackground(Colors.INACTIVE_COLOR);
        }
        activeComponent.setBackground(Colors.ACTIVE_COLOR);
    }

        private void addVerticesButton() {
        addVerticesButton = new JButton("Add Vertices");
        addVerticesButton.addActionListener(e -> {
            changeState(EngineState.ADDING_VERTICES);
            setBackgrounds(addVerticesButton);
        });
        this.add(addVerticesButton);
    }

    private void addEdgesButton() {
        addEdgesButton = new JButton("Add Edges");
        addEdgesButton.addActionListener(e -> {
            changeState(EngineState.ADDING_EDGES);
            setBackgrounds(addEdgesButton);
        });
        this.add(addEdgesButton);
    }

    private void moveVertexButton() {
        moveVertexButton = new JButton("Move Vertex");
        moveVertexButton.addActionListener(e -> {
            changeState(EngineState.MOVE_VERTEX);
            setBackgrounds(moveVertexButton);
        });
        this.add(moveVertexButton);
    }

    private void resetButton() {
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            engine.reset();
            setBackgrounds(addVerticesButton);
        });
        this.add(resetButton);
    }

    private void bestColoringsButton() {
        bestColoringsButton = new JButton("Best Colorings");
        bestColoringsButton.addActionListener(e -> {
            engine.showBestColoring();
        });
        this.add(bestColoringsButton);
    }

    private void sumColoringButton() {
        sumColoringButton = new JButton("Sum Coloring");
        sumColoringButton.addActionListener(e -> {
            engine.runAlgorithm(Algorithm.SUM_COLORING);
        });
        this.add(sumColoringButton);
    }

    private void changeState(EngineState engineState) { 
        if(engine.getEngineState() != EngineState.RUNNING_ALGO) {
            engine.setEngineState(engineState);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();   
    }

    @Override
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
    }
}