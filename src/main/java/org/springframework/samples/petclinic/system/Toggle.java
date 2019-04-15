package org.springframework.samples.petclinic.system;

import java.io.FileWriter;
import java.io.IOException;

public class Toggle {

    public Toggle() {
    }

    private static boolean findOwnerToggle = (((int) (Math.random() * 2)) == 0);

    public static boolean getToggle() {
        return findOwnerToggle;
    }

    public static void setToggle(boolean toggle) {
        findOwnerToggle = toggle;
    }

    public static void toggleFindOwner() {
        findOwnerToggle = !findOwnerToggle;
    }

    public static void logData(String data) {
        FileWriter fileWriter = null;
        try {
            final String path = "src/main/resources/logging/";
            if (findOwnerToggle) {
                fileWriter = new FileWriter(path + "newFindOwner.txt", true);
            } else {
                fileWriter = new FileWriter(path + "oldFindOwner.txt", true);
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
