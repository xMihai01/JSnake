package gamePanels;

import gameFrames.MainFrame;
import settings.AppleNumberSetting;
import settings.DelaySetting;
import settings.UnitSizeSetting;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Settings extends JPanel implements ActionListener {

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    JButton delaySettingButton, appleNumberSettingButton, unitSizeSettingButton, returnButton, resetButton;
    private DelaySetting delaySetting;
    private AppleNumberSetting appleNumberSetting;
    private UnitSizeSetting unitSizeSetting;
    BufferedImage mainMenuImage;
    public Settings() {

        delaySetting=new DelaySetting();
        appleNumberSetting = new AppleNumberSetting();
        unitSizeSetting = new UnitSizeSetting();
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();

        delaySettingButton = new JButton("Delay (" + DelaySetting.getDelayValue() + ")");
        delaySettingButton.setBounds((int)size.getWidth()/3, (int)size.getHeight()/3, 450, 100);
        changeButtonView(delaySettingButton);

        appleNumberSettingButton = new JButton("AppleNumber ("+ AppleNumberSetting.getAppleNumber() + ")");
        appleNumberSettingButton.setBounds((int)size.getWidth()/3, (int)size.getHeight()/3-150, 450, 100);
        changeButtonView(appleNumberSettingButton);

        unitSizeSettingButton = new JButton("UnitSize (" + UnitSizeSetting.getUnitSize() + ")");
        unitSizeSettingButton.setBounds((int)size.getWidth()/3, (int)size.getHeight()/3+150, 450, 100);
        changeButtonView(unitSizeSettingButton);

        returnButton = new JButton("Return");
        returnButton.addActionListener(this);
        returnButton.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        returnButton.setBounds((int)size.getWidth()-400, (int)size.getHeight()-200, 350, 100);
        returnButton.setBackground(Color.DARK_GRAY);
        returnButton.setForeground(Color.BLUE);
        returnButton.setBorder(new MatteBorder(4,4,4,4, Color.RED));

        resetButton = new JButton("Reset Settings");
        resetButton.addActionListener(this);
        resetButton.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        resetButton.setBounds(50, (int)size.getHeight()-200, 350, 100);
        resetButton.setBackground(Color.DARK_GRAY);
        resetButton.setForeground(Color.BLUE);
        resetButton.setBorder(new MatteBorder(4,4,4,4, Color.RED));

        try {
            mainMenuImage = ImageIO.read(new File("mainMenuPhoto.png"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        add(resetButton);
        add(delaySettingButton);
        add(appleNumberSettingButton);
        add(unitSizeSettingButton);
        add(returnButton);
    }

    private void changeButtonView(JButton button) {
        button.setFont(new Font("TimesRoman", Font.PLAIN, 35));
        button.addActionListener(this);
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.orange);
        button.setBorder(new MatteBorder(4,4,4,4, Color.RED));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image resizedPhoto = mainMenuImage.getScaledInstance((int)size.getWidth()*2, (int)size.getHeight()*2, Image.SCALE_DEFAULT);
        g.drawImage(resizedPhoto, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == delaySettingButton) {
            delaySetting.setDelay(Integer.parseInt(JOptionPane.showInputDialog(null, "Delay")));
            delaySettingButton.setText("Delay (" + DelaySetting.getDelayValue() + ")");
        }
        if (e.getSource() == appleNumberSettingButton) {
            appleNumberSetting.setAppleNumber(Integer.parseInt(JOptionPane.showInputDialog(null, "Number of apples to generate: ")));
            appleNumberSettingButton.setText("AppleNumber ("+ AppleNumberSetting.getAppleNumber() + ")");
        }
        if (e.getSource() == unitSizeSettingButton) {
            unitSizeSetting.setUnitSize(Integer.parseInt(JOptionPane.showInputDialog(null, "Unit size in the snake map")));
            unitSizeSettingButton.setText("UnitSize (" + UnitSizeSetting.getUnitSize() + ")");
        }
        if (e.getSource() == resetButton) {
            delaySetting.setDelay(75);
            appleNumberSetting.setAppleNumber(1);
            unitSizeSetting.setUnitSize(25);
            delaySettingButton.setText("Delay (" + DelaySetting.getDelayValue() + ")");
            appleNumberSettingButton.setText("AppleNumber ("+ AppleNumberSetting.getAppleNumber() + ")");
            unitSizeSettingButton.setText("UnitSize (" + UnitSizeSetting.getUnitSize() + ")");
        }
        if (e.getSource() == returnButton) {
            ((Window) getRootPane().getParent()).dispose();
            new MainFrame();
        }
    }
}
