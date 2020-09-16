package storage.springboot.demo.DTO;

public class ProductDescriptionDTO {
	
	private String description;

	public ProductDescriptionDTO() {};
	public ProductDescriptionDTO(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
