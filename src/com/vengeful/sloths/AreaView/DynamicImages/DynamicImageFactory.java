package com.vengeful.sloths.AreaView.DynamicImages;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import com.vengeful.sloths.Utility.IsNumber;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

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
            Document doc = documentBuilder.parse(imageSpec);
            doc.getDocumentElement().normalize();
            switch(doc.getDocumentElement().getNodeName()) {
                case "single_frame_animation":
                    return this.createSingleFrameAnimation(doc.getDocumentElement());
                case "dynamic_timed_animation":
                    return createDynamicTimedAnimation(doc.getDocumentElement());

            }

        } catch (Exception e ) {
            //e.printStackTrace();
        }

            return null;
    }
    private PositioningStrategy createPositionStrategy(Node positionStrategy) {
        switch (positionStrategy.getTextContent()) {
            case "center":
                return new CenteredPositioningStrategy();
            case "occupying":
                return new TileOccupyingPositioningStrategy();
            case "custom":
                return new CustomOffsetPositioningStrategy(
                        Integer.parseInt(positionStrategy.getAttributes().getNamedItem("xOffset").getTextContent()),
                        Integer.parseInt(positionStrategy.getAttributes().getNamedItem("yOffset").getTextContent()));

            default:
                return new CenteredPositioningStrategy();
        }
    }

    private DynamicImage createSingleFrameAnimation(Element root) {
        Element element = root;

        String rootPath = element.getElementsByTagName("rootPath").item(0).getTextContent();

        return new SingleFrameImage(rootPath + element.getElementsByTagName("fileName").item(0).getTextContent(),
                                    Integer.parseInt(element.getElementsByTagName("width").item(0).getTextContent()),
                                    Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent()),
                                    createPositionStrategy(element.getElementsByTagName("positioning").item(0)));
    }

    private DynamicImage createDynamicTimedAnimation(Element root) {
        Element element = root;
        String rootPath = element.getElementsByTagName("rootPath").item(0).getTextContent();

        DynamicImage start = loadDynamicImage(rootPath + element.getElementsByTagName("startConfig").item(0).getTextContent());
        DynamicImage end = loadDynamicImage(rootPath + element.getElementsByTagName("endConfig").item(0).getTextContent());



        ArrayList<String> activeFilePaths = new ArrayList<>();
        NodeList nList = element.getElementsByTagName("fileName");
        for (int i=0; i<nList.getLength(); i++) {
            activeFilePaths.add(rootPath + nList.item(i).getTextContent());
        }



        return new DynamicTimedImage(
                Integer.parseInt(element.getElementsByTagName("width").item(0).getTextContent()),
                Integer.parseInt(element.getElementsByTagName("height").item(0).getTextContent()),
                createPositionStrategy(element.getElementsByTagName("positioning").item(0)),
                start,
                end,
                activeFilePaths
                );
    }
}
