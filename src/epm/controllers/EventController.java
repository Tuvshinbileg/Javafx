package epm.controllers;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import epm.database.DAO;
import epm.models.EventItem;
import epm.models.EventManage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class EventController implements Initializable {

    @FXML
    private ImageView closeEvent;

    @FXML
    private VBox bodyVbox;

    private String day;
    private String month;
    private String eventname;
    private String eventlocation;

    private EventItem eventItem;
    @FXML
    void onCloseEvent(MouseEvent event) {
        if(event.getSource() == closeEvent)
        {
            Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();

            //      new FadeOut(stage).play();
            stage.close();

        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<EventManage>  data= DAO.getObjectRefrence().selectEvent();
            for(EventManage val:data){
                bodyVbox.getChildren().add(initEventPane(val));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

 public HBox initEventPane(EventManage val){
    HBox hBox=callStage(val);

 //   System.out.println(eventItem);
     return  hBox;
 }
    public  HBox callStage(EventManage val){
        HBox root = null;
        String [] finding=val.getFromdate().split("/");
        day=finding[0];
        System.out.println(day);
        month=getMonthWord(Integer.parseInt(finding[1]));
        System.out.println(month);
        eventname=val.getEventName();
        eventlocation=val.getEventDescription();
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("epm/views/eventItem.fxml"));
            root= loader.load();
            EventItemCtrl ctrl = loader.getController();
            eventItem=new EventItem(day,month,eventname,eventlocation);
            System.out.println("SEND"+this.eventItem);
            ctrl.setEventItem(this.eventItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  root;
    }
    public String getMonthWord(int i){
     switch (i){
         case 1:return "JAN";

         case 2:return "FEB";

         case 3:return "MAR";

         case 4:return "APR";

         case 5:return "MAY";

         case 6:return "JUN";

         case 7:return "JUL";

         case 8:return "AUG";

         case 9:return "SEP";
         case 10:return "OCT";
         case 11:return "NOV";
         case 12:return "DEC";

     }
     return "N";
    }

}
