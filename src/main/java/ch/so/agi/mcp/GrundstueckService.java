package ch.so.agi.mcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class GrundstueckService {
    private static final Logger log = LoggerFactory.getLogger(GemeindeService.class);

    private final JdbcClient jdbcClient;

    public GrundstueckService(@Qualifier("pubJdbcClient") JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    @Tool(name = "sogis_get_grundstueck_by_number_and_municipality", description = "Ein Grundstück (Parzelle, Liegenschaft) hat einen eindeutigen Identifikator (EGRID). Dieser kann anhand der Nummer und der Gemeinde abgefragt werden.")
    public Grundstueck getGrundstueckByNumberAndMunicipality(String grundstuecksNummer, String gemeindeName) {
        String stmt = """
SELECT 
    nummer AS grundstuecks_nummer,
    nbident,
    egrid,
    gem.gemeindename  AS gemeinde_name,
    g.geometrie
FROM 
    agi_mopublic_v1.mopublic_grundstueck AS g
    LEFT JOIN agi_mopublic_v1.mopublic_gemeindegrenze AS gem 
    ON g.bfs_nr = gem.bfs_nr
WHERE 
    nummer = :grundstuecksNummer
    AND
    gemeindename = :gemeindeName
                """;
        return jdbcClient.sql(stmt)
                .param("grundstuecksNummer", grundstuecksNummer)
                .param("gemeindeName", gemeindeName)
                .query(Grundstueck.class)
                .optional().orElse(null);
    }
}
