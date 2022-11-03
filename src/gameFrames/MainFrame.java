package gameFrames;

import gamePanels.MainPanel;
import settings.AppleNumberSetting;
import settings.DelaySetting;
import settings.UnitSizeSetting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

    public MainFrame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(500, 500);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("JSnake");

        add(new MainPanel());

        setVisible(true);

        new DelaySetting();
        new AppleNumberSetting();
        new UnitSizeSetting();

    }

}
