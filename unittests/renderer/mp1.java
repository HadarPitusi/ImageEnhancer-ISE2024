package renderer;

import geometries.*;
import lighting.AmbientLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.*;

public class mp1 {
    // יצירת סצנה
    private final Scene scene = new Scene("mp1").setBackground(new Color(120, 140, 255));

    private final Camera.Builder cameraBuilder = Camera.getBuilder().setDirection(new Vector(-100,-100,-150), new Vector(100,50,-100))
            .setRayTracer(new SimpleRayTracer(scene));
    @Test
    public void mpTest(){

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.YELLOW), new Double3(0.1)));
        Material material = new Material().setKt(0.2).setShininess(30).setKs(0.8).setKd(0.1).setKr(0.2);

        scene.geometries.add(
                /**-------משטח 1-------**/
                //שורה 1 ורוד
                new Sphere(new Point(0,0,0), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,0), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,0), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,0), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,0), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //שורה 2 צהבו
                 new Sphere(new Point(0,10,0), 5)
                .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                 new Sphere(new Point(0,-10,0), 5)
                .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,-10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,-10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,-10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,-10,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                 new Sphere(new Point(0,20,0), 5)
                .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(0,-20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(10,-20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-10,-20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(20,-20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),
                new Sphere(new Point(-20,-20,0), 5)
                        .setEmission(new Color(YELLOW)).setMaterial(material),

                /**-------משטח2-------**/
                //שורה 1 אדום
                new Sphere(new Point(0,0,10), 5)
                .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,-10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,-10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,-10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,-10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,-10,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,-20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,-20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,-20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,-20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,-20,10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                /**-------משטח3-------**/
                //שורה 1 אדום
                new Sphere(new Point(0,0,-10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,-10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,-10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,-10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,-10), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,-10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,-10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,-10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,-10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,-10,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(0,-20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(10,-20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-10,-20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(20,-20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                new Sphere(new Point(-20,-20,-10), 5)
                        .setEmission(new Color(RED)).setMaterial(material),
                /**-------משטח4-------**/
                //שורה 1 כחול
                new Sphere(new Point(0,0,20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,-10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,-10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,-10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,-10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,-10,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,-20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,-20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,-20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,-20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,-20,20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),

                /**-------משטח5-------**/
                //שורה 1 לבן
                new Sphere(new Point(0,0,-20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,-20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,-20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,-20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,-20), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,-10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,-10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,-10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,-10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,-10,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(0,-20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,-20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,-20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(20,-20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,-20,-20), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                /**-------משטח6-------**/
                //שורה 1 ירוק
                new Sphere(new Point(0,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,30), 5)
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
                        .setEmission(new Color(GREEN)).setMaterial(material),
                /**-------משטח7------**/
                //שורה 1 ירוק
                new Sphere(new Point(0,0,-30), 5)
                .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(10,0,-30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-10,0,-30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(20,0,-30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                new Sphere(new Point(-20,0,-30), 5)
                        .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //שורה 2
                new Sphere(new Point(0,10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(0,-10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,-10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,-10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,-10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,-10,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(0,20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(0,-20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(10,-20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-10,-20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,-20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,-20,-30), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material)

        );
        cameraBuilder
                .setLocation(new Point(50, 50, 50))
                .setVpDistance(50)
                .setVpSize(60, 60)
                .setImageWriter(new ImageWriter("mp1", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
