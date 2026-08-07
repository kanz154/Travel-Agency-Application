package DatabaseLayer;

import Core_Services.Registration;
import Core_Services.TravelingPackage;
import java.sql.*;

public class CRUD {

    // --- MAIN BOOKING ---
    // --- MAIN BOOKING (ab travelDate bhi sahi save hogi, NOW() ki jagah) ---
public static boolean insertBooking(String userID, int serviceRefId, String bookingDetails, double totalAmount, String travelDate) {
    String sql = "INSERT INTO booking (userId, packageId, bookingDate, totalAmount, status, details) " +
                 "VALUES (?, ?, ?, ?, 'Confirmed', ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, userID != null ? userID : "0");
        pstmt.setInt(2, serviceRefId);
        pstmt.setString(3, travelDate);
        pstmt.setDouble(4, totalAmount);
        pstmt.setString(5, bookingDetails);
        return pstmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("insertBooking error: " + e.getMessage());
        return false;
    }
}

    // --- FLIGHT ---
    public static int insertFlight(String origin, String destination, int price, String departureTime) {
        String sql = "INSERT INTO flight (origin, destination, price, departureTime) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, origin);
            pstmt.setString(2, destination);
            pstmt.setInt(3, price);
            pstmt.setString(4, departureTime);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("insertFlight error: " + e.getMessage());
        }
        return -1;
    }

    // --- BUS ---
    public static int insertBus(String origin, String destination, int price, String departureTime) {
        String sql = "INSERT INTO bus (origin, destination, price, departureTime) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, origin);
            pstmt.setString(2, destination);
            pstmt.setInt(3, price);
            pstmt.setString(4, departureTime);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("insertBus error: " + e.getMessage());
        }
        return -1;
    }

    // --- HOTEL ---
    public static int insertHotel(String hotelName, String location, int pricePerNight, String details) {
        String sql = "INSERT INTO hotel (hotelName, location, pricePerNight, details) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, hotelName);
            pstmt.setString(2, location);
            pstmt.setInt(3, pricePerNight);
            pstmt.setString(4, details);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("insertHotel error: " + e.getMessage());
        }
        return -1;
    }

    // --- VISA ---
    public static int insertVisa(String userId, String country, String visaType, String details) {
        String sql = "INSERT INTO visa (userId, country, visaType, status, details) VALUES (?, ?, ?, 'Confirmed', ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, country);
            pstmt.setString(3, visaType);
            pstmt.setString(4, details);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("insertVisa error: " + e.getMessage());
        }
        return -1;
    }

    // --- PACKAGE: existing ID dhoondhna ---
    public static int findPackageId(String packageName) {
        String sql = "SELECT packageId FROM package WHERE packageName = ? LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, packageName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) return rs.getInt("packageId");
            }
        } catch (SQLException e) {
            System.err.println("findPackageId error: " + e.getMessage());
        }
        return -1;
    }

    // --- REGISTER USER ---
    public boolean registerUser(Registration user) {
        String sql = "INSERT INTO registration (fullName, email, phoneNumber, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFullName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getPassword());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    // --- LOGIN USER ---
    public Registration loginUser(String email, String password) {
        String sql = "SELECT * FROM registration WHERE email = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Registration user = new Registration();
                    user.setId(rs.getInt("userid"));
                    user.setFullName(rs.getString("fullName"));
                    user.setEmail(rs.getString("email"));
                    user.setPhoneNumber(rs.getString("phoneNumber"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
        }
        return null;
    }

    // --- SEARCH PACKAGES (plain array, ArrayList nahi) ---
    public TravelingPackage[] searchPackages(String destination) {
        String countSql = "SELECT COUNT(*) FROM package WHERE destination LIKE ?";
        String dataSql  = "SELECT * FROM package WHERE destination LIKE ?";
        try (Connection conn = DBConnection.getConnection()) {

            int count = 0;
            try (PreparedStatement cstmt = conn.prepareStatement(countSql)) {
                cstmt.setString(1, "%" + destination + "%");
                try (ResultSet rs = cstmt.executeQuery()) {
                    if (rs.next()) count = rs.getInt(1);
                }
            }

            TravelingPackage[] result = new TravelingPackage[count];

            try (PreparedStatement pstmt = conn.prepareStatement(dataSql)) {
                pstmt.setString(1, "%" + destination + "%");
                try (ResultSet rs = pstmt.executeQuery()) {
                    int i = 0;
                    while (rs.next()) {
                        TravelingPackage p = new TravelingPackage();
                        p.setId(rs.getInt("packageId"));
                        p.setPackageName(rs.getString("packageName"));
                        p.setDestination(rs.getString("destination"));
                        p.setPrice(rs.getDouble("price"));
                        result[i] = p;
                        i++;
                    }
                }
            }
            return result;

        } catch (SQLException e) {
            System.err.println("Search error: " + e.getMessage());
            return new TravelingPackage[0];
        }
    }

    // --- GET ALL PACKAGES (plain array, ArrayList nahi) ---
    public TravelingPackage[] getAllPackages() {
        String countSql = "SELECT COUNT(*) FROM package";
        String dataSql  = "SELECT * FROM package";
        try (Connection conn = DBConnection.getConnection()) {

            int count = 0;
            try (Statement cstmt = conn.createStatement();
                 ResultSet crs = cstmt.executeQuery(countSql)) {
                if (crs.next()) count = crs.getInt(1);
            }

            TravelingPackage[] result = new TravelingPackage[count];

            try (Statement pstmt = conn.createStatement();
                 ResultSet rs = pstmt.executeQuery(dataSql)) {
                int i = 0;
                while (rs.next()) {
                    TravelingPackage p = new TravelingPackage();
                    p.setId(rs.getInt("packageId"));
                    p.setPackageName(rs.getString("packageName"));
                    p.setDestination(rs.getString("destination"));
                    p.setPrice(rs.getDouble("price"));
                    result[i] = p;
                    i++;
                }
            }
            return result;

        } catch (SQLException e) {
            System.err.println("Get all packages error: " + e.getMessage());
            return new TravelingPackage[0];
        }
    }

    // --- UPDATE BOOKING STATUS ---
    public boolean updateBookingStatus(int bookingId, String newStatus) {
        String sql = "UPDATE booking SET status = ? WHERE bookingId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Update booking status error: " + e.getMessage());
            return false;
        }
    }
}