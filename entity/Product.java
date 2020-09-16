package storage.springboot.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "barcode")
	private String barcode;
	
	//__________________________________________________________________________
	@OneToMany(mappedBy = "product",
			   cascade = CascadeType.REFRESH,
			   targetEntity = Shelves.class)
	private List<Shelves> shelves;
	
	@OneToMany(mappedBy = "productReport",
			cascade = CascadeType.REFRESH,
			targetEntity = Report.class)
	private List<Report> reports;
	//__________________________________________________________________________
	
	public Product() {}
	public Product(String description, String barcode) {
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
	//____________________________________________________________________________________________________
	@JsonManagedReference(value = "shelves_product")
	public List<Shelves> getShelves() {
		return shelves;
	}
	public void setShelves(List<Shelves> shelves) {
		this.shelves = shelves;
	}
	
	@JsonManagedReference(value = "product_report")
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	//____________________________________________________________________________________________________

}
