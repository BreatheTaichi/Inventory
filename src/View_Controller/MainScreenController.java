package View_Controller;

import Model.InhousePart;
import Model.Inventory;
import Model.OutsourcedPart;
import Model.Product;
import Model.Part;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jaime Gladish
 */
public class MainScreenController implements Initializable {

   @FXML
   private TableView<Part> partsTable;

   @FXML
   private TableColumn<Part, Integer> partsPartIDCol;

   @FXML
   private TableColumn<Part, String> partsPartNameCol;

   @FXML
   private TableColumn<Part, Integer> partsInventoryLevelCol;

   @FXML
   private TableColumn<Part, Double> partsPriceCol;

   @FXML
   private TextField searchParts;

   @FXML
   private Label errorLabel;

   @FXML
   private Label errorLabel2;

   @FXML
   private TableView<Product> productTable;

   @FXML
   private TableColumn<Product, Integer> productsProductIDCol;

   @FXML
   private TableColumn<Product, String> productsProductNameCol;

   @FXML
   private TableColumn<Product, Integer> productsInventoryCol;

   @FXML
   private TableColumn<Product, Double> productsPriceCol;

   @FXML
   private TextField searchProducts;

   @FXML
   void addPartsOnAction(ActionEvent event) throws IOException {
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
      Scene home_page_scene = new Scene(home_page_parent);
      Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();
      stage.close();

      app_stage.hide();
      app_stage.setTitle("Add Part");
      app_stage.setScene(home_page_scene);
      app_stage.show();
   }

   @FXML
   void addProductsOnAction(ActionEvent event) throws IOException {

      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainScreenController.class.getResource("/View_Controller/Product.fxml"));
      AnchorPane ap = (AnchorPane) loader.load();

      ProductController pc = loader.getController();
      pc.populateOnAdd();

      Stage modPart = new Stage();
      modPart.setTitle("Add / Modify Product");
      Scene scene = new Scene(ap);

      Node source = (Node) event.getSource();
      Stage stage = (Stage) source.getScene().getWindow();
      stage.close();
         
      modPart.setScene(scene);
      modPart.show();
   }

   @FXML
   void deletePartsOnAction(ActionEvent event) {
      Inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());

   }

   @FXML
   void deleteProductsOnAction(ActionEvent event) {
      int id = (productTable.getSelectionModel().getSelectedItem()).getProductID();
      Inventory.removeProduct(id);
   }

   @FXML
   void exitMainScreen(ActionEvent event) {
      Platform.exit();
   }

   @FXML
   void modifyPartsOnAction(ActionEvent event) throws IOException {
      if (partsTable.getSelectionModel().getSelectedItem() == null) {
         errorLabel.setText("Please select an item to modify.");
      } else {
         errorLabel.setText("");
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainScreenController.class.getResource("/View_Controller/ModifyPart.fxml"));
         AnchorPane ap = (AnchorPane) loader.load();

         ModifyPartController mpc = loader.getController();
         if (partsTable.getSelectionModel().getSelectedItem() instanceof InhousePart) {
            InhousePart partToModify = (InhousePart) partsTable.getSelectionModel().getSelectedItem();
            mpc.populateInhousePart(partToModify);
         }
         if (partsTable.getSelectionModel().getSelectedItem() instanceof OutsourcedPart) {
            OutsourcedPart partToModify = (OutsourcedPart) partsTable.getSelectionModel().getSelectedItem();
            mpc.populateOutsourcedPart(partToModify);
         }

         Stage modPart = new Stage();
         modPart.setTitle("Modify Part");
         Scene scene = new Scene(ap);

         Node source = (Node) event.getSource();
         Stage stage = (Stage) source.getScene().getWindow();
         stage.close();

         modPart.setScene(scene);
         modPart.show();
      }
   }

   @FXML
   void modifyProductsOnAction(ActionEvent event) throws IOException {
// Get item to modify.
      if (productTable.getSelectionModel().getSelectedItem() == null) {
         errorLabel2.setText("Please select an item to modify.");
      } else {
         Product prodToModify = productTable.getSelectionModel().getSelectedItem();

         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(MainScreenController.class.getResource("/View_Controller/Product.fxml"));
         AnchorPane ap = (AnchorPane) loader.load();

         ProductController pc = loader.getController();
         pc.populateProduct(prodToModify);

         Stage modStage = new Stage();
         modStage.setTitle("Modify Product");
         Scene scene = new Scene(ap);

         Node source = (Node) event.getSource();
         Stage stage = (Stage) source.getScene().getWindow();
         stage.close();

         modStage.hide();
         modStage.setScene(scene);
         modStage.show();
      }
   }

   @FXML
   void searchPartsOnAction(ActionEvent event) {
      // Search by ID if numeric, search by name if not.
      if (searchParts.getText().matches("[0-9]+")) {
         partsTable.getSelectionModel().select(Inventory.partIndex(Integer.valueOf(searchParts.getText())));
      } else {
         partsTable.getSelectionModel().select(Inventory.partName(searchParts.getText()));
      }
   }

   @FXML
   void searchProductsOnAction(ActionEvent event) {
      // Search by ID if numeric, search by name if not.
      if (searchProducts.getText().matches("[0-9]+")) {
         productTable.getSelectionModel().select(Inventory.productIndex(Integer.valueOf(searchProducts.getText())));
      } else {
         productTable.getSelectionModel().select(Inventory.productName(searchProducts.getText()));
      }
   }

   /**
    * Initializes the controller class.
    *
    * @param url
    * @param rb
    */
   @Override
   public void initialize(URL url, ResourceBundle rb) {

      productsProductIDCol.setCellValueFactory(new PropertyValueFactory<>("productID"));
      productsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
      productsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
      productsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

      productTable.setItems(Inventory.products);

      partsPartIDCol.setCellValueFactory(new PropertyValueFactory<>("partID"));
      partsPartNameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
      partsInventoryLevelCol.setCellValueFactory(new PropertyValueFactory<>("inStock"));
      partsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

      partsTable.setItems(Inventory.allParts);

   }
}
