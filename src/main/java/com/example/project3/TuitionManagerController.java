package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitionManagerController {
    @FXML
    private TextField fileName;
    @FXML
    private Label fileFound;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField fname2;
    @FXML
    private TextField lname2;
    @FXML
    private DatePicker dob2;
    @FXML
    private TextField fname3;
    @FXML
    private TextField lname3;
    @FXML
    private DatePicker dob3;
    @FXML
    private ToggleGroup majors;
    @FXML
    private TextField creditCompleted;
    @FXML
    private TextField creditCompleted2;
    @FXML
    private TextField scholarshipAmt;
    @FXML
    private Label studentError;
    @FXML
    private Label studentError2;
    @FXML
    private Label studentError3;
    @FXML
    private ToggleGroup studentType;
    @FXML
    private ToggleGroup outsideState;
    @FXML
    private ToggleGroup state;
    @FXML
    private RadioButton tristate;
    @FXML
    private RadioButton newYork;
    @FXML
    private RadioButton connecticut;
    @FXML
    private RadioButton international;
    @FXML
    private CheckBox studyAbroad;
    @FXML
    private TextArea printArea;
    @FXML
    private MenuItem rbs;
    @FXML
    private MenuItem sas;
    @FXML
    private MenuItem sci;
    @FXML
    private MenuItem soe;
    private Roster roster = new Roster();
    private Enrollment enrollment = new Enrollment();

    @FXML
    protected void onFileSubmit() {
        if(!fileName.getText().isEmpty()) {
            try {
                Scanner scanner = new Scanner(new File("src/main/java/" + fileName.getText()));
                fileFound.setText("Students loaded to the roster.");
                fileFound.setTextFill(Color.GREEN);
                while (scanner.hasNextLine()) {
                    StringTokenizer list = new StringTokenizer(scanner.nextLine(), ",");
                    String studentType = list.nextToken();
                    if (studentType.equals("R")) {
                        Resident student = new Resident(list.nextToken(), list.nextToken(), list.nextToken(),
                                list.nextToken(), Double.parseDouble(list.nextToken()));
                        roster.add(student);
                    } else if (studentType.equals("I")) {
                        International student = new International(list.nextToken(), list.nextToken(), list.nextToken(),
                                list.nextToken(), Double.parseDouble(list.nextToken()), list.nextToken());
                        roster.add(student);
                    } else if (studentType.equals("T")) {
                        TriState student = new TriState(list.nextToken(), list.nextToken(), list.nextToken(),
                                list.nextToken(), Double.parseDouble(list.nextToken()), list.nextToken());
                        roster.add(student);
                    } else if (studentType.equals("N")) {
                        NonResident student = new NonResident(list.nextToken(), list.nextToken(), list.nextToken(),
                                list.nextToken(), Double.parseDouble(list.nextToken()));
                        roster.add(student);
                    }
                }
                fileName.clear();
            } catch (FileNotFoundException f) {
                fileFound.setText("File Not Found.");
                fileFound.setTextFill(Color.RED);
            }
        }
    }
    @FXML
    protected void addStudentSubmit() {
        studentError.setText("");
        if(fname.getText().isEmpty()) { studentError.setText("Missing Field(s)."); }
        else if(lname.getText().isEmpty()) { studentError.setText("Missing Field(s)."); }
        else if(dob.getValue() == null) { studentError.setText("Missing Field(s)."); }
        else if(creditCompleted.getText().isEmpty()) { studentError.setText("Missing Field(s)."); }
        else if(!new Date(String.valueOf(dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))).isValid())
            studentError.setText("DOB invalid: " + dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " not a valid calendar date!");
        else if(!roster.validDate(new Date(String.valueOf(dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))))))
            studentError.setText("DOB invalid: " + dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + " younger than 16 years old.");
        else {
            RadioButton selectedStudent = (RadioButton) studentType.getSelectedToggle();
            RadioButton selectedMajor = (RadioButton) majors.getSelectedToggle();
            try {
                int credits = Integer.parseInt(creditCompleted.getText());
                if(credits < 0)
                    studentError.setText("Credits completed invalid: cannot be negative!");
                else
                    addStudent(fname.getText().trim(), lname.getText().trim(), dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), selectedMajor.getText(), credits, selectedStudent);
            }
            catch (NumberFormatException e){
                studentError.setText("Credits completed invalid: not an integer!");
            }
        }
    }
    @FXML
    protected void removeStudent() {
        studentError.setText("");
        if(fname.getText().isEmpty()) { studentError.setText("Missing Field(s)."); }
        else if(lname.getText().isEmpty()) { studentError.setText("Missing Field(s)."); }
        else if(dob.getValue() == null) { studentError.setText("Missing Field(s)."); }
        else {
            Resident temp = new Resident(fname.getText(), lname.getText(), dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            if(roster.remove(temp)) {
                studentError.setText(temp.getProfile() + " removed from the roster.");
                //fname.clear();
                //lname.clear();
                //dob.setValue(null);
                //creditCompleted.clear();
            }
            else {
                if(roster.checkEmpty())
                    studentError.setText("Student roster is empty!");
                else
                    studentError.setText(temp.getProfile() + " is not in the roster.");
            }
        }
    }
    @FXML
    protected void changeStudentMajor() {
        studentError.setText("");
        if(fname.getText().isEmpty()) { studentError.setText("Missing Field(s)."); }
        else if(lname.getText().isEmpty()) { studentError.setText("Missing Field(s)."); }
        else if(dob.getValue() == null) { studentError.setText("Missing Field(s)."); }
        else {
            Resident temp = new Resident(fname.getText(), lname.getText(), dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            RadioButton selectedMajor = (RadioButton) majors.getSelectedToggle();
            if(roster.checkEmpty())
                studentError.setText("Student roster is empty!");
            else if(roster.majorChange(temp, selectedMajor.getText()))
                studentError.setText(temp.getProfile() + " major changed to " + selectedMajor.getText());
            else
                studentError.setText(temp.getProfile() + " is not in the roster.");
        }
    }
    @FXML
    protected void selectResident() {
        tristate.setDisable(true);
        tristate.setSelected(false);
        newYork.setDisable(true);
        newYork.setSelected(false);
        connecticut.setDisable(true);
        connecticut.setSelected(false);
        international.setDisable(true);
        international.setSelected(false);
        studyAbroad.setDisable(true);
        studyAbroad.setSelected(false);
    }
    @FXML
    protected void selectNonResident() {
        tristate.setDisable(false);
        international.setDisable(false);
        tristate.fire();
    }
    @FXML
    protected void addState() {
        newYork.setDisable(false);
        newYork.fire();
        connecticut.setDisable(false);
        studyAbroad.setSelected(false);
        studyAbroad.setDisable(true);
    }
    @FXML
    protected void addStudyAbroad() {
        studyAbroad.setDisable(false);
        newYork.setDisable(true);
        newYork.setSelected(false);
        connecticut.setDisable(true);
        connecticut.setSelected(false);
    }
    protected void addStudent(String first, String last, String date, String major, int credits, RadioButton student) {
        Student temp = null;
        if(student.getText().equals("Resident")) {
            temp = new Resident(first, last, date, major, credits);
        }
        else {
            RadioButton selectedStudent = (RadioButton) outsideState.getSelectedToggle();
            if(selectedStudent.getText().equals("Tristate")) {
                RadioButton selectedState = (RadioButton) state.getSelectedToggle();
                temp = new TriState(first, last, date, major, credits, selectedState.getText());
            }
            else {
                temp = new International(first, last, date, major, credits, String.valueOf(studyAbroad.isSelected()));
            }
        }
        if(roster.contains(temp))
            studentError.setText(temp.getProfile() + " is already in the roster.");
        else {
            roster.add(temp);
            studentError.setText(temp.getProfile() +" added to the roster.");
            //fname.clear();
            //lname.clear();
            //dob.setValue(null);
            //creditCompleted.clear();
        }
    }
    @FXML
    protected void enrollStudent(){
        studentError2.setText("");
        if(fname2.getText().isEmpty()) { studentError2.setText("Missing Field(s)."); }
        else if(lname2.getText().isEmpty()) { studentError2.setText("Missing Field(s)."); }
        else if(dob2.getValue() == null) { studentError2.setText("Missing Field(s)."); }
        else if(creditCompleted2.getText().isEmpty()) { studentError2.setText("Missing Field(s)."); }
        else {
            try {
                int credit = Integer.parseInt(creditCompleted2.getText());
                if(credit < 0)
                    studentError2.setText("Credits completed invalid: cannot be negative!");
                else {
                    Resident temp = new Resident(fname2.getText().trim(), lname2.getText().trim(), dob2.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                    if(!roster.contains(temp))
                        studentError2.setText("Cannot enroll: " + temp.getProfile() + " is not in the roster.");
                    else {
                        Student s = roster.getStudent(temp);
                        if(credit > 24 || credit < 3) {
                            studentError2.setText("(" + s.typeStudent() + ") " + credit + ": invalid credit hours.");
                        }
                        else if(s instanceof International && ((International) s).isStudyAbroad() && credit > 12) {
                            studentError2.setText("(" + s.typeStudent() + ") " + credit + ": invalid credit hours.");
                        }
                        else if(s instanceof International && !((International) s).isStudyAbroad() && credit < 12) {
                            studentError2.setText("(" + s.typeStudent() + ") " + credit + ": invalid credit hours.");
                        }
                        else {
                            studentError2.setText(s.getProfile() + " enrolled " + credit + " credits");
                            EnrollStudent temp2 = new EnrollStudent(s.getProfile(), credit);
                            if(enrollment.contains(temp2)) { enrollment.changeCredit(temp2, credit); }
                            else { enrollment.add(temp2); }
                        }
                    }
                }
            }
            catch (NumberFormatException e){
                studentError2.setText("Credits completed invalid: not an integer!");
            }
        }
    }
    @FXML
    protected void dropStudent() {
        studentError2.setText("");
        if(fname2.getText().isEmpty()) { studentError2.setText("Missing Field(s)."); }
        else if(lname2.getText().isEmpty()) { studentError2.setText("Missing Field(s)."); }
        else if(dob2.getValue() == null) { studentError2.setText("Missing Field(s)."); }
        else {
            EnrollStudent temp = new EnrollStudent(new Profile(fname2.getText().trim(), lname2.getText().trim(), new Date(dob2.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")))), 0);
            if(!enrollment.contains(temp))
                studentError2.setText(temp.getProfile() + " is not enrolled.");
            else {
                enrollment.remove(temp);
                studentError2.setText(temp.getProfile() + " dropped.");
            }
        }
    }
    @FXML
    protected void updateScholarship() {
        studentError3.setText("");
        if(fname3.getText().isEmpty()) { studentError2.setText("Missing Field(s)."); }
        else if(lname3.getText().isEmpty()) { studentError2.setText("Missing Field(s)."); }
        else if(dob3.getValue() == null) { studentError2.setText("Missing Field(s)."); }
        else if(scholarshipAmt.getText().isEmpty()) { studentError3.setText("Missing Field(s)."); }
        else {
            try {
                int scholarship = Integer.parseInt(scholarshipAmt.getText());
                Resident temp = new Resident(fname3.getText().trim(), lname3.getText().trim(), dob3.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                if(!enrollment.contains(new EnrollStudent(temp.getProfile(), 0)))
                    studentError3.setText(temp.getProfile() + " is not in the roster.");
                else {
                    Student s = roster.getStudent(temp);
                    if(!(s instanceof Resident)) {
                        studentError3.setText(s.getProfile() + " (" + s.typeStudent() + ") is not eligible for the scholarship.");
                    }
                    else if(scholarship > 10000 || scholarship <= 0) {
                        studentError3.setText(scholarship + ": invalid amount.");
                    }
                    else if(enrollment.getStudent(new EnrollStudent(temp.getProfile(), 0)).getCreditsEnrolled() < 12) {
                        studentError3.setText(s.getProfile() + " part time student is not eligible for the scholarship.");
                    }
                    else {
                        ((Resident) s).setScholarship(scholarship);
                        studentError3.setText(s.getProfile() + ": scholarship amount updated.");
                    }
                }
            }
            catch (NumberFormatException e){
                studentError3.setText("Amount is not an integer.");
            }
        }
    }
    @FXML
    protected void printProfile() {
        printArea.clear();
        printArea.setText(roster.print());
    }
    @FXML
    protected void printSchool() {
        printArea.clear();
        printArea.setText(roster.printBySchoolMajor());
    }
    @FXML
    protected void printStanding() {
        printArea.clear();
        printArea.setText(roster.printByStanding());
    }
    @FXML
    protected void printSchool1() {
        printSchool(rbs.getText());
    }
    @FXML
    protected void printSchool2() {
        printSchool(sas.getText());
    }
    @FXML
    protected void printSchool3() {
        printSchool(sci.getText());
    }
    @FXML
    protected void printSchool4() {
        printSchool(soe.getText());
    }
    protected void printSchool(String school) {
        printArea.setText(roster.printSchool(school));
    }
    @FXML
    protected void printEnrolled() {
        printArea.setText(enrollment.print());
    }
    @FXML
    protected void printTuitionDues() {
        printArea.setText(enrollment.printTuition(roster));
    }
    @FXML
    protected void endSemester() {
        printArea.setText(enrollment.printGraduation(roster));
    }
}