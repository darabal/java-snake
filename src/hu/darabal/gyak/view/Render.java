package hu.darabal.gyak.view;

import hu.darabal.gyak.control.Game;
import hu.darabal.gyak.entity.Fruit;
import hu.darabal.gyak.entity.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Render extends JPanel implements ActionListener {
    private Timer timerCurrent = new Timer(100, this);
    ;

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

    public void setRefreshRate(Timer timer) {
        timerCurrent.stop();
        timerCurrent = timer;
        timerCurrent.start();
    }

    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int midX = width / 2 - 15;
        int midY = height / 2;

        // border and background
        setBackground(Color.CYAN);


        g2d.setColor(Color.WHITE);
        g2d.fillRect(5, 40, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(5, 5, width + 5, 5);
        g2d.drawLine(width + 5, 5, width + 5, height + 40);
        g2d.drawLine(width + 5, height + 40, 5, height + 40);
        g2d.drawLine(5, height + 40, 5, 5);

        g2d.drawLine(5, 40, width + 5, 40);
        //state handler / render
        if (game.getGameState().equals("START")) {

            //snake ascii art and start screen
            if (b) {
                g2d.drawString("-Press any key-", width / 2 - 42, 220);
            }
            b = !b;
            g2d.drawString("//////////////////////////////////////////////////////", midX - 70, 165);
            g2d.drawString("SNAKE GAME", midX - 25, 180);
            g2d.drawString("//", midX - 73, 180);
            g2d.drawString("//", midX + 92, 180);
            g2d.drawString("//////////////////////////////////////////////////////", midX - 77, 195);
            g2d.drawString("  __", midX, 58);
            g2d.drawString("{OO}", midX, 72);
            g2d.drawString(" \\__/", midX, 84);
            g2d.drawString("  /^/", midX, 96);
            g2d.drawString("(  (", midX, 108);
            g2d.drawString(" \\   \\", midX, 120);
            g2d.drawString("   ______", midX - 18, 120);
            g2d.drawString("  (______)", midX - 18, 132);
            g2d.drawString("(________()Oo", midX - 18, 144);

        } else if (game.getGameState().equals("SETUP")) {

            if (b) {
                g2d.drawString("-Press any key-", width / 2 - 42, 220);
            }
            b = !b;

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
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(fruit.getX(), fruit.getY(), Game.dimension, Game.dimension);

            g2d.setColor(Color.BLUE);
            for (Rectangle r : snake.getBody()) {
                g2d.fill(r);
            }
        } else if (game.getGameState().equals("END")) {
            g2d.drawString("Your score: " + snake.getBody().size(), 10, 55);

        } else {
            g2d.drawString("ERROR", 10, 55);
        }


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
        System.out.println(snake.getDirection());
    }
}

