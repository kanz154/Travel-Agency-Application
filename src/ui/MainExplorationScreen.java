package ui;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainExplorationScreen {

    private Stage stage;
    private VBox contentArea;

    public MainExplorationScreen(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #0A0F1E;");

        root.setTop(buildNavbar());

        contentArea = new VBox();
        contentArea.setStyle("-fx-background-color: #0A0F1E;");

        ScrollPane scroll = new ScrollPane(contentArea);
        scroll.setFitToWidth(true);
        scroll.getStyleClass().add("scroll-pane");
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        root.setCenter(scroll);

        showHome();

        Scene scene = new Scene(root, 1280, 760);
        scene.getStylesheets().add(
                getClass().getResource("/ui/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Travel Agency Application");
        stage.show();
    }

    private HBox buildNavbar() {
        HBox nav = new HBox(20);
        nav.setAlignment(Pos.CENTER_LEFT);
        nav.getStyleClass().add("navbar");
        nav.setPadding(new Insets(12, 30, 12, 30));

        Label logo = new Label("✈ AEROVISTA TRAVELS");
        logo.getStyleClass().add("brand-logo");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox navLinks = new HBox(4);
        navLinks.setAlignment(Pos.CENTER);
        String[] tabs = {"Home", "Flights", "Hotels", "Buses", "Packages", "Visas"};
        for (String tab : tabs) {
            Button btn = new Button(tab);
            btn.getStyleClass().add("nav-link");
            btn.setOnAction(e -> switchTab(tab));
            navLinks.getChildren().add(btn);
        }

        HBox authArea = new HBox(8);
        authArea.setAlignment(Pos.CENTER);

        if (UserSession.isLoggedIn) {
            Label welcome = new Label("Hi, " + UserSession.name);
            welcome.setStyle("-fx-font-size: 13px; -fx-text-fill: #60A5FA; -fx-font-weight: bold;");
            Button logout = new Button("Logout");
            logout.getStyleClass().add("nav-link");
            logout.setOnAction(e -> { UserSession.logout(); show(); });
            authArea.getChildren().addAll(welcome, logout);
        } else {
            Button signIn = new Button("Sign In");
            signIn.getStyleClass().add("theme-button");
            signIn.setOnAction(e -> showLoginRequiredDialog(null, null));
            authArea.getChildren().add(signIn);
        }

        nav.getChildren().addAll(logo, spacer, navLinks, authArea);
        return nav;
    }

    private void switchTab(String tab) {
        switch (tab) {
            case "Home":     showHome();     break;
            case "Flights":  showFlights();  break;
            case "Hotels":   showHotels();   break;
            case "Buses":    showBuses();    break;
            case "Packages": showPackages(); break;
            case "Visas":    showVisas();    break;
        }
    }

    // ══════════════════════════════════════════
    //  BOOKING GUARD (ab serviceType bhi sath jata hai)
    // ══════════════════════════════════════════
    private void bookOrLogin(String serviceType, String service) {
        if (UserSession.isLoggedIn) {
            new BookingScreen(stage, serviceType, service).show();
        } else {
            showLoginRequiredDialog(serviceType, service);
        }
    }

    private void showLoginRequiredDialog(String serviceType, String service) {
        Stage dialog = new Stage();
        dialog.setTitle("Account Required");

        VBox root = new VBox(16);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #111827; -fx-border-color: #1E3A5F; -fx-border-width: 2;");
        root.setAlignment(Pos.CENTER);
        root.setPrefWidth(380);

        Label icon = new Label("✈");
        icon.setStyle("-fx-font-size: 40px;");

        Label title = new Label("Sign In to Book");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #E2E8F0;");

        Label msg = new Label("You must register first before you can sign in and make a booking.");
        msg.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B; -fx-text-alignment: center;");
        msg.setWrapText(true);
        msg.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        Button registerBtn = new Button("Sign Up");
        registerBtn.setStyle(
            "-fx-background-color: linear-gradient(to right,#1D4ED8,#3B82F6); -fx-text-fill: white; " +
            "-fx-font-weight: bold; -fx-font-size: 13px; " +
            "-fx-background-radius: 8; -fx-padding: 11 34 11 34; -fx-cursor: hand;");
        registerBtn.setOnAction(e -> {
            dialog.close();
            new RegistrationScreen(stage, serviceType, service).show();
        });

        Button loginBtn = new Button("Sign In");
        loginBtn.setStyle(
            "-fx-background-color: #1E293B; -fx-text-fill: #60A5FA; " +
            "-fx-font-weight: bold; -fx-font-size: 13px; " +
            "-fx-background-radius: 8; -fx-padding: 11 34 11 34; -fx-cursor: hand; " +
            "-fx-border-color: #3B82F6; -fx-border-radius: 8;");
        loginBtn.setOnAction(e -> {
            dialog.close();
            new LoginScreen(stage, serviceType, service).show();
        });

        Label hint = new Label("Already have an account? Use Sign In.");
        hint.setStyle("-fx-font-size: 11px; -fx-text-fill: #475569;");

        HBox btnRow = new HBox(12, registerBtn, loginBtn);
        btnRow.setAlignment(Pos.CENTER);

        root.getChildren().addAll(icon, title, msg, btnRow, hint);

        Scene dScene = new Scene(root);
        dScene.getStylesheets().add(
                getClass().getResource("/ui/style.css").toExternalForm());
        dialog.setScene(dScene);
        dialog.setResizable(false);
        dialog.show();
    }

    // ══════════════════════════════════════════
    //  HELPERS
    // ══════════════════════════════════════════
    private ImageView loadImage(String url, double w, double h) {
        try {
            ImageView iv = new ImageView(new Image(url, w, h, true, true));
            iv.setFitWidth(w); iv.setFitHeight(h); iv.setPreserveRatio(false);
            return iv;
        } catch (Exception ex) {
            ImageView iv = new ImageView(); iv.setFitWidth(w); iv.setFitHeight(h);
            return iv;
        }
    }

    private StackPane makeDiamondImg(String url, double size) {
        ImageView iv = loadImage(url, size, size);
        iv.setRotate(-45);
        StackPane sp = new StackPane(iv);
        sp.setPrefSize(size, size); sp.setMaxSize(size, size);
        sp.setRotate(45);
        sp.setStyle("-fx-background-color: #1E3A5F; -fx-background-radius: 20;");
        return sp;
    }

    // --- makeCard ab serviceType bhi leta hai ---
    private VBox makeCard(String imgUrl, String title, String desc, String price, String serviceType) {
        VBox card = new VBox(0);
        card.getStyleClass().add("card");
        card.setPrefWidth(280);
        card.setPadding(new Insets(0, 0, 14, 0));

        ImageView img = loadImage(imgUrl, 280, 170);

        Label t = new Label(title);
        t.getStyleClass().add("card-title");
        t.setPadding(new Insets(12, 14, 4, 14));

        Label d = new Label(desc);
        d.getStyleClass().add("card-desc");
        d.setPadding(new Insets(0, 14, 6, 14));
        d.setWrapText(true);

        Label p = new Label(price);
        p.getStyleClass().add("card-price");
        p.setPadding(new Insets(0, 14, 10, 14));

        Button bookBtn = new Button("Book Now");
        bookBtn.getStyleClass().add("card-button");
        bookBtn.setMaxWidth(Double.MAX_VALUE);
        VBox.setMargin(bookBtn, new Insets(0, 14, 0, 14));
        bookBtn.setOnAction(e -> bookOrLogin(serviceType, title + " — " + price));

        card.getChildren().addAll(img, t, d, p, bookBtn);
        return card;
    }

    private Label sectionTitle(String text) {
        Label lbl = new Label(text);
        lbl.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; " +
                     "-fx-text-fill: #E2E8F0; -fx-padding: 28 0 14 0;");
        return lbl;
    }

    private VBox pageWrapper() {
        VBox w = new VBox(16);
        w.setStyle("-fx-background-color: #0A0F1E;");
        w.setPadding(new Insets(0, 50, 40, 50));
        return w;
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Required");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // ══════════════════════════════════════════
    //  HOME
    // ══════════════════════════════════════════
    private void showHome() {
        contentArea.getChildren().clear();

        HBox hero = new HBox(60);
        hero.setAlignment(Pos.CENTER_LEFT);
        hero.setPadding(new Insets(60, 60, 60, 60));
        hero.setStyle("-fx-background-color: #0D1B3E;");
        hero.setPrefHeight(400);

        VBox heroText = new VBox(14);
        heroText.setAlignment(Pos.CENTER_LEFT);
        heroText.setMaxWidth(480);

        Label enjoy = new Label("Enjoy");
        enjoy.setStyle("-fx-font-size: 52px; -fx-font-weight: 800; -fx-text-fill: #3B82F6;");

        Label theTravel = new Label("The Travel!");
        theTravel.setStyle("-fx-font-size: 52px; -fx-font-weight: 800; -fx-text-fill: #E2E8F0;");

        Label sub = new Label("Choose your destination");
        sub.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #60A5FA;");

        Label desc = new Label(
            "Let's plan your next trip! Choose from our wide range of\n" +
            "custom flights, top rated hotels, and direct visa tracking.");
        desc.setStyle("-fx-font-size: 14px; -fx-text-fill: #64748B;");
        desc.setWrapText(true);

        heroText.getChildren().addAll(enjoy, theTravel, sub, desc);

        GridPane imgGrid = new GridPane();
        imgGrid.setHgap(10); imgGrid.setVgap(10);
        imgGrid.setAlignment(Pos.CENTER);
        imgGrid.setPadding(new Insets(30, 0, 0, 0));

        String[] heroUrls = {
            "https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=300&h=300&fit=crop",
            "https://images.unsplash.com/photo-1507525428034-b723cf961d3e?w=300&h=300&fit=crop",
            "https://images.unsplash.com/photo-1529655683826-aba9b3e77383?w=300&h=300&fit=crop",
            "https://images.unsplash.com/photo-1464822759023-fed622ff2c3b?w=300&h=300&fit=crop"
        };
        imgGrid.add(makeDiamondImg(heroUrls[0], 120), 1, 0);
        imgGrid.add(makeDiamondImg(heroUrls[1], 120), 0, 1);
        imgGrid.add(makeDiamondImg(heroUrls[2], 120), 2, 1);
        imgGrid.add(makeDiamondImg(heroUrls[3], 120), 1, 2);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        hero.getChildren().addAll(heroText, spacer, imgGrid);

        VBox wrapper = pageWrapper();
        wrapper.getChildren().add(sectionTitle("Featured Destinations"));

        HBox row1 = new HBox(20);
        row1.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1524231757912-21f4fe3a7200?w=400&h=220&fit=crop",
                "Istanbul, Turkey", "Historic wonders of the Bosphorus.", "PKR 210,000", "Package"),
            makeCard("https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=400&h=220&fit=crop",
                "Dubai, UAE", "Premium skyline experience.", "PKR 195,000", "Package"),
            makeCard("https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=400&h=220&fit=crop",
                "London, UK", "Historic city tour.", "PKR 350,000", "Package")
        );
        wrapper.getChildren().add(row1);
        contentArea.getChildren().addAll(hero, wrapper);
    }

    // ══════════════════════════════════════════
    //  FLIGHTS
    // ══════════════════════════════════════════
    private void showFlights() {
        contentArea.getChildren().clear();

        VBox searchBar = new VBox(12);
        searchBar.setStyle("-fx-background-color: #0D1B3E; -fx-padding: 24 40 24 40;");

        Label searchTitle = new Label("✈ Flight Bookings");
        searchTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #E2E8F0;");

        String fieldStyle = "-fx-background-color: #1E293B; -fx-background-radius: 8; " +
            "-fx-padding: 10; -fx-font-size: 13px; -fx-text-fill: #E2E8F0; " +
            "-fx-prompt-text-fill: #475569; -fx-border-color: #2D4A6E; -fx-border-radius: 8;";

        HBox searchRow = new HBox(12);
        searchRow.setAlignment(Pos.CENTER_LEFT);

        ComboBox<String> tripType = new ComboBox<>();
        tripType.getItems().addAll("One Way", "Return", "Multi City");
        tripType.setValue("One Way");
        tripType.setStyle("-fx-background-color: #1E293B; -fx-background-radius: 8; " +
            "-fx-font-size: 13px; -fx-pref-width: 130px;");

        ComboBox<String> travelers = new ComboBox<>();
        travelers.getItems().addAll("1 Traveler", "2 Travelers", "3 Travelers", "4+");
        travelers.setValue("1 Traveler");
        travelers.setStyle(tripType.getStyle());

        TextField fromField = new TextField();
        fromField.setPromptText("Flying From");
        fromField.setPrefWidth(160);
        fromField.setStyle(fieldStyle);

        TextField toField = new TextField();
        toField.setPromptText("Flying To");
        toField.setPrefWidth(160);
        toField.setStyle(fieldStyle);

        DatePicker depDate = new DatePicker();
        depDate.setPromptText("Departure Date");
        depDate.setPrefWidth(160);

        Button searchBtn = new Button("Search Flights");
        searchBtn.getStyleClass().add("search-btn");

        searchRow.getChildren().addAll(tripType, travelers, fromField, toField, depDate, searchBtn);
        searchBar.getChildren().addAll(searchTitle, searchRow);

        VBox wrapper = pageWrapper();
        wrapper.getChildren().add(sectionTitle("Available Flights"));

        HBox row = new HBox(20);
        row.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1436491865332-7a61a109cc05?w=400&h=220&fit=crop",
                "Karachi → Dubai", "Direct flight | 3h 15m | PIA", "PKR 55,000", "Flight"),
            makeCard("https://images.unsplash.com/photo-1483450388369-9ed95738483c?w=400&h=220&fit=crop",
                "Lahore → London", "1 Stop | 11h | Emirates", "PKR 185,000", "Flight"),
            makeCard("https://images.unsplash.com/photo-1570710891163-6d3b5c47248b?w=400&h=220&fit=crop",
                "Islamabad → Istanbul", "Direct | 6h | Turkish Airlines", "PKR 98,000", "Flight")
        );
        wrapper.getChildren().add(row);

        searchBtn.setOnAction(e -> {
            String from = fromField.getText().trim();
            String to   = toField.getText().trim();
            if (from.isEmpty() || to.isEmpty()) {
                showAlert("Please enter departure and arrival cities.");
                return;
            }
            bookOrLogin("Flight", "Flight: " + from + " → " + to
                + " | " + tripType.getValue()
                + " | " + travelers.getValue()
                + (depDate.getValue() != null ? " | " + depDate.getValue() : ""));
        });

        contentArea.getChildren().addAll(searchBar, wrapper);
    }

    // ══════════════════════════════════════════
    //  HOTELS
    // ══════════════════════════════════════════
    private void showHotels() {
        contentArea.getChildren().clear();

        String fieldStyle = "-fx-background-color: #1E293B; -fx-background-radius: 8; " +
            "-fx-padding: 10; -fx-font-size: 13px; -fx-text-fill: #E2E8F0; " +
            "-fx-prompt-text-fill: #475569; -fx-border-color: #2D4A6E; -fx-border-radius: 8;";

        VBox header = new VBox(8);
        header.setStyle("-fx-background-color: #0D1B3E; -fx-padding: 24 40 24 40;");
        Label ht = new Label("🏨 Hotels");
        ht.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #E2E8F0;");
        HBox searchRow = new HBox(12);
        searchRow.setAlignment(Pos.CENTER_LEFT);
        TextField cityField = new TextField();
        cityField.setPromptText("City / Destination");
        cityField.setPrefWidth(220);
        cityField.setStyle(fieldStyle);
        Button searchBtn = new Button("Search Hotels");
        searchBtn.getStyleClass().add("search-btn");
        searchRow.getChildren().addAll(cityField, searchBtn);
        header.getChildren().addAll(ht, searchRow);

        VBox wrapper = pageWrapper();
        wrapper.getChildren().add(sectionTitle("Top Rated Hotels"));

        HBox row = new HBox(20);
        row.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1542314831-068cd1dbfeeb?w=400&h=220&fit=crop",
                "Burj Al Arab, Dubai", "7-star luxury | Sea view suite", "PKR 280,000 / night", "Hotel"),
            makeCard("https://images.unsplash.com/photo-1571003123894-1f0594d2b5d9?w=400&h=220&fit=crop",
                "Serena Hotel, Islamabad", "5-star | City center", "PKR 35,000 / night", "Hotel"),
            makeCard("https://images.unsplash.com/photo-1566073771259-6a8506099945?w=400&h=220&fit=crop",
                "Pearl Continental, Lahore", "5-star | Premium rooms", "PKR 28,000 / night", "Hotel")
        );
        wrapper.getChildren().add(row);

        searchBtn.setOnAction(e -> {
            String city = cityField.getText().trim();
            if (city.isEmpty()) { showAlert("Please enter a city."); return; }
            bookOrLogin("Hotel", "Hotel in " + city);
        });

        contentArea.getChildren().addAll(header, wrapper);
    }

    // ══════════════════════════════════════════
    //  BUSES
    // ══════════════════════════════════════════
    private void showBuses() {
        contentArea.getChildren().clear();

        String fieldStyle = "-fx-background-color: #1E293B; -fx-background-radius: 8; " +
            "-fx-padding: 10; -fx-font-size: 13px; -fx-text-fill: #E2E8F0; " +
            "-fx-prompt-text-fill: #475569; -fx-border-color: #2D4A6E; -fx-border-radius: 8;";

        VBox header = new VBox(8);
        header.setStyle("-fx-background-color: #0D1B3E; -fx-padding: 24 40 24 40;");
        Label bt = new Label("🚌 Bus Services");
        bt.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #E2E8F0;");
        HBox searchRow = new HBox(12);
        searchRow.setAlignment(Pos.CENTER_LEFT);
        TextField fromBus = new TextField(); fromBus.setPromptText("From"); fromBus.setPrefWidth(160); fromBus.setStyle(fieldStyle);
        TextField toBus   = new TextField(); toBus.setPromptText("To");   toBus.setPrefWidth(160);   toBus.setStyle(fieldStyle);
        Button searchBtn = new Button("Search Buses");
        searchBtn.getStyleClass().add("search-btn");
        searchRow.getChildren().addAll(fromBus, toBus, searchBtn);
        header.getChildren().addAll(bt, searchRow);

        VBox wrapper = pageWrapper();
        wrapper.getChildren().add(sectionTitle("Available Bus Routes"));

        HBox row = new HBox(20);
        row.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1570125909232-eb263c188f7e?w=400&h=220&fit=crop",
                "Karachi → Lahore", "Daewoo Express | AC | 14h", "PKR 3,500", "Bus"),
            makeCard("https://images.unsplash.com/photo-1544620347-c4fd4a3d5957?w=400&h=220&fit=crop",
                "Lahore → Islamabad", "Faisal Movers | AC | 4h", "PKR 1,200", "Bus"),
            makeCard("https://images.unsplash.com/photo-1464219789935-c2d9d9aba644?w=400&h=220&fit=crop",
                "Islamabad → Peshawar", "Skyways | AC | 2h", "PKR 900", "Bus")
        );
        wrapper.getChildren().add(row);

        searchBtn.setOnAction(e -> {
            String from = fromBus.getText().trim(); String to = toBus.getText().trim();
            if (from.isEmpty() || to.isEmpty()) { showAlert("Please enter both cities."); return; }
            bookOrLogin("Bus", "Bus: " + from + " → " + to);
        });

        contentArea.getChildren().addAll(header, wrapper);
    }

    // ══════════════════════════════════════════
    //  PACKAGES
    // ══════════════════════════════════════════
    private void showPackages() {
        contentArea.getChildren().clear();

        VBox header = new VBox(8);
        header.setStyle("-fx-background-color: #0D1B3E; -fx-padding: 24 40 24 40;");
        Label pt = new Label("🌍 Travel Packages");
        pt.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #E2E8F0;");
        Label ps = new Label("All-inclusive holiday packages tailored for you");
        ps.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B;");
        header.getChildren().addAll(pt, ps);

        VBox wrapper = pageWrapper();
        wrapper.getChildren().add(sectionTitle("Holiday Packages"));

        HBox row1 = new HBox(20);
        row1.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1524231757912-21f4fe3a7200?w=400&h=220&fit=crop",
                "Istanbul 7 Nights", "Flight + Hotel + Tours included", "PKR 210,000 / person", "Package"),
            makeCard("https://images.unsplash.com/photo-1512453979798-5ea266f8880c?w=400&h=220&fit=crop",
                "Dubai 5 Nights", "Flight + 5-star Hotel + Desert Safari", "PKR 195,000 / person", "Package"),
            makeCard("https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=400&h=220&fit=crop",
                "Maldives 6 Nights", "Water villa + All meals + Snorkeling", "PKR 450,000 / person", "Package")
        );

        HBox row2 = new HBox(20);
        row2.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1508009603885-50cf7c579365?w=400&h=220&fit=crop",
                "Bangkok 5 Nights", "Flight + Hotel + City Tour", "PKR 120,000 / person", "Package"),
            makeCard("https://images.unsplash.com/photo-1503899036084-c55cdd92da26?w=400&h=220&fit=crop",
                "Tokyo 8 Nights", "Flight + Hotel + JR Pass", "PKR 380,000 / person", "Package"),
            makeCard("https://images.unsplash.com/photo-1587595431973-160d0d94add1?w=400&h=220&fit=crop",
                "Northern Areas Pakistan", "3 Nights Hunza + Skardu Tour", "PKR 45,000 / person", "Package")
        );

        wrapper.getChildren().addAll(row1, row2);
        contentArea.getChildren().addAll(header, wrapper);
    }

    // ══════════════════════════════════════════
    //  VISAS
    // ══════════════════════════════════════════
    private void showVisas() {
        contentArea.getChildren().clear();

        VBox header = new VBox(8);
        header.setStyle("-fx-background-color: #0D1B3E; -fx-padding: 24 40 24 40;");
        Label vt = new Label("📋 Visa Services");
        vt.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #E2E8F0;");
        Label vs = new Label("Fast and reliable visa processing assistance");
        vs.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B;");
        header.getChildren().addAll(vt, vs);

        VBox wrapper = pageWrapper();
        wrapper.getChildren().add(sectionTitle("Visa Assistance"));

        HBox row1 = new HBox(20);
        row1.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1526778548025-fa2f459cd5c1?w=400&h=220&fit=crop",
                "UAE Tourist Visa", "30-day single entry | Processing: 3-5 days", "PKR 18,000", "Visa"),
            makeCard("https://images.unsplash.com/photo-1513635269975-59663e0ac1ad?w=400&h=220&fit=crop",
                "UK Visit Visa", "6-month multiple entry | Processing: 15 days", "PKR 45,000", "Visa"),
            makeCard("https://images.unsplash.com/photo-1502602898657-3e91760cbb34?w=400&h=220&fit=crop",
                "Schengen Visa", "90 days | Europe | Processing: 10-15 days", "PKR 35,000", "Visa")
        );

        HBox row2 = new HBox(20);
        row2.getChildren().addAll(
            makeCard("https://images.unsplash.com/photo-1508009603885-50cf7c579365?w=400&h=220&fit=crop",
                "Thailand Visa on Arrival", "15 days | Processing: Same day", "PKR 8,000", "Visa"),
            makeCard("https://images.unsplash.com/photo-1506905925346-21bda4d32df4?w=400&h=220&fit=crop",
                "Malaysia e-Visa", "30 days | Online processing: 2-3 days", "PKR 6,500", "Visa"),
            makeCard("https://images.unsplash.com/photo-1524231757912-21f4fe3a7200?w=400&h=220&fit=crop",
                "Turkey e-Visa", "30 days | Online: Instant approval", "PKR 7,500", "Visa")
        );

        wrapper.getChildren().addAll(row1, row2);
        contentArea.getChildren().addAll(header, wrapper);
    }
}