package hu.darabal.gyak.control;

import hu.darabal.gyak.entity.Fruit;
import hu.darabal.gyak.entity.Snake;
import hu.darabal.gyak.view.Render;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private JFrame window;

    private Snake snake;
    private Fruit fruit;

    private String gameState;   // START, SETUP, GAME, END
    private int speed;  // 0=fast 1=normal 2=slow

    private boolean speedChanged = false;

    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 10;


    public Game() {
        gameState = "START";
        speed = 1;
        snake = new Snake();
        fruit = new Fruit(snake);
        window = new JFrame();


        Render r = new Render(this);

        window.add(r);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(width * dimension + 35, height * dimension + 85);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void update() {
        if (checkWallHit() || checkBodyHit()) {
            gameState = "END";

        } else if (checkFoodHit()) {
            snake.grow();
            fruit.RandomSpawn(snake);
        } else {
            snake.move();
        }
    }


    private boolean checkWallHit() {
        if (snake.getHeadX() < 10 || snake.getHeadX() > Game.width * Game.dimension ||
                snake.getHeadY() < 40 || snake.getHeadY() > Game.height * Game.dimension + 30) {
            return true;
        }
        return false;
    }

    private boolean checkBodyHit() {

        for (int i = 1; i < snake.getBody().size(); i++) {
            if (snake.getBody().get(0).x == snake.getBody().get(i).x &&
                    snake.getBody().get(0).y == snake.getBody().get(i).y) {
                return true;
            }
        }
        return false;
    }

    private boolean checkFoodHit() {
        if (snake.getHeadX() == fruit.getX() &&
                snake.getHeadY() == fruit.getY()) {
            return true;
        }
        return false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();

        if (gameState.equals("GAME")) {
            if (keycode == KeyEvent.VK_UP && !snake.getDirection().equals("DOWN")) {
                snake.up();
            }
            if (keycode == KeyEvent.VK_DOWN && !snake.getDirection().equals("UP")) {
                snake.down();
            }
            if (keycode == KeyEvent.VK_LEFT && !snake.getDirection().equals("RIGHT")) {
                snake.left();
            }
            if (keycode == KeyEvent.VK_RIGHT && !snake.getDirection().equals("LEFT")) {
                snake.right();
            }


        } else if (gameState.equals("START") || gameState.equals("END")) {
            gameState = "SETUP";

        } else if (gameState.equals("SETUP")) {
            if (keycode == KeyEvent.VK_UP) {
                if (speed < 2) {
                    speed++;
                }
            }
            if (keycode == KeyEvent.VK_DOWN) {
                if (speed > 0) {
                    speed--;
                }
            }

            if (keycode == KeyEvent.VK_ENTER ||
                    keycode == KeyEvent.VK_SPACE) {

                setSpeedChanged(true);
                gameState = "GAME";
            }
        }
        //System.out.println(keycode);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public JFrame getWindow() {
        return window;
    }

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public Fruit getFruit() {
        return fruit;
    }

    public void setFruit(Fruit fruit) {
        this.fruit = fruit;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isSpeedChanged() {
        return speedChanged;
    }

    public void setSpeedChanged(boolean speedChanged) {
        this.speedChanged = speedChanged;
    }
}