package com.vengeful.sloths.Models.SaveLoad;

import com.vengeful.sloths.Models.Map.MapArea;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * Created by Ian on 3/7/2016.
 */
public class Loader {
    String fileName;
    Document doc;

    public Loader(String fileName) throws ParserConfigurationException, IOException, SAXException {
        this.fileName = fileName;
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        doc = docBuilder.parse(fileName);
    }

    public void loadAreas(String maName, MapArea[] areas){
        Node root = doc.getDocumentElement();
        NodeList nl = doc.getChildNodes();
        if(doc.hasChildNodes()){
            for(int i = 0; i != nl.getLength(); ++i){
                Node curr = nl.item(i);
                if(curr.getNodeType() == Node.ELEMENT_NODE ){
                    //unfinished processing
                }
            }
        }
    }

}
