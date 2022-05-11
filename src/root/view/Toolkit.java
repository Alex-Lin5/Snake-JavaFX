package root.view;

import javafx.scene.paint.Color;
import root.model.Point;

public interface Toolkit {
    void clearField(Point start, Point end);
    // for painter
    void drawSquare(Color theColor, Point location, int size);
    void drawLine(Color theColor, Point start, Point end,
                  int strokeWidth);
    void drawGrid(Color theColor, int width, int height, int size);

    // for messenger
    enum align{LEFT, RIGHT, CENTER}
    void typeCanvas(Color theColor, String text, align pos,
                    Point location);
    void typeConsole(String text);
}
