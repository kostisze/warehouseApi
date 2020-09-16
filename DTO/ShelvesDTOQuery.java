package storage.springboot.demo.DTO;

public class ShelvesDTOQuery {
	
	private Integer id;
	private String productName;
	
	public ShelvesDTOQuery() {}
	public ShelvesDTOQuery(Integer id, String productName) {
		this.id = id;
		this.productName = productName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
