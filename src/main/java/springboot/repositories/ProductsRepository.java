package springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot.entities.Product;

@Repository
public interface ProductsRepository extends CrudRepository<Product,Long> {

}
