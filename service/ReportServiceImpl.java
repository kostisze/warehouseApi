package storage.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.springboot.demo.DTO.ReportDTO;
import storage.springboot.demo.DTO.ReportInfoDTO;
import storage.springboot.demo.dao.ProductRepository;
import storage.springboot.demo.dao.ReportInfoRepository;
import storage.springboot.demo.dao.ReportRepository;
import storage.springboot.demo.dao.ShelfRepository;
import storage.springboot.demo.dao.StorageRepository;
import storage.springboot.demo.entity.Product;
import storage.springboot.demo.entity.Report;
import storage.springboot.demo.entity.ReportInfo;
import storage.springboot.demo.entity.Shelves;
import storage.springboot.demo.entity.Storage;

@Service
public class ReportServiceImpl implements ReportService{
	@Autowired private ReportRepository reportRepository;
	@Autowired private ReportInfoRepository reportInfoRepository;
	@Autowired private ShelfRepository shelfRepository;
	@Autowired private StorageRepository storageRepository;
	@Autowired private ProductRepository productRepository;
	@Autowired private ReportInfoServiceImpl reportInfoConv;
	
	@Override
	public List<ReportDTO> findAll() {
		return convertToDto(reportRepository.findAll());
	}

	@Override
	public ReportDTO findById(int theId) {
		Optional<Report> result = reportRepository.findById(theId);
		Report theReport = null;
		if(result.isPresent()) {
			theReport = result.get();
		}else {
			throw new RuntimeException("Couldn't find the Report " + theId + ".......");
		}
		return convertToDto(theReport);
	}

	@Override
	public Report save(Report theReport) {
		return reportRepository.save(theReport);
	}

	@Override
	public void deleteById(int theId) {
		findById(theId);
		reportRepository.deleteById(theId);
	}

	@Override
	public ReportDTO save(ReportDTO dto) {
		Report entity = convertToEntity(dto);
		entity = reportRepository.save(entity);
		return convertToDto(entity);
	}

	@Override
	public Integer checkQuantity(ReportDTO dto) {
		List<ReportInfoDTO> reportsInfo = reportInfoConv.convertToDto(reportInfoRepository.findAll());
		Integer total;
		Integer amount = 0;
		for(ReportInfoDTO r: reportsInfo) {
			if(r.getReports() != null) {
				
				for(ReportDTO r2:r.getReports()) {
					
					if(r2.getShelvesId() == dto.getShelvesId()) {
						if(r.isRefill()) {
							amount = r2.getQuantity() + amount;
						}else {
							amount = amount - r2.getQuantity();
						}
					}	
				}
			}
		}
		
		ReportInfo entity = null;
		boolean refil;
		Integer repId = dto.getReportId();
		if(repId != null) {
			entity = reportInfoRepository.findById(repId).orElse(null);
		}
		if(entity == null) {
			throw new RuntimeException("Must add reportInfo id...");
		}else {
			refil = entity.isRefill();
		}
		if(!refil) {
			total = amount - dto.getQuantity();
		}else {
			total = amount +dto.getQuantity();
		}
		
		System.out.println(total);
		if(total < 0 ) {
			throw new RuntimeException("Value out of bounds....");
		}
		return total;
	}

	@Override
	public void checkReport(ReportDTO dto) {
		
		Shelves shelf = null;
		Product product = null;
		Integer shelfId = dto.getShelvesId();
		Integer productId = dto.getProductId();
		
		if(shelfId != null) {
			shelf = shelfRepository.findById(shelfId).orElse(null);
		}else {
			throw new RuntimeException("Add shelf id...");
		}
		if(productId != null) {
			product = productRepository.findById(productId).orElse(null);
		}else {
			throw new RuntimeException("Add product id...");
		}
		if(shelf.getProduct().getId() != dto.getProductId()) {
			throw new RuntimeException("Shelf id on your report must agree with the "
					+"valid product id....");
		}
		if(shelf.getStorage().getId() != dto.getStorageId()) {
			throw new RuntimeException("Shelf id must agree on the valid storage id...");
		}
		if(!product.getBarcode().equals(dto.getBarcode())) {
			throw new RuntimeException("Product barcode must agree with product's id...");
		}
		
	}

	//____________________________________________________________________________________________________________________________________________________________-
	//Converter ReportDTO
	public Report convertToEntity(ReportDTO dto) {
		Report reportEntity = null;
		Shelves shelfEntity = null;
		Product productEntity = null;
		Storage storageEntity = null;
		ReportInfo reportInfoEntity = null;
		
		Integer id = dto.getId();
		Integer reportId = dto.getReportId();
		Integer productId = dto.getProductId();
		Integer storageId = dto.getStorageId();
		Integer shelfId = dto.getShelvesId();
		
		if(id != null) {
			reportEntity = reportRepository.findById(id).orElse(null);
		}
		if(reportEntity == null) {
			reportEntity = new Report();
		}
		if(reportId != null) {
			reportInfoEntity = reportInfoRepository.findById(reportId).orElse(null);
		}
		if(productId != null) {
			productEntity = productRepository.findById(productId).orElse(null);
		}
		if(storageId != null) {
			storageEntity = storageRepository.findById(storageId).orElse(null);
		}
		if(shelfId != null) {
			shelfEntity = shelfRepository.findById(shelfId).orElse(null);
		}
		
		reportEntity.setReportInfo(reportInfoEntity);
		reportEntity.setProduct_report(productEntity);
		reportEntity.setStorage_report(storageEntity);
		reportEntity.setShelves_report(shelfEntity);
		reportEntity.setQuantity(dto.getQuantity());
		return reportEntity;
	}
	
	public ReportDTO convertToDto(Report entity) {
		ReportDTO dto = new ReportDTO();
		dto.setId(entity.getId());
		dto.setQuantity(entity.getQuantity());
		dto.setBarcode(entity.getProduct_report().getBarcode());

		if(entity.getStorage_report() != null) {
			dto.setStorageId(entity.getStorage_report().getId());
		}
		
		if(entity.getProduct_report() != null) {
			dto.setShelvesId(entity.getShelves_report().getId());
		}
		
		if(entity.getShelves_report() != null) {
			dto.setProductId(entity.getProduct_report().getId());	
		}
		if(entity.getReportInfo() != null) {
			dto.setReportId(entity.getReportInfo().getId());
		}
		return dto;
	}
	
	public List<ReportDTO> convertToDto(List<Report> entities){
		List<ReportDTO> dtoList = new ArrayList<ReportDTO>();
		if(entities != null) {
			for(Report entity: entities) {
				dtoList.add(convertToDto(entity));
			}
		}
		return dtoList;
	}
	//_________________________________________________________________________________________________________________________
}
