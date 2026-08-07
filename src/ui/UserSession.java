package ui;

public class UserSession {
    public static String userID;
    public static String name;
    public static String email;
    public static String phone;
    public static boolean isLoggedIn = false;

    public static void login(String id, String fullName, String userEmail, String userPhone) {
        userID = id;
        name = fullName;
        email = userEmail;
        phone = userPhone;
        isLoggedIn = true;
    }

    public static void logout() {
        userID = null;
        name = null;
        email = null;
        phone = null;
        isLoggedIn = false;
    }
}