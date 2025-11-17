package edu.westga.cs3211.pirateinventory.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class StockItem {

    private String name;
    private int quantity;
    private Condition condition;
    private Set<SpecialQuality> specialQualities;
    private LocalDate expirationDate;   // can be null

    public StockItem(String name, int quantity, Condition condition,
                     Set<SpecialQuality> specialQualities, LocalDate expirationDate) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        if (condition == null) {
            throw new IllegalArgumentException("Condition is required.");
        }
        if (specialQualities == null) {
            specialQualities = new HashSet<SpecialQuality>();
        }

        if (specialQualities.contains(SpecialQuality.PERISHABLE) && expirationDate == null) {
            throw new IllegalArgumentException("Perishable items need an expiration date.");
        }

        this.name = name;
        this.quantity = quantity;
        this.condition = condition;
        this.specialQualities = new HashSet<SpecialQuality>(specialQualities);
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Condition getCondition() {
        return this.condition;
    }

    public Set<SpecialQuality> getSpecialQualities() {
        return new HashSet<SpecialQuality>(this.specialQualities);
    }

    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }
}