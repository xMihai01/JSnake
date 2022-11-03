package settings;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UnitSizeSetting {
    private static int unitSize;

    public UnitSizeSetting() {

        try  {
            File file = new File("settings/unitSize.txt");
            file.createNewFile();
        }catch (IOException exception) {
            exception.printStackTrace();
        }
        unitSize = 25;
        getAppleNumberFromFile();

    }

    public void setUnitSize(int unitSizeV) {
        unitSize = unitSizeV;
        try (FileWriter fileWriter = new FileWriter("settings/unitSize.txt")) {
            fileWriter.write(Integer.toString(unitSizeV));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public void getAppleNumberFromFile() {

        try {
            File delayFile = new File("settings/unitSize.txt");
            Scanner scanner = new Scanner(delayFile);
            if (scanner.hasNextLine())
                unitSize = scanner.nextInt();
            else setUnitSize(25);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    public static int getUnitSize() {return unitSize;}
}
