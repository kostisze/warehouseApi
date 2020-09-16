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
@Table(name = "report_info")
public class ReportInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "date_")
	private String date;
	
	@Column(name ="description")
	private String description;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "refill")
	private boolean refill;
	
	//___________________________________________________________________________________________________________________
	@OneToMany(mappedBy = "reportInfo", targetEntity = Report.class,
			cascade = CascadeType.ALL)
	private List<Report> reports;
	
	//___________________________________________________________________________________________________________________
	public ReportInfo() {}
	public ReportInfo(Integer id, String date, String description, String name, 
			boolean refill) {
		this.date = date;
		this.description = description;
		this.name = name;
		this.refill = refill;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRefill() {
		return refill;
	}
	public void setRefill(boolean refill) {
		this.refill = refill;
	}
	
	@JsonManagedReference(value = "reportsInfo")
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	
}
