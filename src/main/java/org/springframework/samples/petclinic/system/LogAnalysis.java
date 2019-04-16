package org.springframework.samples.petclinic.system;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogAnalysis {

    public LogAnalysis() {
    }

    public static void findOwnerAnalysis() throws IOException {

        String currentLine;
        double visitedWelcomeOld = 0;
        double visitedWelcomeNew = 0;
        double visitedVetOld = 0;
        double visitedVetNew = 0;
        double visitedErrorOld = 0;
        double visitedErrorNew = 0;
        double totalOld;
        double totalNew;

        long timeStartOld = 0;
        long timeStartNew = 0;
        long timeEndOld = 0;
        long timeEndNew = 0;
        long timeSpendOld = 0;
        long timeSpendNew = 0;


        BufferedReader reader;

        reader = new BufferedReader(new FileReader("src/main/resources/logging/newFindOwner.txt"));
        while ((currentLine = reader.readLine()) != null) {
            if(currentLine.equals("Visited Welcome Page")){
                visitedWelcomeNew++;
            }
            if(currentLine.equals("Visited Error Page")){
                visitedErrorNew++;
            }
            if(currentLine.equals("Visited Vet Page")){
                visitedVetNew++;
            }
        }

        reader = new BufferedReader(new FileReader("src/main/resources/logging/oldFindOwner.txt"));
        while ((currentLine = reader.readLine()) != null) {
            if(currentLine.equals("Visited Welcome Page")){
                visitedWelcomeOld++;
            }
            if(currentLine.equals("Visited Error Page")){
                visitedErrorOld++;
            }
            if(currentLine.equals("Visited Vet Page")){
                visitedVetOld++;
            }
        }

        reader = new BufferedReader(new FileReader("src/main/resources/logging/newWelcome.txt"));
        boolean firstStartFound = false;
        while ((currentLine = reader.readLine()) != null) {
            if(currentLine.startsWith("START") && !firstStartFound){
               timeStartNew = Long.parseLong(currentLine.substring(6));
               firstStartFound = true;
            }
            if(currentLine.startsWith("END")){
                timeEndNew = Long.parseLong(currentLine.substring(4));
            }
        }

        reader = new BufferedReader(new FileReader("src/main/resources/logging/oldWelcome.txt"));
        firstStartFound = false;
        while ((currentLine = reader.readLine()) != null) {
            if(currentLine.startsWith("START") && !firstStartFound){
                timeStartOld = Long.parseLong(currentLine.substring(6));
                firstStartFound = true;
            }
            if(currentLine.startsWith("END")){
                timeEndOld = Long.parseLong(currentLine.substring(4));
            }
        }

        reader.close();

        totalNew = visitedErrorNew+visitedVetNew+visitedWelcomeNew;
        totalOld = visitedErrorOld+visitedVetOld+visitedWelcomeOld;
        timeSpendOld = timeEndOld - timeStartOld;
        timeSpendNew = timeEndNew - timeStartNew;

        System.out.println("\nFind Owner Disabled\n");

        System.out.println("Number of people who redirected to Welcome page:" + visitedWelcomeNew);
        System.out.println("Number of people who redirected to Error page:" + visitedErrorNew);
        System.out.println("Number of people who redirected to Vet page:" + visitedVetNew);

        if (totalNew>0) {
            System.out.println("Percentage of people who redirected to Welcome page:" + visitedWelcomeNew / totalNew);
            System.out.println("Percentage of people who redirected to Error page:" + visitedErrorNew / totalNew);
            System.out.println("Percentage of people who redirected to Vet page:" + visitedVetNew / totalNew);
        }

        System.out.println("\nFind Owner enabled\n");

        System.out.println("Number of people who redirected to Welcome page:" + visitedWelcomeOld);
        System.out.println("Number of people who redirected to Error page:" + visitedErrorOld);
        System.out.println("Number of people who redirected to Vet page:" + visitedVetOld);

        if (totalOld>0) {
            System.out.println("Percentage of people who redirected to Welcome page:" + visitedWelcomeOld/totalOld);
            System.out.println("Percentage of people who redirected to Error page:" + visitedErrorOld/totalOld);
            System.out.println("Percentage of people who redirected to Vet page:" + visitedVetOld/totalOld);
        }

        System.out.println("New Welcome page (no welcome page)");

        System.out.println("Time spent on website:" + timeSpendNew + "Miliseconds");

        System.out.println("Old Welcome page");

        System.out.println("Time spent on website:" + timeSpendOld + "Miliseconds");
    }
}
