package storage.springboot.demo.DTO;

import storage.springboot.demo.entity.Shelves;
import storage.springboot.demo.service.ProductSeviceImpl;

public class ShelvesDTO {
	
	private Integer id;
	private ProductDTO product;
	
	public ShelvesDTO() {}
	public ShelvesDTO(Integer id, ProductDTO product) {
		this.id = id;
		this.product = product;
	}
	public ShelvesDTO(Shelves shelves) {
		this.id = shelves.getId();
		
		if(shelves.getProduct() != null) {
			ProductSeviceImpl con = new ProductSeviceImpl();
			this.product = con.convertToDto(shelves.getProduct());
		}
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ProductDTO getProduct() {
		return product;
	}
	public void setProduct(ProductDTO product) {
		this.product = product;
	}
	
	
}