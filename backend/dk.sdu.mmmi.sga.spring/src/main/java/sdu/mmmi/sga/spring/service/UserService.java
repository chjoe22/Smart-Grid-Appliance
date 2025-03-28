package sdu.mmmi.sga.spring.service;

import sdu.mmmi.sga.spring.data.User;

import java.util.Arrays;
import java.util.List;

// This class is a test class to simulate a service that returns a list of users
public class UserService {
    public List<User> getAllUsers(){
        return Arrays.asList(
                new User(1, "John", "Doe", 25),
                new User(2, "Jane", "Doe", 22),
                new User(3, "Jim", "Doe", 30),
                new User(4, "Jill", "Doe", 35)
        );
    }
}
