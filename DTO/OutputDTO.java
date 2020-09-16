package storage.springboot.demo.DTO;

public class OutputDTO {
	private Integer shelfId;
	private Integer quantity;
	
	public OutputDTO() {}
	public OutputDTO(Integer shelfId, Integer quantity) {
		this.shelfId = shelfId;
		this.quantity = quantity;
	}
	public Integer getShelfId() {
		return shelfId;
	}
	public void setShelfId(Integer shelfId) {
		this.shelfId = shelfId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
