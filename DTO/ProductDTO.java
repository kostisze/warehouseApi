package storage.springboot.demo.DTO;

public class ProductDTO {
	private Integer id;
	private String description;
	private String barcode;
	
	public ProductDTO() {}
	public ProductDTO(Integer id, String description, String barcode) {
		this.id = id;
		this.description = description;

		this.barcode = barcode;
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
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
}
