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
        toggle.setOwnerToggle(false);
        boolean before = toggle.getOwnerToggle();
        toggle.toggleFindOwner();
        boolean after = toggle.getOwnerToggle();

        assertFalse(before);
        assertTrue(after);
    }
    
    @Test
    public void testPaymentToggle(){
        toggle.setPaymentToggle(false);
        boolean before = toggle.getPaymentToggle();
        toggle.togglePayment();
        boolean after = toggle.getPaymentToggle();

        assertFalse(before);
        assertTrue(after);
    }


    @Test
    public void testToggleLogsForOldFindOwnerAreGenerated() throws IOException {
        toggle.setOwnerToggle(false);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/logging/oldFindOwner.txt"));

        int linesBefore = 0;
        int linesAfter;

        while (reader.readLine() != null){
            linesBefore++;
        }

        toggle.logOwnerData("test\n");
        linesAfter = linesBefore;

        while (reader.readLine() != null){
            linesAfter++;
        }
        reader.close();

        assertTrue(linesBefore<linesAfter);
    }

    @Test
    public void testToggleLogsForNewFindOwnerAreGenerated() throws IOException {
        toggle.setOwnerToggle(true);
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/logging/newFindOwner.txt"));

        int linesBefore = 0;
        int linesAfter;

        while (reader.readLine() != null){
            linesBefore++;
        }

        toggle.logOwnerData("test\n");
        linesAfter = linesBefore;

        while (reader.readLine() != null){
            linesAfter++;
        }
        reader.close();

        assertTrue(linesBefore<linesAfter);
    }
}
