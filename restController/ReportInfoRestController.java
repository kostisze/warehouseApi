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
import storage.springboot.demo.DTO.DateBarcodeDTO;
import storage.springboot.demo.DTO.OutputDTO;
import storage.springboot.demo.DTO.ReportInfoDTO;
import storage.springboot.demo.service.ReportInfoService;

@RestController
@RequestMapping("/reportsInfo")
public class ReportInfoRestController {
	@Autowired private ReportInfoService reportInfoService;
	
	@GetMapping("")
	public List<ReportInfoDTO> findAll(){
		return reportInfoService.findAll();
	}
	
	@GetMapping("/{reportId}")
	public ReportInfoDTO getReport(@PathVariable int reportId) {
		return reportInfoService.findById(reportId); 
	}
	
	//________________________________________________________________________________________________

	@PostMapping("")
	public ReportInfoDTO addReport(@RequestBody ReportInfoDTO report) {
		return reportInfoService.reportInfoPost(report);
	}
	
	@PutMapping("")
	public ReportInfoDTO updateReport(@RequestBody ReportInfoDTO report) {
		return reportInfoService.reportInfoPut(report);
	}
	
	@DeleteMapping("/{reportId}")
	public String deleteReport(@PathVariable int reportId) {
		reportInfoService.deleteById(reportId);
		return "Report Infos " + reportId + " deleted...";
	}
	
	//_______________________________________________________________________________________________________________
	
	@GetMapping("/input")
	public List<OutputDTO> calculate(@RequestBody DateBarcodeDTO dto){
		return reportInfoService.calculate(dto);
	}
	
	@GetMapping("/input2")
	public List<OutputDTO> calculate2(@RequestBody DateBarcodeDTO dto){
		return reportInfoService.calculate2(dto);
	}

}
