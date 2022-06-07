package root.view;

import lib.Pen;
import root.model.Point.RectPoint;


public interface Toolkit {
    void clearField(RectPoint start, RectPoint end);
    void setColor(Color color);
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
