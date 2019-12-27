package epm.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import epm.database.DAO;
import epm.models.ManageLeave;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ManageLeaveCtrl implements Initializable {
    @FXML
    private ImageView closeBtn;
    @FXML
    private JFXDatePicker dpLeavedate;

    @FXML
    private JFXTextField txtTotal;

    @FXML
    private TextArea txtReason;

    @FXML
    private JFXTextField txtRemaining;
    String empid;


    @FXML
    void onManageLeave(ActionEvent event) {
        String pattern="dd/MM/yyyy";
        DateTimeFormatter df=DateTimeFormatter.ofPattern(pattern);
        String leavedate=dpLeavedate.getValue().format(df);
        String total=txtTotal.getText();
        String remaing=txtRemaining.getText();
        String reason=txtReason.getText();
        ManageLeave manageLeave=new ManageLeave(leavedate,empid,total,remaing,reason);
        System.out.println(manageLeave);
        try {
           boolean isInserted= DAO.getObjectRefrence().insertManageLeave(manageLeave);
           if(isInserted){
               Alert alert=new Alert(Alert.AlertType.INFORMATION);
               alert.setContentText("Successfuly added");
               alert.show();
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    public void setEmpid(String empid){
        this.empid=empid;
    }
}
