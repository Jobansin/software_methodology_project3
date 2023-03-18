package com.example.project3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 A class to run the Roster add method.
 @author David Harianto, Joban Singh
 **/
class RosterTest {

    /**
     A method to run testcases to check if students are added properly to the roster.
     @author David Harianto, Joban Singh
     **/
    @Test
    void add() {
        Roster roster = new Roster();
        International student1 = new International("Oliver", "Chang", "11/30/2000", "BAIT", 0, "true");
        assertTrue(roster.add(student1));

        International student2 = new International("Oliver", "Chang", "11/30/2000", "BAIT", 0, "true");
        assertFalse(roster.add(student2));

        TriState student3 = new TriState("Bob", "Smith", "11/30/2000", "BAIT", 0, "NY");
        assertTrue(roster.add(student3));

        TriState student4 = new TriState("Bob", "Jones", "11/30/2007", "BAIT", 0, "PA");
        assertFalse(roster.add(student4));

    }

    /**
     A method to run testcases to check if students are removed properly from the roster.
     @author David Harianto, Joban Singh
     **/
    @Test
    void remove() {
        Roster roster = new Roster();
        International student1 = new International("Oliver", "Chang", "11/30/2000", "BAIT", 0, "true");
        roster.add(student1);

        assertTrue(roster.remove(student1));
        assertFalse(roster.remove(student1));

        TriState student2 = new TriState("Bob", "Smith", "11/30/2000", "BAIT", 0, "NY");
        roster.add(student2);
        TriState student3 = new TriState("Bob", "Jones", "11/30/2000", "BAIT", 0, "NY");

        assertFalse(roster.remove(student3));
        assertTrue(roster.remove(student2));


    }
}