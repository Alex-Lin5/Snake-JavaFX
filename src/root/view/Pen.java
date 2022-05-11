package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import root.model.Point;

public final class Pen implements Toolkit {
    private final GraphicsContext gc;
    public Pen(GraphicsContext graph){
        gc = graph;
    }
    @Override
    public void clearField(Point start, Point end) {
        gc.clearRect(start.getX(), start.getY(),
            end.getX(), end.getY());
    }

    @Override
    public void drawSquare(Color theColor, Point location,
                           int size) {
        gc.setFill(theColor);
        gc.fillRect(location.getX(), location.getY(), size, size);
    }

    @Override
    public void drawLine(Color theColor, Point start, Point end,
                         int strokeWidth) {
        gc.setFill(theColor);
        gc.strokeLine(start.getX(), start.getY(),
                end.getX(), end.getY());
    }

    @Override
    public void drawGrid(Color theColor, int width, int height, int size) {
        gc.setFill(theColor);
        for (int i=0; i<width/size; i++)
            gc.strokeLine(i*size, 0, i*size, height-gc.getLineWidth());
        for (int i=0; i<height/size; i++)
            gc.strokeLine(0, i*size, width-gc.getLineWidth(), i*size);
    }

    @Override
    public void typeCanvas(Color theColor, String text,
                           align pos, Point location) {
        gc.setFill(theColor);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.fillText(text, location.getX(), location.getY());
    }

    @Override
    public void typeConsole(String text) {
        System.out.print(text);
    }
}
