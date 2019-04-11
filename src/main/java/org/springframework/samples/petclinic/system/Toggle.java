package org.springframework.samples.petclinic.system;

import java.io.FileWriter;
import java.io.IOException;

public class Toggle {

    public Toggle() {
    }

    private static boolean findOwnerToggle = false;

    public static boolean getToggle() {
        return findOwnerToggle;
    }

    public static void toggleFindOwner() {
        findOwnerToggle = !findOwnerToggle;
    }

    public static void logData(long data) {
        FileWriter fileWriter = null;
        try {
            final String path = "src/main/resources/logging/";
            if (findOwnerToggle) {
                fileWriter = new FileWriter(path + "newFindOwner.txt", true);
            } else {
                fileWriter = new FileWriter(path + "oldFindOwner.txt", true);
            }
            fileWriter.write(String.valueOf(data));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}