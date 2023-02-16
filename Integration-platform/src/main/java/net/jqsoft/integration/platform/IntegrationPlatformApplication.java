package net.jqsoft.integration.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class IntegrationPlatformApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(IntegrationPlatformApplication.class, args);
    }
    
}
