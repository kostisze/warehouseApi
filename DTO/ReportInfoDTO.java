package storage.springboot.demo.DTO;

import java.util.List;
import storage.springboot.demo.entity.ReportInfo;
import storage.springboot.demo.service.ReportServiceImpl;

public class ReportInfoDTO {
	private Integer id;
	private String date;
	private String description;
	private String name;
	private boolean refill;
	private List<ReportDTO> reports;
	
	public ReportInfoDTO() {}
	public ReportInfoDTO(Integer id, String date, String description, 
			String name, boolean refill,
			List<ReportDTO> reports) {
		this.id = id;
		this.date = date;
		this.description = description;
		this.name = name;
		this.refill = refill;
		this.reports = reports;
	}
	public ReportInfoDTO(ReportInfo entity) {
		this.id = entity.getId();
		this.date = entity.getDate();
		this.description = entity.getDescription();
		this.name = entity.getName();
		this.refill = entity.isRefill();
		if(entity.getReports() != null) {
			ReportServiceImpl conv = new ReportServiceImpl();
			reports = conv.convertToDto(entity.getReports());
		}
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
	public List<ReportDTO> getReports() {
		return reports;
	}
	public void setReports(List<ReportDTO> reports) {
		this.reports = reports;
	}
}
