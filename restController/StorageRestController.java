package storage.springboot.demo.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storage.springboot.demo.DTO.StorageDTO;
import storage.springboot.demo.DTO.StorageDTOQuery;
import storage.springboot.demo.service.StorageService;

@RestController
@RequestMapping("/storages")
public class StorageRestController {
	@Autowired private StorageService storageService;

	//________________________________________________________________________________________________
	
	@GetMapping("")
	public List<StorageDTO> findAll(){
		return storageService.findAll();
	}
	
	@GetMapping("/{storageId}")
	public StorageDTO getStorageDto(@PathVariable int storageId) {
		return storageService.findById(storageId);
	}
	
	//________________________________________________________________________________________________

	@PostMapping("")
	public StorageDTO addStorageDto(@RequestBody StorageDTO theStorage) {
		return storageService.save(theStorage);
	}
	
	@PutMapping("")
	public StorageDTO updateStorageDto(@RequestBody StorageDTO theStorage) {
		return storageService.save(theStorage);
	}

	@DeleteMapping("/{storageId}")
	public String deleteStorage(@PathVariable int storageId) {
		storageService.deleteById(storageId);
		return "Storage deleted...";
	}
	
	//________________________________________________________________________________________________
	@PostMapping("/search")
	public List<StorageDTOQuery> searchStorage(@RequestBody StorageDTO dto) {
		return storageService.findByDesc(dto.getDescription());
	}

}
