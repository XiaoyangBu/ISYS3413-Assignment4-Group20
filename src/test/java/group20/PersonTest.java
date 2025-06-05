package group20;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PersonTest {
    // Test Cases for addPerson() function
    @Test
    void testAddPerson_ValidInput() {
        // Test Case 1: Check the function with valid inputs
        Person p = new Person();
        boolean result = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");
        assertTrue(result);
    }

    @Test
    void testAddPerson_InvalidPersonID() {
        // Test Case 2: Check the function with invalid personID

        // Test Data 1
        Person p1 = new Person();
        boolean r1 = p1.addPerson("Alice", "Doe", "dk*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");

        // Test Data 2
        Person p2 = new Person();
        boolean r2 = p2.addPerson("Bob", "Smith", "*dFt_yKB", "7|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");

        assertFalse(r1);
        assertFalse(r2);
    }

    @Test
    void testAddPerson_InvalidAddressFormat() {
        // Test Case 3: Check the function with invalid address format

        // Test Data 1
        Person p1 = new Person();
        boolean r1 = p1.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Melbourne|Victoria|Australia", "28-05-1993");

        // Test Data 2
        Person p2 = new Person();
        boolean r2 = p2.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|3432|Melbourne|Victoria|Australia", "28-05-1993");

        assertFalse(r1);
        assertFalse(r2);
    }

    @Test
    void testAddPerson_WrongState() {
        // Test Case 4: Check the function with state other than Victoria

        Person p = new Person();
        boolean result = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Queensland|Australia", "28-05-1993");
        assertFalse(result);
    }

    @Test
    void testAddPerson_InvalidBirthdateFormat() {
        // Test Case 5: Check the function with invalid format of birthdate

        // Test Data 1
        Person p1 = new Person();
        boolean r1 = p1.addPerson("Bob", "Smith", "32@jFl_yKB", "7|Highland Street|Melbourne|Victoria|Australia", "28/05/1993");

        // Test Data 2
        Person p2 = new Person();
        boolean r2 = p2.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "1993-07-12");

        assertFalse(r1);
        assertFalse(r2);
    }

    @Test
    void testAddPerson_IllogicalBirthYear() {
        // Test Case 6: Check the function with illogical birth year. (Too young or too old)

        // Test Data 1: (Too old)
        Person p1 = new Person();
        boolean r1 = p1.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1800");

        // Test Data 2: (Too young)
        Person p2 = new Person();
        boolean r2 = p2.addPerson("Bob", "Smith", "32@jFl_yKB", "7|Highland Street|Melbourne|Victoria|Australia", "12/03-2024");

        assertFalse(r1);
        assertFalse(r2);
    }

    /* 
    *   Test Cases for updatePersonalDetails() function 
    */
   
    @Test
    void testValidUpdateForAdult() {
        // Test Case 1: valid update for an adult
        Person p = new Person();
        p.addPerson("John", "Doe", "35*dFt_yKB", "10|High Street|Melbourne|Victoria|Australia", "15-05-1990");
        
        boolean result = p.updatePersonalDetails(
            "37*dFt_yKB",  // new ID
            "John",        // same first name
            "Smith",       // new last name
            "20|Park Road|Melbourne|Victoria|Australia", // new address
            "15-05-1990"   // same birthdate
        );
        
        assertTrue(result);
    }

    @Test
    void testInvalidUpdateForMinorAddressChange() {
        // Test Case 2: invalid update for a minor trying to change address
        Person p = new Person();
        p.addPerson("Jane", "Smith", "35*dFt_yKB", "10|High Street|Melbourne|Victoria|Australia", "15-05-2010");
        
        boolean result = p.updatePersonalDetails(
            "35*dFt_yKB",  // same ID
            "Jane",        // same first name
            "Smith",       // same last name
            "20|Park Road|Melbourne|Victoria|Australia", // trying to change address
            "15-05-2010"   // same birthdate
        );
        
        assertFalse(result);
    }

    @Test
    void testInvalidChangingBirthdayAndOtherDetails() {
        // Test Case 3: invalid update - changing birthday and other details
        Person p = new Person();
        p.addPerson("Bob", "Brown", "35*dFt_yKB", "10|High Street|Melbourne|Victoria|Australia", "15-05-1990");
        
        boolean result = p.updatePersonalDetails(
            "37*dFt_yKB",  // trying to change ID
            "Bobby",       // trying to change first name
            "Brown",       // same last name
            "10|High Street|Melbourne|Victoria|Australia", // same address
            "16-05-1990"   // trying to change birthdate
        );
        
        assertFalse(result);
    }

    @Test
    void testInvalidUpdateEvenDigitIdChange() {
        // Test Case 4: invalid update - trying to change personID with even digit
        Person p = new Person();
        p.addPerson("Alice", "Wilson", "24*dFt_yKB", "10|High Street|Melbourne|Victoria|Australia", "15-05-1990");
        
        boolean result = p.updatePersonalDetails(
            "35*dFt_yKB",  // trying to change ID when first digit is even
            "Alice",       // same first name
            "Wilson",      // same last name
            "10|High Street|Melbourne|Victoria|Australia", // same address
            "15-05-1990"   // same birthdate
        );
        
        assertFalse(result);
    }

    @Test
    void testValidUpdateBirthday() {
        // Test Case 5: valid update - changing birthday for an adult
        Person p = new Person();
        p.addPerson("Tom", "Jones", "35*dFt_yKB", "10|High Street|Melbourne|Victoria|Australia", "15-05-1990");
        
        boolean result = p.updatePersonalDetails(
            "35*dFt_yKB",  // same ID
            "Tom",         // same first name
            "Jones",       // same last name
            "10|High Street|Melbourne|Victoria|Australia", // same address
            "16-05-1990"   // changing only birthdate
        );
        
        assertTrue(result);
    }

    // Test Cases for addDemeritPoints() function
    @Test
    void testAddDemeritPoints_AgeUnder21_Over6Points() {
        // Test Case 1: Check the function with valid input, age < 21, totalValidDemeritPoints > 6
        
        // Create and add a valid person
        Person p = new Person();
        boolean added = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");
        assertTrue(added);

        // Set age under 21
        p.setBirthDate("15-11-2007");

        // Set offense history to 2 points in the last 2 years before the current offense
        Map<LocalDate, Integer> history = new HashMap<>();
        history.put(LocalDate.of(2024, 11, 15), 2);  // Add a valid demeritPoints
        p.setOffenseHistory(history);

        // Add new offense with 5 points
        String result = p.addDemeritPoints("15-11-2025", 5);

        assertEquals("Success", result);
        assertTrue(p.getIsSuspended());
        assertEquals(7, p.calculateTotalValidDemeritPoints(LocalDate.of(2025, 11, 15)));
    }

    @Test
    void testAddDemeritPoints_Age21OrOver_Over12Points() {
        // Test Case 2: Check the function with valid input, age >= 21, totalValidDemeritPoints > 12

        // Create and add a valid person
        Person p = new Person();
        boolean added = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");
        assertTrue(added);

        // Set age over 21
        p.setBirthDate("15-11-1998");

        // Set offense history to 14 points in the last 2 years before the current offense
        Map<LocalDate, Integer> history = new HashMap<>();
        history.put(LocalDate.of(2024, 11, 15), 14);  // Add a valid demeritPoints (Points exceed range for testing purposes)
        p.setOffenseHistory(history);

        // Add new offense with 3 points
        String result = p.addDemeritPoints("15-11-2025", 3);

        assertEquals("Success", result);
        assertTrue(p.getIsSuspended());
        assertEquals(17, p.calculateTotalValidDemeritPoints(LocalDate.of(2025, 11, 15)));
    }

    @Test
    void testAddDemeritPoints_AgeUnder21_Under6Points() {
        // Test Case 3: Check the function with valid input, age < 21, totalValidDemeritPoints <= 6

        // Create and add a valid person
        Person p = new Person();
        boolean added = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");
        assertTrue(added);

        // Set age under 21
        p.setBirthDate("15-11-2007");

        // Set offense history to 2 points in the last 2 years before the current offense
        Map<LocalDate, Integer> history = new HashMap<>();
        history.put(LocalDate.of(2024, 11, 15), 2);  // Add a valid demeritPoints
        p.setOffenseHistory(history);

        // Add new offense with 2 points
        String result = p.addDemeritPoints("15-11-2025", 2);

        assertEquals("Success", result);
        assertFalse(p.getIsSuspended());
        assertEquals(4, p.calculateTotalValidDemeritPoints(LocalDate.of(2025, 11, 15)));
    }

    @Test
    void testAddDemeritPoints_Age21OrOver_Under12Points() {
        // Test Case 4: Check the function with valid input, age >= 21, totalValidDemeritPoints <= 12

        // Create and add a valid person
        Person p = new Person();
        boolean added = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");
        assertTrue(added);

        // Set age under 21
        p.setBirthDate("15-11-1998");

        // Set offense history to 6 points in the last 2 years before the current offense
        Map<LocalDate, Integer> history = new HashMap<>();
        history.put(LocalDate.of(2024, 11, 15), 6);  // Add a valid demeritPoints
        p.setOffenseHistory(history);

        // Add new offense with 2 points
        String result = p.addDemeritPoints("15-11-2025", 2);

        assertEquals("Success", result);
        assertFalse(p.getIsSuspended());
        assertEquals(8, p.calculateTotalValidDemeritPoints(LocalDate.of(2025, 11, 15)));
    }

    @Test
    void testAddDemeritPoints_InvalidDateFormat() {
        // Test Case 5: Check the function with invalid date format

        // Create and add a valid person
        Person p = new Person();
        boolean added = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");
        assertTrue(added);

        // Test Data 1: Uses "/" instead of "-"
        String r1 = p.addDemeritPoints("15/11/1998", 6);

        // Test Data 2: Uses wrong order (YYYY-MM-DD)
        String r2 = p.addDemeritPoints("2004-11-12", 6);

        assertEquals("Failed", r1);
        assertEquals("Failed", r2);
    }

    @Test
    void testAddDemeritPoints_InvalidPoints() {
        // Test Case 6: Check the function with invalid demerit points 

        // Create and add a valid person
        Person p = new Person();
        boolean added = p.addPerson("Jane", "Smith", "24*dFt_yKB", "58|Highland Street|Melbourne|Victoria|Australia", "28-05-1993");
        assertTrue(added);

        // Test Data 1: Negative Numbers
        String r1 = p.addDemeritPoints("15-11-1998", -1);

        // Test Data: Outside the range of 1-6
        String r2 = p.addDemeritPoints("13-05-1984", 7);

        assertEquals("Failed", r1);
        assertEquals("Failed", r2);
    }
}