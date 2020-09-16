package storage.springboot.demo.service;

import java.util.List;
import storage.springboot.demo.DTO.ReportDTO;
import storage.springboot.demo.entity.Report;

public interface ReportService {
	
	public List<ReportDTO> findAll();
	public ReportDTO findById(int theId);
	public Report save(Report theReport);
	public void deleteById(int theId);
	public ReportDTO save(ReportDTO dto);
	public Integer checkQuantity(ReportDTO dto);
	public void checkReport(ReportDTO dto);
}
