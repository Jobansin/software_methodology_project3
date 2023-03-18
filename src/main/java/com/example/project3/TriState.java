package com.example.project3;

import com.example.project3.NonResident;

/**
 Represents a TriState object, a child class of the Student object.
 @author David Harianto, Joban Singh
 **/
public class TriState extends NonResident {

    private String state;
    private static final int NY_DISCOUNT = 4000;
    private static final int CT_DISCOUNT = 5000;

    /**
     * This is a constructor that takes in 5 parameters for a student.
     *
     * @param first
     * @param last
     * @param birth
     * @param m
     * @param credits
     * @author David Harianto, Joban Singh
     */
    public TriState(String first, String last, String birth, String m, double credits, String state) {
        super(first, last, birth, m, credits);
        this.state = state.toUpperCase();
    }

    /**
     A method that returns the tuition due for a given student.
     @author David Harianto, Joban Singh
     **/
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(creditsEnrolled < 12)
            return super.tuitionDue(creditsEnrolled);
        else {
            if(state.equals("NY"))
                return super.tuitionDue(creditsEnrolled) - NY_DISCOUNT;
            else
                return super.tuitionDue(creditsEnrolled) - CT_DISCOUNT;
        }
    }

    /**
     A method that returns whether the state is in the Tri-state or not.
     @author David Harianto, Joban Singh
     **/
    public boolean validState() {
        return (state.equals("NY") || state.equals("CT"));
    }

    /**
     * This is a method that returns the student's information as well as what type of student they are.
     *
     * @author David Harianto, Joban Singh
     */
    @Override
    public String toString() {
        return super.toString() + " (non-resident) (tri-state:" + state + ")";
    }
}
