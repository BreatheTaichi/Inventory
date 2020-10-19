
package View_Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 *
 *
 * @author G
 */
public class ProductController implements Initializable {
   
   private Boolean isAdd = false;

   @FXML
   private TableView<Part> modProdAddTable;

   @FXML
   private TextField modifyProductSearchField;

   @FXML
   private TextField modifyProductIDField;

   @FXML
   private TextField modifyProductMinField;

   @FXML
   private TextField modifyProductMaxField;

   @FXML
   private TextField modifyProductPriceField;

   @FXML
   private TextField modifyProductInventoryField;

   @FXML
   private TextField modifyProductNameField;

   @FXML
   private TableView<Part> modProdDeleteTable;
   
   @FXML
   private Label errorLabel;

   @FXML
   private TableColumn<Part, Integer> modifyProductPartIDAddCol;

   @FXML
   private TableColumn<Part, String> modifyProductPartNameAddCol;

   @FXML
   private TableColumn<Part, Integer> modifyProductInventoryAddCol;

   @FXML
   private TableColumn<Part, Double> modifyProductPriceAddCol;

   @FXML
   private TableColumn<Part, Integer> modifyProductPartIDDelCol;

   @FXML
   private TableColumn<Part, String> modifyProductPartNameDelCol;

   @FXML
   private TableColumn<Part, Integer> modifyProductInventoryDelCol;

   @FXML
   private TableColumn<Part, Double> modifyProductPriceDelCol;

   @FXML
   void modifyProductAddPartOnAction(ActionEvent event) {
      Product p = Inventory.lookupProduct(Integer.valueOf(modifyProductIDField.getText()));
      p.addAssociatedPart(modProdAddTable.getSelectionModel().getSelectedItem());
      //p.getAssociatedParts().add(modProdAddTable.getSelectionModel().getSelectedItem());
   }

   @FXML
   void modifyProductDeletePartOnAction(ActionEvent event) {
      Product p = Inventory.lookupProduct(Integer.valueOf(modifyProductIDField.getText()));
      p.removeAssociatedPart(modProdDeleteTable.getSelectionModel().getSelectedItem());
   }

   @FXML
   void modifyProductCancelOnAction(ActionEvent event) throws IOException {
      if (isAdd) {
         Inventory.removeProduct(Integer.valueOf(modifyProductIDField.getText()));
      }
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
      Scene home_page_scene = new Scene(home_page_parent);
      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      app_stage.hide();
      app_stage.setScene(home_page_scene);
      app_stage.show();
   }

   @FXML
   void modifyProductSaveOnAction(ActionEvent event) throws IOException {
      Product ps = Inventory.lookupProduct(Integer.valueOf(modifyProductIDField.getText()));
      if (ps.getAssociatedParts().isEmpty()) {
         errorLabel.setText("Cannot save Product without Parts");
      } else {
         //  product must have a name, price, category, and inventory level
         if (modifyProductNameField.getText().isEmpty() || modifyProductPriceField.getText().isEmpty() || 
                 modifyProductMaxField.getText().isEmpty() || modifyProductMinField.getText().isEmpty() || 
                 modifyProductInventoryField.getText().isEmpty()) {
            errorLabel.setText("All fields must be filled");
         } else {
            ps.setName(modifyProductNameField.getText());
            ps.setPrice(Double.parseDouble(modifyProductPriceField.getText()));
            ps.setMin(Integer.parseInt(modifyProductMinField.getText()));
            ps.setMax(Integer.parseInt(modifyProductMaxField.getText()));
            ps.setInStock(Integer.parseInt(modifyProductInventoryField.getText()));

            Parent home_page_parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
         }
      }
   }

   @FXML
   void modifyProductSearchOnAction(ActionEvent event) {
      // Search by ID if numeric, search by name if not.
      if (modifyProductSearchField.getText().matches("[0-9]+")) {
         modProdAddTable.getSelectionModel().select(Inventory.partIndex(Integer.valueOf(modifyProductSearchField.getText())));
      } else {
         modProdAddTable.getSelectionModel().select(Inventory.partName(modifyProductSearchField.getText()));
      }
      //modProdAddTable.getSelectionModel().select(Inventory.partIndex(Integer.valueOf(modifyProductSearchField.getText())));
   }
   
   public void populateProduct(Product prodToModify) {
      modifyProductMinField.setText(Integer.toString(prodToModify.minProperty().get()));
      modifyProductMaxField.setText(Integer.toString(prodToModify.maxProperty().get()));
      modifyProductPriceField.setText(Double.toString(prodToModify.priceProperty().get()));
      modifyProductInventoryField.setText(Integer.toString(prodToModify.inStockProperty().get()));
      modifyProductIDField.setText(Integer.toString(prodToModify.productIDProperty().get()));
      modifyProductNameField.setText(prodToModify.getName());

      modProdAddTable.setItems(Inventory.allParts);
      modProdDeleteTable.setItems(prodToModify.getAssociatedParts());
   }
   
   public void populateOnAdd() {
      isAdd = true;
      modifyProductIDField.setText(Integer.toString(Inventory.productIDCount()));
      Product ps = new Product();
      ps.setName(modifyProductNameField.getText());
      ps.setPrice(Double.parseDouble(modifyProductPriceField.getText()));
      ps.setMin(Integer.parseInt(modifyProductMinField.getText()));
      ps.setProductID(Integer.parseInt(modifyProductIDField.getText()));
      ps.setMax(Integer.parseInt(modifyProductMaxField.getText()));
      ps.setInStock(Integer.parseInt(modifyProductInventoryField.getText()));
      ps.getAssociatedParts().addAll(Inventory.allParts);
      ps.getAssociatedParts().removeAll(Inventory.allParts);
      modProdAddTable.setItems(Inventory.allParts);
      modProdDeleteTable.setItems(ps.getAssociatedParts());
      Inventory.addProduct(ps);
   }

   /**
    * Initializes the controller class.
    *
    * @param url
    * @param rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {

      modifyProductPartIDDelCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
      modifyProductPartNameDelCol.setCellValueFactory(new PropertyValueFactory<>("name"));
      modifyProductInventoryDelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
      modifyProductPriceDelCol.setCellValueFactory(new PropertyValueFactory<>("price"));

      modifyProductPartIDAddCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
      modifyProductPartNameAddCol.setCellValueFactory(new PropertyValueFactory<>("name"));
      modifyProductInventoryAddCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
      modifyProductPriceAddCol.setCellValueFactory(new PropertyValueFactory<>("price"));

   }

}
