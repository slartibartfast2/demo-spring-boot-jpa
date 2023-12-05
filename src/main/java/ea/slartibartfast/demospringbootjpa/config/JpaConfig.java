package ea.slartibartfast.demospringbootjpa.config;

import ea.slartibartfast.demospringbootjpa.repository.BaseNaturalIdRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "ea.slartibartfast.demospringbootjpa.repository", repositoryBaseClass = BaseNaturalIdRepositoryImpl.class)
@Configuration
public class JpaConfig {
}
