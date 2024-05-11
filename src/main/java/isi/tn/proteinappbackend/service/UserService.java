package isi.tn.proteinappbackend.service;


import isi.tn.proteinappbackend.entity.Role;
import isi.tn.proteinappbackend.entity.User;
import isi.tn.proteinappbackend.repository.RoleRepository;
import isi.tn.proteinappbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private RoleRepository roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin@admin");
        adminUser.setUserPassword(getEncodedPassword("admin123"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }
 public Iterable<User> findAllUsers() {
        return userDao.findAll();
    }


    public String remove(Id id) {
        userDao.deleteById(id.toString());
        return "deleted";
    }


    public String updateUser(Id id, User user) {
        User updatedUser = userDao.findById(id.toString()).get();
        updatedUser.setUserName(user.getUserName());
        updatedUser.setUserFirstName(user.getUserFirstName());
        updatedUser.setUserLastName(user.getUserLastName());
        updatedUser.setUserPassword(user.getUserPassword());
        userDao.save(updatedUser);
        return "Updated";
    }

    public Optional<User> findUserById(Id id) {
        return userDao.findById(id.toString());
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
