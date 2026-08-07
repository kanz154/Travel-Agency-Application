package ui;
import DatabaseLayer.CRUD;
import Core_Services.Registration;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginScreen {
    private Stage stage;
    private String pendingServiceType = null;
    private String pendingService = null;

    public LoginScreen(Stage stage) {
        this.stage = stage;
    }

    public LoginScreen(Stage stage, String pendingServiceType, String pendingService) {
        this.stage = stage;
        this.pendingServiceType = pendingServiceType;
        this.pendingService = pendingService;
    }

    public Scene getScene() {
        show();
        return stage.getScene();
    }

    public void show() {
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #0A0F1E;");
        VBox card = new VBox(16);
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(400);
        card.getStyleClass().add("auth-card");
        Label title = new Label("Welcome Back");
        title.getStyleClass().add("auth-title");
        Label subtitle = new Label("Sign in to your Travel Agency account");
        subtitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #475569;");
        TextField emailField = new TextField();
        emailField.setPromptText("Email address");
        emailField.getStyleClass().add("form-field");
        PasswordField passField = new PasswordField();
        passField.setPromptText("Password");
        passField.getStyleClass().add("form-field");
        Label errorLabel = new Label();
        errorLabel.getStyleClass().add("error-label");
        Button loginBtn = new Button("Sign In");
        loginBtn.getStyleClass().add("btn-primary");
        loginBtn.setPrefWidth(340);
        HBox regRow = new HBox(6);
        regRow.setAlignment(Pos.CENTER);
        Label noAcc = new Label("Don't have an account?");
        noAcc.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B;");
        Button goRegister = new Button("Register here");
        goRegister.getStyleClass().add("link-btn");
        regRow.getChildren().addAll(noAcc, goRegister);
        Button backBtn = new Button("← Back to Home");
        backBtn.getStyleClass().add("link-btn");
        card.getChildren().addAll(
                title, subtitle,
                emailField, passField,
                errorLabel,
                loginBtn, regRow, backBtn
        );
        root.getChildren().add(card);
        loginBtn.setOnAction(e -> {
            String email = emailField.getText().trim();
            String pass  = passField.getText().trim();
            if (email.isEmpty() || pass.isEmpty()) {
                errorLabel.setText("Please fill in all fields.");
                return;
            }
            Registration user = new CRUD().loginUser(email, pass);
            if (user != null) {
                UserSession.login(
                        String.valueOf(user.getId()),
                        user.getFullName(),
                        user.getEmail(),
                        user.getPhoneNumber()
                );
                if (pendingService != null) {
                    new BookingScreen(stage, pendingServiceType, pendingService).show();
                } else {
                    new MainExplorationScreen(stage).show();
                }
            } else {
                errorLabel.setText("Invalid email or password.");
            }
        });
        goRegister.setOnAction(e -> new RegistrationScreen(stage, pendingServiceType, pendingService).show());
        backBtn.setOnAction(e -> new MainExplorationScreen(stage).show());
        Scene scene = new Scene(root, 1280, 760);
        scene.getStylesheets().add(
                getClass().getResource("/ui/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Login — Travel Agency");
        stage.show();
    }
}