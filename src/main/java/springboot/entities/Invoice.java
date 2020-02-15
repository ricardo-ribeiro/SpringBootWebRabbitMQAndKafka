package springboot.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long id;
    public String uuid;
    @CreationTimestamp
    public Timestamp creationTimestamp;
    public Date creationDate;
    @UpdateTimestamp
    public Timestamp updatedDate;
    public Date dueDate;
    public String companyId;
    public String invoiceItems;
    public String invoicingCompany;
    public String invoicedCompany;
    public String totalVatMap;
    public Double invoiceTotalIncludingVat;
    public Double invoiceTotalExcludingVat;


    public Invoice() {
        uuid = UUID.randomUUID().toString();
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(String invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getInvoicingCompany() {
        return invoicingCompany;
    }

    public void setInvoicingCompany(String invoicingCompany) {
        this.invoicingCompany = invoicingCompany;
    }

    public String getInvoicedCompany() {
        return invoicedCompany;
    }

    public void setInvoicedCompany(String invoicedCompany) {
        this.invoicedCompany = invoicedCompany;
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
