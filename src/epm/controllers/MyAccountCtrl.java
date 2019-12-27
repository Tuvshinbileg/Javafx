package epm.controllers;

import com.jfoenix.controls.JFXTextField;
import epm.database.DAO;
import epm.models.MyAccount;
import epm.models.UpdatingAcc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MyAccountCtrl implements Initializable {
    @FXML
    private ImageView closeBtn;
    @FXML
    private Label fNameLabel;

    @FXML
    private Label lNameLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label dobLabel;

    @FXML
    private JFXTextField txtAddress1;

    @FXML
    private JFXTextField txtCity1;

    @FXML
    private JFXTextField txtContactNo1;

    @FXML
    private JFXTextField txtAccount1;

    @FXML
    private JFXTextField txtEmailID1;
    private String empid;
    @FXML
    void onChangebtn(ActionEvent event) {
        String address=txtAddress1.getText();
        String city=txtCity1.getText();
        String contactNo=txtContactNo1.getText();
        String account=txtAccount1.getText();
        String email=txtEmailID1.getText();
        boolean isUpdated=DAO.getObjectRefrence().updateEmpAcc(new UpdatingAcc(address,city,contactNo,account,email),empid);
        if(isUpdated){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully updated");
            alert.show();
        }else{

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Unsuccessfully updated");
            alert.show();
        }
    }

    @FXML
    void onClearbtn(ActionEvent event) {

    }
    @FXML
    void onClickClosebtn(MouseEvent event) {
        if(event.getSource()==this.closeBtn){
            Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            //      new FadeOut(stage).play();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setEmpid(String empid){
        this.empid=empid;
        try {
            MyAccount account= DAO.getObjectRefrence().selectMyAcc(this.empid);
            fNameLabel.setText(account.getFirstName());
            lNameLabel.setText(account.getLastName());
            genderLabel.setText(account.getGender());
            dobLabel.setText(account.getDob());
            txtAddress1.setText(account.getAddress());
            txtAccount1.setText(account.getBankAccount());
            txtCity1.setText(account.getCity());
            txtContactNo1.setText(account.getContactNo());
            txtEmailID1.setText(account.getEmail());
            System.out.println(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
