# ISYS3413 – Assignment 4 
## FlightSearch Implementation and JUnit Testing

### Project Overview
This repository contains the implementation and testing of the `runFlightSearch` method in the `FlightSearch` class for the **WorldWanderer** website.  
The function validates flight search criteria based on 11 specific conditions. If all conditions are met, it initialises the class attributes and returns `true`; otherwise, it returns `false`.

Unit tests are written in **JUnit 5** and cover each condition using boundary values, plus one valid scenario with multiple test data combinations.

---



---

### Validation Conditions Implemented
1. Passenger count between 1 and 9.  
2. Children cannot be in emergency row or first class.  
3. Infants cannot be in emergency row or business class.  
4. Maximum 2 children per adult.  
5. One infant per adult lap.  
6. Departure date cannot be in the past.  
7. Strict DD/MM/YYYY date validation with leap year handling.  
8. Return date must not be before departure date.  
9. Valid seating classes: `economy`, `premium economy`, `business`, `first`.  
10. Only `economy` allows emergency row seating.  
11. Valid airport codes: `syd`, `mel`, `lax`, `cdg`, `del`, `pvg`, `doh` and departure ≠ destination.

---

### Test Cases
- **Total test cases:** 12  
  - One per condition + one valid scenario  
- **Total test data:** 26+ (two boundary data per case, four for valid case)  
- Tests check both **return values** and **attribute updates**.

---

### Requirements
- Java 17 or compatible version  
- JUnit 5 (included in `lib` folder)  
- IDE: Eclipse / IntelliJ / VS Code  
- GitHub Private Repository

---

### How to Run in Eclipse
1. Open Eclipse.  
2. `File → Import → Existing Projects into Workspace`.  
3. Select this project folder.  
4. Add `junit-platform-console-standalone-1.13.0-M3.jar` (from `lib`) to **Build Path**.  
5. Right-click `FlightSearchTest.java` → **Run As → JUnit Test**.

---

### How to Run from Command Line
```bash
javac -cp lib/junit-platform-console-standalone-1.13.0-M3.jar;src -d bin src/flight/*.java
java -jar lib/junit-platform-console-standalone-1.13.0-M3.jar \
  -cp bin --scan-class-path
