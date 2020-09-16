package storage.springboot.demo.DTO.PUT;

import storage.springboot.demo.entity.Shelves;

public class ShelvesPUT {
	
	private Integer id;
	private Integer productId;
	private Integer storageId;
	
	public ShelvesPUT() {}
	public ShelvesPUT(Integer id, Integer storage, Integer product) {
		this.id = id;
		this.productId = product;
		this.storageId = storage;
	}
	public ShelvesPUT(Shelves shelves) {
		this.id = shelves.getId();
		if(shelves.getProduct() != null) {
			this.productId = shelves.getProduct().getId();
		}
		if(shelves.getStorage() != null) {
			this.storageId = shelves.getStorage().getId();
		}
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getStorageId() {
		return storageId;
	}
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}

	
}
