package gameObjects;

import settings.AppleNumberSetting;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Snake {

    private int length;
    private final int snakeBodyX[] = new int[Game.GAME_UNITS];
    private final int snakeBodyY[] = new int[Game.GAME_UNITS];
    private String snakeDirection;

    Snake() {
        length = 5;
        snakeDirection = "EAST";
    }

    public void setSnakeDirection(String snakeDirection) {this.snakeDirection = snakeDirection;}
    public String getSnakeDirection() {return  snakeDirection;}
    public int getLength() {return length;}

    public void moveSnake() {
        for (int i = length; i>0; i--) {
            snakeBodyX[i]=snakeBodyX[i-1];
            snakeBodyY[i]=snakeBodyY[i-1];
        }

        switch (snakeDirection) {
            case "EAST":
                snakeBodyX[0]=snakeBodyX[0]+Game.UNIT_SIZE;
                break;
            case "WEST":
                snakeBodyX[0]=snakeBodyX[0]-Game.UNIT_SIZE;
                break;
            case "NORTH":
                snakeBodyY[0]=snakeBodyY[0]-Game.UNIT_SIZE;
                break;
            case "SOUTH":
                snakeBodyY[0]=snakeBodyY[0]+Game.UNIT_SIZE;
                break;
        }
    }

    public boolean checkSnakeCollision() {

        if (snakeBodyX[0] >Game.SCREEN_WIDTH) {
            snakeBodyX[0] = 0;
        }
        if (snakeBodyX[0] < 0) {
            snakeBodyX[0] = Game.SCREEN_WIDTH-Game.SCREEN_WIDTH%Game.UNIT_SIZE;
        }
        if (snakeBodyY[0] > Game.SCREEN_HEIGHT) {
            snakeBodyY[0] = 0;
        }
        if (snakeBodyY[0] < 0) {
            snakeBodyY[0] = Game.SCREEN_HEIGHT-Game.SCREEN_HEIGHT%Game.UNIT_SIZE;
        }

        for (int i = 1; i<length; i++) {
            if ((snakeBodyX[0] == snakeBodyX[i]) && (snakeBodyY[0] == snakeBodyY[i])) return false;
        }
        return true;
    }

    public void checkForAppleCollision(Apple[] apple) {
        for (int i = 0; i< AppleNumberSetting.getAppleNumber(); i++) {
            if ((snakeBodyX[0] == apple[i].getAppleX()) && (snakeBodyY[0] == apple[i].getAppleY())) {
                length++;
                apple[i].generateApple();
            }
        }
    }

    public void drawSnakeBody(Graphics g) {

        g.setColor(Color.GRAY);
        g.fillRect(snakeBodyX[0], snakeBodyY[0], Game.UNIT_SIZE, Game.UNIT_SIZE);
        g.setColor(Color.BLACK);
        g.fillRect(snakeBodyX[0]+ Game.UNIT_SIZE-Game.UNIT_SIZE/3, snakeBodyY[0]+Game.UNIT_SIZE/3, Game.UNIT_SIZE/5,Game.UNIT_SIZE/5);
        g.fillRect(snakeBodyX[0]+Game.UNIT_SIZE-Game.UNIT_SIZE/3, snakeBodyY[0]+Game.UNIT_SIZE-Game.UNIT_SIZE/3, Game.UNIT_SIZE/5,Game.UNIT_SIZE/5);
        g.fillRect(snakeBodyX[0]+Game.UNIT_SIZE/5, snakeBodyY[0]+ Game.UNIT_SIZE/3, Game.UNIT_SIZE/5,Game.UNIT_SIZE/5);
        g.setColor(Color.GREEN);
        for (int i = 1; i<length; i++) {
            g.fillRect(snakeBodyX[i], snakeBodyY[i], Game.UNIT_SIZE, Game.UNIT_SIZE);
        }

    }
}
