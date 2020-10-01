package frontend.model;

public class Person {

    private int id;

    private String name;

    private  int age;

    public Person(){

    }

    public Person(String name, int age){
        super();
        this.name = name;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
