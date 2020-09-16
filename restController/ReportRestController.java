package storage.springboot.demo.restController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storage.springboot.demo.DTO.ReportDTO;
import storage.springboot.demo.service.ReportService;

@RestController
@RequestMapping("/reports")
public class ReportRestController {
	@Autowired private ReportService reportService;

	//________________________________________________________________________________________________
	
	@GetMapping("")
	public List<ReportDTO> findAll(){
		return reportService.findAll();
	}
	
	@GetMapping("/{reportId}")
	public ReportDTO getReport(@PathVariable int reportId) {
		return reportService.findById(reportId); 
	}
	
	//________________________________________________________________________________________________
	
	@PostMapping("")
	public ReportDTO addReport(@RequestBody ReportDTO report) {
		reportService.checkReport(report);
		reportService.checkQuantity(report);
		return reportService.save(report);
	}
	
	@PutMapping("")
	public ReportDTO updateReport(@RequestBody ReportDTO report) {
		reportService.checkReport(report);
		reportService.checkQuantity(report);
		return reportService.save(report);
	}
	
	@DeleteMapping("/{reportId}")
	public String deleteReport(@PathVariable int reportId) {
		reportService.deleteById(reportId);
		return "Report " + reportId + " deleted...";
	}
	
}
