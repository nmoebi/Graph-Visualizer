package visualizer.engine.config;

import java.awt.Color;

public interface Colors {
    Color BLACK = Color.BLACK;
    Color GRAY = Color.LIGHT_GRAY;
    Color WHITE = Color.WHITE;
    Color ACTIVE_COLOR = new Color(75, 210, 25);
    Color INACTIVE_COLOR = new Color(200, 200, 200);
    Color ALGO_RUNNING_LED = new Color(225, 145, 15);

    Color[] VERTEX_COLORS = new Color[] {
        GRAY,                           // 0
        new Color(80, 195, 240),        // 1
        new Color(180, 225, 80),        // 2
        new Color(240, 125, 100),       // 3
        new Color(250, 190, 75),        // 4
        new Color(205, 160, 255),       // 5
        new Color(110, 235, 215),       // 6
        new Color(255, 155, 210),       // 7
        new Color(140, 175, 255),       // 8
        new Color(130, 150, 245),       // 9
        new Color(100, 210, 110),       // 10
        new Color(245, 110, 175),       // 11
        new Color(245, 85, 85),         // 12
        new Color(50, 190, 200),        // 13
        new Color(110, 160, 255),       // 14
        new Color(85, 205, 215),        // 15
        new Color(80, 200, 135),        // 16
        new Color(125, 210, 80),        // 17
        new Color(175, 205, 55),        // 18
        new Color(210, 130, 240),       // 19
        new Color(235, 105, 145)        // 20
    };   

}