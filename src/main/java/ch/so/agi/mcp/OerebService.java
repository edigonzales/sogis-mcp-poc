package ch.so.agi.mcp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

@Service
public class OerebService {
    private static final Logger log = LoggerFactory.getLogger(GemeindeService.class);

    @Tool(name = "sogis_get_oereb_by_egrid", description = "Liefert alle ÖREB (öffentlich-rechtlichen Eigentumsbeschränkungen) eines Grundstückes (Parzelle / Liegenschaft) inkl Link zum PDF-Auszug und zur Online-Karte (dynamischer Auszug) anhand des eindeutigen Identifikators EGRID.")
    public Oereb getOerebByEgrid(String egrid) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        String urlString = "https://geo.so.ch/api/oereb/extract/xml/?GEOMETRY=true&WITHIMAGES=true&EGRID="+egrid;
        URL url = new URL(urlString);
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(false); 
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(url.openStream());

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();

        List<String> themes = new ArrayList<>();
        {
            XPathExpression expr = xpath.compile("//ConcernedTheme/Text/LocalisedText/Text");
            
            Object result = expr.evaluate(doc, XPathConstants.NODESET);
            org.w3c.dom.NodeList nodes = (org.w3c.dom.NodeList) result;

            for (int i = 0; i < nodes.getLength(); i++) {
                themes.add(nodes.item(i).getTextContent());
            }            
        }
//        File image = new File("/Users/stefan/tmp/foo.png");
//        String base64Data = "";
//        {
//            XPathExpression expr = xpath.compile("//PlanForLandRegisterMainPage/Image/LocalisedBlob/Blob[1]");
//
//            Node blobNode = (Node) expr.evaluate(doc, XPathConstants.NODE);
//            if (blobNode != null) {
//                base64Data = blobNode.getTextContent().trim();
//
//                byte[] imageBytes = Base64.getDecoder().decode(base64Data);
//
//                try (FileOutputStream fos = new FileOutputStream(image)) {
//                    fos.write(imageBytes);
//                }
//            } else {
//                //System.out.println("No <Blob> element found.");
//            }
//        }

        Oereb oereb = new Oereb("https://geo.so.ch/api/oereb/extract/pdf/?GEOMETRY=true&EGRID="+egrid, "https://geo.so.ch/map/?oereb_egrid="+egrid, themes);
        return oereb;
    }
}
