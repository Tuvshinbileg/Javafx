package epm.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import epm.database.DAO;
import epm.models.EventManage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class EventManageCtrl {

    @FXML
    private JFXDatePicker dpFromdate;

    @FXML
    private JFXDatePicker dpTodate;

    @FXML
    private JFXTimePicker tpFromtime;

    @FXML
    private JFXTimePicker tpTotime;

    @FXML
    private TextField txtEvent;

    @FXML
    private TextField txtDescription;
    @FXML
    private ImageView closeBtn;

    @FXML
    void onClickClosebtn(MouseEvent event) {
        if(event.getSource()==this.closeBtn){
            Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            //      new FadeOut(stage).play();
            stage.close();
        }
    }

    @FXML
    void onSavebtn(ActionEvent event) {
        String pattern="dd/MM/yyyy";
        String patternTime="HH:MM";
        DateTimeFormatter df=DateTimeFormatter.ofPattern(pattern);
        DateTimeFormatter tf=DateTimeFormatter.ofPattern(patternTime);
        String fromDate=dpFromdate.getValue().format(df);
        String toDate=dpTodate.getValue().format(df);
        String fromTime=tpFromtime.getValue().toString();
        String toTime=tpTotime.getValue().toString();
        String eventText=txtEvent.getText();
        String eventDescrip=txtDescription.getText();
        EventManage eventManage=new EventManage(fromDate,fromTime,fromTime,toTime,eventText,eventDescrip);
        System.out.println(eventManage);
        boolean inserted= DAO.getObjectRefrence().insertIntoEvent(eventManage);
        if(inserted){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Succesfully added");
            alert.show();
        }else{
          Alert alert=new Alert(Alert.AlertType.WARNING);
          alert.setContentText("failed");
          alert.show();
        }

    }
}
