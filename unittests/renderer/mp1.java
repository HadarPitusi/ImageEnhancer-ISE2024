package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

import static java.awt.Color.*;

public class mp1 {
    private final Scene scene = new Scene("minip").setBackground(new Color(245, 245, 220));

    private final Camera.Builder cameraBuilder =
            Camera.getBuilder().
                    setDirection(new Vector(-8, -8, -3), new Vector(-100, -100, 533.3333333333333))
                    .setRayTracer(new SimpleRayTracer(scene)).
                    setMultiThreading(true).setSuperSempling(true).setAntiAliasingRays(33);

    @Test
    public void mpTest() {
        Color myStrongGreen = new Color(0, 100, 0);
        Color myPurple = new Color(127, 0, 255);
        Color myOrange = new Color(255, 127, 0);
        Color emmisionGreen = new Color(0, 240, 10);
        Color emmisionStrongGreen = new Color(5, 92, 5);
        Color emmisionBlue = new Color(0, 10, 240);
        Color emmisionPurple = new Color(115, 0, 240);
        Color emmisionOrange = new Color(240, 115, 0);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.1)));
        scene.lights.add(
                new PointLight(new Color(255, 240, 245), new Point(-79, -20, 40))
                        .setKl(0.04).setKq(2E-5));//.setLengthOfTheSide(3).setSoftShadowsRays(300));
        scene.lights.add(
                new DirectionalLight(new Color(WHITE), new Vector(-80, -80, 30)));
        scene.lights.add(
                new SpotLight(
                        new Color(700, 400, 400), new Point(0, 50, -10), new Vector(0, -30, 25))
                        .setKl(0.1).setKq(0.0001));
        scene.lights.add(
                new SpotLight(
                        new Color(700, 400, 400), new Point(50, 0, -10), new Vector(-30, 0, 25))
                        .setKl(0.1).setKq(0.0001));
        Material material = new Material().setKd(0.3).setKs(0.5).setShininess(50).setKr(0.1).setKt(0.2);

        scene.geometries.add(
                /**------1-------**/
                new Sphere(new Point(0, 0, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(10, 0, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-10, 0, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(20, 0, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-20, 0, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(0, 10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(10, 10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-10, 10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(20, 10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-20, 10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(0, -10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(10, -10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-10, -10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(20, -10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-20, -10, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(0, 20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(10, 20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-10, 20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(20, 20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-20, 20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(0, -20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(10, -20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-10, -20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(20, -20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),
                new Sphere(new Point(-20, -20, 10), 5)
                        .setEmission(myStrongGreen).setMaterial(material).setEmission(emmisionStrongGreen),

                /**-------2-------**/
                new Sphere(new Point(20, 0, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(-20, 0, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(20, 10, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(-20, 10, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(20, -10, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(-20, -10, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(0, 20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(10, 20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(-10, 20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(20, 20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(-20, 20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(0, -20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(10, -20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(-10, -20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(20, -20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),
                new Sphere(new Point(-20, -20, 0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material).setEmission(emmisionGreen),

                /**-------3-------**/
                new Sphere(new Point(20, 0, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(-20, 0, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(20, 10, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(-20, 10, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(20, -10, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(-20, -10, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(0, 20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(10, 20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(-10, 20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(20, 20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(-20, 20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(0, -20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(10, -20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(-10, -20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(20, -20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),
                new Sphere(new Point(-20, -20, -10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material).setEmission(emmisionBlue),

                /**------4------**/
                new Sphere(new Point(20, 0, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(-20, 0, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(20, 10, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(-20, 10, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(20, -10, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(-20, -10, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(0, 20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(10, 20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(-10, 20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(20, 20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(-20, 20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(0, -20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(10, -20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(-10, -20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(20, -20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),
                new Sphere(new Point(-20, -20, -20), 5)
                        .setEmission(myPurple).setMaterial(material).setEmission(emmisionPurple),

                /**-------5------**/
                new Sphere(new Point(20, 0, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(-20, 0, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                //  new Sphere(new Point(-10, 0, -30), 5)
                //        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                //    new Sphere(new Point(10, 0, -30), 5)
                //          .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                // new Sphere(new Point(0, 0, -30), 5)
                //      .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(20, 10, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(-20, 10, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                // new Sphere(new Point(-10, 10, -30), 5)
                //       .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                //  new Sphere(new Point(10, 10, -30), 5)
                //            .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                /// new Sphere(new Point(0, 10, -30), 5)
                //      .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(20, -10, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(-20, -10, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                //  new Sphere(new Point(-10, -10, -30), 5)
                //        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                //new Sphere(new Point(10, -10, -30), 5)
                //      .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                // new Sphere(new Point(0, -10, -30), 5)
                //       .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(0, 20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(10, 20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(-10, 20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(20, 20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(-20, 20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(0, -20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(10, -20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(-10, -20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(20, -20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),
                new Sphere(new Point(-20, -20, -30), 5)
                        .setEmission(myOrange).setMaterial(material).setEmission(emmisionOrange),


                new Plane(
                        new Point(-8, -13, -35),
                        new Point(-8, 13, -35),
                        new Point(1, 1, -35)
                )
                        .setEmission(new Color(160, 82, 45))
                        .setMaterial(new Material().setKd(0.1).setKs(0.8).setShininess(60)),
                new Plane(
                        new Point(-8, -13, 50),
                        new Point(-8, 13, 50),
                        new Point(1, 1, 50)
                )
                        .setEmission(new Color(160, 82, 45))
                        .setMaterial(new Material().setKd(0.1).setKs(0.8).setShininess(100)),
                //mirror
                new Plane(
                        new Point(-8, -80, -4),
                        new Point(-4, -80, -3),
                        new Point(1, -80, -5)
                )
                        .setEmission(new Color(BLACK))
                        .setMaterial(new Material().setKd(0.4).setShininess(6).setKr(0.8))
        );

        cameraBuilder
                .setLocation(new Point(100, 100, 30))
                .setVpDistance(80)
                .setVpSize(80, 80)
                .setImageWriter(new ImageWriter("minip", 1000, 1000))
                .build()
                .renderImage()
                .writeToImage();
    }
}
