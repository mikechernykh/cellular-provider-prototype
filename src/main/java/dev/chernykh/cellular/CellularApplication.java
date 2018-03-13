package dev.chernykh.cellular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:notice.properties")
public class CellularApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(CellularApplication.class, args);
    }
}
