public class Pet {
    private int id = 0;
    private String name;
    private int age;
    
    public Pet(String name, int age) {
    this.id++;
    this.name = name;
    this.age = age;
    }
    
    public int getId() {
    return id;
    }
    
    public void setId(int id) {
    this.id = id;
    }
    
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