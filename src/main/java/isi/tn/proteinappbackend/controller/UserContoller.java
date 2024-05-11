package isi.tn.proteinappbackend.controller;


import isi.tn.proteinappbackend.entity.User;
import isi.tn.proteinappbackend.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserContoller{

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerNewUser(@RequestBody User user) {
        return userService.registerNewUser(user);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
    
    @GetMapping({"/forAdmin/findAllUsers"})
    @PreAuthorize("hasRole('Admin')")
    public Iterable<User> findAllUsers(){return userService.findAllUsers();}

    @GetMapping({"/forAdmin/removeUser"})
    @PreAuthorize("hasRole('Admin')")
    public String remove(Id id){return userService.remove(id); }


    @GetMapping({"/forAdmin/updateUser"})
    @PreAuthorize("hasRole('Admin')")
    public String updateUser(Id id, User user){ return userService.updateUser(id, user);}

    @GetMapping({"/forAdmin/findUser"})
    @PreAuthorize("hasRole('Admin')")
    public Optional<User> findUserById(Id id) {
        return userService.findUserById(id);
    }

}
