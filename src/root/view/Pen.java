package root.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import root.model.Point.RectPoint;


public final class Pen implements Toolkit {
    private final GraphicsContext gc;
    public Pen(GraphicsContext graph){
        gc = graph;
    }
    @Override
    public void clearField(RectPoint start, RectPoint end) {
        gc.clearRect(start.getX(), start.getY(),
            end.getX(), end.getY());
    }

    @Override
    public void setColor(root.view.Color color) {
        Color colorNew = Color.web(color.getWeb(), 1);
        gc.setFill(colorNew);
        gc.setStroke(colorNew);
    }

    @Override
    public void drawRect(RectPoint start, RectPoint end) {
        gc.fillRect(start.getX(), start.getY(), end.getX(), end.getY());
    }

    @Override
    public void drawSquare(RectPoint location,
                           int size) {
        gc.fillRect(location.getX(), location.getY(), size, size);
    }

    @Override
    public void drawLine(RectPoint start, RectPoint end,
                         int strokeWidth) {
        gc.strokeLine(start.getX(), start.getY(),
                end.getX(), end.getY());
    }

    @Override
    public void drawGrid(int width, int height, int size) {
        for (int i=0; i<width/size; i++)
            gc.strokeLine(i*size, 0, i*size, height-gc.getLineWidth());
        for (int i=0; i<height/size; i++)
            gc.strokeLine(0, i*size, width-gc.getLineWidth(), i*size);
    }

    enum Align{
        LEFT(TextAlignment.LEFT),
        RIGHT(TextAlignment.RIGHT),
        CENTER(TextAlignment.CENTER);

        TextAlignment position;
        Align(TextAlignment pos) {
            this.position = pos;
        }
        public TextAlignment getLib(){
            return position;
        }
    }

    @Override
    public void setAlign(Align pos) {
        gc.setTextAlign(pos.getLib());
    }

    @Override
    public void textCanvas(String text, RectPoint location) {
        gc.fillText(text, location.getX(), location.getY());
    }

    @Override
    public void inkConsole(String text) {
        System.out.print(text);
    }
}
