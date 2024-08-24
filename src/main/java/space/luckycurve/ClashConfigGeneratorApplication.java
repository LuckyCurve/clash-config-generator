package space.luckycurve;

import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ClashConfigGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClashConfigGeneratorApplication.class, args);
    }

    public static Reflections getRootReflections() {
        String packageName = ClashConfigGeneratorApplication.class.getPackage().getName();
        return new Reflections(packageName);
    }
}
