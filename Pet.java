//Pet class
public class Pet {
    private String name;
    private int age;
//constructor
    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }
//getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
