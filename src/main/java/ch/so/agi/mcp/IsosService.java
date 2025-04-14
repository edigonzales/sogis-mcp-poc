package ch.so.agi.mcp;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class IsosService {
    private static final Logger log = LoggerFactory.getLogger(GemeindeService.class);

    private final JdbcClient jdbcClient;
    
    public IsosService(@Qualifier("editJdbcClient") JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    @Tool(name = "sogis_get_isos", description = "Bundesinventar der schützenswerten Ortsbilder der Schweiz von nationaler Bedeutung ISOS und Ortsbildschutz für die Gemeinden des Kantons Solothurn. Inklusive Link zum Objektblatt / PDF und zur Online-Karte / zum Web GIS Client.")
    public List<Isos> getIsos() {
        String stmt = """
SELECT 
    ob.aname_de AS isos_name,
    gem.aname_de AS gemeinde_name,
    pub.file_url AS pdf_link,
    'https://geo.so.ch/map/?t=default&l=ch.bak.bundesinventar-schuetzenswerte-ortsbilder%5B30%5D&bl=hintergrundkarte_sw&s=18898&c='|| ST_X(koordinaten)::text || '%2C' || ST_Y(koordinaten )::text AS karten_link
FROM    
    bak_isos_v1.isosbase_ortsbild AS ob 
    LEFT JOIN bak_isos_v1.isosbase_kanton AS kt 
    ON kt.isosbase_ortsbild_kantone = ob.t_id
    LEFT JOIN bak_isos_v1.isosbase_gemeinde AS gem 
    ON gem.isosbase_ortsbild_gemeinde = ob.t_id 
    LEFT JOIN bak_isos_v1.isosbase_publikation AS pub 
    ON pub.isosbase_ortsbild_publikation = ob.t_id
WHERE 
    kt.acode = 'SO'
                """;
        return jdbcClient.sql(stmt)
                .query(Isos.class)
                .list();   
    }

}
