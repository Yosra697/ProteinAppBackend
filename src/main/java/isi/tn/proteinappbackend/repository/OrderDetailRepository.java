package isi.tn.proteinappbackend.repository;

import isi.tn.proteinappbackend.entity.OrderDetail;
import isi.tn.proteinappbackend.entity.User;

import java.util.List;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer>{

    public List<OrderDetail> findByUser(User user);

}
