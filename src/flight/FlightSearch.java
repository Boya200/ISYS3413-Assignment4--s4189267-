package flight; // Package declaration, tells Java this class belongs to the 'flight' package

import java.time.LocalDate; // For handling dates
import java.time.format.DateTimeParseException; // For catching invalid date formats

public class FlightSearch {

    // ------------------ Class Attributes ------------------
    private String departureDate; // Stores departure date as string
    private String departureAirportCode; // Stores 3-letter departure airport code
    private boolean emergencyRowSeating; // Stores if emergency row is requested
    private String returnDate; // Stores return date as string
    private String destinationAirportCode; // Stores 3-letter destination airport code
    private String seatingClass; // Stores seating class: economy, business, or first
    private int adultPassengerCount; // Number of adult passengers
    private int childPassengerCount; // Number of child passengers (2-11 years old)
    private int infantPassengerCount; // Number of infant passengers (<2 years old)

    // ------------------ Main Function ------------------
    // This function validates flight search inputs based on given conditions
    public boolean runFlightSearch(String departureDate, String departureAirportCode, boolean emergencyRowSeating,
            String returnDate, String destinationAirportCode, String seatingClass,
            int adultPassengerCount, int childPassengerCount, int infantPassengerCount) {

        boolean valid = true; // Flag to track if all conditions are valid

        // ----- Condition 1: Total passengers 1-9 -----
        int totalPassengers = adultPassengerCount + childPassengerCount + infantPassengerCount;
        if (totalPassengers < 1 || totalPassengers > 9) {
            valid = false; // Invalid if no passengers or more than 9
        }

        // ----- Condition 8: Passenger counts must be non-negative -----
        if (adultPassengerCount < 0 || childPassengerCount < 0 || infantPassengerCount < 0) {
            valid = false; // Invalid if any count is negative
        }

        // ----- Condition 6: Airport codes must be valid (3 letters, different) -----
        if (departureAirportCode == null || destinationAirportCode == null ||
                departureAirportCode.length() != 3 || destinationAirportCode.length() != 3 ||
                departureAirportCode.equals(destinationAirportCode)) {
            valid = false; // Invalid if codes null, wrong length, or same
        }

        // ----- Condition 7: Dates must be valid and returnDate after departureDate
        // -----
        try {
            LocalDate depDate = LocalDate.parse(departureDate); // Parse departure date
            LocalDate retDate = LocalDate.parse(returnDate); // Parse return date
            if (!retDate.isAfter(depDate)) { // Return must be after departure
                valid = false;
            }
        } catch (DateTimeParseException e) {
            valid = false; // Invalid format triggers false
        }

        // ----- Condition 9: Seating class must be valid -----
        if (!("economy".equals(seatingClass) || "business".equals(seatingClass) || "first".equals(seatingClass))) {
            valid = false; // Invalid if seating class is not one of the three
        }

        // ----- Condition 10: Only economy can have emergency row -----
        if (emergencyRowSeating && !"economy".equals(seatingClass)) {
            valid = false; // Emergency row requested in non-economy
        }

        // ----- Condition 2: Children cannot be in emergency row or first class -----
        if (childPassengerCount > 0 && (emergencyRowSeating || "first".equals(seatingClass))) {
            valid = false; // Invalid seating for children
        }

        // ----- Condition 3: Infants cannot be in emergency row or business class -----
        if (infantPassengerCount > 0 && (emergencyRowSeating || "business".equals(seatingClass))) {
            valid = false; // Invalid seating for infants
        }

        // ----- Condition 4: Max 2 children per adult -----
        if (childPassengerCount > adultPassengerCount * 2) {
            valid = false; // Too many children per adult
        }

        // ----- Condition 5: Each infant must be accompanied by one adult -----
        if (infantPassengerCount > adultPassengerCount) {
            valid = false; // More infants than adults is invalid
        }

        // ----- Initialize attributes if all validations pass -----
        if (valid) {
            this.departureDate = departureDate;
            this.departureAirportCode = departureAirportCode;
            this.emergencyRowSeating = emergencyRowSeating;
            this.returnDate = returnDate;
            this.destinationAirportCode = destinationAirportCode;
            this.seatingClass = seatingClass;
            this.adultPassengerCount = adultPassengerCount;
            this.childPassengerCount = childPassengerCount;
            this.infantPassengerCount = infantPassengerCount;
        }

        return valid; // Return true if all validations pass, false otherwise
    }

    // ------------------ Getters for Testing ------------------
    public String getDepartureDate() {
        return departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public boolean isEmergencyRowSeating() {
        return emergencyRowSeating;
    }

    public String getSeatingClass() {
        return seatingClass;
    }

    public int getAdultPassengerCount() {
        return adultPassengerCount;
    }

    public int getChildPassengerCount() {
        return childPassengerCount;
    }

    public int getInfantPassengerCount() {
        return infantPassengerCount;
    }
}
