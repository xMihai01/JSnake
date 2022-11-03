package gameFrames;

import gamePanels.Settings;

import javax.swing.*;

public class SettingsFrame extends JFrame {

    public SettingsFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JSnake");

        add(new Settings());

        setVisible(true);
    }

}
