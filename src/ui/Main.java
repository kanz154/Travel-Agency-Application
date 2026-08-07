package ui;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AEROVISTA TRAVELS");
        LoginScreen loginScreen = new LoginScreen(primaryStage);
        primaryStage.setScene(loginScreen.getScene());
        primaryStage.show();
    }
    public static void main(String[] args) { 
        launch(args); 
    }
}