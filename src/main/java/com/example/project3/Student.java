package com.example.project3;

/**
 Represents a Student object which holds a Profile object, a major, and credits completed.
 @author David Harianto, Joban Singh
 **/
public abstract class Student implements Comparable<Student> {

    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    /**
     This is a constructor that takes in 5 parameters for a student.
     @author David Harianto, Joban Singh
     **/
    public Student (String first, String last, String birth, String m, double credits) {
        first = first.substring(0, 1).toUpperCase() + first.substring(1).toLowerCase();
        last = last.substring(0, 1).toUpperCase() + last.substring(1).toLowerCase();
        profile = new Profile(first, last, new Date(birth));
        try {
            major = Major.valueOf(m.toUpperCase());
        } catch (IllegalArgumentException e) {
            major = Major.UNKNOWN;
        }
        creditCompleted = (int) credits;
    }
    /**
     This is a constructor that takes in 3 parameters for a student.
     @author David Harianto, Joban Singh
     **/
    public Student (String first, String last, String birth) {
        first = first.substring(0, 1).toUpperCase() + first.substring(1).toLowerCase();
        last = last.substring(0, 1).toUpperCase() + last.substring(1).toLowerCase();
        profile = new Profile(first, last, new Date(birth));
    }
    /**
     This is a constructor that takes in 4 parameters for a student.
     @author David Harianto, Joban Singh
     **/
    public Student (String first, String last, String birth, String m) {
        first = first.substring(0, 1).toUpperCase() + first.substring(1).toLowerCase();
        last = last.substring(0, 1).toUpperCase() + last.substring(1).toLowerCase();
        profile = new Profile(first, last, new Date(birth));
        try {
            major = Major.valueOf(m.toUpperCase());
        } catch (IllegalArgumentException e) {
            major = Major.UNKNOWN;
        }
    }

    /**
     This method checks if the major is valid.
     @author David Harianto, Joban Singh
     **/
    public boolean validMajor(Major m) { // Check if the major is listed in the enum class for the majors
        if(m == Major.CS || m == Major.EE || m == Major.ITI || m == Major.MATH || m == Major.BAIT) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     This method checks if the amount of credits is valid.
     @author David Harianto, Joban Singh
     **/
    public boolean isValid(int creditEnrolled) { // Check if the credits are valid, must not be negative
        return (creditEnrolled >= 0);
    } //polymorphism

    public boolean changeMajor(Major m) {
        this.major = m;
        return true;
    }

    /**
     This method checks what the standing is of the student.
     @author David Harianto, Joban Singh
     **/
    public String getStanding() {
        if(this.creditCompleted < 30) {
            return "Freshman";
        }
        else if(this.creditCompleted < 60) {
            return "Sophomore";
        }
        else if(this.creditCompleted < 90) {
            return "Junior";
        }
        else {
            return "Senior";
        }
    }
    /**
     This method sets the amount of credits.
     @author David Harianto, Joban Singh
     **/
    public void setCredit(int credits) {
        creditCompleted = credits;
    }
    /**
     This method returns the profile.
     @author David Harianto, Joban Singh
     **/
    public Profile getProfile() {
        return this.profile;
    }
    /**
     This method returns the major.
     @author David Harianto, Joban Singh
     **/
    public Major getMajor() {
        return this.major;
    }
    /**
     This method returns the amount of credits completed.
     @author David Harianto, Joban Singh
     **/

    public int getCreditCompleted() {
        return this.creditCompleted;
    }

    /**
     This method returns the major code, school name, and credits completed.
     @author David Harianto, Joban Singh
     **/
    @Override
    public String toString() {
        // return project1.Student info
        return this.profile.toString() + " (" + this.major.getCode() + " " + this.major +
                " " + this.major.getSchool() + ")" + " credits completed: " + creditCompleted
                + " (" + this.getStanding() + ")";
    }

    /**
     This method checks if the student given is the same student.
     @author David Harianto, Joban Singh
     **/
    @Override
    public boolean equals(Object object) {
        // Compare two students
        if(this == object) {
            return true;
        }
        if(!(object instanceof Student)) {
            return false;
        }
        Student s = (Student) object;
        return this.profile.equals(s.profile);
    }
    /**
     This method returns a number when comparing a given student.
     @author David Harianto, Joban Singh
     **/
    @Override
    public int compareTo(Student s) {
        // return int when comparing students
        return this.profile.compareTo(s.profile);
    }

    /**
     An abstract method that returns the tuition due for a given student.
     @author David Harianto, Joban Singh
     **/
    public abstract double tuitionDue(int creditsEnrolled); //polymorphism

    /**
     An abstract method that returns whether a given student is a resident or not.
     @author David Harianto, Joban Singh
     **/
    public abstract boolean isResident(); //polymorphism

    /**
     An abstract method that returns a string of the type of student that they are (i.e. Resident, Non-Resident, etc).
     @author David Harianto, Joban Singh
     **/
    public abstract String typeStudent();
}