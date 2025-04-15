package dk.sdu.mmmi.sga.spring.data;


// This is a test class to simulate a user object
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private int age;

    public User(int id, String firstname, String lastname, int age ){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }
}
