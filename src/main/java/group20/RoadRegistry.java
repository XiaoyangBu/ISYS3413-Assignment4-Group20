package group20;

import java.util.Scanner;

public class RoadRegistry {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person person = new Person();

        while (true) {
            System.out.println("========================");
            System.out.println("|    Road Registry     |");
            System.out.println("========================");
            System.out.println("1) Add Person");
            System.out.println("2) Update Personal Details");
            System.out.println("3) Add Demerit Points");
            System.out.println("4) Exit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter Person ID: ");
                String personID = scanner.nextLine();

                System.out.print("Enter Address (use | to separate e.g. 58|Highland Street|Melbourne|Victoria|Australia): ");
                String address = scanner.nextLine();

                System.out.print("Enter Birthdate (DD-MM-YYYY): ");
                String birthdate = scanner.nextLine();

                
                boolean personAdded = person.addPerson(firstName, lastName, personID, address, birthdate);
                if (personAdded) {
                    System.out.println("Person added successfully.");
                } else {
                    System.out.println("Failed to add person.");
                }
                
            }
            else if (choice.equals("2")) {  // ADD updatePersonalDetails function and input intake here
                System.out.println("Update Person details");
            }
            else if (choice.equals("3")) {  // ADD addDemeritPoints function and input intake here
                System.out.println("Add Demerit Points");
            }
            else if (choice.equals("4")) {
                System.out.println("Exiting Road Registry.");
                break;
            } 
            else {
                System.out.println("Invalid option. Please try again.");
            }

            System.out.println();
        }

        scanner.close();
    }
}

