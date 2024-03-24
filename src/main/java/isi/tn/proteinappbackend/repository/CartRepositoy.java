package isi.tn.proteinappbackend.repository;

import isi.tn.proteinappbackend.entity.Cart;
import isi.tn.proteinappbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepositoy extends CrudRepository<Cart, Integer> {

    public List<Cart> findByUser(User user);

}
