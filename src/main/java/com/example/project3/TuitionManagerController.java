package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TuitionManagerController {
    @FXML
    private TextField fileName;
    @FXML
    private Button fileSubmit;
    @FXML
    private TextArea fileContents;

    @FXML
    protected void onFileSubmit() {
        try {
            Scanner scanner = new Scanner(new File("src/main/java/" + fileName.getText()));
            while (scanner.hasNextLine()) {
                fileContents.setText(fileContents.getText() + "\n" + scanner.nextLine());
            }
        }
        catch(FileNotFoundException f) {
            fileContents.setText(fileContents.getText() + "File \""+ fileName.getText() + "\" not found.");
        }
    }
}