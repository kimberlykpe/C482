package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/** This class is for Product.*/
public class Product {

    private static final ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** Product constructor
     @param id product id
     @param name product name
     @param price product price
     @param stock product stock
     @param min product min
     @param max product max */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** getter id for product.
     @return id for product */
    public int getId() {
        return id;
    }

    /** setter id for product. */
    public void setId(int id) {
        this.id = id;
    }

    /** getter name for product.
     @return name for product */
    public String getName() {
        return name;
    }

    /** setter name for product. */
    public void setName(String name) {
        this.name = name;
    }

    /** getter price for product.
     @return price for product */
    public double getPrice() {
        return price;
    }

    /** setter price for product. */
    public void setPrice(double price) {
        this.price = price;
    }

    /** getter stock for product.
     @return stock for product */
    public int getStock() {
        return stock;
    }

    /** setter stock for product. */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** getter min for product.
     @return min for product */
    public int getMin() {
        return min;
    }

    /** setter min for product. */
    public void setMin(int min) {
        this.min = min;
    }

    /** getter max for product.
     @return max for product */
    public int getMax() {
        return max;
    }

    /** setter max for product. */
    public void setMax(int max) {
        this.max = max;
    }

    /** adds associated parts to associated parts table.
     @param part adds part */
    public void addAssociatedParts (Part part) {
        associatedParts.add(part);
    }

    /** deletes part from associated parts table from product
     @param selectedAssociatedPart associated part
     @return true if associated part is selected
     @return false if no associated part is selected */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else {
            return false;
        }
    }

    /** table for associated parts
     @return associatedParts associated part selected */
    public static ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }


}
