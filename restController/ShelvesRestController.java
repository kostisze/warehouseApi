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
import storage.springboot.demo.DTO.ShelvesDTO;
import storage.springboot.demo.DTO.ShelvesDTOQuery;
import storage.springboot.demo.DTO.PUT.ShelvesPUT;
import storage.springboot.demo.service.ShelfService;

@RestController
@RequestMapping("/shelves")
public class ShelvesRestController {
	@Autowired private ShelfService shelfService;

	//________________________________________________________________________________________________
	
	@GetMapping("")
	public List<ShelvesDTO> findAll(){
		return shelfService.findAll();
	}
	
	@GetMapping("/{shelvesId}")
	public ShelvesDTO getShelves(@PathVariable int shelvesId) {
		return shelfService.findById(shelvesId);
	}
	
	//_______________________________________________________________________________________________
	
	@PostMapping("")
	public ShelvesPUT addShelves(@RequestBody ShelvesPUT theShelfDto) {
		return shelfService.save(theShelfDto);
	}
	
	@PutMapping("")
	public ShelvesPUT updateShelves(@RequestBody ShelvesPUT theShelfDto) {	
		return shelfService.save(theShelfDto);
	}
	
	@DeleteMapping("/{shelfId}")
	public String deleteShelves(@PathVariable int shelfId) {
		shelfService.deleteById(shelfId);
		return "Shelf " +shelfId+ " deleted";
	}
	
	//_______________________________________________________________________________________________
	@PostMapping("/search")
	public ShelvesDTOQuery search(@RequestBody ShelvesDTO dto){
		return shelfService.searchById(dto.getId());
	}
}
