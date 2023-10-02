/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crapgui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author LauHeiYee
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private AnchorPane scenepane;
    @FXML
    private Button cancelBuuton;
    @FXML
    private TextField studentid;
    
    
    
    @FXML
    private void startButtonAction(ActionEvent event) throws IOException {
        
        Parent root=FXMLLoader.load(getClass().getResource("/scene2/game.fxml"));
        Scene scene =new Scene(root);
        Stage stage=new Stage(StageStyle.DECORATED);
        stage.setTitle("Crap");
        stage.setScene(scene);
        stage.show();
      
        
        
        
    }
    
    @FXML
    private void cancelbuttonAction(ActionEvent event){
        Stage stage=(Stage)scenepane.getScene().getWindow();
        stage.close();
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
