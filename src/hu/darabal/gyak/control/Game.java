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

    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 10;


    public Game() {
        gameState="START";
        speed = 1;
        snake = new Snake();
        fruit = new Fruit(snake);
        window = new JFrame();


        Render r = new Render(this);
        r.setRefreshRate(new Timer(200,r));

        window.add(r);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(width*dimension+25,height*dimension+85);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public void update() {
        if (checkWallCollision() || checkBodyCollision()){
            gameState = "END";

        } else if (checkFoodCollision()){
            snake.grow();
            fruit.RandomSpawn(snake);
        } else {
            snake.move();
        }
    }


    private boolean checkWallCollision() {
        if (snake.getHeadX() < 5 || snake.getHeadX() >= Game.width * Game.dimension+40 ||
                snake.getHeadY() < 5 || snake.getHeadY() >= Game.height * Game.dimension+40) {
            System.out.println("Wall hit");
            return true;
        }
        return false;
    }

    private boolean checkBodyCollision() {

        for (int i=1; i<snake.getBody().size();i++){
            if (snake.getBody().get(0).x == snake.getBody().get(i).x &&
                    snake.getBody().get(0).y == snake.getBody().get(i).y ) {
                System.out.println("Body hit: "+snake.getHeadX()+" and "+snake.getBody().get(i).x);
                System.out.println("          "+snake.getHeadY()+" and "+snake.getBody().get(i).y);


                return true;
            }
        }
        return false;
    }

    private boolean checkFoodCollision() {
        if (snake.getHeadX() == fruit.getX() &&
                snake.getHeadY() == fruit.getY()) {
            System.out.println("Food hit");
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

        if (gameState.equals("GAME")){
            if (keycode == KeyEvent.VK_UP && !snake.getDirection().equals("UP")) {
                snake.up();
            }
            if (keycode == KeyEvent.VK_DOWN && !snake.getDirection().equals("DOWN")) {
                snake.down();
            }
            if (keycode == KeyEvent.VK_LEFT && !snake.getDirection().equals("RIGHT")) {
                snake.left();
            }
            if (keycode == KeyEvent.VK_RIGHT && !snake.getDirection().equals("LEFT")) {
                snake.right();
            }



        } else if (gameState.equals("START") || gameState.equals("END")) {
            gameState="SETUP";

        } else if (gameState.equals("SETUP")) {
            if (keycode == KeyEvent.VK_UP) {
                if (speed <2){
                    speed++;
                }
            }
            if (keycode == KeyEvent.VK_DOWN){
                if (speed >0){
                    speed--;
                }
            }

            if (keycode == KeyEvent.VK_ENTER){
                gameState="GAME";
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
}
