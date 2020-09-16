package storage.springboot.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import storage.springboot.demo.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	

}
