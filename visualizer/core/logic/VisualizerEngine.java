package visualizer.core.logic;

import java.awt.Graphics2D;
import visualizer.algorithm.AlgoManager;
import visualizer.algorithm.Algorithm;
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
    private final AlgoManager algoManager;
    private final Graph graph;
    private final Config config;

    public VisualizerEngine(final InputManager inputManager,
                            final RenderManager renderManager,
                            final StateManager stateManager,
                            final AlgoManager algoManager,
                            final Config config,
                            final Graph graph) {
        this.inputManager = inputManager;
        this.renderManager = renderManager;
        this.stateManager = stateManager;
        this.algoManager = algoManager;
        this.config = config;
        this.graph = graph;

        initialiseManagers();
        registerBackgroundAndUi();
    }

    private void initialiseManagers() {
        inputManager.setEngine(this);
        inputManager.setGraph(graph);
        inputManager.addHandlers();

        algoManager.setEngine(this);
        algoManager.setGraph(graph);
        algoManager.initialiseAlgorithms();
    }

    private void registerBackgroundAndUi() {
        registerObject(new Background(config));
    }

    @Override
    public void step() {
        
    }

    @Override
    public void showBestColoring() {
        graph.getColoringManager().setNextBestColoring();
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
    }

    @Override
    public void revertEngineState() {
        stateManager.revertEngineState();
    }

    @Override
    public void reset() {
        if(getEngineState() == EngineState.RUNNING_ALGO) {
            algoManager.stopAlgo();
        }
        renderManager.reset();
        graph.reset();
        setEngineState(EngineState.ADDING_VERTICES);
    }

    @Override
    public Config getConfig() {
        return config;
    }

    @Override
    public void runAlgorithm(Algorithm algo) {
        algoManager.runAlgorithm(algo);
    }
}