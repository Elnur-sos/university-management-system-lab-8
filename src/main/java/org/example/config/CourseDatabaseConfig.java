
package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.example.course.repository",
        entityManagerFactoryRef = "courseEntityManagerFactory",
        transactionManagerRef = "courseTransactionManager"
)
public class CourseDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.course")
    public DataSourceProperties courseDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource courseDataSource() {
        return courseDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "courseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean courseEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(courseDataSource())
                .packages("org.example.course.entity")
                .persistenceUnit("course")
                .build();
    }

    @Bean(name = "courseTransactionManager")
    public PlatformTransactionManager courseTransactionManager(
            @Qualifier("courseEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
