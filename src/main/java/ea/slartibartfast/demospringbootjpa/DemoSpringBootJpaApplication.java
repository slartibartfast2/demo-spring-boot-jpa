package ea.slartibartfast.demospringbootjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"ea.slartibartfast.demospringbootjpa.model.entity"})
public class DemoSpringBootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBootJpaApplication.class, args);
    }

}
