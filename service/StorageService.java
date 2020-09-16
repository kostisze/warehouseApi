package storage.springboot.demo.service;

import java.util.List;

import storage.springboot.demo.DTO.StorageDTO;
import storage.springboot.demo.DTO.StorageDTOQuery;
import storage.springboot.demo.entity.Storage;

public interface StorageService {
	
	public List<StorageDTO> findAll();
	public StorageDTO findById(int theId);
	public Storage save(Storage storage);
	public void deleteById(int theId);
	public StorageDTO save(StorageDTO dto);
	public List<StorageDTOQuery> findByDesc(String name);
}
