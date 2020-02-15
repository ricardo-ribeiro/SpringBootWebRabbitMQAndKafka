package springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.entities.Invoice;
import springboot.repositories.InvoicesRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoicingService {

    private InvoicesRepository invoicesRepository;

    InvoicingService(@Autowired InvoicesRepository invoicesRepository){
        this.invoicesRepository = invoicesRepository;
    }

    public Invoice saveInvoice(Invoice invoice){
        return this.invoicesRepository.save(invoice);
    }
    public List<Invoice> getInvoices(){
        return (List<Invoice>) this.invoicesRepository.findAll();
    }
    public Optional<Invoice> getInvoice(int id){
        return this.invoicesRepository.findById(Long.valueOf(id));
    }
}
