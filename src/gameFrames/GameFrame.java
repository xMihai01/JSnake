package gameFrames;

import gameObjects.Game;

import javax.swing.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setTitle("JSnake");

        add(new Game());

        setVisible(true);
    }

}
