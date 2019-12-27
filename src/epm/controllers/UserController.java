/**
 * Sample Skeleton for 'user.fxml' Controller Class
 */
package epm.controllers;


import animatefx.animation.FadeIn;
import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import epm.database.DAO;
import epm.models.EventManage;
import epm.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.sf.jasperreports.engine.JRException;

public class UserController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Pane loginSeparator;

    @FXML
    private JFXButton eventButton;

    @FXML
    private Circle btnExit;
    @FXML
    private Label lblName;
    private User user;
    int eventNum;
    @FXML
    void handleMouseEvent(MouseEvent event) {
        if(event.getSource() == btnExit){
            System.exit(0);
        }
    }
    @FXML
    void onAttendance(ActionEvent event) {

    }

    @FXML
    void onChangePassword(ActionEvent event) {
        callStage("epm/views/passwordWindow.fxml");
    }

    @FXML
    void onManageLeave(ActionEvent event) {
        callStageManage("epm/views/attendance/manageLeave.fxml");
    }

    @FXML
    void onReport(ActionEvent event) {
        try {
            new PrintReport().showReport();

        } catch (JRException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onSalaryDetails(ActionEvent event) {

    }

    @FXML
    void onMyAccount(ActionEvent event) {

        callStageMyAccount("epm/views/myAccount.fxml");
    }
    @FXML
    void onSignOut(ActionEvent event) {
        callStage("epm/views/login.fxml");
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @FXML
    void showEvent(ActionEvent event) {
        callStage("epm/views/eventWindow.fxml");
    }

    @FXML
    void onEntryTime(ActionEvent event) throws SQLException {
        String date = mGetDate();
        String time = mGetTime();
        boolean b = DAO.getObjectRefrence().insertEntryTime(user.getEmpcode(), date, time);
        if (b) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Succesfully :" + date + " ," + time
                    + "\n" + "empcode:" + user.getEmpcode());
            alert.show();
        }
    }


    @FXML
    void onExitTime(ActionEvent event) throws SQLException {
        String date = mGetDate();
        String time = mGetTime();
        boolean b = DAO.getObjectRefrence().updateExitTime(user.getEmpcode(), date, time);
        if (b) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Succesfully :" + date + " ," + time
                    + "\n" + "empcode:" + user.getEmpcode());
            alert.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<EventManage> list=DAO.getObjectRefrence().selectEvent();
            for(EventManage v:list){
                eventNum++;

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @FXML


    public void callStage(String url) {
        Parent root;
        try {
            Stage stage = new Stage();
            root = FXMLLoader.load(getClass().getClassLoader().getResource(url));
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
    public void callStageMyAccount(String url){
        Parent root;
        try {
            Stage stage=new Stage();
            FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource(url));
            root= loader.load();
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
            MyAccountCtrl ctrl = loader.getController();
            ctrl.setEmpid(user.getEmpcode());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            new FadeIn(root).play();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void callStageManage(String url) {
        Parent root;
        try {
            Stage stage=new Stage();
            FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource(url));
            root= loader.load();
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
            ManageLeaveCtrl ctrl = loader.getController();
            ctrl.setEmpid(user.getEmpcode());
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            new FadeIn(root).play();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public String mGetDate() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR);
    }

    public String mGetTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }

    public void setUser(User usr) {
        this.user = usr;
        lblName.setText(usr.getEmpcode());
    }

}
