package isi.tn.proteinappbackend.repository;

import isi.tn.proteinappbackend.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findAll(Pageable pageable);

    public List<Product>  findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
            String key1, String key2, Pageable pageable);


}
