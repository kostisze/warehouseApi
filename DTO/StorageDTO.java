package storage.springboot.demo.DTO;

import java.util.List;

public class StorageDTO {
	
	private Integer id;
	private String description;
	private List<ShelvesDTO> shelves;
	
	public StorageDTO() {}
	public StorageDTO(Integer id, String description, List<ShelvesDTO> shelves) {
		this.id = id;
		this.description = description;
		this.shelves = shelves;
	}
	public StorageDTO(Integer id, String description) {
		this.id = id;
		this.description = description;
		this.shelves = null;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ShelvesDTO> getShelves() {
		return shelves;
	}
	public void setShelves(List<ShelvesDTO> shelves) {
		this.shelves = shelves;
	}	
}
