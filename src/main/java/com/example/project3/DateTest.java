package com.example.project3;

import com.example.project3.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 A class to run the Date isValid method.
 @author David Harianto, Joban Singh
 **/
class DateTest {

    /**
     A method that runs the test cases to see if Date isValid method works.
     @author David Harianto, Joban Singh
     **/
    @org.junit.jupiter.api.Test
    void isValid() {
        // Test cases for Date
        Date date1 = new Date("02/29/2015"); // Tests if a non-leap year and having the day 29 in the month Feb is valid.
        assertFalse(date1.isValid());

        Date date2 = new Date("02/00/2020");// Tests if having a 0 number for the date is valid.
        assertFalse(date2.isValid());

        Date date3 = new Date("13/01/2017");// Tests if having a month greater than 13 is valid.
        assertFalse(date3.isValid());

        Date date4 = new Date("01/01/-2000");// Tests if having a negative number for the year is valid.
        assertFalse(date4.isValid());

        Date date5 = new Date("02/20/3000");// Tests if having a single number for the year is valid.
        assertFalse(date5.isValid());

        Date date6 = new Date("02/29/2016");// Tests if a leap year and having the day 29 in the month Feb is valid.
        assertTrue(date6.isValid());

        Date date7 = new Date("09/2/2022");// Tests if having the day 31 is valid for January, March, May, July, August, October, December is valid.
        assertTrue(date7.isValid());
    }
}