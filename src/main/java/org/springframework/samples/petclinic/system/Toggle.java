package org.springframework.samples.petclinic.system;

import java.io.FileWriter;
import java.io.IOException;

public class Toggle {

    public Toggle() {
    }

    private static boolean findOwnerToggle = false;
    private static boolean welcomePageVetToggle = false;

    public static boolean getFindOwnerToggle() {
        return findOwnerToggle;
    }
    public static boolean getWelcomePageVetToggle() {
        return welcomePageVetToggle;
    }

    public static void setFindOwnerToggle(boolean toggle) {
        findOwnerToggle = toggle;
    }
    public static void setWelcomePageVetToggle(boolean toggle) {
        welcomePageVetToggle = toggle;
    }

    public static void toggleFindOwner() { findOwnerToggle = !findOwnerToggle; }
    public static void toggleWelcomePageVet() { welcomePageVetToggle = !welcomePageVetToggle; }

    final static String path = "src/main/resources/logging/";

    public static void logFindOwnerData(String data) {
        FileWriter fileWriterFindOwner = null;
        try {
            if (findOwnerToggle) {
                fileWriterFindOwner = new FileWriter(path + "newFindOwner.txt", true);
            } else {
                fileWriterFindOwner = new FileWriter(path + "oldFindOwner.txt", true);
            }
            fileWriterFindOwner.write(data);
            fileWriterFindOwner.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void logWelcomePageVetData(String data) {
        FileWriter fileWriterWelcomePageVet = null;
        try {
            if (welcomePageVetToggle) {
                fileWriterWelcomePageVet = new FileWriter(path + "newWelcomePageVet.txt", true);
            } else {
                fileWriterWelcomePageVet = new FileWriter(path + "oldWelcomePageVet.txt", true);
            }
            fileWriterWelcomePageVet.write(data);
            fileWriterWelcomePageVet.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
