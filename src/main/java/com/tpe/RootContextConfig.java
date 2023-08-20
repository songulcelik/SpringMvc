package com.tpe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;
//9 kodlari acik acik yazmamak icin properties uzantili dosya olustururz. java aktindaki resourcesde olusturuyoruz
@Configuration
@PropertySource("classpath:db.properties")//properti dosyasinin yerini
public class RootContextConfig {

    //10
    @Autowired
    private Environment environment;//propertilerdeki degiskenlere ulasmamizi saglar


    //11
    @Bean//hibernate ile DB ye erişim için Session Factory lazım. LocalSessionFactoryBean sf dondurcek sinif
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());//JDBC ile ilgili özellikler
        sessionFactory.setHibernateProperties(hibernateProperties());//hibernate ile ilgili öz.
        sessionFactory.setPackagesToScan(new String[]{"com.tpe.domain"});// hb entilerden tablo olusturuyor. entitylerin packageını gösterdik
        return sessionFactory;
    }

    //12 ;//JDBC ile ilgili özellikler
    //neden private sadece bu classta parametre olarak girmek icin olusturduk

    private DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName" ));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }

    //13 //hibernate ile ilgili öz.
    private Properties hibernateProperties(){
        Properties properties=new Properties();//key,value seklinde propertiler girebiliyoruz
        properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        return properties;
    }
    //14 siradaki domaine student pojo olustur

}
