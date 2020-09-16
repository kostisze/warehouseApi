package storage.springboot.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.querydsl.jpa.impl.JPAQuery;
import storage.springboot.demo.DTO.ProductDTO;
import storage.springboot.demo.dao.ProductRepository;
import storage.springboot.demo.entity.Product;
import storage.springboot.demo.entity.QProduct;

@Service
public class ProductSeviceImpl implements ProductService {
	@Autowired private ProductRepository productRepository;
	@PersistenceContext private EntityManager entityManager;
	
	@Override
	public List<ProductDTO> findAll() {
		return convertToDto(productRepository.findAll());
	}

	@Override
	public ProductDTO findById(int theId) {
		Optional<Product> result = productRepository.findById(theId);
		Product theProduct = null;
		if(result.isPresent()) {
			theProduct = result.get();
		}else {
			throw new RuntimeException("Couldn't find product " + theId +".......");
		}
		return convertToDto(theProduct);
	}

	@Override
	public Product save(Product theProduct) {
		return productRepository.save(theProduct);
	}

	@Override
	public void deleteById(int theid) {
		findById(theid);
		productRepository.deleteById(theid);
	}

	@Override
	public ProductDTO save(ProductDTO dto) {
		Product entity = convertToEntity(dto);
		entity = productRepository.save(entity);
		return convertToDto(entity);
	}

	@Override
	public ProductDTO findProductsByName(String name) {
		QProduct product = QProduct.product;
		JPAQuery<Product> query = new JPAQuery<>(entityManager);
		Product pro = query.from(product).where(product.description.containsIgnoreCase(name)).fetchOne();
		if(pro == null) {
			throw new RuntimeException("Product not found...");
		}
		return convertToDto(pro);
	}
	
	//_______________________________________________________________________________________________________________________________________________________
	// Converter ProductDTO
	public Product convertToEntity(ProductDTO dto) {
		Product entity = null;
		Integer id = dto.getId();
		
		if(id != null) {
			entity = productRepository.findById(id).orElse(null);
		}
		if(entity==null) {
			entity = new Product();
		}
		BeanUtils.copyProperties(dto, entity);
		
		return entity;
	}
	
	public ProductDTO convertToDto(Product entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		dto.setBarcode(entity.getBarcode());
		return dto;
	}
	
	public List<ProductDTO> convertToDto(List<Product> entities){
		List<ProductDTO> dtoList = new ArrayList<ProductDTO>();
		for(Product entity: entities) {
			ProductDTO dto = convertToDto(entity);
			dtoList.add(dto);
		}
		return dtoList;
	}
	//_______________________________________________________________________________________________________________________________________________________
}
