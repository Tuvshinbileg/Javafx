package epm.controllers;

import epm.models.EventItem;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class EventItemCtrl implements Initializable {
    @FXML
    private Label eventDay;

    @FXML
    private Label eventMonth;

    @FXML
    private Label eventName;

    @FXML
    private Label eventLocation;
    private EventItem eventItem;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setEventItem(EventItem e){
        this.eventItem=e;

        System.out.println("EVENTITEM"+e);

        eventDay.setText(eventItem.getDay());
        eventMonth.setText(eventItem.getMonth());
        eventName.setText(eventItem.getName());
        eventLocation.setText(eventItem.getLoc());
    }

}
