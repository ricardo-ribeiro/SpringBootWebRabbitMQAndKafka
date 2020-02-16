package springboot.config;

import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracesConfiguration {
    @Bean
    InMemoryHttpTraceRepository getInMemHttpTraceRepository(){
        return new InMemoryHttpTraceRepository();
    }
}
