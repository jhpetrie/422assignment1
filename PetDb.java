import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

//pet database class of type arraylist
public class PetDb {
    private static ArrayList<Pet> petList;
    static Scanner scan = new Scanner(System.in);

    // constructor
    public PetDb() {
        petList = new ArrayList<>();
    }

    // add pet to arraylist
    public void addPet() {
        System.out.println("Input pet name as 'done' at any time to stop");
        // Loops, adding pets
        while (true) {
            // If the pet list reaches 5 elements, notifies user max has been reached and exits loop and method
            if (petList.size() < 5) {
                System.out.print("Add pet (name age): ");
                // Take a user input string as a string array and splits at a space
                String[] input = scan.nextLine().split(" ");
                // if done is input, breaks out of the switch and loops back into menu
                if (input[0].equalsIgnoreCase("done")) {
                    System.out.println("Pets added.");
                    break;
                } else {
                    try {
                        // takes the string at index 0 as the name
                        String name = input[0];
                        // takes the string at index 1 and parses to an int
                        int age = Integer.parseInt(input[1]);
                        // pass name and age to pet constructor
                        petList.add(new Pet(name, age));
                    } catch (Exception e) {
                        System.out.println("ERROR: Invalid input");
                    }
                }
            } else {
                System.out.println("\nMaximum number of pets (5) has been reached\n");
                break;
            }
        }
    }

    public static void printHeader() {
        // prints table header
        System.out.println("+----------------------+");
        System.out.printf("| %-2s | %-9s | %-3s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
    }

    public static void loadFile(String fileName) {
        try {
            // Create a File object for the text file
            File file = new File(fileName);
            // Create a Scanner object for the file
            Scanner fileScan = new Scanner(file);
            // Read each line in the file
            while (fileScan.hasNextLine()) {
                if (petList.size() < 5) {
                    // Splits the line into pet name and pet age using the "|" delimiter
                    String[] line = fileScan.nextLine().split("\\|");
                    // Creates a new Pet object with its pet name and pet age
                    Pet newPet = new Pet(line[0], Integer.parseInt(line[1]));
                    // Adds the Pet object to the petList
                    petList.add(newPet);
                } else {
                    System.out.println("\nERROR: Too many pets in " + fileName);
                    System.out.println("\nMaximum number of pets must not exceed 5\n");
                    break;
                }
            }
            System.out.println("\n" + fileName + " loaded succesfully\n");
            // Closes the file scanner
            fileScan.close();

        } catch (FileNotFoundException e) {
            System.out.println("\nERROR: " + fileName + " does not exist or is named something else.\n");
        }
    }

    // Method for saving to a file
    public static void saveFile(String fileName, ArrayList<Pet> list) {
        try {
            // File writer wrapped in bufferedwriter
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));

            // Loops over the array list, writing name + | + age of each pet
            for (int i = 0; i < list.size(); i++) {
                bw.write(list.get(i).getName() + "|" + list.get(i).getAge());
                bw.newLine();
            }
            System.out.println("Succesfully saved to " + fileName);
            // Close bufferedwriter
            bw.close();
        } catch (Exception e) {
            e.getStackTrace();
        }

    }

    // method to view pets
    public void viewPets() {
        // prints the pet database
        printHeader();
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
        printHeader();
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

    // Method for searching by age, same as by name but with an int parameter
    public static void searchAge(int searchAge) {
        printHeader();
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

    // Update Pet
    public void updatePet() {
        while (true) {
            System.out.print("Enter the pet ID to update: ");
            int petId = scan.nextInt();
            scan.nextLine();
            // checks to ensure input ID to edit is valid (more than 0 less than max)
            if (petId >= 0 && petId < petList.size()) {
                // asks for new name and age
                System.out.print("\nEnter new name and new age: ");
                String[] input = scan.nextLine().split(" ");
                // reuses pet adding logic to update pet
                String newName = input[0];
                int newAge = Integer.parseInt(input[1]);
                // gets the pet at that index (id)
                Pet pet = petList.get(petId);
                // prints required message and sets name and age based on passed parameters
                System.out.println(pet.getName() + " " + pet.getAge() + " changed to " + newName + " " + newAge);
                pet.setName(newName);
                pet.setAge(newAge);
                break;
            } else {
                System.out.println("\nERROR: Invalid pet ID\n");
            }
        }
    }

    // Remove pet
    public static void removePet() {
        int id = 0;
        System.out.println("Enter the pet ID to remove, input '6' to clear all pets: ");
        try {
            id = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Invalid pet ID inpu. Must input an integer");
        }
        scan.nextLine();
        if (id >= 0 && id < petList.size()) {
            Pet pet = petList.get(id);
            System.out.println(id + " " + pet.getName() + " " + pet.getAge() + " has been removed.");
            petList.remove(id);
        } else if (id == 6) {
            petList.clear();
            System.out.println("\nAll pets removed!\n");
        } else {
            System.out.println("Invalid pet ID");
        }
    }

    // MAIN
    public static void main(String[] args) {
        PetDb petDb = new PetDb();
        System.out.println("Pet Database Program\n");
        // Menu that will loop until the program is exited
        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1. View all pets");
            System.out.println("2. Add more pets");
            System.out.println("3. Update an existing pet");
            System.out.println("4. Remove an existing pet");
            System.out.println("5. Search pets by name");
            System.out.println("6. Search pets by age");
            System.out.println("7. Save current Pet Database to a File");
            System.out.println("8. Load pets from a File");
            System.out.println("9. Exit Program");
            System.out.print("Your choice: ");
            int option = scan.nextInt();
            scan.nextLine();
            // logic based on user input
            switch (option) {
                case 1: // View Pets
                    petDb.viewPets();
                    break;
                case 2: // Add pets
                    petDb.addPet();
                    break;
                case 3: // update pet
                    // first prints the petdb
                    petDb.viewPets();
                    if (petList.isEmpty()) {
                        System.out.println("\nERROR: There are no existing pets in the database!\n");
                    } else {
                        petDb.updatePet();
                    }
                    break;
                case 4: // remove pet
                    // first prints pet db
                    petDb.viewPets();
                    removePet();
                    break;
                case 5: // name search
                    System.out.print("Enter a name to search: ");
                    String searchName = scan.nextLine();
                    searchName(searchName);
                    break;
                case 6: // age search
                    System.out.print("Enter age to search: ");

                    int searchAge = scan.nextInt();
                    scan.nextLine();
                    searchAge(searchAge);
                    break;
                case 7: // Save pet list to a text file
                    System.out.println("Input desired file name");
                    saveFile(scan.nextLine() + ".txt", petList);
                    break;
                case 8: // Load pets from a file
                    if (petList.isEmpty()) {
                        while (petList.isEmpty()) {
                            System.out
                                    .println("Input the name of the txt file you would like to load: ");
                            String fileName = scan.nextLine() + ".txt";
                            loadFile(fileName);
                        }
                    } else {
                        System.out.println("\nA pet database is already present!\n");
                    }
                    break;
                case 9: // View Pets
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