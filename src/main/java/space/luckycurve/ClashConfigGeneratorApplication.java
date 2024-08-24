package space.luckycurve;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Slf4j
@SpringBootApplication
public class ClashConfigGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClashConfigGeneratorApplication.class, args);
    }
}
