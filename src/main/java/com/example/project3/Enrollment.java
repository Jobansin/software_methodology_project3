package com.example.project3;

import java.text.DecimalFormat;

/**
 Represents a Enrollment object which holds an array of EnrollStudent
 @author David Harianto, Joban Singh
 **/
public class Enrollment {
    private EnrollStudent[] enrollStudents;
    private int size;


    /**
     This method sets the array size to 4.
     @author David Harianto, Joban Singh
     **/
    public Enrollment() {
        size = 4;
        enrollStudents = new EnrollStudent[size];
    }

    /**
     This method increases the array capacity by 4.
     @author David Harianto, Joban Singh
     **/
    public void grow() {
        EnrollStudent[] temporary = new EnrollStudent[size + 4];
        for (int x = 0; x < size; x++){
            temporary[x] = enrollStudents[x];
        }
        enrollStudents = temporary;
        size += 4;
    }

    /**
     This method adds an EnrollStudent object to the end of the array.
     @author David Harianto, Joban Singh
     **/
    public void add(EnrollStudent enrollStudent){
        if(enrollStudents[size - 1] != null) {
            this.grow();
        }
        for (int x = 0; x < size; x++) {
            if (enrollStudents[x] == null) {
                enrollStudents[x] = enrollStudent;
                break;
            }
        }
    } //add to the end of array

    /**
     This method removes an EnrollStudent object from the array and places the last object in the
     array at the removed object's index.
     @author David Harianto, Joban Singh
     **/
    public void remove(EnrollStudent enrollStudent) {
        // Get index of last student
        int indexLastStudent = 0;
        EnrollStudent lastStudent = null;
        for (int x = 0; x < size; x++) {
            if(enrollStudents[x] != null) {
                indexLastStudent = x;
                lastStudent = enrollStudents[x];
            }
        }
        // Replace the removed student with the last student
        for (int x = 0; x < size; x++) {
            if(enrollStudents[x].equals(enrollStudent)) {
                enrollStudents[x] = lastStudent;
                enrollStudents[indexLastStudent] = null;
                break;
            }
        }
    } //move the last one in the array to replace the deleting index position

    /**
     This method checks if the array contains a specific EnrollStudent object.
     @author David Harianto, Joban Singh
     **/
    public boolean contains(EnrollStudent enrollStudent){
        if (!isEmpty()) {
            for (int x = 0; x < size; x++) {
                if (enrollStudents[x] != null) {
                    if (enrollStudents[x].equals(enrollStudent))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     This method returns the EnrollStudent object when found.
     @author David Harianto, Joban Singh
     **/
    public EnrollStudent getStudent(EnrollStudent enrollStudent) {
        for (int x = 0; x < size; x++) {
            if (enrollStudents[x] != null) {
                if (enrollStudents[x].equals(enrollStudent))
                    return enrollStudents[x];
            }
        }
        return null;
    }

    /**
     This method changes the credits enrolled for a given student.
     @author David Harianto, Joban Singh
     **/
    public void changeCredit(EnrollStudent enrollStudent, int credits){
        if (!isEmpty()) {
            for (int x = 0; x < size; x++) {
                if (enrollStudents[x] != null) {
                    if (enrollStudents[x].equals(enrollStudent)) {
                        enrollStudents[x].changeCreditsEnrolled(credits);
                        break;
                    }
                }
            }
        }
    }

    /**
     This method returns if the array is empty or not.
     @author David Harianto, Joban Singh
     **/
    public boolean isEmpty() {
        for (int x = 0; x < size; x++) {
            if (enrollStudents[x] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     This method prints the tuition due for enrolled students.
     @author David Harianto, Joban Singh
     **/
    public String printTuition(Roster roster) {
        if(isEmpty())
            return "Enrollment is empty!";
        else {
            StringBuilder string = new StringBuilder();
            string.append("** Tuition due **\n");
            for (int i = 0; i < size; i++) {
                if (enrollStudents[i] != null) {
                    DecimalFormat d = new DecimalFormat("'$'0.00");
                    Student temp = roster.getStudent(new Resident(enrollStudents[i].getProfile().getFirstname(),
                            enrollStudents[i].getProfile().getLastname(), enrollStudents[i].getProfile().getDob().toString()));
                    string.append(enrollStudents[i] + ": tuition due: " + d.format(temp.tuitionDue(enrollStudents[i].getCreditsEnrolled())) + "\n");
                }
            }
            string.append("* end of tuition due *");
            return string.toString();
        }
    }

    /**
     This method prints out all the EnrollStudent objects in the array that are eligible for graduation.
     @author David Harianto, Joban Singh
     **/
    public String printGraduation(Roster roster) {
        if(isEmpty())
            return "Enrollment is empty!";
        else {
            StringBuilder string = new StringBuilder();
            string.append("** list of students eligible for graduation **\n");
            for (int i = 0; i < size; i++) {
                if (enrollStudents[i] != null) {
                    int totalCredits = enrollStudents[i].getCreditsEnrolled() + roster.getStudent(
                            new Resident(enrollStudents[i].getProfile().getFirstname(), enrollStudents[i].getProfile().getLastname(),
                                    enrollStudents[i].getProfile().getDob().toString())).getCreditCompleted();
                    if (totalCredits >= 120) {
                        roster.getStudent(new Resident(enrollStudents[i].getProfile().getFirstname(), enrollStudents[i].getProfile().getLastname(),
                                enrollStudents[i].getProfile().getDob().toString())).setCredit(totalCredits);
                        string.append(roster.getStudent(new Resident(enrollStudents[i].getProfile().getFirstname(), enrollStudents[i].getProfile().getLastname(),
                                enrollStudents[i].getProfile().getDob().toString())) + "\n");
                    }
                }
            }
            return string.toString();
        }
    }

    /**
     This method prints out all the EnrollStudent objects in the array unsorted.
     @author David Harianto, Joban Singh
     **/
    public String print() {
        if(isEmpty())
            return "Enrollment is empty!";
        else {
            StringBuilder string = new StringBuilder();
            string.append("** Enrollment **\n");
            for (int i = 0; i < size; i++) {
                if (enrollStudents[i] != null)
                    string.append(enrollStudents[i] + "\n");
            }
            string.append("* End of enrollment *");
            return string.toString();
        }
    } //print the array as is without sorting
}