package springboot.handlers;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.entities.Invoice;
import springboot.services.InvoicingService;

import java.io.FileNotFoundException;

@RestController
public class InvoicesHandler {

    private final InvoicingService invoicingService;

    public InvoicesHandler(@Autowired InvoicingService invoicingService){
        this.invoicingService = invoicingService;
    }

    @GetMapping(path = "/invoices")
    public ResponseEntity<ArrayNode> getInvoices() throws FileNotFoundException {
        return new ResponseEntity(invoicingService.getInvoices(),HttpStatus.OK);
    }
    @PostMapping(path = "/invoice", consumes = "application/json",produces = "application/json")
    public ResponseEntity<Invoice> getInvoice(@RequestBody Invoice invoice) throws FileNotFoundException {
        return new ResponseEntity(invoicingService.saveInvoice(invoice),HttpStatus.OK);
    }
    @GetMapping(path = "/invoices/{id}")
    public ResponseEntity<ArrayNode> getInvoice(@PathVariable int id) throws FileNotFoundException {
        return new ResponseEntity(invoicingService.getInvoices(),HttpStatus.OK);
    }


}
