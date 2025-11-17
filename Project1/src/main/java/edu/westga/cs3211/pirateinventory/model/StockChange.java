package edu.westga.cs3211.pirateinventory.model;

import java.time.LocalDateTime;

public class StockChange {

    private String crewMateId;
    private String itemName;
    private int quantityAdded;
    private Condition condition;
    private SpecialQuality quality;
    private String compartmentId;
    private int remainingCapacity;
    private LocalDateTime timestamp;

    public StockChange(String crewMateId, String itemName, int quantityAdded,
                       Condition condition, SpecialQuality quality, String compartmentId,
                       int remainingCapacity) {

        this.crewMateId = crewMateId;
        this.itemName = itemName;
        this.quantityAdded = quantityAdded;
        this.condition = condition;
        this.quality = quality;
        this.compartmentId = compartmentId;
        this.remainingCapacity = remainingCapacity;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() { return this.timestamp; }
    public String getCrewMateId() { return this.crewMateId; }
    public String getItemName() { return this.itemName; }
    public int getQuantityAdded() { return this.quantityAdded; }
    public Condition getCondition() { return this.condition; }
    public SpecialQuality getQuality() { return this.quality; }
    public String getCompartmentId() { return this.compartmentId; }
    public int getRemainingCapacity() { return this.remainingCapacity; }

    @Override
    public String toString() {
        return timestamp + " - " + crewMateId +
               " added " + quantityAdded + " " + itemName +
               " (Cond: " + condition + ", Qual: " + quality +
               ") stored in " + compartmentId +
               " | free: " + remainingCapacity;
    }
}
