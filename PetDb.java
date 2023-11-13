import java.util.ArrayList;
import java.util.Scanner;

//pet database class of type arraylist
public class PetDb {
    private static ArrayList<Pet> petList;

    // constructor
    public PetDb() {
        petList = new ArrayList<>();
    }

    // add pet to arraylist
    public void addPet(String name, int age) {
        petList.add(new Pet(name, age));
    }

    // method to view pets
    public void viewPets() {
        // prints the pet database
        System.out.println("+----------------------+");
        System.out.printf("| %-2s | %-9s | %-3s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        // loops over the arraylist getting the pet objects and printing their name and
        // age
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            System.out.printf("| %-2d | %-9s | %-3d |\n", i, pet.getName(), pet.getAge());
        }
        System.out.println("+----------------------+");
        // prints the size of the arraylist to indicate the number of rows
        System.out.println(petList.size() + " rows in set.");
    }

    // method for searching by a name
    public static void searchName(String searchName) {
        // prints table header
        System.out.println("+----------------------+");
        System.out.printf("| %-2s | %-9s | %-3s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        // initialize a variable for countin rows
        int rows = 0;
        // loops over array list checking to see if the paramater that has been passed
        // is the name of the pet
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            // if the name is found, prints the pet attributes and increases the row count
            // by 1
            if (pet.getName().equalsIgnoreCase(searchName)) {
                System.out.printf("| %-2d | %-9s | %-3d |\n", i, pet.getName(), pet.getAge());
                rows++;
            }
        }
        System.out.println("+----------------------+");
        // prints the row count
        System.out.println(rows + " rows in set.");
    }

    // method for searching by age, same as by name but with an int parameter
    public static void searchAge(int searchAge) {
        System.out.println("+----------------------+");
        System.out.printf("| %-2s | %-9s | %-3s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        int rows = 0;
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            if (pet.getAge() == searchAge) {
                System.out.printf("| %-2d | %-9s | %-3d |\n", i, pet.getName(), pet.getAge());
                rows++;
            }
        }
        System.out.println("+----------------------+");
        System.out.println(rows + " rows in set.");
    }

    // MAIN
    public static void main(String[] args) {
        PetDb petDb = new PetDb();
        Scanner scan = new Scanner(System.in);
        System.out.println("Pet Database Program\n");
        // menu that will loop until the program is exited
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. View all pets");
            System.out.println("2. Add more pets");
            System.out.println("3. Update an existing pet");
            System.out.println("4. Remove an existing pet");
            System.out.println("5. Search pets by name");
            System.out.println("6. Search pets by age");
            System.out.println("7. Exit Program");
            System.out.print("Your choice: ");
            int option = scan.nextInt();
            scan.nextLine();
            // logic based on user input
            switch (option) {
                // View Pets
                case 1:
                    petDb.viewPets();
                    break;
                // Add pets
                case 2:
                    System.out.println("Input pet name as 'done' at any time to stop");
                    // Loops, adding pets
                    while (true) {
                        System.out.print("Add pet (name age): ");
                        // Take a user input string as a string array and splits at a space
                        String[] input = scan.nextLine().split(" ");
                        // if done is input, breaks out of the switch and loops back into menu
                        if (input[0].equalsIgnoreCase("done")) {
                            System.out.println("Pets added.");
                            break;
                        } else {
                            // takes the string at index 0 as the name
                            String name = input[0];
                            // takes the string at index 1 and parses to an int
                            int age = Integer.parseInt(input[1]);
                            // pass name and age to pet constructor
                            petDb.addPet(name, age);
                        }
                    }
                case 3:
                    System.out.println("option 3");
                    break;
                case 4:
                    System.out.println("option 4");
                    break;
                    // name search
                case 5:
                    System.out.print("Enter a name to search: ");
                    String searchName = scan.nextLine();
                    searchName(searchName);
                    break;
                    // age search
                case 6:
                    System.out.print("Enter age to search: ");
                    int searchAge = scan.nextInt();
                    scan.nextLine();
                    searchAge(searchAge);
                    break;
                    // exit the program
                case 7:
                    System.out.println("Goodbye!");
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number 1-7");
            }
        }
    }
}