package hu.darabal.gyak.entity;

import hu.darabal.gyak.control.Game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {
    private ArrayList<Rectangle> body;
    private int width = Game.width;
    private int height = Game.height;
    private int dimension = Game.dimension;

    private String direction;   // NONE, UP, DOWN, LEFT, RIGHT

    public Snake() {
        body = new ArrayList<>();
        Rectangle temp = new Rectangle(dimension, dimension);
        temp.setLocation(width / 2 * dimension, height / 2 * dimension);
        body.add(temp);

        temp = new Rectangle(dimension, dimension);
        temp.setLocation((width / 2 - 1) * dimension, (height / 2) * dimension);
        body.add(temp);

        temp = new Rectangle(dimension, dimension);
        temp.setLocation((width / 2 - 2) * dimension, (height / 2) * dimension);
        body.add(temp);

        direction = "NONE";
        for (Rectangle r : this.body) {
            System.out.println("snake: " + r.x + ", " + r.y);
        }
    }

    public void move() {
        if (direction != "NONE") {
            Rectangle head = body.get(0);
            Rectangle temp = new Rectangle(dimension, dimension);

            if (direction == "UP") {
                temp.setLocation(head.x, head.y - dimension);
            } else if (direction == "LEFT") {
                temp.setLocation(head.x - dimension, head.y);
            } else if (direction == "DOWN") {
                temp.setLocation(head.x, head.y + dimension);
            } else {
                temp.setLocation(head.x + dimension, head.y);
            }

            body.add(0, temp);
            body.remove(body.size() - 1);
        }
    }

    public void grow() {
        if (direction != "NONE") {
            Rectangle head = body.get(0);
            Rectangle temp = new Rectangle(dimension, dimension);

            if (direction == "UP") {
                temp.setLocation(head.x, head.y - dimension);
            } else if (direction == "LEFT") {
                temp.setLocation(head.x - dimension, head.y);
            } else if (direction == "DOWN") {
                temp.setLocation(head.x, head.y + dimension);
            } else {
                temp.setLocation(head.x + dimension, head.y);
            }

            body.add(0, temp);
        }
    }


    // directions set
    public void up() {
        direction = "UP";
        System.out.println("UP");
    }

    public void left() {
        direction = "LEFT";
        System.out.println("LEFT");
    }

    public void down() {
        direction = "DOWN";
        System.out.println("DOWN");
    }

    public void right() {
        direction = "RIGHT";
        System.out.println("RIGHT");
    }

    // getter setters
    public ArrayList<Rectangle> getBody() {
        return this.body;
    }

    public void setBody(ArrayList<Rectangle> body) {
        this.body = body;
    }

    public int getHeadX() {
        return body.get(0).x;
    }

    public int getHeadY() {
        return body.get(0).y;
    }


    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
