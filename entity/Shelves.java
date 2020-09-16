package storage.springboot.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "shelves")
public class Shelves{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//______________________________________________________________________________________________
	@ManyToOne(targetEntity = Storage.class, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "storage_id")
	private Storage storage;
	
	@ManyToOne(targetEntity = Product.class, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id")
	private Product product;
	
	@OneToMany(mappedBy = "shelvesReport", cascade = CascadeType.REFRESH,
			targetEntity = Report.class)
	private List<Report> reports;
	
	//______________________________________________________________________________________________	
	public Shelves() {}
	public Shelves(Storage theStorage, Product theProduct) {
		this.storage = theStorage;
		this.product = theProduct;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//______________________________________________________________________________________________
	
	@JsonBackReference(value = "shelves_storage")
	public Storage getStorage() {
		return storage;
	}
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	
	@JsonBackReference(value = "shelves_product")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@JsonManagedReference(value = "shelves_report")
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	//_____________________________________________________________________________________________
}
