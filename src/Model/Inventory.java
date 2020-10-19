package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jaime Gladish
 */
public class Inventory {

   public Inventory() {
      System.out.println("Inventory constructor called.");
   }

   public static ObservableList<Product> products = FXCollections.observableArrayList();
   public static ObservableList<Part> allParts = FXCollections.observableArrayList();
   
   public static int productIDVar = 0;
   public static int partIDVar = 0;

   public static int productIDCount() {
      return (productIDVar += 1);
   }

   public static int partIDCount() {
      return (partIDVar += 1);
   }

   public static void addProduct(Product newProduct) {
      products.add(newProduct);
   }

   public static boolean removeProduct(int delProduct) {
      for (Product product : products) {
         if (product.getProductID() == delProduct) {
            return products.remove(product);
         }
      }
      return false;
   }
   
   public static int partIndex(int searchIndex) {
      Part part = lookupPart(searchIndex);
      return allParts.indexOf(part);
   }
   
   public static int partName(String searchName) {
      Part part = lookupPartName(searchName);
      return allParts.indexOf(part);
   }
   
   public static int productName(String searchName) {
      Product product = lookupProductName(searchName);
      return products.indexOf(product);
   }
   
   public static int productIndex(int searchIndex) {
      Product product = lookupProduct(searchIndex);
      return products.indexOf(product);
   }

   public static Product lookupProduct(int find) {
      for (Product product : products) {
         if (product.getProductID() == find) {
            return product;
         }
      }
      return null;
   }

   public static void addPart(Part newPart) {
      allParts.add(newPart);
   }

   public static boolean deletePart(Part delPart) {
      for (Part part : allParts) {
         if (part.equals(delPart)) {
            return allParts.remove(delPart);
         }
      }
      return false;
   }

   public static Part lookupPart(int findPart) {
      for (Part part : allParts) {
         if (part.getPartID() == findPart) {
            return part;
         }
      }
      return null;
   }

   public static Part lookupPartName(String findPart) {
      for (Part part : allParts) {
         if (part.getName().equals(findPart)) {
            return part;
         }
      }
      return null;
   }
   
   public static Product lookupProductName(String findProduct) {
      for (Product product : products) {
         if (product.getName().equals(findProduct)) {
            return product;
         }
      }
      return null;
   }

   public static void updatePart(Part update) {
      allParts.stream().filter((part) -> 
              (part.getPartID() == update.getPartID())).forEachOrdered((part) -> {
         part = update;
      });
   }   
   public static void updatesPart(Part update) {
      for (Part part : allParts) {
         if (part.getPartID() == update.getPartID()) {
            part = update;
         }
      }
   }
}
