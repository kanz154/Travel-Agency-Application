package ui;

import DatabaseLayer.CRUD;
import Core_Services.Registration;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RegistrationScreen {

    private Stage stage;
    private String pendingServiceType = null;
    private String pendingService = null;

    public RegistrationScreen(Stage stage) {
        this.stage = stage;
    }

    public RegistrationScreen(Stage stage, String pendingServiceType, String pendingService) {
        this.stage = stage;
        this.pendingServiceType = pendingServiceType;
        this.pendingService = pendingService;
    }

    public void show() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #0A0F1E;");

        VBox card = new VBox(16);
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(420);
        card.getStyleClass().add("auth-card");

        Label title = new Label("Create Account");
        title.getStyleClass().add("auth-title");

        Label subtitle = new Label("Join us and start exploring the world");
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #475569;");

        TextField nameField = new TextField();
        nameField.setPromptText("Full Name");
        nameField.getStyleClass().add("form-field");

        TextField emailField = new TextField();
        emailField.setPromptText("Email Address");
        emailField.getStyleClass().add("form-field");

        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        phoneField.getStyleClass().add("form-field");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        passField.getStyleClass().add("form-field");

        PasswordField confirmField = new PasswordField();
        confirmField.setPromptText("Confirm Password");
        confirmField.getStyleClass().add("form-field");

        Label errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label");
        errorLabel.setWrapText(true);

        Label successLabel = new Label();
        successLabel.getStyleClass().add("success-label");

        Button registerBtn = new Button("Create Account");
        registerBtn.getStyleClass().add("btn-primary");
        registerBtn.setPrefWidth(340);

        HBox loginRow = new HBox(6);
        loginRow.setAlignment(Pos.CENTER);
        Label hasAcc = new Label("Already have an account?");
        hasAcc.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B;");
        Button goLogin = new Button("Sign In");
        goLogin.getStyleClass().add("link-btn");
        loginRow.getChildren().addAll(hasAcc, goLogin);

        card.getChildren().addAll(
                title, subtitle,
                nameField, emailField, phoneField,
                passField, confirmField,
                errorLabel, successLabel,
                registerBtn, loginRow
        );

        root.getChildren().add(card);

        registerBtn.setOnAction(e -> {
            String name    = nameField.getText().trim();
            String email   = emailField.getText().trim();
            String phone   = phoneField.getText().trim();
            String pass    = passField.getText().trim();
            String confirm = confirmField.getText().trim();

            errorLabel.setText("");
            successLabel.setText("");

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()
                    || pass.isEmpty() || confirm.isEmpty()) {
                errorLabel.setText("All fields are required.");
                return;
            }
            if (!pass.equals(confirm)) {
                errorLabel.setText("Passwords do not match.");
                return;
            }

            try {
                Registration user = new Registration(name, email, phone, pass);
                boolean success = new CRUD().registerUser(user);

                if (success) {
                    Registration dbUser = new CRUD().loginUser(email, pass);
                    if (dbUser != null) {
                        UserSession.login(
                            String.valueOf(dbUser.getId()),
                            dbUser.getFullName(),
                            dbUser.getEmail(),
                            dbUser.getPhoneNumber()
                        );
                    }
                    successLabel.setText("Account Created Successfully!");
                    if (pendingService != null) {
                        new BookingScreen(stage, pendingServiceType, pendingService).show();
                    } else {
                        new MainExplorationScreen(stage).show();
                    }
                } else {
                    errorLabel.setText("Registration failed. Email might already exist.");
                }
            } catch (Exception ex) {
                errorLabel.setText(ex.getMessage());
            }
        });

        goLogin.setOnAction(e -> new LoginScreen(stage, pendingServiceType, pendingService).show());

        Scene scene = new Scene(root, 1280, 760);
        scene.getStylesheets().add(
                getClass().getResource("/ui/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Register - Travel Agency");
        stage.show();
    }
}