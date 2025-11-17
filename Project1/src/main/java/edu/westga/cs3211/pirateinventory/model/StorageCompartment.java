package edu.westga.cs3211.pirateinventory.model;

public class StorageCompartment {

    private String id;
    private int capacity;               
    private int used;                  
    private boolean supportsSpecialQuality;


    public StorageCompartment(String id, int capacity, boolean supportsSpecialQuality) {
        this.id = id;
        this.capacity = capacity;
        this.used = 0;
        this.supportsSpecialQuality = supportsSpecialQuality;
    }


    public String getId() {
        return this.id;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getUsed() {
        return this.used;
    }

    public int getAvailableSpace() {
        return this.capacity - this.used;
    }


 
    public boolean canStore(int quantity, SpecialQuality quality) {
        // If the stock is PERISHABLE but this compartment doesn’t support it
        if (quality == SpecialQuality.PERISHABLE && !this.supportsSpecialQuality) {
            return false;
        }
        // Check free space
        return quantity <= getAvailableSpace();
    }

 
    public void addStock(int quantity) {
        this.used += quantity;
    }

    @Override
    public String toString() {
        // This is what will show up if you ever put compartments in a ComboBox/ListView
        return this.id + " (free: " + getAvailableSpace() + ")";
    }
}
