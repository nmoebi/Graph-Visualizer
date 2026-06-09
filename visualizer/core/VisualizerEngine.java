package visualizer.core;

import java.awt.Graphics2D;
import visualizer.engine.InputManager;
import visualizer.engine.VisEngine;
import visualizer.engine.config.Config;
import visualizer.engine.render.RenderManager;
import visualizer.engine.render.Renderable;
import visualizer.engine.states.EngineState;
import visualizer.engine.states.StateManager;
import visualizer.graph.Graph;
import visualizer.uicomponents.Background;

public class VisualizerEngine implements VisEngine{
    private final InputManager inputManager;
    private final RenderManager renderManager;
    private final StateManager stateManager;
    private final Graph graph;
    private final Config config;

    public VisualizerEngine(final InputManager inputManager,
                            final RenderManager renderManager,
                            final StateManager stateManager,
                            final Config config) {
        this.inputManager = inputManager;
        this.renderManager = renderManager;
        this.stateManager = stateManager;
        this.config = config;
        this.graph = new Graph();

        initialiseInputManager();
        registerBackgroundAndUi();
    }

    private void initialiseInputManager() {
        inputManager.setEngine(this);
        inputManager.setGraph(graph);
        inputManager.addHandlers();
    }

    private void registerBackgroundAndUi() {
        registerObject(new Background(config));
    }

    @Override
    public void step() {
        
    }

    @Override
    public void render(final Graphics2D graphics) {
        renderManager.render(graphics);
    }

    @Override
    public void registerObject(final Object object) {
        if (object instanceof Renderable renderable) {
            renderManager.registerRenderable(renderable);
        }
    }

    @Override
    public void deregisterObject(final Object object) {
        if (object instanceof Renderable renderable) {
            renderManager.deregisterRenderable(renderable);
        }   
    }

    @Override
    public EngineState getEngineState() {
        return stateManager.getEngineState();
    }

    @Override
    public void setEngineState(EngineState state) {
        stateManager.setEngineState(state);
        System.out.println("Engine state changed to " + state);
    }

    @Override
    public void reset() {

        renderManager.reset();
        graph.reset();
        setEngineState(EngineState.ADDING_VERTICES);
    }
}