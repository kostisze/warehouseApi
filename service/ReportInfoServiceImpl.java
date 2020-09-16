package storage.springboot.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.springboot.demo.DTO.DateBarcodeDTO;
import storage.springboot.demo.DTO.OutputDTO;
import storage.springboot.demo.DTO.ReportDTO;
import storage.springboot.demo.DTO.ReportInfoDTO;
import storage.springboot.demo.dao.ReportInfoRepository;
import storage.springboot.demo.entity.ReportInfo;
import storage.springboot.demo.restController.ReportRestController;

@Service
public class ReportInfoServiceImpl implements ReportInfoService{
	@Autowired private ReportInfoRepository reportInfoRepository;
	@Autowired private ReportRestController reportController;
	
	@Override
	public List<ReportInfoDTO> findAll() {
		return convertToDto(reportInfoRepository.findAll());
	}

	@Override
	public ReportInfoDTO findById(int theId) {
		Optional<ReportInfo> result = reportInfoRepository.findById(theId);
		ReportInfo reportInfo = null;
		if(result.isPresent()) {
			reportInfo = result.get();
		}else {
			throw new RuntimeException("Couldn't find report " + theId +".......");
		}
		return convertToDto(reportInfo);
	}

	@Override
	public ReportInfo save(ReportInfo theReport) {
		return reportInfoRepository.save(theReport);
	}

	@Override
	public void deleteById(int theId) {
		findById(theId);
		reportInfoRepository.deleteById(theId);
	}

	@Override
	public ReportInfoDTO save(ReportInfoDTO dto) {
		ReportInfo entity = convertToEntity(dto);
		entity = reportInfoRepository.save(entity);
		return convertToDto(entity);
	}
	
	@Override
	public ReportInfoDTO reportInfoPost(ReportInfoDTO dto) {
		List<ReportDTO> reports = dto.getReports();
		dto.setReports(null);
		Integer id = save(dto).getId();
		
		List<ReportDTO> list = new ArrayList<ReportDTO>();
		if(reports !=null) {
			for(ReportDTO report: reports) {
				list.add(reportController.addReport(report));
			}
		}
		ReportInfoDTO reportInfo = findById(id);
		reportInfo.setReports(list);
		return reportInfo;
	}
	
	@Override
	public ReportInfoDTO reportInfoPut(ReportInfoDTO dto) {
		List<ReportDTO> reports = dto.getReports();
		dto.setReports(null);
		Integer id = save(dto).getId();
		
		List<ReportDTO> list = new ArrayList<ReportDTO>();
		if(reports !=null) {
			for(ReportDTO report: reports) {
				list.add(reportController.updateReport(report));
			}
		}
		ReportInfoDTO reportInfo = findById(id);
		reportInfo.setReports(list);
		return reportInfo;
	}
	
	//_______________________________________________________________________________________________________________
	@Override
	public List<OutputDTO> calculate(DateBarcodeDTO dto) {
		List<ReportInfoDTO> reports = convertToDto(reportInfoRepository.calculateReport(dto.getDate()));
		
		List<ReportInfoDTO> reportsInfo = new ArrayList<ReportInfoDTO>();
		for(ReportInfoDTO r: reports) {
			ReportInfoDTO tempReportInfo = new ReportInfoDTO();
			boolean notEmpty = false;
			
			List<ReportDTO> reports2 = new ArrayList<ReportDTO>();
			for(ReportDTO rep:r.getReports()) {
				if(rep.getBarcode().equals(dto.getBarcode())) {
					notEmpty = true;
					reports2.add(rep);
				}
			}
			if(notEmpty == true) {
				BeanUtils.copyProperties(r, tempReportInfo);
				tempReportInfo.setReports(reports2);
				reportsInfo.add(tempReportInfo);
			}
		}
		//______________________________________________________________
		Set<Integer> shelfSet = new HashSet<Integer>();
		for(ReportInfoDTO r1: reportsInfo) {
			for(ReportDTO r2:r1.getReports()) {
				shelfSet.add(r2.getShelvesId());
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		//______________________________________________________________
		for(Integer s:shelfSet) {
			Integer sum = 0;
			
			for(ReportInfoDTO r3:reportsInfo) {
				for(ReportDTO r4:r3.getReports()) {
					
					if(s == r4.getShelvesId()) {
						if(r3.isRefill() == true) {
							sum = sum + r4.getQuantity();
						}else {
							sum = sum - r4.getQuantity();
						}
					}
					
				}
			}
			map.put(s, sum);
		}
		String result = "";
		for(Integer key:map.keySet()) {
			result = result + "\nShelf id: " + key + ", quantity: " + map.get(key);
		}
		System.out.println(result);
		List<OutputDTO> outputResult = new ArrayList<OutputDTO>();
		for(Integer key:map.keySet()) {
			OutputDTO output = new OutputDTO();
			output.setShelfId(key);
			output.setQuantity(map.get(key));
			
			outputResult.add(output);
		}
		return outputResult;
	}
	
	@Override
	public List<OutputDTO> calculate2(DateBarcodeDTO dto) {
		List<ReportInfoDTO> listInfoDto = convertToDto(reportInfoRepository.calculateReport2(dto.getDate(), dto.getBarcode()));
		
		List<ReportInfoDTO> cleanReportsInfo = new ArrayList<ReportInfoDTO>();
		for(ReportInfoDTO infoDto: listInfoDto) {
			ReportInfoDTO tempReportInfo = new ReportInfoDTO();
			boolean notEmpty = false;
			
			List<ReportDTO> reports = new ArrayList<ReportDTO>();
			for(ReportDTO rep:infoDto.getReports()) {
				if(rep.getBarcode().equals(dto.getBarcode())) {
					notEmpty = true;
					reports.add(rep);
				}
			}
			if(notEmpty == true) {
				BeanUtils.copyProperties(infoDto, tempReportInfo);
				tempReportInfo.setReports(reports);
				cleanReportsInfo.add(tempReportInfo);
			}	
		}
		Set<Integer> shelfSet = new HashSet<Integer>();
		for(ReportInfoDTO r1: cleanReportsInfo) {
			for(ReportDTO r2:r1.getReports()) {
				shelfSet.add(r2.getShelvesId());
			}
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(Integer s:shelfSet) {
			Integer sum = 0;
			
			for(ReportInfoDTO r3:cleanReportsInfo) {
				for(ReportDTO r4:r3.getReports()) {
					
					if(s == r4.getShelvesId()) {
						if(r3.isRefill() == true) {
							sum = sum + r4.getQuantity();
						}else {
							sum = sum - r4.getQuantity();
						}
					}
					
				}
			}
			map.put(s, sum);
		}
		String result = "";
		for(Integer key:map.keySet()) {
			result = result + "\nShelf id: " + key + ", quantity: " + map.get(key);
		}
		System.out.println(result);
		List<OutputDTO> outputResult = new ArrayList<OutputDTO>();
		for(Integer key:map.keySet()) {
			OutputDTO output = new OutputDTO();
			output.setShelfId(key);
			output.setQuantity(map.get(key));
			
			outputResult.add(output);
		}
		
		return outputResult;
	}
	
	//________________________________________________________________________________________________________________
	
	//________________________________________________________________________________________________________________
	//Converter ReportInfoDTO
	public ReportInfo convertToEntity(ReportInfoDTO dto) {
		ReportInfo reportInfoEntity = null;
		Integer id = dto.getId();
		if(id != null) {
			reportInfoEntity = reportInfoRepository.findById(id).orElse(null);
		}
		if(reportInfoEntity == null) {
			reportInfoEntity = new ReportInfo();
		}
		BeanUtils.copyProperties(dto, reportInfoEntity);
		return reportInfoEntity;
	}
	
	public ReportInfoDTO convertToDto(ReportInfo entity) {
		ReportInfoDTO dto = new ReportInfoDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setDate(entity.getDate());
		dto.setName(entity.getName());
		dto.setRefill(entity.isRefill());
		
		if(entity.getReports() != null) {
			ReportServiceImpl conv = new ReportServiceImpl();
			List<ReportDTO> reportsDto = new ArrayList<ReportDTO>();
			reportsDto = conv.convertToDto(entity.getReports());
			dto.setReports(reportsDto);
		}
		return dto;
	}
	
	public List<ReportInfoDTO> convertToDto(List<ReportInfo> entities) {
		List<ReportInfoDTO> dtoList = new ArrayList<ReportInfoDTO>();
		for(ReportInfo entity: entities) {
			dtoList.add(convertToDto(entity));
		}
		return dtoList;
	}
	//________________________________________________________________________________________________________________


}
