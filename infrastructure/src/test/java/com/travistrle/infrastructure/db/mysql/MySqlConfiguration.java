package com.travistrle.infrastructure.db.mysql;

import com.travistrle.core.adapters.auto.AutoRepository;
import com.travistrle.infrastructure.db.mysql.auto.AutoRepositoryImpl;
import com.travistrle.infrastructure.db.mysql.auto.AutoRepositoryMySql;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.travistrle.infrastructure")
@EnableJpaRepositories(basePackages = "com.travistrle.infrastructure.db.mysql")
@EnableTransactionManagement
@EnableAutoConfiguration
public class MySqlConfiguration {

  @Bean
  public AutoRepository provideAutoRepositoryImpl(AutoRepositoryMySql repository) {
    return new AutoRepositoryImpl(repository);
  }

  /**
   * provide datasource.
   *
   * @return {@link DataSource}
   */
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .generateUniqueName(true)
        .setType(EmbeddedDatabaseType.H2)
        .setScriptEncoding("UTF-8")
        .ignoreFailedDrops(true)
        .build();
  }

  /**
   * provide jpa vendor adapter.
   *
   * @return {@link JpaVendorAdapter}
   */
  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setShowSql(true);
    adapter.setGenerateDdl(true);
    adapter.setDatabase(Database.H2);
    adapter.setDatabasePlatform("org.hibernate.dialect.H2Dialect");
    return adapter;
  }

  /**
   * provide entity manager factory.
   *
   * @param dataSource {@link DataSource}
   * @param jpaVendorAdapter {@link JpaVendorAdapter}
   * @return {@link LocalContainerEntityManagerFactoryBean}
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {

    Properties props = new Properties();
    props.setProperty("hibernate.format_sql", String.valueOf(true));

    LocalContainerEntityManagerFactoryBean emf =
        new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(dataSource);
    emf.setPackagesToScan("com.travistrle.infrastructure.db.mysql");
    emf.setJpaVendorAdapter(jpaVendorAdapter);
    emf.setJpaProperties(props);

    return emf;
  }

  /**
   * provide transaction manager.
   *
   * @param emf {@link EntityManagerFactory}
   * @return {@link PlatformTransactionManager}
   */
  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  /**
   * provide persistence translation.
   *
   * @return {@link BeanPostProcessor}
   */
  @Bean
  public BeanPostProcessor persistenceTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }
}
