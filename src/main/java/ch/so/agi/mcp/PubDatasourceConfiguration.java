package ch.so.agi.mcp;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PubDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.pub")
    public DataSourceProperties pubDataSourceProperties() {
        return new DataSourceProperties();
    }
}
