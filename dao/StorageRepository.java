package storage.springboot.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import storage.springboot.demo.entity.Storage;


public interface StorageRepository extends JpaRepository<Storage, Integer>{
	
}
