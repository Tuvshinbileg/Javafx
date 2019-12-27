package epm.controllers;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import epm.database.DAO;
import epm.models.Payslip;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PayslipController implements Initializable {

    @FXML
    private JFXDatePicker dpFromdate;

    @FXML
    private JFXDatePicker dpTodate;

    @FXML
    private TableView<Payslip> mTable;

    @FXML
    private TableColumn<Payslip, String> colSno;

    @FXML
    private TableColumn<Payslip, String> colEmpid;

    @FXML
    private TableColumn<Payslip, String> colName;

    @FXML
    private TableColumn<Payslip, String> colDepartment;

    @FXML
    private TableColumn<Payslip, String> colDesignation;

    @FXML
    private TableColumn<Payslip, String> colLossPay;

    @FXML
    private TableColumn<Payslip, String> colNetsalary;

    @FXML
    private TableColumn<Payslip, String> colPayDate;
    private ObservableList data;
    @FXML
    private ImageView onClosebtn;


    @FXML
    void onGenerate(ActionEvent event) {
        System.out.println("GENEERATE");
        String pattern="dd/MM/yyyy";
        DateTimeFormatter df=DateTimeFormatter.ofPattern(pattern);
        String fromDate=dpFromdate.getValue().format(df);
        String toDate=dpTodate.getValue().format(df);
        System.out.println(fromDate+", "+toDate);
     //   DAO.getObjectRefrence().selectByEmpid("EMP104");
        List<Payslip> list=DAO.getObjectRefrence().selectPayslip(fromDate,toDate);
        if(list==null){
            System.out.println("Payslip is null");
        }else {
            for(Payslip val:list){
                data.add(val);
            }

        }

    }


    @FXML
    void onClickClose(MouseEvent event) {
        if(event.getSource()==this.onClosebtn){
            Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            //      new FadeOut(stage).play();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }
    public void initTable(){
        data=FXCollections.observableArrayList();
        colEmpid.setCellValueFactory(new PropertyValueFactory<>("empid"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<>("department"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colLossPay.setCellValueFactory(new PropertyValueFactory<>("lossOfpay"));
        colNetsalary.setCellValueFactory(new PropertyValueFactory<>("netsal"));
        colPayDate.setCellValueFactory(new PropertyValueFactory<>("paydate"));
        colSno.setCellValueFactory(new PropertyValueFactory<>("sno"));
        mTable.setItems(data);

    }
}
