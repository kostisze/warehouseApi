package storage.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import storage.springboot.demo.DTO.ShelvesDTO;
import storage.springboot.demo.DTO.StorageDTO;
import storage.springboot.demo.DTO.StorageDTOQuery;
import storage.springboot.demo.dao.StorageRepository;
import storage.springboot.demo.entity.QProduct;
import storage.springboot.demo.entity.QShelves;
import storage.springboot.demo.entity.QStorage;
import storage.springboot.demo.entity.Shelves;
import storage.springboot.demo.entity.Storage;

@Service
public class StorageServiceImpl implements StorageService {
	@Autowired private StorageRepository storageRepository;
	
	//_______________________________________________________________________________________________________________________________________
	// Projections and criteria....
	@PersistenceContext private EntityManager em;
	private static QStorage qStorage = QStorage.storage;
	private static QShelves qShelves = QShelves.shelves;
	private static QProduct qProduct = QProduct.product;
	
	private FactoryExpression<StorageDTOQuery> storageDtoProjection(){
		return Projections.bean(StorageDTOQuery.class, qStorage.id, 
				qStorage.description, qShelves.id.as("shelvesId"), qProduct.description.as("productName"));
	}
	
	private Predicate predicate(String name){
	    BooleanBuilder predicate = new BooleanBuilder();
	    
	    if(!StringUtils.isEmpty(name)) {
	    	predicate.and(qStorage.description.containsIgnoreCase(name));
	    }
	    
	    return predicate;
	}
	//_______________________________________________________________________________________________________________________________________
	
	
	@Override
	public List<StorageDTO> findAll() {
		return convertToDto(storageRepository.findAll());
	}

	@Override
	public StorageDTO findById(int theId) {
		Optional<Storage> result = storageRepository.findById(theId);
		Storage theStorage = null;
		if(result.isPresent()) {
			theStorage = result.get();
		}else {
			throw new RuntimeException("Couldn't find storage " + theId +".......");
		}
		return convertToDto(theStorage);
	}

	@Override
	public Storage save(Storage theStorage) {
		return storageRepository.save(theStorage);
	}

	
	@Override
	public void deleteById(int theId) {
		findById(theId);
		storageRepository.deleteById(theId);
	}

	@Override
	public StorageDTO save(StorageDTO dto) {
		Storage storage = convertToEntity(dto);
		storage = storageRepository.save(storage);
		return convertToDto(storage);
	}

	
	//_______________________________________________________________________________________________________________________________________
	@Override
	public List<StorageDTOQuery> findByDesc(String name) {
		JPAQuery<StorageDTOQuery> query =  new JPAQuery<>(em);
		
		List<StorageDTOQuery> dto = query.select(storageDtoProjection()).
				from(qStorage).leftJoin(qStorage.shelves, qShelves).
				leftJoin(qShelves.product, qProduct).where(predicate(name)).fetch();
		return dto;
	}
	
	//_______________________________________________________________________________________________________________________________________
	//Converter StorageDTO
	public Storage convertToEntity(StorageDTO dto) {
		Storage entity = null;
		Integer id = dto.getId();
		
		if(id != null) {
			entity = storageRepository.findById(id).orElse(null);
		}
		if(entity==null) {
			entity = new Storage();
		}
		BeanUtils.copyProperties(dto, entity);
		
		return entity;
	}
	
	public StorageDTO convertToDto(Storage entity) {
		StorageDTO dto = new StorageDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		
		if(entity.getShelves() != null) {
			List<ShelvesDTO> shelvesList = new ArrayList<ShelvesDTO>();
			ShelfServiceImpl conv = new ShelfServiceImpl();
			for(Shelves s: entity.getShelves()) {
				ShelvesDTO dtoShelf = new ShelvesDTO();
				dtoShelf = conv.convertToDto(s);
				shelvesList.add(dtoShelf);
			}
			dto.setShelves(shelvesList);
		}
		return dto;
	}
	
	public List<StorageDTO> convertToDto(List<Storage> entities){
		List<StorageDTO> dtoList = new ArrayList<StorageDTO>();
		for(Storage entity: entities) {
			StorageDTO dto = convertToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}
	//_______________________________________________________________________________________________________________________________________
}
