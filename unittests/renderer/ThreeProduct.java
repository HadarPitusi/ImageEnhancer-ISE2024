package renderer;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class ThreeProduct {

        /** Scene for the tests */
        private final Scene scene         = new Scene("Test scene");
        /** Camera builder for the tests with triangles */
        private final Camera.Builder cameraBuilder = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setRayTracer(new SimpleRayTracer(scene));

        /** Produce a picture of a sphere lighted by a spot light */
        @Test
        public void twoSpheres() {
            scene.geometries.add(
                    new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE))
                            .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                    new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
            scene.lights.add(
                    new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                            .setKl(0.0004).setKq(0.0000006));

            cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                    .setVpSize(150, 150)
                    .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
                    .build()
                    .renderImage()
                    .writeToImage();
        }

        /** Produce a picture of a sphere lighted by a spot light */
        @Test
        public void twoSpheresOnMirrors() {
            scene.geometries.add(
                    new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100))
                            .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                    .setKt(new Double3(0.5, 0, 0))),
                    new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20))
                            .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                    new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                            new Point(670, 670, 3000))
                            .setEmission(new Color(20, 20, 20))
                            .setMaterial(new Material().setKr(1)),
                    new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                            new Point(-1500, -1500, -2000))
                            .setEmission(new Color(20, 20, 20))
                            .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));
            scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
            scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
                    .setKl(0.00001).setKq(0.000005));

            cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000)
                    .setVpSize(2500, 2500)
                    .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
                    .build()
                    .renderImage()
                    .writeToImage();
        }

        /** Produce a picture of a two triangles lighted by a spot light with a
         * partially
         * transparent Sphere producing partial shadow */
        @Test
        public void stage7() {
            scene.geometries.add(
                    new Triangle(new Point(-60, 80, -105), new Point(-170, -95, -105), new Point(40, -95, -105))
                            .setMaterial(new Material().setKd(1).setKs(1).setShininess(100)),
                    new Triangle(new Point(40, 118.33, -200), new Point(-100, -101.67, -105), new Point(180, -101.67, -105))
                            .setEmission(new Color(0, 0, 255)) // Set to BLUE
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKr(0.8)), // Reflectivity (Kr)
                    new Sphere(new Point(-48.33, -1.67, -105), 30d).setEmission(new Color(128, 0, 255))
                            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
                    new Sphere(new Point(-48.33, -1.67, -105), 10d).setEmission(new Color(0, 0, 0))
                            .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.1)),
                    new Sphere(new Point(40, -1.67, -105), 20d).setEmission(new Color(255, 0, 0)) // RED color
                            .setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(40))
            );
            scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1)); // WHITE ambient light
            scene.lights.add(
                    new PointLight(new Color(255, 255, 255), new Point(50, 50, 0)) // WHITE point light
                            .setKl(0.0004).setKq(0.000006)
            );

            cameraBuilder.setLocation(new Point(0, 0, 1100)).setVpDistance(2500)
                    .setVpSize(600, 600)
                    .setImageWriter(new ImageWriter("stage7", 600, 600))
                    .build()
                    .renderImage()
                    .writeToImage();
        }
}
