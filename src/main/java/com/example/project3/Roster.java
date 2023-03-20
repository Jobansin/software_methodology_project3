package com.example.project3;

/**
 Represents a Roster object which holds an array of Students
 @author David Harianto, Joban Singh
 **/
public class Roster {
    private Student[] roster;
    private int size;
    private static final int NOT_VALID = -1;
    private static final int MINIMUM_AGE = 16;

    /**
     * This method sets the roster size to 4.
     *
     * @author David Harianto, Joban Singh
     **/
    public Roster() {
        size = 4;
        roster = new Student[size];
    }

    /**
     * This method searches for a given student on the roster.
     *
     * @author David Harianto, Joban Singh
     **/
    private int find(Student student) {
        for (int x = 0; x < size; x++) {
            if (roster[x].getProfile().equals(student.getProfile())) {
                return x;
            }
        }
        return NOT_VALID;
    }
    //search the given student in roster


    /**
     * This method increases the array capacity by 4.
     *
     * @author David Harianto, Joban Singh
     **/
    private void grow() {
        Student[] temporary = new Student[size + 4];
        for (int x = 0; x < size; x++) {
            temporary[x] = roster[x];
        }
        roster = temporary;
        size += 4;
    } //increase the array capacity by 4

    /**
     * This method adds a student to the end of the roster.
     *
     * @author David Harianto, Joban Singh
     **/
    public boolean add(Student student) {
        if (roster[size - 1] != null) {
            this.grow();
        }
        for (int x = 0; x < size; x++) {
            if (roster[x] == null) {
                // Check if credits are valid
                if (student.isValid(student.getCreditCompleted()) == false) {
                    return false;
                }
                // Check if major is valid
                else if (student.getMajor() == Major.UNKNOWN) {
                    return false;
                }
                // Check if student already exists
                else if (this.contains(student)) {
                    return false;
                } else if (student.getProfile().getDob().isValid() == false) {
                    return false;
                } else if (validDate(student.getProfile().getDob()) == false) {
                    return false;
                } else {
                    roster[x] = student;
                    //studentAdded(student);
                    break;
                }
            }
        }
        return true;
    } //add student to end of array

    /**
     * This method prints that the given student was added to the list.
     *
     * @author David Harianto, Joban Singh
     **/
    public void studentAdded(Student student) {
        System.out.println(student.getProfile() + " added to the roster.");
    }

    /**
     * This method prints the amount of credits is invalid.
     *
     * @author David Harianto, Joban Singh
     **/
    public void badCredit(boolean credit) {
        if (!credit) {
            System.out.println("Credits completed invalid: cannot be negative!");
        }
    }

    /**
     * This method checks if the DOB is valid.
     *
     * @author David Harianto, Joban Singh
     **/
    public boolean validDate(Date dob) {
        Date date = new Date();
        date.changeYear(date.getYear() - MINIMUM_AGE);
        if (dob.compareTo(date) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method prints that the date provided is not a valid date.
     *
     * @author David Harianto, Joban Singh
     **/
    public void badDate(boolean date, Student student) {
        if (!date) {
            System.out.println("DOB invalid: " + student.getProfile().getDob() + " not a valid calendar date!");
        }
    }

    /**
     * This method prints that student is underage (younger than 16).
     *
     * @author David Harianto, Joban Singh
     **/
    public void underAge(boolean date, Student student) {
        if (!date) {
            System.out.println("DOB invalid: " + student.getProfile().getDob() + " younger than 16 years old.");
        }
    }

    /**
     * This method prints that major code is invalid.
     *
     * @author David Harianto, Joban Singh
     **/
    public void badMajor(Student student) {
        System.out.println("Major code invalid: " + student.getMajor());
    }

    /**
     * This method prints that student is already in roster.
     *
     * @author David Harianto, Joban Singh
     **/
    public void studentExists(Student student) {
        if (this.contains(student)) {
            System.out.println(student.getProfile() + " is already in the roster.");
        }
    }

    /**
     * This method prints that student is not in roster.
     *
     * @author David Harianto, Joban Singh
     **/
    public void invalidStudent(boolean invalid, Student student) {
        if (!invalid) {
            System.out.println(student.getProfile() + " is not in the roster.");
        }
    }

    /**
     * This method removes a student from the roster and maintains the order after they are removed.
     *
     * @author David Harianto, Joban Singh
     **/
    public boolean remove(Student student) {
        if (checkEmpty()) {
            return false;
        } else if (!this.contains(student)) {
            return false;
        } else {
            Student[] temporary = new Student[size];
            Student removed = null;
            int index = 0;
            for (int x = 0; x < size; x++) {
                if (roster[x] != null) {
                    if (!roster[x].equals(student)) {
                        temporary[index] = roster[x];
                        index++;
                    } else {
                        removed = roster[x];
                    }
                }
            }
            roster = temporary;
            return true;
        }
    }//maintain the order after remove


    /**
     * This method prints the student removed from the roster.
     *
     * @author David Harianto, Joban Singh
     **/
    public void studentRemoved(Student student) {
        System.out.println(student.getProfile() + " removed from the roster.");
    }

    /**
     * This method checks in the roster contains a student.
     *
     * @author David Harianto, Joban Singh
     **/
    public boolean contains(Student student) {
        for (int x = 0; x < size; x++) {
            if (roster[x] != null) {
                if (roster[x].equals(student))
                    return true;
            }
        }
        return false;
    } //if the student is in roster

    /**
     * This method prints out that roster list is empty.
     *
     * @author David Harianto, Joban Singh
     **/
    public void empty() {
        System.out.println("Student roster is empty!");
    }

    /**
     * This method returns if the array is empty or not.
     *
     * @author David Harianto, Joban Singh
     **/
    public boolean checkEmpty() {
        for (int x = 0; x < size; x++) {
            if (this.roster[x] != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method changes a students major.
     *
     * @author David Harianto, Joban Singh
     **/
    public boolean majorChange(Student student, String major) {
        if (checkEmpty()) {
            //return "Student roster is empty!";
            return false;
        } else if (!this.contains(student)) {
            //return student.getProfile() + " is not in the roster.";
            return false;
        } else {
            //return roster[this.find(student)].getProfile() + " major changed to " + major.toUpperCase();
            roster[this.find(student)].changeMajor(Major.valueOf(major.toUpperCase()));
            return true;
        }
    }

    /**
     * This method returns the student object when found.
     *
     * @author David Harianto, Joban Singh
     **/
    public Student getStudent(Student student) {
        for (int x = 0; x < size; x++) {
            if (roster[x] != null) {
                if (roster[x].equals(student))
                    return roster[x];
            }
        }
        return null;
    }

    /**
     * This method returns the last student.
     *
     * @author David Harianto, Joban Singh
     **/
    public int lastStudent() {
        int lastStudent = 0;
        for (int i = 0; i < size; i++) {
            if (roster[i] != null)
                lastStudent = i;
        }
        return lastStudent;
    }

    /**
     * This method prints out the school which the student is in.
     *
     * @author David Harianto, Joban Singh
     **/
    public String printSchool(String school) {
        if (checkEmpty()) {
            return "Student roster is empty!";
        } else if (!school.trim().toUpperCase().equals("SAS") && !school.trim().toUpperCase().equals("SOE")
                && !school.trim().toUpperCase().equals("RBS") && !school.trim().toUpperCase().equals("SC&I")) {
            return "School doesn't exist: " + school;
        } else {
            if (lastStudent() > 0) {
                for (int i = 0; i <= lastStudent(); i++) {
                    for (int j = 1; j <= (lastStudent()); j++) {
                        if (roster[j - 1].compareTo(roster[j]) > 0) {
                            Student temporary = roster[j - 1];
                            roster[j - 1] = roster[j];
                            roster[j] = temporary;
                        }
                    }
                }
            }
            StringBuilder string = new StringBuilder();
            string.append("* Students in " + school + " *\n");
            for (int i = 0; i < size; i++) {
                if (roster[i] != null) {
                    if (roster[i].getMajor().getSchool().equals(school.trim().toUpperCase())) {
                        string.append(roster[i] + "\n");
                    }
                }
            }
            string.append("* end of list **");
            return string.toString();
        }

    }

    /**
     * This method prints the roster sorted by profile.
     *
     * @author David Harianto, Joban Singh
     **/
    public String print() {
        if (checkEmpty()) {
            return "Student roster is empty!";
        } else {
            if (lastStudent() > 0) {
                for (int i = 0; i <= lastStudent(); i++) {
                    for (int j = 1; j <= lastStudent(); j++) {
                        if (roster[j - 1].compareTo(roster[j]) > 0) {
                            Student temporary = roster[j - 1];
                            roster[j - 1] = roster[j];
                            roster[j] = temporary;
                        }
                    }
                }
            }
            StringBuilder string = new StringBuilder();
            string.append("* Student roster sorted by last name, first name, DOB **\n");
            for (int i = 0; i < size; i++) {
                if (roster[i] != null)
                    string.append(roster[i] + "\n");
            }
            string.append("* end of roster **");
            return string.toString();
        }
    } //print roster sorted by profiles

    /**
     * This method prints the roster sorted by school major.
     *
     * @author David Harianto, Joban Singh
     **/
    public String printBySchoolMajor() {
        if (checkEmpty()) {
            return "Student roster is empty!";
        } else {
            if (lastStudent() > 0) {
                for (int i = 0; i <= lastStudent(); i++) {
                    for (int j = 1; j <= lastStudent(); j++) {
                        if (roster[j - 1].getMajor().getSchool().compareTo(roster[j].getMajor().getSchool()) > 0) {
                            Student temporary = roster[j - 1];
                            roster[j - 1] = roster[j];
                            roster[j] = temporary;
                        } else if (roster[j - 1].getMajor().getSchool().compareTo(roster[j].getMajor().getSchool()) == 0) {
                            if (roster[j - 1].getMajor().compareTo(roster[j].getMajor()) > 0) {
                                Student temporary = roster[j - 1];
                                roster[j - 1] = roster[j];
                                roster[j] = temporary;
                            } else if (roster[j - 1].getMajor().compareTo(roster[j].getMajor()) == 0) {
                                if (roster[j - 1].compareTo(roster[j]) > 0) {
                                    Student temporary = roster[j - 1];
                                    roster[j - 1] = roster[j];
                                    roster[j] = temporary;
                                }
                            }
                        }
                    }
                }
            }
            StringBuilder string = new StringBuilder();
            string.append("* Student roster sorted by school, major **\n");
            for (int i = 0; i < size; i++) {
                if (roster[i] != null)
                    string.append(roster[i] + "\n");
            }
            string.append("* end of roster **");
            return string.toString();
        }

    } //print roster sorted by school major

    /**
     * This method prints the roster sorted by standing.
     *
     * @author David Harianto, Joban Singh
     **/
    public String printByStanding() {
        if (checkEmpty()) {
            return "Student roster is empty!";
        } else {
            if (lastStudent() > 0) {
                for (int i = 0; i <= lastStudent() ; i++) {
                    for (int j = 1; j <= lastStudent(); j++) {
                        if (roster[j - 1].getStanding().compareTo(roster[j].getStanding()) > 0) {
                            Student temporary = roster[j - 1];
                            roster[j - 1] = roster[j];
                            roster[j] = temporary;
                        } else if (roster[j - 1].getStanding().compareTo(roster[j].getStanding()) == 0) {
                            if (roster[j - 1].compareTo(roster[j]) > 0) {
                                Student temporary = roster[j - 1];
                                roster[j - 1] = roster[j];
                                roster[j] = temporary;
                            }
                        }
                    }
                }
            }
            StringBuilder string = new StringBuilder();
            string.append("* Student roster sorted by standing **\n");
            for (int i = 0; i < size; i++) {
                if (roster[i] != null)
                    string.append(roster[i] + "\n");
            }
            string.append("* end of roster **");
            return string.toString();
        }
    } //print roster sorted by standing
}