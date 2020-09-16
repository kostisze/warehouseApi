package storage.springboot.demo.service;

import java.util.List;
import storage.springboot.demo.DTO.ShelvesDTO;
import storage.springboot.demo.DTO.ShelvesDTOQuery;
import storage.springboot.demo.DTO.PUT.ShelvesPUT;
import storage.springboot.demo.entity.Shelves;

public interface ShelfService {
	
	public List<ShelvesDTO> findAll();
	public ShelvesDTO findById(int theId);
	public Shelves save(Shelves theShelf);
	public void deleteById(int theId);
	public ShelvesPUT save(ShelvesPUT dto);
	public ShelvesDTOQuery searchById(Integer id);
	
}
