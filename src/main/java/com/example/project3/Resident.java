package com.example.project3;

/**
 Represents a Resident object, a child class of the Student object.
 @author David Harianto, Joban Singh
 **/
public class Resident extends Student {

    private int scholarship;
    private static final int UNIVERSITY_FEE = 3268;
    private static final int FULL_TIME_TUITION = 12536;
    private static final int PART_TIME_TUITION = 404;
    private static final int MAX_CREDITS = 16;

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
    public Resident(String first, String last, String birth, String m, double credits) {
        super(first, last, birth, m, credits);
        scholarship = 0;
    }

    /**
     * This is a constructor that takes in 3 parameters for a student.
     *
     * @param first
     * @param last
     * @param birth
     * @author David Harianto, Joban Singh
     */
    public Resident(String first, String last, String birth) {
        super(first, last, birth);
        scholarship = 0;
    }

    /**
     A method that returns the tuition due for a given student.
     @author David Harianto, Joban Singh
     **/
    @Override
    public double tuitionDue(int creditsEnrolled) {
        double tuition = 0;
        if(creditsEnrolled < 12) {
            tuition += UNIVERSITY_FEE * 0.80; // 80% of full-time rate
            tuition += PART_TIME_TUITION * creditsEnrolled;
        }
        else {
            tuition += FULL_TIME_TUITION;
            tuition += UNIVERSITY_FEE;
            if(creditsEnrolled > MAX_CREDITS)
                tuition += (creditsEnrolled - MAX_CREDITS) * PART_TIME_TUITION;
            tuition = tuition - scholarship;
        }
        return tuition;
    }

    /**
     * This is a method that returns if they are a resident or not.
     *
     * @author David Harianto, Joban Singh
     */
    @Override
    public boolean isResident() {
        return true;
    }

    /**
     * This is a method that returns what type of student they are.
     *
     * @author David Harianto, Joban Singh
     */
    @Override
    public String typeStudent() {
        return "Resident";
    }

    /**
     * This is a method that sets the scholarship amount for the Resident object.
     *
     * @author David Harianto, Joban Singh
     */
    public void setScholarship(int amount) {
        scholarship = amount;
    }

    /**
     * This is a method that returns the student's information as well as what type of student they are.
     *
     * @author David Harianto, Joban Singh
     */
    @Override
    public String toString() {
        return super.toString() + " (resident)";
    }

}
