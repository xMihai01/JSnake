package settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AppleNumberSetting {

    private static int appleNumber;

    public AppleNumberSetting() {

        try  {
            File file = new File("settings/appleNumber.txt");
            file.createNewFile();
        }catch (IOException exception) {
            exception.printStackTrace();
        }
        appleNumber = 1;
        getAppleNumberFromFile();

    }

    public void setAppleNumber(int appleNumberV) {
        appleNumber = appleNumberV;
        try (FileWriter fileWriter = new FileWriter("settings/appleNumber.txt")) {
            fileWriter.write(Integer.toString(appleNumberV));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void getAppleNumberFromFile() {

        try {
            File delayFile = new File("settings/appleNumber.txt");
            Scanner scanner = new Scanner(delayFile);
            if (scanner.hasNextLine())
                appleNumber = scanner.nextInt();
            else setAppleNumber(1);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static int getAppleNumber() {return appleNumber;}
}
