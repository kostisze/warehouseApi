package storage.springboot.demo.DTO;

public class StorageDTOQuery {
	
	private Integer id;
	private String description;
	private Integer shelvesId;
	private String productName;
	
	public StorageDTOQuery() {}
	public StorageDTOQuery(Integer id, String description, Integer shelvesId,
			String productName) {
		this.id = id;
		this.description = description;
		this.shelvesId = shelvesId;
		this.productName = productName;

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
	public Integer getShelvesId() {
		return shelvesId;
	}
	public void setShelvesId(Integer shelvesId) {
		this.shelvesId = shelvesId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
