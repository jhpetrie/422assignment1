import java.util.ArrayList;
import java.util.Scanner;

public class PetDb {
    private ArrayList<Pet> petList;

    public PetDb() {
        petList = new ArrayList<>();
    }

    public void addPet(String name, int age) {
        petList.add(new Pet(name, age));
        System.out.println("pet added successfully");
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
            System.out.print("Enter your choice: ");
            int option = scan.nextInt();
            switch(option) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                default:
                    System.out.println("Invalid choice. Please enter a number 1-7");
            }
        }
    }
}