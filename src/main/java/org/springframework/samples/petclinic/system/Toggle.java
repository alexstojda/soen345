package org.springframework.samples.petclinic.system;

import java.io.FileWriter;
import java.io.IOException;

public class Toggle {

    public Toggle() {
    }

    private static boolean findOwnerToggle = (((int) (Math.random() * 2)) == 0);
    private static boolean disableHomePageToggle = true;

    public static boolean getOwnerToggle() {
        return findOwnerToggle;
    }
    public static boolean getHomePageToggle() {
        return disableHomePageToggle;
    }
    public static void setOwnerToggle(boolean toggle) {
        findOwnerToggle = toggle;
    }
    public static void setHomePageToggle(boolean toggle) {
        disableHomePageToggle = toggle;
    }
    public static void toggleFindOwner() {
        findOwnerToggle = !findOwnerToggle;
    }
    public static void toggleDisableHomePage() {
        disableHomePageToggle = !disableHomePageToggle;
    }

    public static void logOwnerData(String data) {
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

    public static void logWelcomeData(String data) {
        FileWriter fileWriter = null;
        try {
            final String path = "src/main/resources/logging/";
            if (disableHomePageToggle) {
                fileWriter = new FileWriter(path + "newWelcome.txt", true);
            } else {
                fileWriter = new FileWriter(path + "oldWelcome.txt", true);
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
