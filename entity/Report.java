package storage.springboot.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "report")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	//______________________________________________________________________________________________
	@ManyToOne(targetEntity = ReportInfo.class)
	@JoinColumn(name = "report_id")
	private ReportInfo reportInfo;
	
	//______________________________________________________________________________________________
	@ManyToOne(targetEntity = Storage.class, cascade =
			CascadeType.REFRESH)
	@JoinColumn(name = "storage_id")
	private Storage storageReport;
	
	@ManyToOne(targetEntity = Product.class, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "product_id")
	private Product productReport;
	
	@ManyToOne(targetEntity = Shelves.class, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "shelves_id")
	private Shelves shelvesReport;
	//______________________________________________________________________________________________
	public Report() {}
	public Report(ReportInfo reportInfo, Storage storageReport, 
			Product productReport, Shelves shelvesReport) {
		this.reportInfo = reportInfo;
		this.storageReport = storageReport;
		this.productReport = productReport;
		this.shelvesReport = shelvesReport;
	}
	
	
	//______________________________________________________________________________________________
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	//______________________________________________________________________________________________
	
	
	@JsonBackReference(value = "storage_report")
	public Storage getStorage_report() {
		return storageReport;
	}
	public void setStorage_report(Storage storage_report) {
		this.storageReport = storage_report;
	}
	
	@JsonBackReference(value = "product_report")
	public Product getProduct_report() {
		return productReport;
	}
	public void setProduct_report(Product product_report) {
		this.productReport = product_report;
	}
	
	@JsonBackReference(value = "shelves_report")
	public Shelves getShelves_report() {
		return shelvesReport;
	}
	public void setShelves_report(Shelves shelves_report) {
		this.shelvesReport = shelves_report;
	}
	
	@JsonBackReference(value = "reportsInfo")
	public ReportInfo getReportInfo() {
		return reportInfo;
	}
	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	//______________________________________________________________________________________________
	
}
