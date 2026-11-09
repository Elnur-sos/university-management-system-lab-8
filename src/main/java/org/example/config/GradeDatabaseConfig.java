
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
        basePackages = "org.example.grade.repository",
        entityManagerFactoryRef = "gradeEntityManagerFactory",
        transactionManagerRef = "gradeTransactionManager"
)
public class GradeDatabaseConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.grade")
    public DataSourceProperties gradeDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource gradeDataSource() {
        return gradeDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean(name = "gradeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean gradeEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        return builder
                .dataSource(gradeDataSource())
                .packages("org.example.grade.entity")
                .persistenceUnit("grade")
                .build();
    }

    @Bean(name = "gradeTransactionManager")
    public PlatformTransactionManager gradeTransactionManager(
            @Qualifier("gradeEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {

        return new JpaTransactionManager(entityManagerFactory);
    }
}
