package com.vengeful.sloths.AreaView.DynamicImages;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Alex on 2/21/2016.
 */
public class DynamicImageFactory {
    private static DynamicImageFactory ourInstance = new DynamicImageFactory();

    public static DynamicImageFactory getInstance() {
        return ourInstance;
    }

    private DynamicImageFactory() {
    }

    public DynamicImage loadDynamicImage(String xmlPath) {
        try {
            File imageSpec = new File(xmlPath);
            DocumentBuilderFactory dbFactory = new DocumentBuilderFactoryImpl();
            DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
            Document doc =  documentBuilder.parse(imageSpec);
            doc.getDocumentElement().normalize();
            System.out.println("root element: " + doc.getDocumentElement().getNodeName());
            switch(doc.getDocumentElement().getNodeName()) {
                case "single_frame_animation":
                    return this.createSingleFrameAnimation(doc.getDocumentElement());


            }

        } catch (Exception e ) {
            e.printStackTrace();
        }

            return null;
    }
    private PositioningStrategy createPositionStrategy(String positionStrategyName) {
        switch (positionStrategyName) {
            case "center":
                return new CenteredPositioningStrategy();
            default:
                return new CenteredPositioningStrategy();
        }
    }

    private DynamicImage createSingleFrameAnimation(Element root) {
        Node node = root;
        System.out.println(node.getNodeName());
        Element element = (Element) node;

        System.out.println("root path: " + element.getElementsByTagName("rootPath").item(0).getTextContent());
        String rootPath = element.getElementsByTagName("rootPath").item(0).getTextContent();
        return null;
//        return new SingleFrameImage(rootPath + element.getElementsByTagName("fileName").item(0).getTextContent(),
//                                    createPositionStrategy(element.getElementsByTagName("positioning").item(0).getTextContent()));
    }
}
