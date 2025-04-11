package ch.so.agi.mcp;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EditDatasourceConfiguration {
    @Bean
    @ConfigurationProperties("spring.datasource.edit")
    public DataSourceProperties editDataSourceProperties() {
        return new DataSourceProperties();
    }
}
