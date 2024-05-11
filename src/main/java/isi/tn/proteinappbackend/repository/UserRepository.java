package isi.tn.proteinappbackend.repository;

import isi.tn.proteinappbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
  public List<User> findAllUsers();
    public User findUserById(Long id);
    public String remove(Long id);
    public String updateUser(Long id, User user);
}
