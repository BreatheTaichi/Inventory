/*  Exception controls chosen:
 *  Set 1:
      ensuring that a product must always have at least one part
    Set 2:
      ensuring that a product must have a name, price, category, and inventory level (default 0)
 */

package View_Controller;

import Model.Product;
import Model.OutsourcedPart;
import Model.InhousePart;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Jaime Gladish
 */

public class MainClass extends Application {
      @Override
   public void start(Stage stage) throws Exception {
      
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(MainClass.class.getResource("/View_Controller/MainScreen.fxml"));
      AnchorPane ap = (AnchorPane)loader.load();
      
      stage.setTitle("Inventory");
      Scene scene = new Scene(ap);
      stage.setScene(scene);
      stage.show();
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
      
      OutsourcedPart pa1 = new OutsourcedPart("PA1", 2.12, 7, 24, 8, "Company Name A");
      InhousePart pa2 = new InhousePart("PA2", 3.82, 2, 37, 5, 225);
      OutsourcedPart pa3 = new OutsourcedPart("PA3", 2.00, 3, 15, 8, "Company Name A");
      InhousePart pa4 = new InhousePart("PA4", 3.73, 4, 27, 7, 225);
      Product.getAllParts().add(pa1);
      Product.getAllParts().add(pa2);
      Product.getAllParts().add(pa3);
      Product.getAllParts().add(pa4);
      
      Product pr1 = new Product("PR1", 21.15, 3, 4, 8);
      Product pr2 = new Product("PR2", 13.00, 2, 2, 5);
      Product pr3 = new Product("PR3", 32.02, 3, 4, 8);
      Product pr4 = new Product("PR4", 11.12, 12, 22, 55);
      Product.getAllProducts().add(pr1);
      Product.getAllProducts().add(pr2);
      Product.getAllProducts().add(pr3);
      Product.getAllProducts().add(pr4);
      pr1.addAssociatedPart(pa1);
      pr1.addAssociatedPart(pa1);
      pr1.addAssociatedPart(pa2);
      pr2.addAssociatedPart(pa1);
      pr2.addAssociatedPart(pa4);
      pr3.addAssociatedPart(pa1);
      pr3.addAssociatedPart(pa3);
      pr3.addAssociatedPart(pa4);
      pr4.addAssociatedPart(pa3);
      pr4.addAssociatedPart(pa4);
      
      launch(args);
   }
}
