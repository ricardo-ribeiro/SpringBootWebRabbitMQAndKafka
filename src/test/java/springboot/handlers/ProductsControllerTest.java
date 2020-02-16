package springboot.handlers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springboot.dtos.ProductDTO;
import springboot.entities.Product;
import springboot.services.ProductsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static springboot.services.ProductsServiceTest.createProductDTO;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ProductsControllerTest {

    @MockBean
    private ProductsService productsService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JavaMailSender javaMailSender;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDTO testProduct;

    @Before
    public void setUp() throws Exception {
        testProduct = createProductDTO();

        given(productsService.createProduct(any()))
                .willReturn(testProduct.toProduct());

        given(productsService.findById(any()))
                .willReturn(Optional.of(testProduct.toProduct()));

        given(productsService.findAll())
                .willReturn(new ArrayList<Product>(){{add(testProduct.toProduct());}});
        given(productsService.findAll())
                .willReturn(new ArrayList<Product>(){{add(testProduct.toProduct());}});


    }


    @Test
    public void createNewProduct() throws Exception {
        ProductDTO tProduct = createProductDTO();
        tProduct.setId(1L);
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/product")
                        .header("Authorization","Basic cm9vdDpyb290")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createProductDTO())))
                .andDo(
                        print()
                )
                .andExpect(
                        status()
                                .isCreated()
                )
                .andExpect(
                        content()
                                .json(
                                        objectMapper
                                                .writeValueAsString(
                                                        tProduct
                                                )
                                        ,false)
                );
    }

    @Test
    public void getProducts_OK() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/products").header("Authorization","Basic cm9vdDpyb290"))
                .andDo(
                        print()
                )
                .andExpect(
                        status()
                                .isOk()
                )
                .andExpect(
                        content()
                                .json(
                                        objectMapper
                                                .writeValueAsString(
                                                        new ArrayList<ProductDTO>(){{
                                                            add(testProduct);
                                                        }})
                               )
                );
    }

    @Test
    public void getProduct_1234_NOT_FOUND() throws Exception {
        given(productsService.findById(any())).willReturn(Optional.ofNullable(null));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/products/1234").header("Authorization","Basic cm9vdDpyb290"))
                .andDo(
                        print()
                )
                .andExpect(
                        status()
                                .isNotFound()
                )
                .andExpect(
                        content()
                                .json(
                                        objectMapper
                                                .writeValueAsString(
                                                            new HashMap<String,String>(){{put("error","Product Not Found");}}
                                                )
                                )
                );
    }

    @Test
    public void getProduct_1_OK() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/products/1").header("Authorization","Basic cm9vdDpyb290"))
                .andDo(
                        print()
                )
                .andExpect(
                        status()
                                .isOk()
                )
                .andExpect(
                        content()
                                .json(
                                        objectMapper
                                                .writeValueAsString(
                                                            testProduct
                                                )
                                )
                );
    }


}