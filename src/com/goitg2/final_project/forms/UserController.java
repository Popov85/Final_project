package com.goitg2.final_project.forms;

import com.goitg2.final_project.application.Session;
import com.goitg2.final_project.application.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UserController {

        @FXML
        private AnchorPane form;

        @FXML
        private ProgressBar progressUnblock;

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
                Session session = new Session(email, card);
                try {
                        displayMessage(session.startSession());
                } catch (Exception e) {
                        displayWarning("Warning!", "Wrong parameter!", e.getMessage());
                }
        }

        @FXML
        private void cleanMainMessage(MouseEvent event) {
                mainMessageLabel.setText("");
        }

        @FXML
        private void cleanCardNumber(KeyEvent event) {
                cardTextField.clear();
        }

        private void displayWarning(String title, String header, String content) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(title);
                alert.setHeaderText(header);
                alert.setContentText(content);
                alert.showAndWait();
        }

        private void displayMessage(String message) throws InterruptedException {
                if (message.startsWith("DECLINE")) {
                        mainMessageLabel.setText("Your card has been declined!");
                        warningLabel.setVisible(true);
                        warningLabel.setText(message.substring(9));
                        warningExplainLabel.setVisible(true);
                        // TODO image
                        cardTextField.clear();
                } else if (message.startsWith("SUCCESS")) {
                        mainMessageLabel.setText(message.substring(9));
                        warningLabel.setVisible(false);
                        warningExplainLabel.setVisible(false);
                } else if (message.startsWith("TEMP")) {
                        tempLabel.setVisible(true);
                        form.setDisable(true);
                        warningLabel.setVisible(false);
                        warningExplainLabel.setVisible(false);
                        progressUnblock.setVisible(true);
                        progressUnblock.progressProperty().addListener(new ChangeListener<Number>() {
                                @Override
                                public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                                        if(t1.doubleValue()==1){
                                                progressUnblock.setVisible(false);
                                                form.setDisable(false);
                                                cardTextField.clear();
                                                tempLabel.setVisible(false);
                                                warningLabel.setVisible(false);
                                        }
                                }
                        });
                        Task task = createProgress();
                        progressUnblock.progressProperty().unbind();
                        progressUnblock.progressProperty().bind(task.progressProperty());
                        new Thread(task).start();
                } else if (message.startsWith("CONST")) {
                        form.setDisable(true);
                        warningLabel.setVisible(true);
                        warningLabel.setText(message.substring(7));
                        warningExplainLabel.setVisible(true);
                        warningExplainLabel.setText("Address the system admin to get unblocked!");
                        tempLabel.setVisible(false);
                }
        }

        public Task createProgress(){
                return new Task() {
                        @Override
                        protected Object call() throws Exception {
                                long time = User.BLOCKING_TIME/1000;
                                for (int i = 1; i <= time; i++) {
                                        updateProgress(i, time);
                                        Thread.sleep(1000);
                                }
                                return true;
                        }
                };
        }
}
