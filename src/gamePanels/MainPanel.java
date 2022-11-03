package gamePanels;

import gameFrames.GameFrame;
import gameFrames.SettingsFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel implements ActionListener {

    static Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    JButton play, settingsButton, quitButton;
    BufferedImage mainMenuImage;

    public MainPanel () {

        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    ((Window) getRootPane().getParent()).dispose();
            }
        });

        play = new JButton("Play");
        play.setFont(new Font("TimesRoman", Font.PLAIN, 70));
        play.setBounds((int)size.getWidth()/3, (int)size.getHeight()/3, 500, 100);
        play.setBackground(Color.DARK_GRAY);
        play.setForeground(Color.cyan);
        play.setBorder(new MatteBorder(4, 4, 4, 4, Color.RED));
        play.addActionListener(this);

        settingsButton = new JButton("Settings");
        settingsButton.addActionListener(this);
        settingsButton.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        settingsButton.setBounds(50, (int)size.getHeight()-200, 350, 100);
        settingsButton.setBackground(Color.DARK_GRAY);
        settingsButton.setForeground(Color.BLUE);
        settingsButton.setBorder(new MatteBorder(4, 4, 4, 4, Color.RED));

        quitButton = new JButton("Quit");
        quitButton.addActionListener(this);
        quitButton.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        quitButton.setBounds((int)size.getWidth()-400, (int)size.getHeight()-200, 350, 100);
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.BLUE);
        quitButton.setBorder(new MatteBorder(4,4,4,4, Color.RED));
        try {
            mainMenuImage = ImageIO.read(new File("mainMenuPhoto.png"));
        }catch (IOException exception) {
            exception.printStackTrace();
        }
        add(play);
        add(settingsButton);
        add(quitButton);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image resizedPhoto = mainMenuImage.getScaledInstance((int)size.getWidth()*2, (int)size.getHeight()*2, Image.SCALE_DEFAULT);
        //Image resizedPhoto = mainMenuImage.getScaledInstance(4000, 3000, Image.SCALE_SMOOTH);
        g.drawImage(resizedPhoto, 0, 0, null);
        g.setColor(Color.RED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == play) {
            ((Window) getRootPane().getParent()).dispose();
            new GameFrame();
        }
        if (e.getSource() == settingsButton) {
            ((Window) getRootPane().getParent()).dispose();
            new SettingsFrame();
        }
        if (e.getSource() == quitButton)
            ((Window) getRootPane().getParent()).dispose();
    }
}
