import java.util.HashMap;
import java.util.Date;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Person {

    private String personID;
    private String firstName;
    private String lastName;
    private String address;
    private String birthdate;
    private int demeritPoints; // A variable that holds the demerit points with the offense day
    private boolean isSuspended;
    private Map<LocalDate, Integer> offenseHistory = new HashMap<>();


    public boolean addPerson() {
        return true;
    //TODO: This method adds information about a person to a TXT file.
    //Condition 1: PersonID should be exactly 10 characters long;
    //the first two characters should be numbers between 2 and 9, there should be at least two special characters between characters 3 and 8,
    //and the last two characters should be upper case letters (A – Z). Example: "56s_d%&fAB"
    //Condition 2: The address of the Person should follow the following format: Street Number|Street|City|State|Country.
    //The State should be only Victoria. Example: 32|Highland Street|Melbourne|Victoria|Australia.
    //Condition 3: The format of the birth date of the person should follow the following format: DD-MM-YYYY. Example: 15-11-1990
    //Instruction: If the Person's information meets the above conditions and any other conditions you may want to consider,
    //the information should be inserted into a TXT file, and the addPerson function should return true.
    //Otherwise, the information should not be inserted into the TXT file, and the addPerson function should return false.
    }


    public boolean updatePersonalDetails() {
        return true;

        //TODO: This method allows updating a given person's ID, firstName, lastName, address and birthday in a TXT file.
        //Changing personal details will not affect their demerit points or the suspension status.
        // All relevant conditions discussed for the addPerson function also need to be considered and checked in the updatPerson function.
        //Condition 1: If a person is under 18, their address cannot be changed.
        //Condition 2: If a person's birthday is going to be changed, then no other personal detail (i.e, person's ID, firstName, lastName, address) can be changed.
        //Condition 3: If the first character/digit of a person's ID  is an even number, then their ID cannot be changed.
        //Instruction: If the Person's updated information meets the above conditions and any other conditions you may want to consider,
        //the Person's information should be updated in the TXT file with the updated information, and the updatePersonalDetails function should return true.
        //Otherwise, the Person's updated information should not be updated in the TXT file, and the updatePersonalDetails function should return false.

    }






    /**
     * Adds demerit points for a given person in a TXT file.
     *
     * @param offenseDate Offense Date in DD-MM-YYYY format
     * @param points Demerit points between 1-6
     * @return "Success" or "Failed"
     */
    public String addDemeritPoints(String offenseDate, int points) {

        //This method adds demerit points for a given person in a TXT file.
        //Condition 1:  The format of the date of the offense should follow the following format: DD-MM-YYYY. Example: 15-11-1990
        //Condition 2:  The demerit points must be a whole number between 1-6
        //Condition 3:  If the person is under 21, the isSuspended variable should be set to true if the total demerit points within two years exceed 6.
        //If the person is over 21, the isSuspended variable should be set to true if the  total  demerit points within two years exceed  12.
        //Instruction: If the above condiaitons and any other conditions you may want to consider are met, the demerit points for a person should be inserted into the TXT file,
        //and the addDemeritPoints function should return "Sucess". Otherwise, the addDemeritPoints function should return "Failed".


        // Check if the offense date is in correct format: DD-MM-YYYY（Condition 1）
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate offenseDay;
        try {
            offenseDay = LocalDate.parse(offenseDate, dtf);
        } catch (Exception e) {
            return "Failed";
        }

        // Check if the demerit point is a whole number between 1-6（Condition 2）
        if (points < 1 || points > 6) {
            return "Failed"; //Demerit point is illegal, must be between 1 - 6
        }

        // Update offences history
        if (offenseHistory == null) offenseHistory = new HashMap<>(); // Prevent if offenseHistory is null 
        offenseHistory.put(offenseDay, points);

        // Re-count all demerit points in the last 2 years, including current offense
        int sum = 0;
        for (Map.Entry<LocalDate, Integer> entry : offenseHistory.entrySet()) {
            LocalDate date = entry.getKey();
            if ((date.isEqual(offenseDay) || date.isBefore(offenseDay)) // date <= offenseDay
                    && date.isAfter(offenseDay.minusYears(2))) {        // date > offenseDay-2年
                sum += entry.getValue();
            }
        }


        // Calculate the driver's current age
        LocalDate birth = LocalDate.parse(this.birthdate, dtf);
        int age = Period.between(birth, offenseDay).getYears();

        // Check if suspended（Condition 3）
        if (age < 21 && sum > 6) {
            this.isSuspended = true;
        } else if (age >= 21 && sum > 12) {
            this.isSuspended = true;
        } else {
            this.isSuspended = false;
        }

        // Update total demerit point and suspend status in TXT file
        this.demeritPoints = sum; // I choose to use the demerit points in last 2 years from current date.

        // Update the TXT file (Overwrite and update the current driver's information into TXT file）
        // What I choose to do here: Read in all lines, look for the line with personID, then replace and rewrite all information behind
        try {
            Path filePath = Paths.get("person_data.txt");
            File file = filePath.toFile();
            if (!file.exists()) {
                // File doesn't excit, write in a new line
                FileWriter fw = new FileWriter(file, true);
                fw.write(this.toDataLine());
                fw.close();
            } else {
                // Read in all
                StringBuilder sb = new StringBuilder();
                boolean found = false;
                for (String line : Files.readAllLines(filePath)) {
                    if (line.startsWith(this.personID + "|")) {
                        sb.append(this.toDataLine());
                        found = true;
                    } else {
                        sb.append(line).append("\n");
                    }
                }
                if (!found) sb.append(this.toDataLine());
                // Rewrite into the file
                FileWriter fw = new FileWriter(file, false);
                fw.write(sb.toString());
                fw.close();
            }
            return "Success";
        } catch (Exception e) {
            return "Failed";
        }

    }

    // Change Person into a TXT data line
    public String toDataLine() {
        // personID|firstName|lastName|address|birthdate|demeritPoints|isSuspended
        return personID + "|" + firstName + "|" + lastName + "|" + address + "|" + birthdate + "|" + demeritPoints + "|" + isSuspended + "\n";
    }
}
