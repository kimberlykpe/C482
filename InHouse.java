package Model;

/** This class InHouse parts in Model that extends the Part class.*/
public class InHouse extends Part {

    /** Sets the machineID as an integer. */
    private int machineID;

    /** InHouse constructor for Part.
     @param id the ID of the part
     @param name the name of the part
     @param price the price of the part
     @param stock the stock level of the part
     @param min the minimum stock of the part
     @param max the maximum stock of the part
     @param machineID the machine ID */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {

        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /** machineID getter.
     @return machineId of part */
    public String getMachineID() {
        return String.valueOf(machineID);
    }

    /** MachineID setter.
     @param machineID machineID of the part */
    public void setMachineID(int machineID) { this.machineID = machineID; }

}

