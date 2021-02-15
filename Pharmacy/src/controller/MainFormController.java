package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainFormController {
    public AnchorPane root;
    public Label lblTime;
    public Label lblDate;
    public JFXButton btnLogOut;
    public JFXButton btnChaneling;
    public ImageView btnChaneling1;

    public void openCashierForm(MouseEvent mouseEvent) throws IOException {
        setUI("CashierMainForm.fxml");
    }

    public void openChannelingForm(MouseEvent mouseEvent) throws IOException {
        setUI("ChannelingMainForn.fxml");

    }

    public void openManagementForm(MouseEvent mouseEvent) throws IOException {
        setUI("ManagerLoginForm.fxml");
    }
    private void setUI(String location) throws IOException {
        this.root.getChildren().clear();
        this.root.getChildren()
                .add(FXMLLoader.
                        load(this.getClass().getResource("/view/" +
                                location)));
    }
    public void initialize() throws IOException {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

        Timeline date = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalDate currentDate = LocalDate.now();
            lblDate.setText(currentDate.getDayOfMonth() + "-" + currentDate
                    .getMonthValue() + "-" + currentDate.getYear());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        date.setCycleCount(Animation.INDEFINITE);
        date.play();
        setUI("DefaultForm.fxml");

    }

    public void back(MouseEvent mouseEvent) throws IOException {
         setUI("DefaultForm.fxml");
    }

    public void LogOut(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"))));
    }
}
