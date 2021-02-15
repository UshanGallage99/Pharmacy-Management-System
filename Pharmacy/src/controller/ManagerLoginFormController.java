package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ManagerLoginFormController {
    public JFXButton btnClose;
    public AnchorPane root;
    public JFXTextField txtUserName;
    public JFXButton btnLog;
    public JFXButton btnClose1;
    public JFXPasswordField txtPw;

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage exitStage=(Stage)btnClose.getScene().getWindow();
        exitStage.close();
    }

    public void btnLogOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtUserName.getText().trim();
        String password = txtPw.getText().trim();
        if (email.length() > 0 && password.length() > 0) {

            if (email.equalsIgnoreCase("green123")
                    && password.equalsIgnoreCase("12345")) {

                this.root.getChildren().clear();
                this.root.getChildren()
                        .add(FXMLLoader.
                                load(this.getClass().getResource("/view/ManagementMainForm.fxml")));

            } else {
                new Alert(Alert.AlertType.WARNING, "Wrong Password Or User Name !",
                        ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty !",
                    ButtonType.OK).show();

        }
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"))));
    }
}

