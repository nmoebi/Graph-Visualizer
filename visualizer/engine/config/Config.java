package visualizer.engine.config;

public final class Config {
    private final int width;
    private final int height;

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
}