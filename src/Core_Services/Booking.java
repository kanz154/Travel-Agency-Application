package Core_Services;
import java.time.LocalDateTime;

public class Booking {
    private int id;
    private int userId;
    private String serviceType;
    private int serviceId;
    private LocalDateTime bookingDate;
    private double totalAmount;
    private String status;

    public Booking() {
    }

    public Booking(String userID, String bookingDetails) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    public int getId() { 
        return id; 
    } 
    public void setId(int id) { 
        this.id = id; 
    }
    public int getUserId() { 
        return userId; 
    } 
    public void setUserId(int userId) { 
        this.userId = userId; 
    }
    public String getServiceType() { 
        return serviceType; 
    } public void setServiceType(String serviceType) { 
        this.serviceType = serviceType; 
    }
    public int getServiceId() { 
        return serviceId; 
    } 
    public void setServiceId(int serviceId) { 
        this.serviceId = serviceId; 
    }
    public double getTotalAmount() { 
        return totalAmount; 
    } 
    public void setTotalAmount(double totalAmount) { 
        this.totalAmount = totalAmount; 
    }
    public String getStatus() { 
        return status; 
    } 
    public void setStatus(String status) { 
        this.status = status; 
    }

    public String getBookingDate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDetails() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBookingId(String valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setBookingDate(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDetails(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static class DAO {

        public DAO() {
        }
    }
}
