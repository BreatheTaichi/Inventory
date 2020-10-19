/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import static Model.Inventory.allParts;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jaime Gladish
 */
public class InhousePart extends Part {

   IntegerProperty macID = new SimpleIntegerProperty();

   public InhousePart(String name, double price,
           int howMany, int theMax, int theMin, int macID) {
      this.partID = new SimpleIntegerProperty(Inventory.partIDCount());
      this.name = new SimpleStringProperty(name);
      this.price = new SimpleDoubleProperty(price);
      this.inStock = new SimpleIntegerProperty(howMany);
      this.min = new SimpleIntegerProperty(theMin);
      this.max = new SimpleIntegerProperty(theMax);
      this.macID = new SimpleIntegerProperty(macID);
   }

   public final void setMachineID(int machineID) {
      this.macID.set(machineID);
   }

   public final IntegerProperty MachineIDProperty() {
      return macID;
   }

   public final int getMachineID() {
      return macID.get();
   }

   public static InhousePart lookupInhousePart(int findPart) {
      for (Part part : allParts) {
         if (part.getPartID() == findPart) {
            return (InhousePart) part;
         }
      }
      return null;
   }
}
