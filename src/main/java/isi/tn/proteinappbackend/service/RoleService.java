package isi.tn.proteinappbackend.service;


import isi.tn.proteinappbackend.entity.Role;
import isi.tn.proteinappbackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
