package root.view;

import javafx.scene.text.TextAlignment;
import root.model.Point.RectPoint;
//import root.model.RectPoint;

public interface Toolkit {
    void clearField(RectPoint start, RectPoint end);
    void setColor(String color);
    // for painter
    void drawRect(RectPoint start, RectPoint end);
    void drawSquare(RectPoint location, int size);
    void drawLine(RectPoint start, RectPoint end,
                  int strokeWidth);
    void drawGrid(int width, int height, int size);

    // for messenger
//    enum Align{LEFT, RIGHT, CENTER}
    void setAlign(Pen.Align pos);
    void textCanvas(String text, RectPoint location);
    void inkConsole(String text);
}
