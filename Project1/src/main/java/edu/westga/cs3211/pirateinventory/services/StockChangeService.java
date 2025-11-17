package edu.westga.cs3211.pirateinventory.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.westga.cs3211.pirateinventory.model.StockChange;
import edu.westga.cs3211.pirateinventory.model.SpecialQuality;

public class StockChangeService {

    private List<StockChange> changes = new ArrayList<>();

    public void logChange(StockChange change) {
        changes.add(change);
    }

    public List<StockChange> getAllChanges() {
        // most recent first
        List<StockChange> reversed = new ArrayList<>(changes);
        reversed.sort((a,b) -> b.getTimestamp().compareTo(a.getTimestamp()));
        return reversed;
    }

    public List<StockChange> filterByQuality(SpecialQuality quality) {
        List<StockChange> result = new ArrayList<>();
        for (StockChange c : changes) {
            if (c.getQuality() == quality) {
                result.add(c);
            }
        }
        return result;
    }

    public List<StockChange> filterByCrewMate(String crewMateId) {
        List<StockChange> result = new ArrayList<>();
        for (StockChange c : changes) {
            if (c.getCrewMateId().equals(crewMateId)) {
                result.add(c);
            }
        }
        return result;
    }

    public List<StockChange> filterByDateRange(LocalDateTime start, LocalDateTime end) {
        List<StockChange> result = new ArrayList<>();
        for (StockChange c : changes) {
            if (!c.getTimestamp().isBefore(start) && !c.getTimestamp().isAfter(end)) {
                result.add(c);
            }
        }
        return result;
    }
}
