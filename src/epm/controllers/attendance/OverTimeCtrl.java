package epm.controllers.attendance;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import epm.database.DAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class OverTimeCtrl implements Initializable {
    @FXML
    private ImageView closeBtn;

    @FXML
    private JFXComboBox<String> cbxEmpid;

    @FXML
    private JFXDatePicker dpDate;

    @FXML
    private JFXTextField txtHours;


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
    void onSave(ActionEvent event) {
        String pattern = "dd/MM/yyyy";
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        String empid = cbxEmpid.getSelectionModel().getSelectedItem();
        String hours = txtHours.getText();
        String date = dpDate.getValue().format(df);
        try {
            boolean isInserted=DAO.getObjectRefrence().insertIntoOverTime(empid,date,Integer.parseInt(hours));
        if(isInserted){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully inserted");
            alert.show();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initCombox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initCombox() throws SQLException {
        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        List<String> list = DAO.getObjectRefrence().selectAllEmpid();

                        ObservableList data = FXCollections.observableArrayList();
                        for (String a : list) {
                            System.out.println(a);
                            data.add(a);
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cbxEmpid.setItems(data);
                            }
                        });
                        return null;
                    }
                };
            }
        };
        service.start();

    }
}
