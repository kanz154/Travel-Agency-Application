package travelagencyapplication;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import ui.*;
import DatabaseLayer.*;
import Core_Services.*;

public class TravelAgencyApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println("=====================================");
        System.out.println("AEROVISTA TRAVELS");
        System.out.println("=====================================");

        boolean dbConnected = initializeDatabaseConnection();

        if (!dbConnected) {
            showErrorAlert("Database Connection Failed",
                "Cannot connect to MySQL. Please ensure XAMPP MySQL is running.\n\nApp will open but bookings won't save.");
        }

        primaryStage.setTitle("Travel Agency Application");

        new MainExplorationScreen(primaryStage).show();
    }

    private boolean initializeDatabaseConnection() {
        try {
            if (DBConnection.getConnection() != null) {
                System.out.println("[DB] Connected to MySQL successfully.");
                CRUD databaseOperations = new CRUD();
                Registration userTest = new Registration();
                TravelingPackage packageTest = new TravelingPackage();
                System.out.println("[CoreServices] Model classes loaded.");
                System.out.println("[DatabaseLayer] CRUD operations initialized.");
                return true;
            }
        } catch (Exception e) {
            System.err.println("[DB] Connection Error: " + e.getMessage());
        }
        return false;
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}