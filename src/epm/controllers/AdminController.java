
package epm.controllers;


import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML // fx:id="eventButton"
    private JFXButton eventButton; // Value injected by FXMLLoader

    @FXML // fx:id="loginSeparator"
    private Pane loginSeparator; // Value injected by FXMLLoader

    @FXML // fx:id="btnExit"
    private Circle btnExit; // Value injected by FXMLLoader

    @FXML
    void showEvent(ActionEvent event) {
        callStage("epm/views/eventWindow.fxml");

    }

    @FXML
    void handleMouseEvent(MouseEvent event) {
        if(event.getSource() == btnExit){
            System.exit(0);
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    void onSetSalary(ActionEvent event) {
        callStage("epm/views/setSalary.fxml");

    }
    @FXML
    void onChangePass(ActionEvent event) {
    callStage("epm/views/passwordWindow.fxml");

    }
    @FXML
    void onEmployee(ActionEvent event) {
        System.out.println("EMployee");
       callStage("epm/views/empDetails.fxml");

    }
    @FXML
    void onBonus(ActionEvent event) {
        callStage("epm/views/attendance/overTime.fxml");
    }
    @FXML
    void onManageEvent(ActionEvent event) {
        callStage("epm/views/eventManage.fxml");

    }


    @FXML
    void onPayslip(ActionEvent event) {
        callStage("epm/views/payslip.fxml");

    }

    @FXML
    void onReport(ActionEvent event) {
        callStage("epm/views/attendanceReport.fxml");

    }

    @FXML
    void onSignOut(ActionEvent event) {
        callStage("epm/views/login.fxml");
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public  void callStage(String url){
        Parent root;
        try {
            Stage stage=new Stage();
            root= FXMLLoader.load(getClass().getClassLoader().getResource(url));
            root.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                }
            });
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            new FadeIn(root).play();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
