
package Model;

import static Model.Inventory.allParts;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jaime Gladish
 */
public class OutsourcedPart extends Part {

   private StringProperty companyName = new SimpleStringProperty();

   public OutsourcedPart(String name, double price,
           int howMany, int theMax, int theMin, String companyName) {
      this.partID = new SimpleIntegerProperty(Inventory.partIDCount());
      this.name = new SimpleStringProperty(name);
      this.price = new SimpleDoubleProperty(price);
      this.inStock = new SimpleIntegerProperty(howMany);
      this.min = new SimpleIntegerProperty(theMin);
      this.max = new SimpleIntegerProperty(theMax);
      this.companyName = new SimpleStringProperty(companyName);
   }

   public StringProperty companyNameProperty() {
      return companyName;
   }

   public final void setCompanyName(String company) {
      this.companyName.set(company);
   }

   public final String getCompanyName() {
      return companyName.get();
   }

   public static OutsourcedPart lookupOutsourcedPart(int findPart) {
      for (Part part : allParts) {
         if (part.getPartID() == findPart) {
            return (OutsourcedPart) part;
         }
      }
      return null;
   }
}
