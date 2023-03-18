package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TuitionManagerController {
    @FXML
    private TextField fileName;
    @FXML
    private Label fileFound;
    private Roster roster = new Roster();

    @FXML
    protected void onFileSubmit() {
        try {
            Scanner scanner = new Scanner(new File("src/main/java/" + fileName.getText()));
            fileFound.setText("Students loaded to the roster.");
            fileFound.setTextFill(Color.GREEN);
            while (scanner.hasNextLine()) {
                StringTokenizer list = new StringTokenizer(scanner.nextLine(), ",");
                String studentType = list.nextToken();
                if(studentType.equals("R")) {
                    Resident student = new Resident(list.nextToken(), list.nextToken(), list.nextToken(),
                            list.nextToken(), Double.parseDouble(list.nextToken()));
                    roster.add(student);
                }
                else if(studentType.equals("I")) {
                    International student = new International(list.nextToken(), list.nextToken(), list.nextToken(),
                            list.nextToken(), Double.parseDouble(list.nextToken()), list.nextToken());
                    roster.add(student);
                }
                else if(studentType.equals("T")) {
                    TriState student = new TriState(list.nextToken(), list.nextToken(), list.nextToken(),
                            list.nextToken(), Double.parseDouble(list.nextToken()), list.nextToken());
                    roster.add(student);
                }
                else if(studentType.equals("N")) {
                    NonResident student = new NonResident(list.nextToken(), list.nextToken(), list.nextToken(),
                            list.nextToken(), Double.parseDouble(list.nextToken()));
                    roster.add(student);
                }
            }
        }
        catch(FileNotFoundException f) {
            fileFound.setText("File Not Found.");
            fileFound.setTextFill(Color.RED);
        }
    }
}