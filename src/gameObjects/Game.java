package gameObjects;

import gameFrames.MainFrame;
import settings.AppleNumberSetting;
import settings.DelaySetting;
import settings.UnitSizeSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel  implements ActionListener {

    static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    static final int SCREEN_HEIGHT = (int) size.getHeight();
    static final int SCREEN_WIDTH = (int) size.getWidth();
    static int UNIT_SIZE = UnitSizeSetting.getUnitSize();
    static final int GAME_UNITS = (SCREEN_HEIGHT*SCREEN_WIDTH)/UNIT_SIZE;
    final int DELAY = DelaySetting.getDelayValue();
    final int APPLES = AppleNumberSetting.getAppleNumber();
    Snake snake;
    Apple[] apple;
    Timer timer;
    boolean isOver = false;
    boolean isPaused = false;

    public Game() {

        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && !snake.getSnakeDirection().equals("NORTH"))
                    snake.setSnakeDirection("SOUTH");
                if ((e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) && !snake.getSnakeDirection().equals("SOUTH"))
                    snake.setSnakeDirection("NORTH");
                if ((e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) && !snake.getSnakeDirection().equals("EAST"))
                    snake.setSnakeDirection("WEST");
                if ((e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) && !snake.getSnakeDirection().equals("WEST"))
                    snake.setSnakeDirection("EAST");
                if (e.getKeyCode() == KeyEvent.VK_R)
                    resetGame();
                if (e.getKeyCode() == KeyEvent.VK_SPACE && !isOver) {
                    isPaused = !isPaused;
                    if (isPaused)
                        timer.stop();
                    else
                        timer.start();
                    repaint();
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    timer.stop();
                    ((Window) getRootPane().getParent()).dispose();
                    new MainFrame();
                }
                if (e.getKeyCode() == KeyEvent.VK_E)
                    System.out.println(DELAY);

            }
        });

        snake = new Snake();
        apple = new Apple[APPLES];
        for (int i = 0; i<APPLES; i++) {
            apple[i] = new Apple();
        }
        timer = new Timer(DELAY, this);
        UNIT_SIZE = UnitSizeSetting.getUnitSize();
        startGame();

    }

    public void startGame() {
        for (int i = 0; i<APPLES; i++)
            apple[i].generateApple();
        timer.start();
    }
    public void resetGame() {
        snake = new Snake();
        for (int i = 0; i<APPLES; i++)
            apple[i].generateApple();
        isOver= false;
        timer.start();
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

            for (int i = 0; i < GAME_UNITS; i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);

            }

        for (int i = 0; i<APPLES; i++)
            apple[i].drawApple(g);
        snake.drawSnakeBody(g);

        if (isPaused) {
            g.setColor(Color.WHITE);
            g.setFont(new Font(("TimesRoman"), Font.BOLD, 100));
            g.drawString("PAUSED", SCREEN_WIDTH/3, SCREEN_HEIGHT/2-20);
        }

        if (isOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.BOLD, 50));
            g.drawString("You lost! Your score was: " + Integer.toString(snake.getLength()-5), SCREEN_WIDTH/3-20, SCREEN_HEIGHT/2-20);
            g.drawString("Press ESCAPE to return.", SCREEN_WIDTH/3-20, SCREEN_HEIGHT/2+30);
            g.drawString("Press R to restart.", SCREEN_WIDTH/3-20, SCREEN_HEIGHT/2+80);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        snake.moveSnake();
        snake.checkForAppleCollision(apple);
        if (!snake.checkSnakeCollision()) {
            isOver = true;
            timer.stop();
        }

        repaint();
    }
}
