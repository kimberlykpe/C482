package Model;

/** This class is for Part. @author Kimberly Escala. */
public abstract class Part {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** Part constructor
     @param id the ID of the part
     @param name the name of the part
     @param price the price of the part
     @param stock the stock level of the part
     @param min the minimum stock of the part
     @param max the maximum stock of the part */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /** getter id for part.
     @return id for part */
    public int getId() {
        return id;
    }

    /** setter id for part. */
    public void setId(int id) { this.id = id; }

    /** getter name for part.
     @return name for part */
    public String getName() {
        return name;
    }

    /** setter name for part. */
    public void setName(String name) {
        this.name = name;
    }

    /** getter price for part.
     @return price for part */
    public double getPrice() {
        return price;
    }

    /** setter price for part. */
    public void setPrice(double price) {
        this.price = price;
    }

    /** getter stock for part.
     @return stock for part */
    public int getStock() {
        return stock;
    }

    /** setter stock for part. */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /** getter min for part.
     @return min for part */
    public int getMin() {
        return min;
    }

    /** setter min for part. */
    public void setMin(int min) {
        this.min = min;
    }

    /** getter max for part.
     @return max for part */
    public int getMax() {
        return max;
    }

    /** setter max for part. */
    public void setMax(int max) { this.max = max; }


}
