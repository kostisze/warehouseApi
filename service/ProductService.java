package storage.springboot.demo.service;

import java.util.List;
import storage.springboot.demo.DTO.ProductDTO;
import storage.springboot.demo.entity.Product;

public interface ProductService {
	
	public List<ProductDTO> findAll();
	public ProductDTO findById(int theId);
	public Product save(Product theProduct);
	public void deleteById(int theId);
	public ProductDTO save(ProductDTO dto);
	public ProductDTO findProductsByName(String name);
	
}
