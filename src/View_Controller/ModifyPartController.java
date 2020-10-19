
package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Part;
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
public class ModifyPartController implements Initializable {

   boolean isInHouse = true;

   @FXML
   public RadioButton modInPartRadio;

   @FXML
   public ToggleGroup modPartToggleGroup;

   @FXML
   public RadioButton modOutPartRadio;

   @FXML
   private TextField modPartIDField;

   @FXML
   private TextField modPartMacIDField;

   @FXML
   private TextField modPartCompanyNameField;

   @FXML
   private TextField modPartMinField;

   @FXML
   private TextField modPartMaxField;

   @FXML
   private TextField modPartPriceField;

   @FXML
   private TextField modPartInventoryField;

   @FXML
   private TextField modPartNameField;

   @FXML
   void ModPartOutsourcedRadio(ActionEvent event) {
      isInHouse = false;
      modPartCompanyNameField.setDisable(false);
      modPartMacIDField.setDisable(true);
   }

   @FXML
   void modPartCancelOnAction(ActionEvent event) throws IOException {
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
      Scene home_page_scene = new Scene(home_page_parent);
      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
      app_stage.setScene(home_page_scene);
      app_stage.show();
   }

   @FXML
   void modPartInHouseRadio(ActionEvent event) {
      isInHouse = true;
      modPartCompanyNameField.setDisable(true);
      modPartMacIDField.setDisable(false);
   }

   @FXML
   void modPartSaveOnAction(ActionEvent event) throws IOException {
      
      // Check if outsourced or inhouse then save changes to the part
      if (isInHouse) {
         // get all fields, set object to them
         InhousePart ip = InhousePart.lookupInhousePart(Integer.valueOf(modPartIDField.getText()));
         ip.setName(modPartNameField.getText());
         ip.setPrice(Double.valueOf(modPartPriceField.getText()));
         ip.setInStock(Integer.valueOf(modPartInventoryField.getText()));
         ip.setMax(Integer.valueOf(modPartMaxField.getText()));
         ip.setMin(Integer.valueOf(modPartMinField.getText()));
         ip.setMachineID(Integer.valueOf(modPartMacIDField.getText()));
         ip.setPartID(Integer.valueOf(modPartIDField.getText()));
      } else {
         // get all fields, set object to them
         OutsourcedPart op = OutsourcedPart.lookupOutsourcedPart(Integer.valueOf(modPartIDField.getText()));
         op.setName(modPartNameField.getText());
         op.setCompanyName(modPartCompanyNameField.getText());
         op.setPrice(Double.valueOf(modPartPriceField.getText()));
         op.setInStock(Integer.valueOf(modPartInventoryField.getText()));
         op.setMax(Integer.valueOf(modPartMaxField.getText()));
         op.setMin(Integer.valueOf(modPartMinField.getText()));
         op.setPartID(Integer.valueOf(modPartIDField.getText()));
      }

      Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
      Scene home_page_scene = new Scene(home_page_parent);
      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      
      app_stage.setScene(home_page_scene);
      app_stage.show();
   }

   public void populateInhousePart(InhousePart partToModify) {
      modPartCompanyNameField.setDisable(true);
      modPartMacIDField.setDisable(false);
      isInHouse = true;
      modInPartRadio.setSelected(true);

      InhousePart part = partToModify;
      modPartIDField.setText(String.valueOf(part.getPartID()));
      modPartIDField.setDisable(true);

      modPartNameField.setText(part.getName());
      modPartMacIDField.setText(String.valueOf(part.getMachineID()));
      modPartMinField.setText(String.valueOf(part.getMin()));
      modPartMaxField.setText(String.valueOf(part.getMax()));
      modPartCompanyNameField.setText("In House");
      modPartInventoryField.setText(String.valueOf(part.getInStock()));
      modPartPriceField.setText(String.valueOf(part.getPrice()));

   }

   public void populateOutsourcedPart(OutsourcedPart partToModify) {
      modPartCompanyNameField.setDisable(false);
      modPartMacIDField.setDisable(true);
      isInHouse = false;
      modOutPartRadio.setSelected(true);

      OutsourcedPart part = partToModify;
      modPartIDField.setText(String.valueOf(part.getPartID()));
      modPartIDField.setDisable(true);

      modPartNameField.setText(part.getName());
      modPartMacIDField.setText("Outsourced No MacID");
      modPartMinField.setText(String.valueOf(part.getMin()));
      modPartMaxField.setText(String.valueOf(part.getMax()));
      modPartCompanyNameField.setText(String.valueOf(part.getCompanyName()));
      modPartInventoryField.setText(String.valueOf(part.getInStock()));
      modPartPriceField.setText(String.valueOf(part.getPrice()));
   }

   /**
    * Initializes the controller class.
    *
    * @param url
    * @param rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {

   }

}
