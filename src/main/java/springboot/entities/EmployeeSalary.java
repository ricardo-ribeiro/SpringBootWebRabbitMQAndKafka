package springboot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class EmployeeSalary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String uuid;
    public String name;
    public String lastname;
    public Date dob;
    public String phoneNumber1;
    public String phoneNumber2;
    public String email1;
    public String email2;



    public EmployeeSalary() {
        uuid = UUID.randomUUID().toString();
    }


}
