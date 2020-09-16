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
import storage.springboot.demo.DTO.ProductDTO;
import storage.springboot.demo.DTO.ProductDescriptionDTO;
import storage.springboot.demo.service.ProductService;


@RestController
@RequestMapping("/products")
public class ProductRestController {
	@Autowired private ProductService productService;
	
	//________________________________________________________________________________________________
	
	@GetMapping("")
	public List<ProductDTO> findAll(){
		return productService.findAll();
	}
	
	@GetMapping("/{productId}")
	public ProductDTO getProduct(@PathVariable int productId) {
		return productService.findById(productId);
	}
	
	
	//________________________________________________________________________________________________
	
	
	@PostMapping("")
	public ProductDTO addProduct(@RequestBody ProductDTO dto) {
		return productService.save(dto);
	}
	
	@PutMapping("")
	public ProductDTO updateProduct(@RequestBody ProductDTO dto) {
		return productService.save(dto);
	}
	
	@DeleteMapping("/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		productService.deleteById(productId);
		return "Product " + productId + " deleted...";
	}
	
	//_____________________________________________________________________________________________________________
	
	@PostMapping("/search")
	public ProductDTO findByProductDescription(@RequestBody ProductDescriptionDTO dto) {
		return productService.findProductsByName(dto.getDescription());
	}
}
