package springboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import springboot.messaging.KafkaEventChannels;
import springboot.messaging.PaymentMessagingChannel;
import springboot.services.InvoicingService;

@SpringBootApplication
@EnableBinding({PaymentMessagingChannel.class, KafkaEventChannels.class})
public class Application {

    @Autowired
    private JavaMailSender javaMailSender;

    @Bean
    CommandLineRunner commandLineRunner(
            @Autowired InvoicingService invoicingService,
            @Autowired ObjectMapper objectMapper
    ){
        return args -> {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("ExampleApplication@example.com");
            msg.setTo("to_1@gmail.com", "to_2@gmail.com", "to_3@yahoo.com");

            msg.setSubject("Testing from Spring Boot");
            msg.setText("Hello World \n Spring Boot Email");

            javaMailSender.send(msg);
//
//            /**
//             * Creating a Invoice
//             */
//            Invoice invoice = new Invoice();
//
//
//            invoice.setInvoiceItems(
//                    objectMapper
//                            .createArrayNode()
//                            .add(
//                                    objectMapper
//                                            .createObjectNode()
//                                            .put("id",0)
//                                            .put("VAT",20.0)
//                                            .put("price",350.0)
//                                            .put("quantity",21)
//                                            .put("description","The Item Description")
//                            ).toString()
//            );
//
//            invoice.setCreationDate(Date.valueOf(LocalDate.now()));
//            invoice.setDueDate(Date.valueOf(LocalDate.now().plusDays(30)));
//
//            invoice.setCompanyId("atomicleap");
//            invoice.setInvoicedCompany(objectMapper.writeValueAsString(new HashMap<Double, Double>(){{
//                        put(20.0,144434.4);
//                        put(13.4,0.0);
//                        put(0.4,1222.0);
//                    }})
//            );
//            invoice.setTotalVatMap(objectMapper.writeValueAsString(new HashMap<Double, Double>(){{
//                        put(20.0,144434.4);
//                        put(13.4,0.0);
//                        put(0.4,1222.0);
//                    }})
//            );
//            invoice.setInvoiceTotalExcludingVat(1444.99999);
//            invoice.setInvoiceTotalIncludingVat(3444.99999);
//
//            invoicingService.saveInvoice(invoice);
//


        };
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class);

    }
}
