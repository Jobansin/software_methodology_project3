package com.example.project3;

/**
 Represents an International object, a child class of the Student object.
 @author David Harianto, Joban Singh
 **/
public class International extends NonResident {

    private boolean isStudyAbroad;
    private static final int HEALTH_FEE = 2650;
    private static final int UNIVERSITY_FEE = 3268;

    /**
     * This is a constructor that takes in 5 parameters for a student.
     *
     * @param first
     * @param last
     * @param birth
     * @param major
     * @param credits
     * @author David Harianto, Joban Singh
     */
    public International(String first, String last, String birth, String major, double credits) {
        super(first, last, birth, major, credits);
        isStudyAbroad = false;
    }

    /**
     * This is a constructor that takes in 6 parameters for a student.
     *
     * @param first
     * @param last
     * @param birth
     * @param major
     * @param credits
     * @param studyAbroad
     * @author David Harianto, Joban Singh
     */
    public International(String first, String last, String birth, String major, double credits, String studyAbroad) {
        super(first, last, birth, major, credits);
        isStudyAbroad = Boolean.parseBoolean(studyAbroad);
    }

    /**
     * This is a method that returns what type of student they are.
     *
     * @author David Harianto, Joban Singh
     */
    @Override
    public String typeStudent() {
        if(isStudyAbroad)
            return "International student study abroad";
        else
            return "International student";
    }

    /**
     A method that returns the tuition due for a given student.
     @author David Harianto, Joban Singh
     **/
    @Override
    public double tuitionDue(int creditsEnrolled) {
        if(!isStudyAbroad) {
            return super.tuitionDue(creditsEnrolled) + HEALTH_FEE;
        }
        else {
            return HEALTH_FEE + UNIVERSITY_FEE;
        }
    }

    /**
     * This is a method that returns if an International student is studying abroad or not.
     *
     * @author David Harianto, Joban Singh
     */
    public boolean isStudyAbroad() {
        return isStudyAbroad;
    }

    /**
     * This is a method that returns the student's information as well as what type of student they are.
     *
     * @author David Harianto, Joban Singh
     */
    @Override
    public String toString() {
        if(isStudyAbroad)
            return super.toString() + " (non-resident) (international:studyAbroad)";
        else
            return super.toString() + " (non-resident) (international)";
    }
}
