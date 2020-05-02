package lt.pauliusk.luminor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LuminorApplication {
    public static void main(String[] args) {
        SpringApplication.run(LuminorApplication.class, args);
    }
}
