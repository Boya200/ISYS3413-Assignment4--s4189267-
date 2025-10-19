package flight; // Package declaration; tells Java this class belongs to 'flight' folder

import org.junit.jupiter.api.Test; // Import the JUnit 5 @Test annotation
import static org.junit.jupiter.api.Assertions.*; // Import JUnit assertions like assertTrue, assertFalse, assertEquals

public class FlightSearchTest { // Test class for FlightSearch

    // ---------- Test 1: All valid ----------
    @Test // Marks this method as a test case
    public void testValidCase() {
        FlightSearch fs = new FlightSearch(); // Create a new instance of FlightSearch
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false, // departure date, departure airport, emergency row
                "2025-10-25", "del", "economy", // return date, destination airport, seating class
                2, 2, 1 // adult count, child count, infant count
        );
        assertTrue(result); // Expect the function to return true because inputs are valid
        assertEquals("maa", fs.getDepartureAirportCode()); // Check if departure airport is set correctly
        assertEquals("del", fs.getDestinationAirportCode()); // Check if destination airport is set correctly
        assertEquals(2, fs.getAdultPassengerCount()); // Check if adult count is set correctly
        assertEquals(2, fs.getChildPassengerCount()); // Check if child count is set correctly
        assertEquals(1, fs.getInfantPassengerCount()); // Check if infant count is set correctly
    }

    // ---------- Test 2: Total passengers > 9 ----------
    @Test
    public void testTooManyPassengers() {
        FlightSearch fs = new FlightSearch(); // Create FlightSearch instance
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false,
                "2025-10-25", "del", "economy",
                5, 4, 1 // 5+4+1=10 passengers, which exceeds max 9
        );
        assertFalse(result); // Expect false because total passengers > 9
    }

    // ---------- Test 3: No passengers ----------
    @Test
    public void testNoPassengers() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false,
                "2025-10-25", "del", "economy",
                0, 0, 0 // No passengers
        );
        assertFalse(result); // Should return false because at least 1 passenger is required
    }

    // ---------- Test 4: Children in first class ----------
    @Test
    public void testChildrenInFirstClass() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false,
                "2025-10-25", "del", "first",
                2, 1, 0 // Child in first class is invalid
        );
        assertFalse(result); // Expect false
    }

    // ---------- Test 5: Infants in business class ----------
    @Test
    public void testInfantsInBusiness() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false,
                "2025-10-25", "del", "business",
                1, 0, 1 // Infant in business class is invalid
        );
        assertFalse(result); // Expect false
    }

    // ---------- Test 6: Too many children per adult ----------
    @Test
    public void testTooManyChildrenPerAdult() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false,
                "2025-10-25", "del", "economy",
                1, 3, 0 // 1 adult cannot sit with 3 children (>2 children per adult)
        );
        assertFalse(result); // Expect false
    }

    // ---------- Test 7: More infants than adults ----------
    @Test
    public void testMoreInfantsThanAdults() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false,
                "2025-10-25", "del", "economy",
                1, 0, 2 // 2 infants with only 1 adult is invalid
        );
        assertFalse(result); // Expect false
    }

    // ---------- Test 8: Emergency row in non-economy ----------
    @Test
    public void testEmergencyRowInBusiness() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", true, // Emergency row
                "2025-10-25", "del", "business", // Business class cannot have emergency row
                2, 0, 0);
        assertFalse(result); // Expect false
    }

    // ---------- Test 9: Children in emergency row ----------
    @Test
    public void testChildrenInEmergencyRow() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", true, // Emergency row
                "2025-10-25", "del", "economy",
                2, 1, 0 // Child in emergency row is invalid
        );
        assertFalse(result); // Expect false
    }

    // ---------- Test 10: Infants in emergency row ----------
    @Test
    public void testInfantsInEmergencyRow() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", true, // Emergency row
                "2025-10-25", "del", "economy",
                2, 0, 1 // Infant in emergency row is invalid
        );
        assertFalse(result); // Expect false
    }

    // ---------- Test 11: Invalid date (return before departure) ----------
    @Test
    public void testInvalidDates() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-25", "maa", false, // Departure after return
                "2025-10-20", "del", "economy",
                2, 0, 0);
        assertFalse(result); // Expect false
    }

    // ---------- Test 12: Invalid airport codes (same or wrong length) ----------
    @Test
    public void testInvalidAirportCodes() {
        FlightSearch fs = new FlightSearch();
        boolean result = fs.runFlightSearch(
                "2025-10-20", "maa", false,
                "2025-10-25", "maa", "economy", // Same departure and destination
                2, 0, 0);
        assertFalse(result); // Expect false
    }
}
