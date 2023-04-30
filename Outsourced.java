package Model;

/** This class InHouse parts in Model that extends the Part class.*/
public class Outsourced extends Part {

    /** Sets the companyName as a string. */
    private String companyName;

    /** Outsourced constructor for Part.
     @param id the ID of the part
     @param name the name of the part
     @param price the price of the part
     @param stock the stock level of the part
     @param min the minimum stock of the part
     @param max the maximum stock of the part
     @param companyName the company name */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {

        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /** Company name setter.
     @param companyName company name of the part */
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    /** Company name getter.
     @return companyName company name of part */
    public String getCompanyName() { return companyName; }


}

