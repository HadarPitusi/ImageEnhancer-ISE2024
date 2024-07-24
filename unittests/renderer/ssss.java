package renderer;

import geometries.Plane;
import geometries.Sphere;
import lighting.AmbientLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class ssss {

    private final Scene scene = new Scene("sssss").setBackground(new Color(BLUE));

    private final Camera.Builder cameraBuilder = Camera.getBuilder().setDirection(new Vector(-8,-8,-3), new Vector(-100,-100,533.3333333333333))
            .setRayTracer(new SimpleRayTracer(scene));//.setAntiAliasingRays(20);
    @Test
    public void mpTest() {

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.YELLOW), new Double3(0.1)));
        scene.lights.add(
                new PointLight(new Color(700, 400, 400), new Point(-79,-20, 40))
                        .setKl(4E-4).setKq(2E-5).setLengthOfTheSide(2));//.setSoftShadowsRays(25));
        scene.geometries.add(
                /**-------משטח2-------**/
                //שורה 1 ורוד
                new Sphere(new Point(0, 0, 0), 30)
                        .setEmission(new Color(BLACK)),
        new Plane(
                new Point(-8, -13, -35),
                new Point(-8, 13, -35),
                new Point(1, 1, -35)
        )
                .setEmission(new Color(160, 82, 45))
                .setMaterial(new Material().setKd(0.1).setKs(0.8).setShininess(60)));
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
