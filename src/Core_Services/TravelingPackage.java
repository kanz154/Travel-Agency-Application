package Core_Services;

public class TravelingPackage {
    private int id;
    private String packageName;
    private String destination;
    private int durationDays;
    private double price;

    public TravelingPackage() {
    }
    public int getId() { 
        return id; 
    } 
    public void setId(int id) { 
        this.id = id; 
    }
    public String getPackageName() { 
        return packageName; 
    } public void setPackageName(String packageName) { 
        this.packageName = packageName; 
    }
    public String getDestination() { 
        return destination; 
    } 
    public void setDestination(String destination) { 
        this.destination = destination; 
    }
    public int getDurationDays() { 
        return durationDays; 
    } public void setDurationDays(int durationDays) { 
        this.durationDays = durationDays; 
    }
    public double getPrice() { 
        return price; 
    } 
    public void setPrice(double price) { 
        this.price = price; 
    }

    public String getDuration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

