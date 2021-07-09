package hu.darabal.gyak.entity;

import hu.darabal.gyak.control.Game;

import java.awt.*;

public class Fruit {
    private int x;
    private int y;


    public Fruit(Snake s) {
        this.RandomSpawn(s);
    }

    public void RandomSpawn(Snake snake) {
        boolean onSnake = false;

        do {
            x = (int) (Math.random() * Game.width + 1) * Game.dimension;
            y = (int) (Math.random() * Game.height + 4) * Game.dimension;

            for (Rectangle r : snake.getBody()) {
                if (r.x == x && r.y == y) {
                    onSnake = true;
                }
            }
        } while (onSnake);

        System.out.println("fruit: " + x + ", " + y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
