package springboot.entities;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ProductTest {

    public final static String DESCRIPTION = "Description";
    public final static String NAME = "Name";
    public final static Double PRICE = 1.23;
    public final static String[] CATEGORIES = {"Electronics","Computing"};

    @Test
    public void shouldBuildAProduct() {
        Product product = new Product();

        product.setCategory(CATEGORIES);
        product.setDescription(DESCRIPTION);
        product.setName(NAME);
        product.setPrice(PRICE);

        assertEquals(CATEGORIES,product.getCategory());
        assertEquals(DESCRIPTION,product.getDescription());
        assertEquals(NAME,product.getName());
        assertEquals(PRICE,product.getPrice());
    }

}