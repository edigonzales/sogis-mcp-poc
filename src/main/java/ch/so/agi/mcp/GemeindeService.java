package ch.so.agi.mcp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class GemeindeService {
    private static final Logger log = LoggerFactory.getLogger(GemeindeService.class);

    private final JdbcClient jdbcClient;
    
    public GemeindeService(@Qualifier("pubJdbcClient") JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    @Tool(name = "sogis_get_area_of_municipalities", description = "Alle offiziellen Namen der Gemeinden des Kantons Solothurn mit ihrer Fläche / ihrem Flächenmass in Quadratmeter.")
    public List<Gemeinde> getGemeinden() {
        return jdbcClient.sql("SELECT gemeindename AS name,ST_Area(geometrie) AS flaeche FROM agi_hoheitsgrenzen_pub_v1.hoheitsgrenzen_gemeindegrenze")
                .query(Gemeinde.class)
                .list();
    }

}
