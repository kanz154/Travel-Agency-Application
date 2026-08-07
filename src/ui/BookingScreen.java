package ui;

import DatabaseLayer.CRUD;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BookingScreen {

    private Stage stage;
    private String serviceType;    // "Flight" / "Hotel" / "Bus" / "Visa" / "Package"
    private String serviceDetails;

    public BookingScreen(Stage stage, String serviceType, String serviceDetails) {
        this.stage = stage;
        this.serviceType = serviceType;
        this.serviceDetails = serviceDetails;
    }

    public void show() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #0A0F1E;");

        VBox card = new VBox(16);
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(500);
        card.getStyleClass().add("auth-card");

        Label title = new Label("Complete Your Booking");
        title.getStyleClass().add("auth-title");

        Label serviceLabel = new Label("Service: " + serviceDetails);
        serviceLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #60A5FA;");
        serviceLabel.setWrapText(true);

        Label bookingFor = new Label("Booking for: " + UserSession.email);
        bookingFor.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748B;");

        Separator sep = new Separator();

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.setText(UserSession.name != null ? UserSession.name : "");
        nameField.getStyleClass().add("form-field");

        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.setText(UserSession.email != null ? UserSession.email : "");
        emailField.setEditable(false);
        emailField.getStyleClass().add("form-field");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.setText(UserSession.phone != null ? UserSession.phone : "");
        phoneField.getStyleClass().add("form-field");

        DatePicker travelDate = new DatePicker();
        travelDate.setPromptText("Travel Date");
        travelDate.setPrefWidth(340);

        ComboBox<String> personsBox = new ComboBox<>();
        personsBox.getItems().addAll("1 Person","2 Persons","3 Persons","4 Persons","5+ Persons");
        personsBox.setValue("1 Person");
        personsBox.setPrefWidth(340);
        personsBox.getStyleClass().add("combo-box");

        Label errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label");
        errorLabel.setWrapText(true);

        Button confirmBtn = new Button("Confirm Booking");
        confirmBtn.getStyleClass().add("btn-primary");
        confirmBtn.setPrefWidth(340);

        Button backBtn = new Button("← Back to Home");
        backBtn.getStyleClass().add("link-btn");

        card.getChildren().addAll(
                title, serviceLabel, bookingFor, sep,
                nameField, emailField, phoneField,
                travelDate, personsBox,
                errorLabel, confirmBtn, backBtn
        );

        root.getChildren().add(card);

        confirmBtn.setOnAction(e -> {
            errorLabel.setText("");

            if (nameField.getText().trim().isEmpty()
                    || phoneField.getText().trim().isEmpty()
                    || travelDate.getValue() == null) {
                errorLabel.setText("Please fill all required fields.");
                return;
            }

            try {
                String bookingDetails =
                        serviceDetails
                        + " | Name: "    + nameField.getText().trim()
                        + " | Email: "   + emailField.getText()
                        + " | Phone: "   + phoneField.getText().trim()
                        + " | Date: "    + travelDate.getValue()
                        + " | Persons: " + personsBox.getValue();

                String digits = serviceDetails.replaceAll("[^0-9]", "");
                double amount = digits.isEmpty() ? 0 : Double.parseDouble(digits);
                String dateStr = travelDate.getValue().toString();

                // --- Ab serviceType saaf pata hai, guess nahi karna parta ---
                int refId = -1;

                switch (serviceType) {
                    case "Flight": {
                        String[] parts = serviceDetails.split("→");
                        String origin = parts.length > 0 ? parts[0].replace("Flight:", "").trim() : "";
                        String dest   = parts.length > 1 ? parts[1].split("—")[0].trim() : "";
                        refId = CRUD.insertFlight(origin, dest, (int) amount, dateStr);
                        break;
                    }
                    case "Bus": {
                        String[] parts = serviceDetails.split("→");
                        String origin = parts.length > 0 ? parts[0].replace("Bus:", "").trim() : "";
                        String dest   = parts.length > 1 ? parts[1].split("—")[0].trim() : "";
                        refId = CRUD.insertBus(origin, dest, (int) amount, dateStr);
                        break;
                    }
                    case "Hotel": {
                        String name = serviceDetails.split("—")[0]
                                        .replace("Hotel in ", "").trim();
                        refId = CRUD.insertHotel(name, name, (int) amount, bookingDetails);
                        break;
                    }
                    case "Visa": {
                        String name = serviceDetails.split("—")[0].trim();
                        refId = CRUD.insertVisa(UserSession.userID, name, name, bookingDetails);
                        break;
                    }
                    case "Package": {
                        String name = serviceDetails.split("—")[0].trim();
                        refId = CRUD.findPackageId(name);
                        break;
                    }
                }

                boolean success = CRUD.insertBooking(UserSession.userID, refId, bookingDetails, amount, dateStr);

                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Booking Confirmed");
                    alert.setHeaderText("Booking Successful!");
                    alert.setContentText(
                            "Your booking has been saved successfully.\n\n"
                            + "Name: "      + nameField.getText().trim()
                            + "\nEmail: "   + emailField.getText()
                            + "\nPhone: "   + phoneField.getText().trim()
                            + "\nPackage: " + serviceDetails
                            + "\nAmount: PKR " + (int) amount
                            + "\nDate: "    + travelDate.getValue()
                            + "\nPersons: " + personsBox.getValue()
                    );
                    alert.showAndWait();
                    confirmBtn.setDisable(true);
                    new MainExplorationScreen(stage).show();
                } else {
                    errorLabel.setText("Booking failed. Please try again.");
                }

            } catch (Exception ex) {
                errorLabel.setText("Error: " + ex.getMessage());
            }
        });

        backBtn.setOnAction(e -> new MainExplorationScreen(stage).show());

        Scene scene = new Scene(root, 1280, 760);
        scene.getStylesheets().add(
                getClass().getResource("/ui/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Booking Form - Travel Agency");
        stage.show();
    }
}