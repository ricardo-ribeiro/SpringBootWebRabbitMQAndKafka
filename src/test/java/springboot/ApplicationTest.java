package springboot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.dtos.ProductDTO;
import springboot.entities.Product;
import springboot.repositories.ProductsRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static springboot.entities.ProductTest.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {


    @Autowired
    private ProductsRepository productsRepository;

    @Test
    public void shouldCreate10Products(){
        int numberOfProducts = 10;
        for (int i = 1; i <= numberOfProducts; i++) {
            Product testProduct = new Product();
            testProduct.setCategory(CATEGORIES);
            testProduct.setDescription(DESCRIPTION);
            testProduct.setName(NAME);
            testProduct.setPrice(PRICE);
            productsRepository.save(testProduct);
        }

        List<Product> products = (List<Product>) productsRepository.findAll();

        products.stream().forEach(prod -> {
            assertTrue(ProductDTO.fromProduct(prod).getCategory().containsAll(Arrays.asList(CATEGORIES)));
            assertEquals(DESCRIPTION,prod.getDescription());
            assertEquals(NAME,prod.getName());
            assertEquals(PRICE,prod.getPrice());
        });

    }


}