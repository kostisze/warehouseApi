package storage.springboot.demo.DTO;

import storage.springboot.demo.entity.Report;

public class ReportDTO {
	
	private Integer id;
	private Integer reportId;
	private Integer storageId;
	private Integer shelvesId;
	private Integer productId;
	private Integer quantity;
	private String barcode;
	
	public ReportDTO () {}
	public ReportDTO(Integer reportId, Integer storageId, 
			Integer shelvesId, Integer productId, Integer reportInfo, Integer quantity) {
		this.id = reportId;
		this.storageId = storageId;
		this.shelvesId = shelvesId;
		this.productId = productId;
		this.quantity = quantity;
		this.reportId = reportInfo;
	}
	public ReportDTO(Report entity) {
		if(entity != null) {
			this.id = entity.getId();
		}
		if(entity.getProduct_report() != null) {
			this.productId = entity.getProduct_report().getId();
			this.barcode = entity.getProduct_report().getBarcode();
		}
		if(entity.getStorage_report() != null) {
			this.storageId = entity.getStorage_report().getId();
		}
		if(entity.getShelves_report() != null) {
			this.shelvesId = entity.getShelves_report().getId();
		}
		if(entity.getReportInfo() != null) {
			this.reportId = entity.getReportInfo().getId();
		}
		this.quantity = entity.getQuantity();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStorageId() {
		return storageId;
	}
	public void setStorageId(Integer storageId) {
		this.storageId = storageId;
	}
	public Integer getShelvesId() {
		return shelvesId;
	}
	public void setShelvesId(Integer shelvesId) {
		this.shelvesId = shelvesId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	
	
	
}
