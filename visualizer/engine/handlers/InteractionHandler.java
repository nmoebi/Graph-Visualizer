package visualizer.engine.handlers;

public interface InteractionHandler {

    default void onPress(int x, int y) {};
    default void onRelease(int x, int y) {};
    default void onDrag(int x, int y) {};
    default void onClick(int x, int y) {};
}