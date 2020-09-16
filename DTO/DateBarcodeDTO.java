package storage.springboot.demo.DTO;

public class DateBarcodeDTO {
	
	private String date;
	private String barcode;
	
	public DateBarcodeDTO() {}
	public DateBarcodeDTO(String date, String barcode) {
		this.date = date;
		this.barcode = barcode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	

}
