package springboot.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.dtos.ProductDTO;
import springboot.entities.Product;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static springboot.entities.ProductTest.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductsServiceTest {

    @MockBean
    JavaMailSender javaMailSender;

    @Autowired
    private ProductsService productsService;

    public static ProductDTO createProductDTO(){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setCategory(Arrays.asList(CATEGORIES));
        productDTO.setDescription(DESCRIPTION);
        productDTO.setName(NAME);
        productDTO.setPrice(PRICE);
        return productDTO;
    }

    public static void assertProductDTO_VS_Product(ProductDTO productDTO, Product product){

        assertTrue(productDTO.getCategory().containsAll(Arrays.asList(product.getCategory())));
        assertEquals(productDTO.getDescription(),product.getDescription());
        assertEquals(productDTO.getName(),product.getName());
        assertEquals(productDTO.getPrice(),product.getPrice());
    }

    @Test
    public void createProduct() {
        ProductDTO productDTO = createProductDTO();

        Product product = productsService.createProduct(productDTO);
        assertProductDTO_VS_Product(productDTO,product);

    }

    @Test
    public void findAll() {
        ProductDTO productDTO = createProductDTO();

        Product product = productsService.createProduct(productDTO);
        assertProductDTO_VS_Product(productDTO,product);

        List<Product> productsServiceList = productsService.findAll();
        assertProductDTO_VS_Product(productDTO,productsServiceList.get(0));

    }

    @Test
    public void findById() {
        ProductDTO productDTO = createProductDTO();

        Product product = productsService.createProduct(productDTO);
        assertProductDTO_VS_Product(productDTO,product);


        Optional<Product> productOptional = productsService.findById(ProductDTO.fromProduct(product));
        assertProductDTO_VS_Product(productDTO,productOptional.get());

    }
}