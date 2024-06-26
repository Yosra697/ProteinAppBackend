package isi.tn.proteinappbackend.repository;

import isi.tn.proteinappbackend.entity.OrderDetail;
import isi.tn.proteinappbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {

    public List<OrderDetail> findByUser(User user);

}
