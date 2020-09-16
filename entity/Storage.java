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
@Table(name = "storage")
public class Storage{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "description")
	private String description;
	
	//__________________________________________________________________________
	@OneToMany(mappedBy = "storage" ,
			   cascade =CascadeType.ALL, 
			   targetEntity = Shelves.class)
	private List<Shelves> shelves;
	
	@OneToMany(mappedBy = "storageReport",cascade = CascadeType.REFRESH,
			targetEntity = Report.class)
	private List<Report> reports;
	
	//__________________________________________________________________________
	public Storage() {}
	public Storage(String description) {
		this.description = description;
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
	//______________________________________________________________________________________________
	@JsonManagedReference(value = "shelves_storage")
	public List<Shelves> getShelves() {
		return shelves;
	}
	public void setShelves(List<Shelves> shelves) {
		this.shelves = shelves;
	}
	
	@JsonManagedReference(value = "storage_report")
	public List<Report> getReports() {
		return reports;
	}
	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	
	
	//_______________________________________________________________________________________________	
}
