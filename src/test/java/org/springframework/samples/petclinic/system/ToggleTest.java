package org.springframework.samples.petclinic.system;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.*;

public class ToggleTest {

    private Toggle toggle;

    @Before
    public void setUp() {
        toggle = new Toggle();
    }

    @Test
    public void testFindOwnerToggle(){
        toggle.setFindOwnerToggle(false);
        boolean before = toggle.getFindOwnerToggle();
        toggle.toggleFindOwner();
        boolean after = toggle.getFindOwnerToggle();

        assertFalse(before);
        assertTrue(after);
    }


    @Test
    public void testWelcomePageVetToggle(){
        toggle.setWelcomePageVetToggle(false);
        boolean before = toggle.getWelcomePageVetToggle();
        toggle.toggleWelcomePageVet();
        boolean after = toggle.getWelcomePageVetToggle();

        assertFalse(before);
        assertTrue(after);
    }

    @Test
    public void testToggleLogsForOldFindOwnerAreGenerated() throws IOException {
        toggle.setFindOwnerToggle(false);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/logging/oldFindOwner.txt"));

        int linesBefore = 0;
        int linesAfter;

        while (reader.readLine() != null){
            linesBefore++;
        }

        toggle.logFindOwnerData("test\n");
        linesAfter = linesBefore;

        while (reader.readLine() != null){
            linesAfter++;
        }
        reader.close();

        assertTrue(linesBefore<linesAfter);
    }

    @Test
    public void testToggleLogsForOldWelcomePageVetAreGenerated() throws IOException {
        toggle.setWelcomePageVetToggle(false);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/logging/oldWelcomePageVet.txt"));

        int linesBefore = 0;
        int linesAfter;

        while (reader.readLine() != null){
            linesBefore++;
        }

        toggle.logWelcomePageVetData("test\n");
        linesAfter = linesBefore;

        while (reader.readLine() != null){
            linesAfter++;
        }
        reader.close();

        assertTrue(linesBefore<linesAfter);
    }

    @Test
    public void testToggleLogsForNewFindOwnerAreGenerated() throws IOException {
        toggle.setFindOwnerToggle(true);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/logging/newFindOwner.txt"));

        int linesBefore = 0;
        int linesAfter;

        while (reader.readLine() != null){
            linesBefore++;
        }

        toggle.logFindOwnerData("test\n");
        linesAfter = linesBefore;

        while (reader.readLine() != null){
            linesAfter++;
        }
        reader.close();

        assertTrue(linesBefore<linesAfter);
    }

    @Test
    public void testToggleLogsForNewWelcomePageVetAreGenerated() throws IOException {
        toggle.setWelcomePageVetToggle(true);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/logging/newWelcomePageVet.txt"));

        int linesBefore = 0;
        int linesAfter;

        while (reader.readLine() != null){
            linesBefore++;
        }

        toggle.logWelcomePageVetData("test\n");
        linesAfter = linesBefore;

        while (reader.readLine() != null){
            linesAfter++;
        }
        reader.close();

        assertTrue(linesBefore<linesAfter);
    }
}
