package epm.controllers;

import com.jfoenix.controls.JFXDatePicker;
import epm.database.DAO;
import epm.models.ReportAdminAttendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ReportAttendance implements Initializable {
    @FXML
    private JFXDatePicker dpFromDate;

    @FXML
    private JFXDatePicker dpToDate;

    @FXML
    private TableView<ReportAdminAttendance> mTable;

    @FXML
    private TableColumn<ReportAdminAttendance, String> colEmpid;

    @FXML
    private TableColumn<ReportAdminAttendance, String> colEntryDate;

    @FXML
    private TableColumn<ReportAdminAttendance, String> colEntryTime;

    @FXML
    private TableColumn<ReportAdminAttendance, String> colExitTime;

    @FXML
    private TableColumn<ReportAdminAttendance, String> colWorkedHours;
    private ObservableList data;
    @FXML
    private ImageView closeBtn;

    @FXML
    void onClosebtn(MouseEvent event) {
        if(event.getSource()==this.closeBtn){
            Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            //      new FadeOut(stage).play();
            stage.close();
        }
    }

    @FXML
    void onLoad(ActionEvent event) throws SQLException {
        String pattern="dd/MM/yyyy";
        DateTimeFormatter df=DateTimeFormatter.ofPattern(pattern);
        String fromDate=dpFromDate.getValue().format(df);
        String toDate=dpToDate.getValue().format(df);
        System.out.println(fromDate+", "+toDate);
      List<ReportAdminAttendance> list=  DAO.getObjectRefrence().selectPresent(fromDate,toDate);
      for(ReportAdminAttendance val:list){
        System.out.println(val.toString());
          data.add(val);
      }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    initTable();
    }
    public void initTable(){
        data= FXCollections.observableArrayList();
        colEmpid.setCellValueFactory(new PropertyValueFactory<>("empid"));
        colEntryDate.setCellValueFactory(new PropertyValueFactory<>("entryDate"));
        colEntryTime.setCellValueFactory(new PropertyValueFactory<>("entryTime"));
        colExitTime.setCellValueFactory(new PropertyValueFactory<>("exitTime"));
        colWorkedHours.setCellValueFactory(new PropertyValueFactory<>("whours"));
        mTable.setItems(data);
    }
}
