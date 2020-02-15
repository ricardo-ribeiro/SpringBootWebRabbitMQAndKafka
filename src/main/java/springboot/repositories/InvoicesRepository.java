package springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot.entities.Invoice;

@Repository
public interface InvoicesRepository extends CrudRepository<Invoice,Long> {

}
