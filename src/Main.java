import gameFrames.MainFrame;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        new File("settings").mkdirs();

        new MainFrame();
    }
}