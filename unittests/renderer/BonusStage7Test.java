package renderer;

import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

public class BonusStage7Test {

    private final Scene scene = new Scene("BonusStage7Test").setBackground(new Color(120, 140, 255));

    private final Camera.Builder cameraBuilder = Camera.getBuilder().setDirection(new Vector(1, 1, 0), new Vector(0, 0, 1))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    void Bonus_stage_7() {

        //We see the bird from the front
        Material mateBirdSkin = new Material().setKt(0.2).setShininess(30).setKs(0.1).setKd(0.7);
        Material mateBirdOrgans = new Material().setKd(0.7).setShininess(10);
        Material mateBirdBeak = new Material().setKr(0.001).setKs(0.2).setKd(0.5);
        Material mateEye = new Material().setKt(1).setKs(0.5).setKd(0.01);
        scene.setAmbientLight(new AmbientLight(new Color(20, 150, 240), new Double3(0.1)));

        scene.geometries.add(
                /**------BIRD-------**/
                //head
                new Sphere(new Point(-14, 0, 2), 10)
                        .setEmission(new Color(207, 37, 203)).setMaterial(mateBirdSkin.setKt(0.005)),
                //body
                new Sphere(new Point(2, 2, -3), 14)
                        .setEmission(new Color(207, 37, 203)).setMaterial(mateBirdSkin),
                //eyes
                new Sphere(new Point(-20, -8, 4.5), 1)
                        .setEmission(new Color(0, 0, 0)),
                new Sphere(new Point(-20, -6, 4.5), 2)
                        .setEmission(new Color(255, 255, 220)).setMaterial(mateEye),
                new Sphere(new Point(-19, 8, 4.5), 1)
                        .setEmission(new Color(0, 0, 0)),
                new Sphere(new Point(-20, 6, 4.5), 2)
                        .setEmission(new Color(255, 255, 220)).setMaterial(mateEye),
                //beak
                new Triangle(new Point(-20, -4, 0), new Point(-17, -2, -5.5), new Point(-29, 5, -12))
                        .setEmission(new Color(130, 50, 10)).setMaterial(mateBirdBeak),
                new Triangle(new Point(-20, 4, 0), new Point(-17, 2, -5.5), new Point(-29, 5, -12))
                        .setEmission(new Color(130, 50, 10)).setMaterial(mateBirdBeak),
                new Triangle(new Point(-20, -4, 0), new Point(-20, 4, 0), new Point(-29, 5, -12))
                        .setEmission(new Color(130, 50, 10)).setMaterial(mateBirdBeak),
                new Triangle(new Point(-17, -2, -5.5), new Point(-17, 2, -5.5), new Point(-26, 5, -13))
                        .setEmission(new Color(130, 50, 10)).setMaterial(mateBirdBeak),
                //legs
                new Triangle(new Point(5, -7, -13), new Point(6, -6, -16), new Point(-8, -13, -27))
                        .setEmission(new Color(100, 40, 10)).setMaterial(mateBirdOrgans),
                new Triangle(new Point(5, 9, -13), new Point(6, 8, -16), new Point(-8, 13, -27))
                        .setEmission(new Color(100, 40, 10)).setMaterial(mateBirdOrgans),
                //wings
                new Triangle(new Point(-7, -8, 5), new Point(3.5, -9, 6), new Point(18, -34, -5))
                        .setEmission(new Color(203, 86, 235)).setMaterial(mateBirdOrgans),
                new Triangle(new Point(-5, 12, 5), new Point(3.5, 13, 6), new Point(18, 30, -5))
                        .setEmission(new Color(203, 86, 235)).setMaterial(mateBirdOrgans),
                /**------LAND-----**/
                //water
                new Plane(new Point(-8, -13, -25), new Point(-8, 13, -25), new Point(1, 1, -25))
                        .setEmission(new Color(0, 10, 230)).setMaterial(new Material().setKd(0.6).setShininess(5)),
                new Plane(new Point(-20, -30, -15), new Point(-20, 30, -15), new Point(-10, 0, -15))
                        .setEmission(new Color(0, 0, 255)).setMaterial(new Material().setKr(0.5).setKt(0.5))
        );

        scene.lights.add(new PointLight(new Color(255, 255, 255), new Point(0, 0, 10)).setKl(0.00001).setKq(0.0001));
        scene.lights.add(new SpotLight(new Color(800, 500, 250), new Point(-80, 20, 20), new Vector(82, -18, -25)).setKl(0.1).setKq(0.0001));
        scene.lights.add(new DirectionalLight(new Color(100, 150, 150), new Vector(22, -18, -35)));


        cameraBuilder
                .setLocation(new Point(-50, -50, 0))
                .setVpDistance(40)
                .setVpSize(60, 60)
                .setImageWriter(new ImageWriter("BonusStage7Test", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }


}
