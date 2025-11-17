package edu.westga.cs3211.pirateinventory.services;

import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.pirateinventory.model.SpecialQuality;
import edu.westga.cs3211.pirateinventory.model.StorageCompartment;

public class StorageService {

    private List<StorageCompartment> compartments;

    public StorageService() {
        this.compartments = new ArrayList<>();

        compartments.add(new StorageCompartment("A1", 20, true));
        compartments.add(new StorageCompartment("B1", 50, false));
        compartments.add(new StorageCompartment("C1", 10, true));
    }

    public List<StorageCompartment> findValidCompartments(int qty, SpecialQuality quality) {
        List<StorageCompartment> valid = new ArrayList<>();

        for (StorageCompartment c : compartments) {
            if (c.canStore(qty, quality)) {
                valid.add(c);
            }
        }

        return valid;
    }

    public void storeInCompartment(StorageCompartment compartment, int qty) {
        compartment.addStock(qty);
    }
}
