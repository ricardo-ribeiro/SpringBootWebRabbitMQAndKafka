package springboot.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springboot.entities.Employee;
import springboot.entities.Invoice;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
