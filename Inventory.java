package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class for Inventory for the application.*/
public class Inventory {
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static boolean lookupProduct1;
    public static int partId;
    public static boolean lookupProduct2;

    /** Allows user to add part to the inventory.
     @param newPart adds new part to inventory. */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /** Allows user to add product to the inventory.
     @param newProduct adds new product to inventory. */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /** This allows the user to look up parts.
    @param partId part id
     @return searcchParts the results from the search.
     @return null if empty. */
    public static Part lookupPart(int partId) {
        Part searchParts = null;

        for (Part part : allParts) {
            if (part.getId() == partId) {
                searchParts = part;
                return searchParts;
            }
        }

        return null;
    }

    /** This allows the user to look up products.
     @param productId product id
     @return searcchProducts the results from the search.
     @return null if empty. */
    public static Product lookupProduct(int productId) {
        Product searchProducts = null;

        for(Product product : allProducts){
            if(product.getId() == productId){
                searchProducts = product;
                return searchProducts;
            }
        }
        return null;
    }

    /** Search part by name.
     @param partName the part name
     @return searchParts to their correct match. */
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> searchParts = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().toLowerCase().contains(partName.toLowerCase())) {
                searchParts.add(part);
            }
        }
        if (searchParts.isEmpty()){
            lookupProduct1 = false;
            return searchParts;
        }
        lookupProduct1 = true;
        return searchParts;
    }

    /** Search product by name.
     @param productName the product name
     @return searchProducts to their correct match. */
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> searchProducts = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(productName.toLowerCase())) {
                searchProducts.add(product);
            }
        }
        return searchProducts;
    }

    /** Allows the application to update the part.
     @param index index the part
     @param selectedPart the part that needs to be updated. */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /** Search product by name.
     @param index index the product
     @param selectedProduct the product that needs to be updated. */
    public static void updateProduct(int index, Product selectedProduct) {
        allProducts.set(index, selectedProduct);
    }

    /** Deletes the selected part.
     @param selectedPart deletes the selected part
     @return true if a part is selected
     @return false is a part is not selected */
    public static boolean deletePart (Part selectedPart) {
        if(allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /** Deletes the selected product.
     @param selectedPart deletes the selected part
     @return true if a part is selected
     @return false is a part is not selected */
    public static boolean deleteProduct(Product selectedPart) {
        if(allProducts.contains(selectedPart)) {
            allProducts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /** Displays the table of parts.
     @return allParts the parts */
    public static ObservableList<Part> getAllParts() { return allParts; }

    /** Displays the table of products.
     @return allProducts the products */
    public static ObservableList<Product> getAllProducts() { return allProducts; }



}

