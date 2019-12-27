package epm.controllers.admin;

import epm.database.DAO;
import epm.models.AdminDesignation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Designation implements Initializable {
    private ObservableList<AdminDesignation> data;
    @FXML
    private TextField txtDesignation;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TableColumn<AdminDesignation, String> colDesign;

    @FXML
    private TableColumn<AdminDesignation, String> colDescrip;
    @FXML
    private TableView<AdminDesignation> mTable;

    @FXML
    void onAddbtn(ActionEvent event) throws SQLException {
        String txtDesign=txtDesignation.getText();
        String txtDescript=txtDescription.getText();
        if(txtDesign.length()!=0 &txtDescript.length()!=0){
        AdminDesignation obj=new AdminDesignation(txtDesign,txtDescript);
        data.add(obj);
        //insert to database
           boolean t= DAO.getObjectRefrence().insertIntoDesignation(obj.getDescription(),obj.getDesignation());
            if(t){alertRecordInserted();}
        }
        System.out.println("Hello from Designation");
    }

    @FXML
    void onClearbtn(ActionEvent event) {
        txtDesignation.clear();
        txtDescription.clear();
    }

    @FXML
    void onClosebtn(ActionEvent event) {

    }

    @FXML
    void onDeletebtn(ActionEvent event) {
    AdminDesignation rmvObj=mTable.getSelectionModel().getSelectedItem();
    data.remove(rmvObj);
    //delete from db
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colDesign.setCellValueFactory(new PropertyValueFactory("designation"));
        colDescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        data= FXCollections.observableArrayList();
        mTable.setItems(data);

    }
    public void alertRecordInserted(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("EPM");
        alert.setContentText("Record inserted");
        alert.showAndWait();
    }
}
