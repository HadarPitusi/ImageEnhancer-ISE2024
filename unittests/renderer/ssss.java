package renderer;

import geometries.Sphere;
import lighting.AmbientLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.*;

public class ssss {

    private final Scene scene = new Scene("sssss").setBackground(new Color(BLUE));

    private final Camera.Builder cameraBuilder = Camera.getBuilder().setDirection(new Vector(-8,-8,-3), new Vector(-100,-100,533.3333333333333))
            .setRayTracer(new SimpleRayTracer(scene)).setAntiAliasingRays(1);
    @Test
    public void mpTest() {

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.YELLOW), new Double3(0.1)));
        scene.lights.add(
                new PointLight(new Color(700, 400, 400), new Point(-79,-20, 40))
                        .setKl(4E-4).setKq(2E-5));//.setLengthOfTheSide(2).setSoftShadowsRays(10));
        scene.geometries.add(
                /**-------משטח2-------**/
                //שורה 1 ורוד
                new Sphere(new Point(0, 0, 0), 30)
                        .setEmission(new Color(BLACK)));
        cameraBuilder
                .setLocation(new Point(100, 100, 30))
                .setVpDistance(60)
                .setVpSize(60, 60)
                .setImageWriter(new ImageWriter("sssss", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
