package renderer;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.*;

public class mp1 {
    // יצירת סצנה
    private final Scene scene = new Scene("mp1").setBackground(new Color(WHITE));

    private final Camera.Builder cameraBuilder = Camera.getBuilder().setDirection(new Vector(-8,-8,-3), new Vector(-100,-100,533.3333333333333))
            .setRayTracer(new SimpleRayTracer(scene)).setNumOfRays(50);
    @Test
    public void mpTest(){

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.YELLOW), new Double3(0.1)));
        scene.lights.add(new PointLight(new Color(255, 255, 255), new Point(0, 0, 10)).setKl(0.00001).setKq(0.0001));
        scene.lights.add(new SpotLight(new Color(800, 500, 250), new Point(-80, 20, 20), new Vector(82, -18, -25)).setKl(0.1).setKq(0.0001));
        scene.lights.add(new DirectionalLight(new Color(100, 150, 150), new Vector(22, -18, -35)));
        Material material = new Material().setKt(0.2).setShininess(30).setKs(0.8).setKd(0.1).setKr(0.2);

        scene.geometries.add(
                /**-------משטח2-------**/
                //שורה 1 ורוד
                new Sphere(new Point(0,0,0), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,0,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,0,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,0,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                //שורה 2 צהבו
                 new Sphere(new Point(0,10,0), 5)
                .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                 new Sphere(new Point(0,-10,0), 5)
                .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,-10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,-10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,-10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,-10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                 new Sphere(new Point(0,20,0), 5)
                .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(0,-20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,-20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,-20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,-20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,-20,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),

                /**-------משט1-------**/
                //שורה 1 אדום
                new Sphere(new Point(0,0,10), 5)
                .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,0,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,0,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,0,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,0,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(0,-10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,-10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,-10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,-10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,-10,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(0,20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(0,-20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,-20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,-20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,-20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,-20,10), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                /**-------משטח3-------**/
                //שורה 1 אדום
                new Sphere(new Point(0,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,-20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,-20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,-20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,-20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,-20,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                /**-------משטח4-------**/
                //שורה 1 כחול
                new Sphere(new Point(0,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(0,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(0,20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(0,-20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,-20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,-20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,-20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,-20,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),

                /**-------משטח5-------**/
                //שורה 1 לבן
                new Sphere(new Point(0,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(0,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(0,20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(0,-20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,-20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,-20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(20,-20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,-20,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                /**-------משטח6-------**/
                //שורה 1 ירוק
              /**  new Sphere(new Point(0,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),**/
                //שורה 2
               /** new Sphere(new Point(0,10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(0,-10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,-10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,-10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,-10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,-10,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(0,20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(0,-20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,-20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,-20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,-20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,-20,30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),**/


                /**-------משטח7------**/
                //שורה 1 ירוק
                new Sphere(new Point(0,0,-30), 5)
                .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,0,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,0,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,0,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,0,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,-10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,-10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,-10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,-10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,-10,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,-20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,-20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,-20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,-20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,-20,-30), 5)
                        .setEmission(new Color(RED)).setMaterial(material),

                new Plane(new Point(-8, -13, -35), new Point(-8, 13, -35), new Point(1, 1, -35))
                        .setEmission(new Color(WHITE)).setMaterial(new Material().setKd(0.6).setShininess(6).setKs(0.8)),
                new Plane(new Point(-8, -80, -4), new Point(-4, -80, -3), new Point(1, -80, -5))
                        .setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.6).setShininess(6).setKr(0.8))

        );
        cameraBuilder
                .setLocation(new Point(100, 100, 30))
                .setVpDistance(60)
                .setVpSize(60, 60)
                .setImageWriter(new ImageWriter("mp1", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
