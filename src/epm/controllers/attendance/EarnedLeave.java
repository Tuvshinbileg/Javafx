package epm.controllers.attendance;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import epm.database.DAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EarnedLeave implements Initializable {

    @FXML
    private JFXComboBox<String> cbxEmpid;

    @FXML
    private JFXDatePicker dpDate;

    @FXML
    void onClose(ActionEvent event) {
        Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onOK(ActionEvent event) {
        System.out.println("");
        String empd=cbxEmpid.getSelectionModel().getSelectedItem();
        String date=dpDate.getValue().toString();
     boolean a=  DAO.getObjectRefrence().insertIntoEarnedLeave(empd,date);
     if(a){
         Alert alert=new Alert(Alert.AlertType.INFORMATION);
         alert.setContentText("Successfuly added");
         alert.show();

     }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cbxInitValues();
    }
    public void cbxInitValues(){
        try {
        List<String> list=    DAO.getObjectRefrence().selectAllEmpid();
          for(String val:list){
              cbxEmpid.getItems().add(val);
              System.out.println(val);
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
