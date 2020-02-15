package springboot.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.dtos.ProductDTO;
import springboot.exceptions.ProductNotFound;
import springboot.services.ProductsService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping(path = "/products")
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return new ResponseEntity<List<ProductDTO>>(
                productsService
                        .findAll()
                        .stream()
                        .map(product -> ProductDTO.fromProduct(product))
                        .collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable(name = "id") Long id) throws ProductNotFound {
        ProductDTO idDTO = new ProductDTO();
        idDTO.setId(id);
        return new ResponseEntity<ProductDTO>(ProductDTO.fromProduct(productsService.findById(idDTO).orElseThrow(()->new ProductNotFound())),HttpStatus.OK);
    }

    @PostMapping(path = "/product")
    public ResponseEntity<ProductDTO> createNewProduct(@RequestBody ProductDTO productDTO){

        return new ResponseEntity<ProductDTO>(
                ProductDTO
                        .fromProduct(
                                productsService
                                        .createProduct(productDTO)
                        ),
                HttpStatus.CREATED
        );
    }
}
