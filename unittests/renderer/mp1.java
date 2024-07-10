package renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.*;
import scene.Scene;

import static java.awt.Color.*;

public class mp1 {
    // יצירת סצנה
    Scene scene = new Scene("Flour Mill Scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void mpTest(){
        // יצירת גוף התחנה (תיבה)
        //abcd-קיר
        Polygon bodyBase = new Polygon(
                new Point(0, 0, 0),
                new Point(40, 0,0),
                new Point(40, 0, 40),
                new Point(0, 0, 40)
        );
        //bcgh-קיר
        Polygon bodyTop = new Polygon(
                new Point(40, 0,0),
                new Point(40, 0, 40),
                new Point(40, -40,40),
                new Point(40, -40,0)
        );
        //abhe
        Polygon bodyBack = new Polygon(
                new Point(0, 0, 0),
                new Point(40, 0,0),
                new Point(40, -40,0),
                new Point(0,-40, 0)
        );
        //cdfg
        Polygon bodyLeft = new Polygon(
                new Point(40, 0, 40),
                new Point(0, 0, 40),
                new Point(0, -40,40),
                new Point(40, -40,40)
        );

        scene.geometries.add(bodyBase.setEmission(new Color(GREEN)), bodyTop.setEmission(new Color(YELLOW)), bodyBack.setEmission(new Color(BLUE)), bodyLeft.setEmission(new Color(RED)));

        // מיקום המצלמה
        Point cameraLocation = new Point(-4.2,-7.5,2.5);

        // כיוון המצלמה (מהמצלמה לנקודת המרכז של החדר)
        Vector direction = new Vector(1, 0,0);

        // וקטור ה-up
        Vector up = new Vector(0, 1,0);

        cameraBuilder
                .setLocation(cameraLocation)  // מיקום המצלמה
                .setDirection(direction, up)  // כיוון המצלמה ווקטור up מאונך
                .setVpDistance(100)
                .setVpSize(600, 600)
                .setImageWriter(new ImageWriter("mp", 600, 600))
                .build()
                .renderImage()
                .writeToImage();
    }
}
