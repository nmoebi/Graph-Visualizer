package visualizer.engine.config;

public final class Config {
    private final int width;
    private final int height;
    private int gridSize = 50;

    public Config(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }
}