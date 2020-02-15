package springboot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String uuid;
    public String companyId;
    public String invoicedCompany;
    public String invoicingCompany;
    public boolean countForVat;
    public String totalVatMap;
    public Double invoiceTotalIncludingVat;
    public Double invoiceTotalExcludingVat;


    public Expense() {
        uuid = UUID.randomUUID().toString();
    }

    public String getTotalVatMap() {
        return totalVatMap;
    }

    public void setTotalVatMap(String totalVatMap) {
        this.totalVatMap = totalVatMap;
    }

    public Double getInvoiceTotalIncludingVat() {
        return invoiceTotalIncludingVat;
    }

    public void setInvoiceTotalIncludingVat(Double invoiceTotalIncludingVat) {
        this.invoiceTotalIncludingVat = invoiceTotalIncludingVat;
    }

    public Double getInvoiceTotalExcludingVat() {
        return invoiceTotalExcludingVat;
    }

    public void setInvoiceTotalExcludingVat(Double invoiceTotalExcludingVat) {
        this.invoiceTotalExcludingVat = invoiceTotalExcludingVat;
    }
}
