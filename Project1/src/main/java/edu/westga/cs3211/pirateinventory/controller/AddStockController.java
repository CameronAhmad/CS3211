package edu.westga.cs3211.pirateinventory.controller;

import java.util.List;
import java.util.Optional;


import edu.westga.cs3211.pirateinventory.model.Condition;
import edu.westga.cs3211.pirateinventory.model.SpecialQuality;
import edu.westga.cs3211.pirateinventory.model.StockChange;
import edu.westga.cs3211.pirateinventory.model.StorageCompartment;
import edu.westga.cs3211.pirateinventory.model.User;
import edu.westga.cs3211.pirateinventory.services.ServiceRegistry;
import edu.westga.cs3211.pirateinventory.services.StockChangeService;
import edu.westga.cs3211.pirateinventory.services.StorageService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddStockController {
	
	private StockChangeService stockChangeService = ServiceRegistry.getStockChangeService();
	private StorageService storageService = ServiceRegistry.getStorageService();

	private User loggedInUser;

	public void setUser(User user) {
	    this.loggedInUser = user;
	}
	
	@FXML
	private void handleLogout() {
	    loadViewWithUser("/edu/westga/cs3211/pirateinventory/view/LoginView.fxml");
	}




    @FXML private TextField itemNameField;
    @FXML private TextField quantityField;
    @FXML private ComboBox<Condition> conditionBox;
    @FXML private ComboBox<SpecialQuality> qualityBox;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        conditionBox.getItems().setAll(Condition.values());
        qualityBox.getItems().setAll(SpecialQuality.values());
    }
    

    @FXML
    private void handleAddStock() {

        String item = itemNameField.getText().trim();
        String qtyStr = quantityField.getText().trim();
        Condition condition = conditionBox.getValue();
        SpecialQuality quality = qualityBox.getValue();

        // Validation
        if (item.isEmpty() || qtyStr.isEmpty() || condition == null || quality == null) {
            messageLabel.setText("All fields must be filled out.");
            return;
        }

        int qty;
        try {
            qty = Integer.parseInt(qtyStr);
        } catch (NumberFormatException e) {
            messageLabel.setText("Quantity must be a number.");
            return;
        }

        // STEP 3–4: Find valid compartments
        List<StorageCompartment> valid = storageService.findValidCompartments(qty, quality);

        if (valid.isEmpty()) {
            // ALT FLOW A4–A5
            messageLabel.setText("No storage compartment has enough space or correct quality for this stock.");
            return;
        }

        // STEP 5–6: Ask user which compartment to use
        ChoiceDialog<StorageCompartment> dialog =
                new ChoiceDialog<>(valid.get(0), valid);
        dialog.setTitle("Select Compartment");
        dialog.setHeaderText("Choose a compartment to store the stock:");
        dialog.setContentText("Available compartments:");

        Optional<StorageCompartment> result = dialog.showAndWait();

        if (!result.isPresent()) {
            messageLabel.setText("Stock addition canceled.");
            return;
        }

        StorageCompartment chosen = result.get();

        // STEP 7: Store stock
        storageService.storeInCompartment(chosen, qty);

     // STEP 8: Log Stock Change
        StockChange change = new StockChange(
                loggedInUser.getUsername(),              // crewmateId
                item,                             // itemName
                qty,            // quantityAdded
                condition,                        // condition
                quality,                          // special quality
                chosen.getId(),                   // compartment ID
                chosen.getAvailableSpace()        // remaining capacity
        );


        stockChangeService.logChange(change);


    }
    
    private void loadViewWithUser(String viewPath) {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
	        Parent root = loader.load();
	        Stage stage = (Stage) itemNameField.getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
