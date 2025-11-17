package edu.westga.cs3211.pirateinventory.controller;

import edu.westga.cs3211.pirateinventory.model.Role;
import edu.westga.cs3211.pirateinventory.model.User;
import edu.westga.cs3211.pirateinventory.services.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Login view (matches the Login Page in the wireframe).
 */
public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private AuthenticationService auth;
    private User loggedInUser;

    public void setUser(User user) {
        this.loggedInUser = user;
    }


    public LoginController() {
        this.auth = new AuthenticationService();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = this.usernameField.getText().trim();
        String password = this.passwordField.getText().trim();

        // Missing input
        if (username.isEmpty() || password.isEmpty()) {
            this.messageLabel.setText("Both username and password are required.");
            return;
        }

        // Authenticate user
        User user = this.auth.authenticate(username, password);

        if (user == null) {
            this.messageLabel.setText("ERROR: Bad credentials!");
            return;
        }

        // Route by role
       Role role = user.getRole();

        switch (role) {
            case CREWMATE:
            	 loadViewWithUser("/edu/westga/cs3211/pirateinventory/view/AddStockView.fxml", user);
                break;

            case QUARTERMASTER:
            	 loadViewWithUser("/edu/westga/cs3211/pirateinventory/view/ViewStockChangesView.fxml", user);
                break;

            default:
                this.messageLabel.setText("Unknown user role.");
        }
    }
        
    private void loadViewWithUser(String viewPath, User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewPath));
            Parent root = loader.load();

            // Pass logged-in user to next controller
            Object controller = loader.getController();

            if (controller instanceof AddStockController) {
                ((AddStockController) controller).setUser(user);
            }
            else if (controller instanceof ViewStockChangesController) {
                ((ViewStockChangesController) controller).setUser(user);
            }

            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
