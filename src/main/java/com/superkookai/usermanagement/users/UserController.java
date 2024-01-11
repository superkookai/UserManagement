package com.superkookai.usermanagement.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @GetMapping("api/users")
    public List<User> getUsers(@RequestParam("active") Optional<Boolean> active){
        return userService.getUsers(active);
    }

    @PostMapping("api/users")
    public User createUser(@RequestBody UserRequest request){
        return userService.createUser(request);
    }

    @PutMapping("api/users/{id}")
    public void editUser(@PathVariable("id") int id, @RequestBody UserRequest request){
        userService.editUser(id,request);
    }

    @DeleteMapping("api/users/{id}")
    public void deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
    }
}

record UserRequest(String name, int age){}

class User{
    private int id;
    private String name;
    private int age;
    private Boolean active;

    public User(int id, String name, int age, Boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setName(String name) {
        this.name = name;
    }
}