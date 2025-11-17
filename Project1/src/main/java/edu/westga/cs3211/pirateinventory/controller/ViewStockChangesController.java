package edu.westga.cs3211.pirateinventory.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import edu.westga.cs3211.pirateinventory.model.SpecialQuality;
import edu.westga.cs3211.pirateinventory.model.StockChange;
import edu.westga.cs3211.pirateinventory.model.User;
import edu.westga.cs3211.pirateinventory.services.ServiceRegistry;
import edu.westga.cs3211.pirateinventory.services.StockChangeService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ViewStockChangesController {
	
	private User loggedInUser;
	private StockChangeService stockService = ServiceRegistry.getStockChangeService();


	public void setUser(User user) {
	    this.loggedInUser = user;
	}
	
	@FXML
	private void handleLogout() {
	    loadViewWithUser("/edu/westga/cs3211/pirateinventory/view/LoginView.fxml");
	}

	
	 @FXML
	    private ListView<String> changesList;

	    @FXML
	    private ComboBox<SpecialQuality> qualityFilter;

	    @FXML
	    private ComboBox<String> crewFilter;

	    @FXML
	    private DatePicker startDate;

	    @FXML
	    private DatePicker endDate;


	@FXML
    public void initialize() {
        // Populate dropdowns
        qualityFilter.getItems().setAll(SpecialQuality.values());

        // Fill crew names dynamically from stock changes
        crewFilter.getItems().clear();
        for (StockChange sc : stockService.getAllChanges()) {
            if (!crewFilter.getItems().contains(sc.getCrewMateId())) {
                crewFilter.getItems().add(sc.getCrewMateId());
            }
        }

        // Initially show all
        loadAllChanges();
    }


   
    // LOAD EVERYTHING
    private void loadAllChanges() {
        changesList.getItems().clear();
        List<StockChange> all = stockService.getAllChanges();

        for (StockChange sc : all) {
            changesList.getItems().add(sc.toString());
        }
    }


    // -----------------------------
    // FILTER BY QUALITY
    // -----------------------------
    @FXML
    private void filterByQuality() {
        SpecialQuality q = qualityFilter.getValue();
        if (q == null) return;

        changesList.getItems().clear();
        List<StockChange> filtered = stockService.filterByQuality(q);

        for (StockChange sc : filtered) {
            changesList.getItems().add(sc.toString());
        }
    }


    // FILTER BY CREWMATE
    @FXML
    private void filterByCrew() {
        String crew = crewFilter.getValue();
        if (crew == null) return;

        changesList.getItems().clear();
        List<StockChange> filtered = stockService.filterByCrewMate(crew);

        for (StockChange sc : filtered) {
            changesList.getItems().add(sc.toString());
        }
    }


    // FILTER BY DATE RANGE
    @FXML
    private void filterByDateRange() {
        if (startDate.getValue() == null || endDate.getValue() == null) return;

        LocalDateTime start = startDate.getValue().atStartOfDay();
        LocalDateTime end = endDate.getValue().atTime(LocalTime.MAX);

        changesList.getItems().clear();
        List<StockChange> filtered = stockService.filterByDateRange(start, end);

        for (StockChange sc : filtered) {
            changesList.getItems().add(sc.toString());
        }
    }


    // RESET FILTERS
    @FXML
    private void clearFilters() {
        qualityFilter.setValue(null);
        crewFilter.setValue(null);
        startDate.setValue(null);
        endDate.setValue(null);
        loadAllChanges();
    }
    
    private void loadViewWithUser(String viewPath) {
    	 try {
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
    	        Parent root = loader.load();
    	        Stage stage = (Stage) changesList.getScene().getWindow();
    	        stage.setScene(new Scene(root));
    	        stage.show();
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
	}
}
