package com.goitg2.final_project.forms;

import com.goitg2.final_project.application.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UserController {

        @FXML
        private AnchorPane form;

        @FXML
        private Label mainMessageLabel;

        @FXML
        private Label warningLabel;

        @FXML
        private Label warningExplainLabel;

        @FXML
        private Label tempLabel;

        @FXML
        private TextField cardTextField;

        @FXML
        private TextField emailTextField;

        @FXML
        private void cleanForm(ActionEvent event) {
                emailTextField.clear();
                cardTextField.clear();
        }

        @FXML
        private void getData(ActionEvent event) {
                String email = emailTextField.getText();
                String card = cardTextField.getText();
                Session session = new Session(email,card);
                try {
                        displayMessage(session.startSession());
                } catch (Exception e) {
                        displayWarning("Warning!", "Wrong parameter!", e.getMessage());
                }
        }

        private void displayWarning(String title, String header, String content) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.showAndWait();
        }

        private void displayMessage(String message) {
                if (message.startsWith("DECLINE")) {
                        mainMessageLabel.setText("Your card has been declined!");
                        warningLabel.setVisible(true);
                        warningLabel.setText(message.substring(9));
                        warningExplainLabel.setVisible(true);
                        // TODO image
                } else if (message.startsWith("SUCCESS")) {
                        mainMessageLabel.setText(message.substring(9));
                } else if (message.startsWith("TEMP")) {
                        tempLabel.setVisible(true);
                        // TODO  disable everything
                        form.setDisable(true);
                        warningExplainLabel.setVisible(false);
                }
        }
}
