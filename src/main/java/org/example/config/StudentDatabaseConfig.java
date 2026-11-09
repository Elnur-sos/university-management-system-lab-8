
package org.example.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "org.example.student.repository",
        entityManagerFactoryRef = "studentEntityManagerFactory",
        transactionManagerRef = "studentTransactionManager"
)
public class StudentDatabaseConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.student")
    public DataSourceProperties studentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource studentDataSource() {
        return studentDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Primary
    @Bean(name = "studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(studentDataSource())
                .packages("org.example.student.entity")
                .persistenceUnit("student")
                .build();
    }

    @Primary
    @Bean(name = "studentTransactionManager")
    public PlatformTransactionManager studentTransactionManager(
            @Qualifier("studentEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
