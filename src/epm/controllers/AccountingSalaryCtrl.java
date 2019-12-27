package epm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import epm.controllers.employeeInfo.EmpDetails;
import epm.database.DAO;
import epm.models.Department;
import epm.models.ManageLeave;
import epm.models.Payslip;
import epm.models.employee.SimpleEmp;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AccountingSalaryCtrl implements Initializable {
    @FXML
    private JFXDatePicker dpFromdate;

    @FXML
    private JFXDatePicker dpTodate;

    @FXML
    private JFXComboBox<String> cbxEmpid;

    @FXML
    private JFXTextField txtWhour;

    @FXML
    private JFXTextField txtSalary;

    @FXML
    private JFXTextField txtBonus;

    @FXML
    private JFXTextField txtLossPay;
    private  SimpleEmp simpleEmp;
    @FXML
    private JFXTextField txtNetsalary;
    @FXML
    private ImageView closeBtn;
    Payslip mPayslip;


    @FXML
    void onSavebtn(ActionEvent event) {
    String date=getCurentdate();
    mPayslip.setPaydate(date);
    System.out.println(mPayslip);
    boolean isInserted=DAO.getObjectRefrence().insertIntoPayslip(mPayslip);

   if(isInserted){
       Alert alert=new Alert(Alert.AlertType.INFORMATION);
       alert.setContentText("Succesfully inserted");
       alert.show();
   }
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
        mPayslip=new Payslip();
        try {
            comboxEvent();
            initCombox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void comboxEvent() {
        ManageLeave manageLeave=null;

        cbxEmpid.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                    String pattern = "dd/MM/yyyy";
                    DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
                    String fromDate = "";
                    String toDate = "";
            int totalSalary=0;
            int lossMoney=0;
            int netsalary=0;
            int overtimeHours=0;
            int bonus=0;
            fromDate = dpFromdate.getValue().format(df);
            toDate = dpTodate.getValue().format(df);
            System.out.println(fromDate + ", " + toDate);
                    System.out.println("Selected Value:" + newValue);
                    if ( fromDate.length() != 0 && toDate.length() != 0) {
                         simpleEmp=DAO.getObjectRefrence().selectByEmpid(newValue);
                        String whours = DAO.getObjectRefrence().selectWhoursPresent(fromDate, toDate, newValue);
                        String hourlySalary =simpleEmp.getHourlySalary();
                        String overtime=DAO.getObjectRefrence().selectOvertime(newValue);
                        overtimeHours=Integer.parseInt(overtime);
                        System.out.println("hourly salary:"+hourlySalary);
                        txtWhour.setText(whours);
                        try {
                            totalSalary = Integer.parseInt(hourlySalary) * Integer.parseInt(whours);
                            String lossHourly=DAO.getObjectRefrence().selectManageLeave(newValue);
                            System.out.println("lossHourly"+lossHourly);
                            lossMoney= Integer.parseInt(hourlySalary) * Integer.parseInt(lossHourly);
                            netsalary=  totalSalary-lossMoney;
                           bonus=overtimeHours*Integer.parseInt(hourlySalary);
                        }catch (NumberFormatException e){
                            Alert alert=new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("Date is not true");
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        txtSalary.setText(String.valueOf(totalSalary));
                        txtLossPay.setText(String.valueOf(lossMoney));
                        txtBonus.setText(String.valueOf(bonus));
                        int netSalaryRes=netsalary+bonus;
                        txtNetsalary.setText(String.valueOf(netSalaryRes));
                        mPayslip.setEmpid(newValue);
                        mPayslip.setLossOfpay(txtLossPay.getText());
                        mPayslip.setDepartment(simpleEmp.getDepartment());
                        mPayslip.setDesignation(simpleEmp.getDesignation());
                        mPayslip.setName(simpleEmp.getName());
                        mPayslip.setNetsal(txtNetsalary.getText());


                    } else {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setContentText("You have to fill dates first");
                        alert.show();
                    }

                }
        );
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

    public String  getCurentdate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return  formatter.format(date);
    }

}
