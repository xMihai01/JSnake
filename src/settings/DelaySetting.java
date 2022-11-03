package settings;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DelaySetting {

    private static int delay;

    public DelaySetting() {

        try  {
            File file = new File("settings/delay.txt");
            file.createNewFile();
        }catch (IOException exception) {
            exception.printStackTrace();
        }
        delay = 75;
        getDelay();

    }

    public void setDelay(int delayV) {
        delay = delayV;
        try (FileWriter fileWriter = new FileWriter("settings/delay.txt")) {
            fileWriter.write(Integer.toString(delayV));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void getDelay() {

        try {
            File delayFile = new File("settings/delay.txt");
            Scanner scanner = new Scanner(delayFile);
            if (scanner.hasNextLine())
                delay = scanner.nextInt();
            else setDelay(75);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static int getDelayValue() {return delay;}
}
