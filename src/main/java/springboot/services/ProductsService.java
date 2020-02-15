package springboot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dtos.ProductDTO;
import springboot.entities.Product;
import springboot.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    Logger logger = LoggerFactory.getLogger(ProductsService.class);

    @Autowired
    ProductsRepository productsRepository;

    public Product createProduct(ProductDTO productDTO){
        logger.info("Creating Product {}",productDTO);
        return productsRepository.save(productDTO.toProduct());
    }

    public List<Product> findAll(){
        logger.info("Looking UP for All Products");
        return (List<Product>) productsRepository.findAll();
    }

    public Optional<Product> findById(ProductDTO productDTO){
        return  productsRepository.findById(productDTO.getId());
    }

}
