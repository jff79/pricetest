package com.itx.pricetest.infrastructure.config;

import com.itx.pricetest.application.PriceService;
import com.itx.pricetest.application.PriceServiceImpl;
import com.itx.pricetest.domain.PriceRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@Configuration
@RequiredArgsConstructor
public class PriceTestApplicationConfig {

    private final PriceRepository priceRepository;

    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        Resource initSchema = new ClassPathResource("in/db/schema.sql");
        Resource initData = new ClassPathResource("in/db/import.sql");
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(initSchema, initData));
        return initializer;
    }

    @Bean
    public PriceService priceService() {
        return new PriceServiceImpl(this.priceRepository);
    }

    @Bean
    public HttpStatus defaultStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}