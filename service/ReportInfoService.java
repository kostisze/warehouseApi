package storage.springboot.demo.service;

import java.util.List;
import storage.springboot.demo.DTO.DateBarcodeDTO;
import storage.springboot.demo.DTO.OutputDTO;
import storage.springboot.demo.DTO.ReportInfoDTO;
import storage.springboot.demo.entity.ReportInfo;

public interface ReportInfoService {
	public List<ReportInfoDTO> findAll();
	public ReportInfoDTO findById(int theId);
	public ReportInfo save(ReportInfo theReport);
	public void deleteById(int theId);
	public ReportInfoDTO save(ReportInfoDTO dto);
	public ReportInfoDTO reportInfoPost(ReportInfoDTO dto);
	public ReportInfoDTO reportInfoPut(ReportInfoDTO dto);
	public List<OutputDTO> calculate(DateBarcodeDTO dto);
	public List<OutputDTO> calculate2(DateBarcodeDTO dto);

}
