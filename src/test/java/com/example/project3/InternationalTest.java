package com.example.project3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 A class to run the International tuitionDue method.
 @author David Harianto, Joban Singh
 **/
class InternationalTest {

    /**
     A method to run testcases to see if International tuitionDue method returns the right amount.
     @author David Harianto, Joban Singh
     **/
    @Test
    void tuitionDue() {
        // International Study Abroad
        International student1 = new International("Oliver", "Chang", "11/30/2000", "BAIT", 0, "true");
        assertEquals(5918, student1.tuitionDue(12));

        // International Not Study Abroad
        International student2 = new International("Mia", "Diaz", "6/30/2002", "BAIT", 0, "false");
        assertEquals(35655, student2.tuitionDue(12));
    }
}