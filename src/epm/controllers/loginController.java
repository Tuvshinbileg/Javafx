package epm.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import epm.AdminStart;
import epm.database.DAO;
import epm.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import animatefx.animation.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class loginController implements Initializable {

    @FXML // fx:id="forgotPassAdmin"
    private Label forgotPassAdmin; // Value injected by FXMLLoader

    @FXML // fx:id="btnAdminLog"
    private ImageView btnAdminLog; // Value injected by FXMLLoader

    @FXML // fx:id="pnlAdminLogin"
    private Pane pnlAdminLogin; // Value injected by FXMLLoader

    @FXML // fx:id="userSecretCode"
    private JFXTextField userSecretCode; // Value injected by FXMLLoader

    @FXML // fx:id="userPass"
    private JFXPasswordField txtUserPass; // Value injected by FXMLLoader

    @FXML // fx:id="userIdRecover"
    private JFXTextField userIdRecover; // Value injected by FXMLLoader

    @FXML // fx:id="pnlUserLogin"
    private Pane pnlUserLogin; // Value injected by FXMLLoader

    @FXML // fx:id="adminPass"
    private JFXTextField adminPass; // Value injected by FXMLLoader

    @FXML // fx:id="loginButtonAdmin"
    private JFXButton loginButtonAdmin; // Value injected by FXMLLoader

    @FXML // fx:id="loginSeparator"
    private Pane loginSeparator; // Value injected by FXMLLoader

    @FXML // fx:id="userID"
    private JFXTextField txtUserID; // Value injected by FXMLLoader

    @FXML // fx:id="pnlRecovery"
    private Pane pnlRecovery; // Value injected by FXMLLoader

    @FXML // fx:id="resetPassButton"
    private JFXButton resetPassButton; // Value injected by FXMLLoader

    @FXML // fx:id="loginButton"
    private JFXButton loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="adminID"
    private JFXTextField adminID; // Value injected by FXMLLoader

    @FXML // fx:id="loginPage"
    private BorderPane loginPage; // Value injected by FXMLLoader

    @FXML // fx:id="btnBackToUser2"
    private ImageView btnBackToUser2; // Value injected by FXMLLoader

    @FXML // fx:id="userNewPass"
    private JFXTextField userNewPass; // Value injected by FXMLLoader

    @FXML // fx:id="btnBackToUser"
    private ImageView btnBackToUser; // Value injected by FXMLLoader

    @FXML // fx:id="forgotPass"
    private Label forgotPass; // Value injected by FXMLLoader

    @FXML // fx:id="btnExit"
    private Circle btnExit; // Value injected by FXMLLoader
    private double xOffset = 0;
    private double yOffset = 0;
    private User user;

    @FXML
    public void handleMouseEvent(MouseEvent event) {
        if(event.getSource() == btnExit){
            System.exit(0);
        }
        if(event.getSource() == forgotPass || event.getSource() == forgotPassAdmin ){
            pnlUserLogin.toBack();
            pnlAdminLogin.toBack();
            new FadeIn(pnlRecovery).play();
            pnlRecovery.toFront();
        }
        if(event.getSource() == btnAdminLog){
            pnlUserLogin.toBack();
            pnlRecovery.toBack();
            new FadeIn(pnlAdminLogin).play();
            pnlAdminLogin.toFront();

        }
        if(event.getSource() == btnBackToUser || event.getSource() == btnBackToUser2){
            pnlAdminLogin.toBack();
            pnlRecovery.toBack();
            new FadeIn(pnlUserLogin).play();
            pnlUserLogin.toFront();
        }
    }

    @FXML
    private void onLogin(ActionEvent event) throws Exception {
       System.out.println("Login");
        String username=txtUserID.getText();
        String userPass=txtUserPass.getText();
        if(!username.trim().equals("") && !userPass.trim().equals("")){
             user=DAO.getObjectRefrence().isLogin(username,userPass);
            if(user!=null){
                System.out.println("Succesfully");
                if(user.getType().equals("admin")){
                    callStage("epm/views/admin/admin.fxml",1);
                    final Node source = (Node) event.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                }
                if(user.getType().equals("emp")){
                    callStage("epm/views/user/user.fxml",2);
                    final Node source = (Node) event.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }

    // This method is called by the FXMLLoader when initialization is complete
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public  void callStage(String url,int state){
        Parent root;
        try {
            Stage stage=new Stage();
            FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource(url));
            root= loader.load();
            if(state==2) {
                UserController ctrl = loader.getController();
                ctrl.setUser(user);
            }
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
