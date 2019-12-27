package epm.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChangePass {

    @FXML
    private ImageView closePassChange;

    @FXML
    void onHandleMouse(MouseEvent event) {
        if(event.getSource()==closePassChange){
            Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            //      new FadeOut(stage).play();
            stage.close();
        }

    }
}
