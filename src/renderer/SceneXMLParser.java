package renderer;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.w3c.dom.*;
import primitives.Color;
import primitives.Point;
import scene.Scene;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class SceneXMLParser {

    public static Scene parse(String xmlFilePath) {
        Scene scene = new Scene("xmlTry");

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(xmlFilePath));

            // Parse background color
            String[] bgColor = doc.getElementsByTagName("scene").item(0).getAttributes().getNamedItem("background-color").getNodeValue().split(" ");
            scene.setBackground(new Color(Double.parseDouble(bgColor[0]), Double.parseDouble(bgColor[1]), Double.parseDouble(bgColor[2])));

            // Parse ambient light
            Element ambientLightElement = (Element) doc.getElementsByTagName("ambient-light").item(0);
            String[] ambLightColor = ambientLightElement.getAttribute("color").split(" ");
            Color color = new Color(Double.parseDouble(ambLightColor[0]), Double.parseDouble(ambLightColor[1]), Double.parseDouble(ambLightColor[2]));
            double kA = Double.parseDouble(ambientLightElement.getAttribute("kA"));
            AmbientLight ambientLight = new AmbientLight(color, kA);
            scene.setAmbientLight(ambientLight);

            // Parse geometries
            NodeList geometries = doc.getElementsByTagName("geometries").item(0).getChildNodes();
            for (int i = 0; i < geometries.getLength(); i++) {
                Node node = geometries.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element geometry = (Element) node;
                    switch (geometry.getTagName()) {
                        case "sphere":
                            String[] center = geometry.getAttribute("center").split(" ");
                            Point centerPoint = new Point(Double.parseDouble(center[0]), Double.parseDouble(center[1]), Double.parseDouble(center[2]));
                            double radius = Double.parseDouble(geometry.getAttribute("radius"));
                            scene.setGeometries(new Geometries(new Sphere(centerPoint, radius)));
                            break;
                        case "triangle":
                            String[] p0 = geometry.getAttribute("p0").split(" ");
                            String[] p1 = geometry.getAttribute("p1").split(" ");
                            String[] p2 = geometry.getAttribute("p2").split(" ");
                            scene.setGeometries(new Geometries(new Triangle(new Point(Double.parseDouble(p0[0]), Double.parseDouble(p0[1]), Double.parseDouble(p0[2])),
                                    new Point(Double.parseDouble(p1[0]), Double.parseDouble(p1[1]), Double.parseDouble(p1[2])),
                                    new Point(Double.parseDouble(p2[0]), Double.parseDouble(p2[1]), Double.parseDouble(p2[2])))));
                            break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return scene;
    }
}
