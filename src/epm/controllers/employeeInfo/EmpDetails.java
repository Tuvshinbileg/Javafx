package epm.controllers.employeeInfo;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import epm.database.DAO;
import epm.models.AdminDesignation;
import epm.models.Department;
import epm.models.employee.EmployeeDetail;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class EmpDetails implements Initializable {
    @FXML
    private ImageView closeBtn;

    @FXML
    private JFXTextField txtEmpcode;

    @FXML
    private JFXTextField txtFirstName;

    @FXML
    private JFXTextField txtLastname;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtPincode;

    @FXML
    private JFXDatePicker dpDob;

    @FXML
    private JFXTextField txtCity;

    @FXML
    private JFXTextField txtContactNo;

    @FXML
    private JFXComboBox<String> cbxGender;

    @FXML
    private JFXTextField txtDepartment;

    @FXML
    private JFXTextField txtPass;
    @FXML
    private  JFXComboBox<String> cbxDepartment;

    @FXML
    private JFXComboBox<String> cbxDesignation;

    @FXML
    private JFXTextField txtHourlySalary;

    @FXML
    private JFXTextField txtAccount;

    @FXML
    private JFXDatePicker dpDoj;
    @FXML
    private JFXTextField txtEmailID;
    @FXML
    private JFXComboBox<String> cbxUsertype;
    @FXML
    private JFXTextField txtUsername;
    String selectedGender;
    @FXML
    void onAddbtn(ActionEvent event) throws SQLException {
        String department=cbxDepartment.getSelectionModel().getSelectedItem();
        String designation=cbxDesignation.getSelectionModel().getSelectedItem();
        String usertype=cbxUsertype.getSelectionModel().getSelectedItem();
        String dob = dpDob.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String doj=dpDoj.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        EmployeeDetail employeeDetail=new EmployeeDetail(txtEmpcode.getText(),txtFirstName.getText(),txtLastname.getText(),selectedGender,txtAddress.getText(),txtContactNo.getText(),dob,txtCity.getText(),txtEmailID.getText(),department,designation,txtHourlySalary.getText(),doj,txtAccount.getText(),txtUsername.getText(),usertype,txtPincode.getText(),txtPass.getText());
      boolean inserted=  DAO.getObjectRefrence().insertIntoEmployee(employeeDetail);
      if(inserted){
          Alert alert=new Alert(Alert.AlertType.INFORMATION);
          alert.setContentText("Succesfully added ");
          alert.show();
          clearAllfields();
      }
        System.out.println(employeeDetail);
  }
    @FXML
    void onClearbtn(ActionEvent event) {
        clearAllfields();
    }


    @FXML
    void onClickClosebtn(MouseEvent event) {
        if(event.getSource()==this.closeBtn){
            Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void init() throws SQLException {
        cbxGender.getItems().addAll("Male","FEMALE");
        cbxUsertype.getItems().addAll("admin","emp");
        cbxGender.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                selectedGender=newValue;
                System.out.println(newValue);
            }
        });

        Service<Void> service=new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        List<Department> list= DAO.getObjectRefrence().selectDepartment();
                        ObservableList data = FXCollections.observableArrayList();
                        for(Department a:list){
                            data.add(a.getDepartmentName());

                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                cbxDepartment.setItems(data);
                            }
                        });
                        return null;
                    }
                };
            }
        };
        Service<Void> service1=new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        List<AdminDesignation> list=DAO.getObjectRefrence().selectDesignation();
                        ObservableList data = FXCollections.observableArrayList();
                        for(AdminDesignation val:list){
                            data.add(val.getDesignation());
                            //    cbxDesignation.getItems().add(val.getDesignation());
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cbxDesignation.setItems(data);
                            }
                        });
                        return null;
                    }
                };
            }
        };
        service.start();
        service1.start();
        fillDepartmentcbx();
        fillDesignationcbx();
    }
    public void fillDepartmentcbx() throws SQLException {
        List<Department> list= DAO.getObjectRefrence().selectDepartment();
        ObservableList data = FXCollections.observableArrayList();
        for(Department a:list){
            data.add(a.getDepartmentName());

        }
            cbxDepartment.setItems(data);
    }
    public void fillDesignationcbx() throws SQLException {
        List<AdminDesignation> list=DAO.getObjectRefrence().selectDesignation();
        ObservableList data = FXCollections.observableArrayList();
        for(AdminDesignation val:list){
        data.add(val.getDesignation());
            //    cbxDesignation.getItems().add(val.getDesignation());
        }
        cbxDesignation.setItems(data);
    }
    public void clearAllfields(){
        txtEmpcode.clear();
        txtAccount.clear();
        txtHourlySalary.clear();
        txtContactNo.clear();
        txtPass.clear();
        txtFirstName.clear();
        txtLastname.clear();
        txtAddress.clear();
        txtPincode.clear();
        txtContactNo.clear();
        txtPass.clear();
        txtHourlySalary.clear();
        txtEmailID.clear();
        cbxDesignation.setValue("");
        cbxDepartment.setValue("");
        cbxGender.setValue("");
        dpDob.getEditor().clear();
        dpDoj.getEditor().clear();

    }



}
