package epm.controllers.admin;

import epm.database.DAO;
import epm.models.Department;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddDepartment implements Initializable {

    @FXML
    private TextField txtDepartment;

    @FXML
    private TableView<Department> tableDepartment;
    private ObservableList<Department> data;
    @FXML
    private TableColumn<Department, String> colDepartment;
    @FXML
    void onAddbtn(ActionEvent event) throws SQLException {
        System.out.println("From department");
        if(txtDepartment.getText().length()!=0){
           Department dp= new Department(txtDepartment.getText());
            data.add(dp);
            //Insert into db
          boolean truth=DAO.getObjectRefrence().insertIntoDepartment(dp.getDepartmentName());
            if(truth){
                alertRecordInserted();

            }
        }
    }


    @FXML
    void onClearbtn(ActionEvent event) {
        txtDepartment.clear();
    }

    @FXML
    void onClosebtn(ActionEvent event) {

    }

    @FXML
    void onDeletebtn(ActionEvent event) {
        Department dp=tableDepartment.getSelectionModel().getSelectedItem();
        data.remove(dp);
        //remove from db
        alertRecordInserted();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        data= FXCollections.observableArrayList();
        tableDepartment.setItems(data);
    }
    public void alertRecordInserted(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("EPM");
        alert.setContentText("Record inserted");
        alert.showAndWait();
    }
}
