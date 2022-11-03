package gameObjects;

import java.awt.*;
import java.util.Random;

public class Apple {

    private Point appleLocation;

    Random random;

    public Apple() {
        random = new Random();
        appleLocation = new Point();
    }

    public int getAppleX() {return appleLocation.x;}
    public int getAppleY() {return appleLocation.y;}
    public Point getApple() {return appleLocation;}

    public void generateApple() {
        appleLocation.x = random.nextInt(Game.SCREEN_WIDTH/Game.UNIT_SIZE)*Game.UNIT_SIZE;
        appleLocation.y = random.nextInt(Game.SCREEN_HEIGHT/Game.UNIT_SIZE)*Game.UNIT_SIZE;
    }

    public void drawApple(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(appleLocation.x, appleLocation.y, Game.UNIT_SIZE, Game.UNIT_SIZE);
    }

}
