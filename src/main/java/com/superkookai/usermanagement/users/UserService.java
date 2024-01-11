package com.superkookai.usermanagement.users;

import com.superkookai.usermanagement.mail.GoogleMailService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    List<User> users = new ArrayList<>(
            List.of(
                    new User(1,"Joe",35,true),
                    new User(2,"Jim",18,true),
                    new User(3,"Jane",47,false)
            )
    );

    private GoogleMailService googleMailService;

    public UserService() {
        this.googleMailService = new GoogleMailService();
        this.googleMailService.setUrl("mail.google.com");
        this.googleMailService.setPort("42");
    }

    public List<User> getUsers(Optional<Boolean> active){
        if (active.isPresent()){
            return users.stream().filter(u -> u.getActive() == active.get()).toList();
        }
        return users;
    }

    public User createUser(UserRequest request){
        Optional<Integer> maxId = users.stream()
                .map(User::getId)
                .max(Integer::compareTo);
        int nextId = maxId.orElse(0) + 1;
        User user = new User(nextId, request.name(), request.age(), true);
        users.add(user);

        //Send email
        googleMailService.sendEmail("dev@gmail.com","User created: " + request.name() );

        return user;
    }

    public void editUser(int id,UserRequest request){
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        if (user.isPresent()){
            User u = user.get();
            u.setName(request.name());
        }
    }

    public void deleteUser(int id){
        users.removeIf(u -> u.getId() == id);
    }
}
