package org.springframework.samples.petclinic.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Simple tests for the methods in the Person class
 */

public class PersonTests {

    private Person testPerson;

    @Before
    public void setUp() {
        testPerson = new Person();
    }

    @Test
    public void testGetNameWithoutSetting() {

        //testing an unset first name
        assertEquals(null,testPerson.getFirstName());

        //testing an unset last name
        assertEquals(null,testPerson.getLastName());
    }

    @Test
    public void testSetAndGetFirstName() {

        //testing with an empty string and then with a regular string

        testPerson.setFirstName("");
        assertEquals("", testPerson.getFirstName());

        testPerson.setFirstName("Bob");
        assertEquals("Bob", testPerson.getFirstName());

    }

    @Test
    public void testSetAndGetLastName() {

        //testing with an empty string and then with a regular string

        testPerson.setLastName("");
        assertEquals("", testPerson.getLastName());

        testPerson.setLastName("Smith");
        assertEquals("Smith", testPerson.getLastName());

    }
}
