package ua.flibberty.wysiwyg;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
@ComponentScan({
        "ua.flibberty.wysiwyg.application",
        "ua.flibberty.wysiwyg.persistence",
        "ua.flibberty.wysiwyg.demo"
})

@EnableTransactionManagement
@PropertySource("classpath:db.properties")
public class SpringPersistenceConfig {

  @Inject
  Environment env;

  @Bean
  public DataSource dataSource() {
    final HikariConfig hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl("jdbc:h2:mem:wysiwyg_db;DB_CLOSE_DELAY=-1");
    hikariConfig.setAutoCommit(env.getProperty("hikari.autoCommit", Boolean.class));
    hikariConfig.setConnectionTimeout(env.getProperty("hikari.connectionTimeout", Long.class));

    return new HikariDataSource(hikariConfig);
  }

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("ua.flibberty.wysiwyg.domain");
    sessionFactory.setConfigLocation(new ClassPathResource("hibernate_spring.cfg.xml"));

    return sessionFactory;
  }

  @Bean
  public PlatformTransactionManager txManager() {
    return new HibernateTransactionManager(sessionFactory().getObject());
  }
}
