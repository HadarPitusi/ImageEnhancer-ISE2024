package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

//import java.awt.*;

public class ImageWriterTest {

    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("yellow", 800, 500);
        int cubeSize=50;
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(i, j, new Color(java.awt.Color.YELLOW));
            }
        }
        for (int i = 0; i < imageWriter.getNx(); i += cubeSize) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(i, j, new Color(java.awt.Color.RED));
            }
        }
        for (int j = 0; j < imageWriter.getNy(); j += cubeSize) {
            for (int i = 0; i < imageWriter.getNx(); i++) {
                imageWriter.writePixel(i, j, new Color(java.awt.Color.RED));
            }
        }
        imageWriter.writeToImage();
    }
}
