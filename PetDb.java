import java.util.ArrayList;
import java.util.Scanner;

public class PetDb {
    private ArrayList<Pet> petList;

    public PetDb() {
        petList = new ArrayList<>();
    }

    public void addPet(String name, int age) {
        petList.add(new Pet(name, age));
    }

    public void showPets() {
        System.out.println("+----------------------+");
        System.out.printf("| %-3s | %-10s | %-4s |\n", "ID", "NAME", "AGE");
        System.out.println("+----------------------+");
        for (int i = 0; i < petList.size(); i++) {
            Pet pet = petList.get(i);
            System.out.printf("| %-3d | %-10s | %-4d |\n", i, pet.getName(), pet.getAge());
        }
        System.out.println("+----------------------+");
        System.out.println(petList.size() + " rows in set.");
    }

    public static void main(String[] args) {
        PetDb petDb = new PetDb();
        Scanner scan = new Scanner(System.in);
        System.out.println("Pet Database Program\n");
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
            switch (option) {
                case 1:
                    System.out.println("Your 1");
                    break;
                case 2:
                    System.out.println("Input pet name as 'done' at any time to stop");
                    while (true) {
                        System.out.print("Add pet (name age): ");
                        String[] input = scan.nextLine().split(" ");
                        if (input[0].equals("done")) {
                            System.out.println("Pets added.");
                            break;
                        } else {
                            String name = input[0];
                            int age = Integer.parseInt(input[1]);
                            petDb.addPet(name, age);
                        }
                    }
                case 3:
                    System.out.println("option 3");
                    break;
                case 4:
                    System.out.println("option 4");
                    break;
                case 5:
                    System.out.println("option 5");
                    break;
                case 6:
                    System.out.println("option 6");
                    break;
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