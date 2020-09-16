package storage.springboot.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import storage.springboot.demo.entity.Shelves;

public interface ShelfRepository extends JpaRepository<Shelves, Integer>{
}
