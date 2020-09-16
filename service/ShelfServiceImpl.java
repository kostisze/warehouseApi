package storage.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.FactoryExpression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;

import storage.springboot.demo.DTO.ShelvesDTO;
import storage.springboot.demo.DTO.ShelvesDTOQuery;
import storage.springboot.demo.DTO.PUT.ShelvesPUT;
import storage.springboot.demo.dao.ProductRepository;
import storage.springboot.demo.dao.ShelfRepository;
import storage.springboot.demo.dao.StorageRepository;
import storage.springboot.demo.entity.Product;
import storage.springboot.demo.entity.QProduct;
import storage.springboot.demo.entity.QShelves;
import storage.springboot.demo.entity.Shelves;
import storage.springboot.demo.entity.Storage;

@Service
public class ShelfServiceImpl implements ShelfService {
	@Autowired private ShelfRepository shelfRepository;
	@Autowired private ProductRepository productRepository;
	@Autowired private StorageRepository storageRepository;
	
	
	//_______________________________________________________________________________________________________________________________________
	// Projections and criteria....
	@PersistenceContext private EntityManager em;
	private static QShelves qShelves = QShelves.shelves;
	private static QProduct qProduct = QProduct.product;
	
	private FactoryExpression<ShelvesDTOQuery> storageDtoProjection(){
		return Projections.bean(ShelvesDTOQuery.class, qShelves.id, 
				qProduct.description.as("productName"));
	}
	
	private Predicate predicate(Integer id){
	    BooleanBuilder predicate = new BooleanBuilder();
	    
	    if(id != null) {
	    	predicate.and(qShelves.id.eq(id));
	    }
	    
	    return predicate;
	}
	//_______________________________________________________________________________________________________________________________________
	
	
	@Override
	public List<ShelvesDTO> findAll() {		
		return convertToDto(shelfRepository.findAll());
	}

	@Override
	public ShelvesDTO findById(int theId) {
		Optional<Shelves> result = shelfRepository.findById(theId);
		
		Shelves theShelf = null;
		if(result.isPresent()) {
			theShelf = result.get();
		}else {
			throw new RuntimeException("Couldn't find shelf " + theId +".......");
		}
		return convertToDto(theShelf);
	}

	@Override
	public Shelves save(Shelves theShelf) {
		return shelfRepository.save(theShelf);
	}

	@Override
	public void deleteById(int theId) {
		findById(theId);
		shelfRepository.deleteById(theId);
	}

	@Override
	public ShelvesPUT save(ShelvesPUT dto) {
		Shelves entity = convertToEntity(dto);
		entity = shelfRepository.save(entity);
		return new ShelvesPUT(entity);
	}

	//_______________________________________________________________________________________________________________________________________
	@Override
	public ShelvesDTOQuery searchById(Integer id) {
		JPAQuery<ShelvesDTOQuery> query =  new JPAQuery<>(em);
		
		ShelvesDTOQuery dto = query.select(storageDtoProjection()).
				from(qShelves).leftJoin(qShelves.product, qProduct).
				where(predicate(id)).fetchFirst();
		return dto;
	}
	//_______________________________________________________________________________________________________________________________________
	
	//_______________________________________________________________________________________________________________________________________
	//Converter ShelvesDTO
	public Shelves convertToEntity(ShelvesDTO dto) {
		Shelves entity = null;
		Integer id = dto.getId();
		
		if(id != null) {
			entity = shelfRepository.findById(id).orElse(null);
		}
		if(entity==null) {
			entity = new Shelves();
		}
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}
	
	public ShelvesDTO convertToDto(Shelves entity) {
		ShelvesDTO dto = new ShelvesDTO();
		dto.setId(entity.getId());
		if(entity.getProduct() != null) {
			ProductSeviceImpl c = new ProductSeviceImpl();
			dto.setProduct(c.convertToDto(entity.getProduct()));
		}
		return dto;
	}
	
	public List<ShelvesDTO> convertToDto(List<Shelves> entities){
		List<ShelvesDTO> dtoList = new ArrayList<ShelvesDTO>();
		for(Shelves entity: entities) {
			ShelvesDTO dto = convertToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}
	
	//Converter of ShelvesPUT
	public Shelves convertToEntity(ShelvesPUT dto) {
		Shelves shelfEntity = null;
		Product productEntity = null;
		Storage storageEntity = null;
		
		Integer id = dto.getId();
		Integer productId = dto.getProductId();
		Integer storageId = dto.getStorageId();
		
		if(id != null) {
			shelfEntity = shelfRepository.findById(id).orElse(null);
		}
		if(shelfEntity==null) {
			shelfEntity = new Shelves();
		}
		
		if(productId != null) {
			productEntity = productRepository.findById(productId).orElse(null);
		}
		if(storageId != null) {
			storageEntity = storageRepository.findById(storageId).orElse(null);
		}
		shelfEntity.setProduct(productEntity);
		shelfEntity.setStorage(storageEntity);

		return shelfEntity;
	}
	//_______________________________________________________________________________________________________________________________________
	
	
}
