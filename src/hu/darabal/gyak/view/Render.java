package hu.darabal.gyak.view;

import hu.darabal.gyak.control.Game;
import hu.darabal.gyak.entity.Fruit;
import hu.darabal.gyak.entity.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Render extends JPanel implements ActionListener {
    private Timer timerCurrent = new Timer(200, this);

    private static boolean b;

    private int width;
    private int height;

    private Game game;

    private Snake snake;
    private Fruit fruit;

    public Render(Game g) {
        timerCurrent.start();
        game = g;
        snake = g.getSnake();
        fruit = g.getFruit();

        this.addKeyListener(g);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        width = Game.width * Game.dimension;
        height = Game.height * Game.dimension;
        b = true;
    }

    public void setRefreshRate(boolean timeChange) {
        if (timeChange) {
            if (game.getSpeed() == 0) {
                timerCurrent.stop();
                timerCurrent = new Timer(100, this);    // FAST
                timerCurrent.start();
            } else if (game.getSpeed() == 2) {
                timerCurrent.stop();
                timerCurrent = new Timer(10, this);    // SLOW
                timerCurrent.start();
            }
            game.setSpeedChanged(false);
        }
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int midX = 150;
        int midY = 220;

        // border and background
        setBackground(Color.CYAN);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(10, 40, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(10, 10, width + 10, 10);
        g2d.drawLine(width + 10, 10, width + 10, height + 40);
        g2d.drawLine(width + 10, height + 40, 10, height + 40);
        g2d.drawLine(10, height + 40, 10, 10);

        g2d.drawLine(10, 40, width + 10, 40);

        //state handler / render
        if (game.getGameState().equals("START")) {

            //snake ascii art and start screen
            if (b) {
                g2d.drawString("-Press any key-", midX - 30, midY+30);
            }
            b = !b;
            g2d.drawString("//////////////////////////////////////////////////////", midX - 70, midY-25);
            g2d.drawString("SNAKE GAME", midX - 25, midY-10);
            g2d.drawString("//", midX - 73, midY-10);
            g2d.drawString("//", midX + 92, midY-10);
            g2d.drawString("//////////////////////////////////////////////////////", midX - 77, midY+5);
            g2d.drawString("  __", midX, midY-132);
            g2d.drawString("{OO}", midX, midY-118);
            g2d.drawString(" \\__/", midX, midY-106);
            g2d.drawString("  /^/", midX, midY-94);
            g2d.drawString("(  (", midX, midY-82);
            g2d.drawString(" \\   \\", midX, midY-70);
            g2d.drawString("   ______", midX - 18, midY-70);
            g2d.drawString("  (______)", midX - 18, midY-58);
            g2d.drawString("(________()Oo", midX - 18, midY-46);

        } else if (game.getGameState().equals("SETUP")) {

            g2d.drawString("Select the SPEED!", midX - 33, midY - 55);
            g2d.drawString("slow", midX, midY - 25);
            g2d.drawString("normal", midX, midY);
            g2d.drawString("fast", midX, midY + 25);

            // draws the selecting box
            if (game.getSpeed() == 2) {
                g2d.drawLine(midX - 10, midY - 40, midX + 45, midY - 40);
                g2d.drawLine(midX + 45, midY - 40, midX + 45, midY - 20);
                g2d.drawLine(midX + 45, midY - 20, midX - 10, midY - 20);
                g2d.drawLine(midX - 10, midY - 20, midX - 10, midY - 40);
            } else if (game.getSpeed() == 0) {
                g2d.drawLine(midX - 10, midY - 40 + 50, midX + 45, midY - 40 + 50);
                g2d.drawLine(midX + 45, midY - 40 + 50, midX + 45, midY - 20 + 50);
                g2d.drawLine(midX + 45, midY - 20 + 50, midX - 10, midY - 20 + 50);
                g2d.drawLine(midX - 10, midY - 20 + 50, midX - 10, midY - 40 + 50);
            } else {
                g2d.drawLine(midX - 10, midY - 40 + 25, midX + 45, midY - 40 + 25);
                g2d.drawLine(midX + 45, midY - 40 + 25, midX + 45, midY - 20 + 25);
                g2d.drawLine(midX + 45, midY - 20 + 25, midX - 10, midY - 20 + 25);
                g2d.drawLine(midX - 10, midY - 20 + 25, midX - 10, midY - 40 + 25);
            }

        } else if (game.getGameState().equals("GAME")) {
            setRefreshRate(game.isSpeedChanged());

            // renders fruit
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(fruit.getX(), fruit.getY(), Game.dimension, Game.dimension);

            // renders snake
            g2d.setColor(Color.ORANGE);
            for (int i=0; i<snake.getBody().size(); i++){
                if (i==0){
                    g2d.fill(snake.getBody().get(0));
                    g2d.setColor(Color.GREEN);
                } else {
                    g2d.fill(snake.getBody().get(i));
                }
            }

            g2d.setColor(Color.BLACK);
            g2d.drawString("Your score: " + snake.getBody().size(), 15, 30);

        } else if (game.getGameState().equals("END")) {
            g2d.drawString("GAME OVER",127,170);
            g2d.drawString("Your score: " + snake.getBody().size(), 127, 190);
        } else {
            g2d.drawString("ERROR", 15, 55);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}

