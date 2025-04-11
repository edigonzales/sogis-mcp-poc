package ch.so.agi.mcp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
@SpringBootApplication
public class SogisMcpPocApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SogisMcpPocApplication.class, args);
    }
    
    @Bean
    public List<ToolCallback> sogisTools(GemeindeService gemeindeService) {
        return List.of(ToolCallbacks.from(gemeindeService));
    }

    @Bean
    public DataSource editDataSource(EditDatasourceConfiguration editDatasourceConfiguration) {
        return editDatasourceConfiguration.editDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }

    @Bean
    public DataSource pubDataSource(PubDatasourceConfiguration pubDatasourceConfiguration) {
        return pubDatasourceConfiguration.pubDataSourceProperties()
          .initializeDataSourceBuilder()
          .build();
    }
    
//    @Bean
//    public JdbcTemplate editJdbcTemplate(@Qualifier("editDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
//
//    @Bean
//    public JdbcTemplate pubJdbcTemplate(@Qualifier("pubDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }

    @Bean
    public JdbcClient editJdbcClient(@Qualifier("editDataSource") DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }
    
    @Bean
    public JdbcClient pubJdbcClient(@Qualifier("pubDataSource") DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }

    // for testing purposes
    @Bean
    public CommandLineRunner startup(GemeindeService gemeindeService) {

        return args -> {
            //System.out.println(gemeindeService.getGemeinden());
        };
    }
}


