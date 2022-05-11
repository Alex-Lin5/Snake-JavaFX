package root.controller;

import root.model.Board;
import root.model.Point;

import java.util.LinkedList;
import java.util.Random;

public final class Arbiter {
    private Random random;
    private Board board;
    public Arbiter(Board board){
        this.board = board;
        random = new Random();
//        random.setSeed(1);
    }

    public Point generatePoint (int width, int height) {
//        public Point generatePoint (int width, int height) {
        int x, y;
        boolean stacked;
        Point point;
        LinkedList<Point> body = board.getSnake(0).getBody();

        do {
            stacked = false;
            x = random.nextInt(width);
            y = random.nextInt(height);
            point = new Point(x, y);
            point = point.getPointOnGrid();
            if (body.contains(point))
                stacked = true;
        } while (stacked);
        return point;
    }
}
