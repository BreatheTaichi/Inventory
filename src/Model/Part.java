/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Inventory.allParts;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jaime Gladish
 */
public abstract class Part {
   
   protected IntegerProperty partID = new SimpleIntegerProperty();
   protected StringProperty name = new SimpleStringProperty();
   protected DoubleProperty price = new SimpleDoubleProperty();
   protected IntegerProperty inStock = new SimpleIntegerProperty();
   protected IntegerProperty min = new SimpleIntegerProperty();
   protected IntegerProperty max = new SimpleIntegerProperty();

   public final void setName(String newName) {
      this.name.set(newName);
   }

   public final String getName() {
      return name.get();
   }
   
   public final StringProperty nameProperty() {
      return name;
   }

   public final void setPrice(double newPrice) {
      this.price.set(newPrice);
   }

   public final double getPrice() {
      return price.get();
   }
   
   public final DoubleProperty priceProperty() {
      return price;
   }

   public final void setInStock(int stockSet) {
      this.inStock.set(stockSet);
   }

   public final int getInStock() {
      return inStock.get();
   }
   
   public final IntegerProperty inStockProperty() {
      return inStock;
   }

   public final void setMin(int newMin) {
      this.min.set(newMin);
   }

   public final int getMin() {
      return min.get();
   }
   
   public final IntegerProperty minProperty() {
      return min;
   }

   public final void setMax(int newMax) {
      this.max.set(newMax);
   }

   public final int getMax() {
      return max.get();
   }
   
   public final IntegerProperty maxProperty() {
      return max;
   }

   public final void setPartID(int setID) {
      this.partID.set(setID);
   }

   public final int getPartID() {
      return partID.get();
   }
   
   public final IntegerProperty partIDProperty() {
      return partID;
   }

   public static Part lookupPart(int findPart) {
      for (Part part : allParts) {
         if (part.getPartID() == findPart) {
            return part;
         }
      }
      return null;
   }
}
