module edu.westga.cs3211.pirateinventory {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens edu.westga.cs3211.pirateinventory.view to javafx.fxml;
    opens edu.westga.cs3211.pirateinventory.controller to javafx.fxml;

    exports edu.westga.cs3211.pirateinventory.main;
}
