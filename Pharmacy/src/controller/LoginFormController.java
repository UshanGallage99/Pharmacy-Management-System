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

public class LoginFormController {
    public JFXButton btnClose;
    public JFXButton btnLog;
    public JFXTextField txtUserName;
    //public JFXTextField txtPassword;
    public AnchorPane root;
    public JFXPasswordField txtPw;

    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage exitStage=(Stage)btnClose.getScene().getWindow();
        exitStage.close();
    }

    public void btnLogOnAction(ActionEvent actionEvent) throws IOException {
        String user = txtUserName.getText().trim();
        String password = txtPw.getText().trim();
        if (user.length() > 0 && password.length() > 0) {

            if (user.equalsIgnoreCase("green123")
                    && password.equalsIgnoreCase("1234")) {

                this.root.getChildren().clear();
                this.root.getChildren()
                        .add(FXMLLoader.
                                load(this.getClass().getResource("/view/MainForm.fxml")));

            } else {
                new Alert(Alert.AlertType.WARNING, "Wrong Password Or User Name !",
                        ButtonType.OK).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty !",
                    ButtonType.OK).show();

        }
    }
}
