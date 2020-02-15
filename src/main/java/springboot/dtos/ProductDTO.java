package springboot.dtos;

import springboot.entities.Product;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProductDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private String name;
    @NotEmpty
    private List<String> category;
    @NotNull
    private Double price;
    private Date createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Product toProduct(){
        Product product = new Product();
        product.setId(this.getId());
        product.setPrice(this.getPrice());
        product.setDescription(this.getDescription());
        product.setCategory(this.getCategory().toArray(new String[0]));
        product.setName(this.getName());
        product.setCreatedAt((java.sql.Date) this.getCreatedAt());
        return product;
    }
    public static ProductDTO fromProduct(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setCategory(Arrays.asList(product.getCategory()));
        productDTO.setName(product.getName());
        return  productDTO;
    }
}
