import java.util.*;


public class Taxi {
    // Static variable to keep track of the number of taxis created
    static int taxicount = 0; // Taxi id
    
    // Instance variables representing the properties of a Taxi
    int id;             // Taxi id
    boolean booked;     // Taxi booked or not
    char currentSpot;   // Taxi current spot 
    int freeTime;       // When Taxi becomes free 
    int totalEarnings;  // Total earnings of taxi
    List<String> trips; // All details of all trips by this taxi
    
    // Constructor to initialize a new Taxi object
    public Taxi() {
        booked = false;         // Default: Taxi not booked
        currentSpot = 'A';      // Default: Taxi starts at spot 'A'
        freeTime = 6;           // Default: Taxi becomes free at 6 AM
        trips = new ArrayList<String>(); // Initialize an empty list to store trip details
        taxicount += 1;         // Increment the count of taxis created
        id = taxicount;         // Assign the current taxi count as the ID of this taxi
    }
    
    // Method to set details of a Taxi with provided values
    public void setDetails(boolean booked, char currentSpot, int freeTime, int totalEarnings, String tripDetail) {
        this.booked = booked;               // Update booked status
        this.currentSpot = currentSpot;     // Update current spot
        this.freeTime = freeTime;           // Update free time
        this.totalEarnings = totalEarnings; // Update total earnings
        this.trips.add(tripDetail);         // Add the new trip detail to the list of trips
    }
    
    // Method to print all details of a Taxi
    public void printDetails() {
        System.out.println("Taxi - " + this.id + " | Total Earnings - " + this.totalEarnings);
        System.out.println("TaxiId  | Booking ID | Customer ID | From | TO | Pickuptime | Droptime | Amount");
        for (String trip : trips) {
            System.out.println(id + "       " + trip);
        }
        System.out.println("______________________________________________________________________________");
    }
    
    // Method to print specific details of a Taxi
    public void printTaxiDetails() {
        System.out.println("Taxi - " + this.id + " | Total Earnings - " + this.totalEarnings + " | Current spot - " + this.currentSpot + " | Free Time - " + this.freeTime);
    }
}
