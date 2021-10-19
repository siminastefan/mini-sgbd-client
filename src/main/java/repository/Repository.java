package repository;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Repository {

    public static final String FILENAME = "files/dbms.xml";

    public void createDatabase(String databaseName) throws Exception{

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(FILENAME);
        Element root = document.getDocumentElement();
        System.out.println(root.getTagName());

        Element newDatabase = document.createElement("Database");
        newDatabase.setAttribute("databaseName", databaseName);


        root.appendChild(newDatabase);

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(FILENAME);
        transformer.transform(source, result);

    }

}
