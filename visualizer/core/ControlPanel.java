package visualizer.core;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import visualizer.engine.VisEngine;
import visualizer.engine.config.Config;
import visualizer.engine.states.EngineState;

public class ControlPanel extends JPanel implements ActionListener {

    private final VisEngine engine;
    private final Color activeColor = Color.green;
    private final Color defaultColor = Color.lightGray;
    private final Color backgroundColor = Color.white;

    public ControlPanel(final VisEngine engine, Config config) {
        super();
        this.engine = engine;

        setLayout(new FlowLayout(FlowLayout.CENTER, 12, 12));
        setBackground(backgroundColor);
        setBorder(BorderFactory.createLineBorder(Color.lightGray, 2));

        addComponents();
    }

    private void addComponents() {
        addVerticesButton();
        addEdgesButton();
        moveVertexButton();
    }

    private void setBackgrounds(Component activeComponent) {
        for(Component component : this.getComponents()) {
            component.setBackground(defaultColor);
        }
        activeComponent.setBackground(activeColor);
    }

        private void addVerticesButton() {
        JButton addVerticesButton = new JButton("Add Vertices");
        addVerticesButton.setBackground(activeColor);
        addVerticesButton.addActionListener(e -> {
            engine.setEngineState(EngineState.ADDING_VERTICES);
            setBackgrounds(addVerticesButton);
        });
        this.add(addVerticesButton);
    }

    private void addEdgesButton() {
        JButton addEdgesButton = new JButton("Add Edges");
        addEdgesButton.setBackground(defaultColor);
        addEdgesButton.addActionListener(e -> {
            engine.setEngineState(EngineState.ADDING_EDGES);
            setBackgrounds(addEdgesButton);
        });
        this.add(addEdgesButton);
    }

    private void moveVertexButton() {
        JButton moveVertexButton = new JButton("Move Vertex");
        moveVertexButton.setBackground(defaultColor);
        moveVertexButton.addActionListener(e -> {
            engine.setEngineState(EngineState.MOVE_VERTEX);
            setBackgrounds(moveVertexButton);
        });
        this.add(moveVertexButton);
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