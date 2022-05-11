package root.view;

import javafx.scene.text.TextAlignment;
import root.model.Point;

public interface Toolkit {
    void clearField(Point start, Point end);
    void setColor(String color);
    // for painter
    void drawRect(Point start, Point end);
    void drawSquare(Point location, int size);
    void drawLine(Point start, Point end,
                  int strokeWidth);
    void drawGrid(int width, int height, int size);

    // for messenger
//    enum Align{LEFT, RIGHT, CENTER}
    void setAlign(Pen.Align pos);
    void textCanvas(String text, Point location);
    void inkConsole(String text);
}
