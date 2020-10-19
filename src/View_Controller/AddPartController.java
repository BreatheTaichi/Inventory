/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author G
 */
public class AddPartController implements Initializable {
   
   boolean isInHouse = true;

   @FXML
   public RadioButton addInPartRadio;

   @FXML
   public ToggleGroup addPartRadioGroup;

   @FXML
   public RadioButton addOutPartRadio;
   
   @FXML
   private TextField addPartIDField;

   @FXML
   private TextField addPartCompanyNameField;

   @FXML
   private TextField addPartMacIDField;

   @FXML
   private TextField addPartMinField;

   @FXML
   private TextField addPartMaxField;

   @FXML
   private TextField addPartPriceField;

   @FXML
   private TextField addPartInventoryField;

   @FXML
   private TextField addPartNameField;

   @FXML
   void addPartCancelOnAction(ActionEvent event) throws IOException {
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
      Scene home_page_scene = new Scene(home_page_parent);
      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(home_page_scene);
      app_stage.show();
   }

   @FXML
   void addPartInHouseRadio(ActionEvent event) {
      isInHouse = true;
      addPartCompanyNameField.setDisable(true);
      addPartMacIDField.setDisable(false);
   }

   @FXML
   void addPartOutsourcedRadio(ActionEvent event) {
      isInHouse = false;
      addPartCompanyNameField.setDisable(false);
      addPartMacIDField.setDisable(true);
   }

   @FXML
   void addPartSaveOnAction(ActionEvent event) throws IOException {
      if(isInHouse) {
         // get all fields and make an inhouse part
         InhousePart part;
         part = new InhousePart(addPartNameField.getText(), Double.valueOf(addPartPriceField.getText()), 
                 Integer.valueOf(addPartInventoryField.getText()), Integer.valueOf(addPartMaxField.getText()), 
                 Integer.valueOf(addPartMinField.getText()), Integer.valueOf(addPartMacIDField.getText()));
         Inventory.addPart(part);
      } else {
         // get all fields and make an outsourced part
         OutsourcedPart part;
         part = new OutsourcedPart(addPartNameField.getText(), Double.valueOf(addPartPriceField.getText()), 
                 Integer.valueOf(addPartInventoryField.getText()), Integer.valueOf(addPartMaxField.getText()), 
                 Integer.valueOf(addPartMinField.getText()), addPartCompanyNameField.getText());
         Inventory.addPart(part);
      }
      
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
      Scene home_page_scene = new Scene(home_page_parent);
      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(home_page_scene);
      app_stage.show();
   }

   /**
    * Initializes the controller class.
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {
         addPartCompanyNameField.setDisable(true);
         addPartMacIDField.setDisable(false);
         addInPartRadio.setSelected(true);

      
      
   }

}
