
package Model;

import static Model.Inventory.allParts;
import static Model.Inventory.products;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jaime Gladish
 */
public class Product {

   private ObservableList associatedParts = FXCollections.observableArrayList();

   private IntegerProperty productID = new SimpleIntegerProperty();
   private StringProperty name = new SimpleStringProperty();
   private DoubleProperty price = new SimpleDoubleProperty();
   private IntegerProperty inStock = new SimpleIntegerProperty();
   private IntegerProperty min = new SimpleIntegerProperty();
   private IntegerProperty max = new SimpleIntegerProperty();
   
   public Product() {
      
   }

   public Product(String name, double price, int inStock, int min, int max) {
      this.associatedParts = associatedParts;
      this.productID = new SimpleIntegerProperty(Inventory.productIDCount());
      this.name = new SimpleStringProperty(name);
      this.price = new SimpleDoubleProperty(price);
      this.inStock = new SimpleIntegerProperty(inStock);
      this.min = new SimpleIntegerProperty(min);
      this.max = new SimpleIntegerProperty(max);
   }

   public void setName(String newName) {
      this.name.set(newName);
   }

   public final String getName() {
      return this.name.get();
   }
   
   public final StringProperty nameProperty() {
      return name;
   }

   public void setPrice(double newPrice) {
      price.set(newPrice);
   }

   public final double getPrice() {
      return this.price.get();
   }
   
   public final DoubleProperty priceProperty() {
      return price;
   }

   public void setInStock(int stockSet) {
      this.inStock.set(stockSet);
   }

   public final int getInStock() {
      return this.inStock.get();
   }
   
   public final IntegerProperty inStockProperty() {
      return inStock;
   }

   public void setMin(int newMin) {
      this.min.set(newMin);
   }

   public final int getMin() {
      return this.min.get();
   }
   
   public final IntegerProperty minProperty() {
      return min;
   }

   public final void setMax(int newMax) {
      this.max.set(newMax);
   }

   public final int getMax() {
      return this.max.get();
   }
   
   public final IntegerProperty maxProperty() {
      return max;
   }

   public void addAssociatedPart(Part newPart) {
      associatedParts.add(newPart);
   }
   
   public void removeAssociatedPart(Part delPart) {
      associatedParts.remove(delPart);
   }
   
   public static ObservableList<Part> getAllParts() {
      return allParts;
   }
   
   public static ObservableList<Product> getAllProducts() {
      return products;
   }

   public ObservableList<Part> getAssociatedParts() {
      return this.associatedParts;
   }

   public final void setProductID(int setID) {
      this.productID.set(setID);
   }

   public final int getProductID() {
      return this.productID.get();
   }
   
   public final IntegerProperty productIDProperty() {
      return productID;
   }

   public void printID() {
      products.forEach((product) -> {
         System.out.println(product.productID);
      });
   }
}
