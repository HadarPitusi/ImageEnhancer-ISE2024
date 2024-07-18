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

    /**
     * Shininess value for most of the geometries in the tests
     */
    private static final int SHININESS = 301;
    /**
     * Diffusion attenuation factor for some of the geometries in the tests
     */
    private static final double KD = 0.5;
    /**
     * Diffusion attenuation factor for some of the geometries in the tests
     */
    private static final Double3 KD3 = new Double3(0.2, 0.6, 0.4);

    /**
     * Specular attenuation factor for some of the geometries in the tests
     */
    private static final double KS = 0.5;
    /**
     * Specular attenuation factor for some of the geometries in the tests
     */
    private static final Double3 KS3 = new Double3(0.2, 0.4, 0.3);

    /**
     * Material for some of the geometries in the tests
     */
    //private final Material material = new Material().setKd(KD3).setKs(KS3).setShininess(SHININESS);

    // יצירת סצנה
    private final Scene scene = new Scene("mp1").setBackground(new Color(245,245,220));

    private final Camera.Builder cameraBuilder = Camera.getBuilder().setDirection(new Vector(-8,-8,-3), new Vector(-100,-100,533.3333333333333))
            .setRayTracer(new SimpleRayTracer(scene));//.setNumOfRays(10);
    @Test
    public void mpTest(){
       // scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.1)));
        //scene.lights.add(new PointLight(new Color(RED), new Point(50, -40, 60)).setKl(0.00001).setKq(0.0001));
       // scene.lights.add(new SpotLight(new Color(800, 500, 250), new Point(-80, 20, 20), new Vector(82, -18, -25)).setKl(0.1).setKq(0.0001));
     //   scene.lights.add(new DirectionalLight(new Color(YELLOW), new Vector(20, 0, -10)));
     //   Material material = new Material().setKt(0.2).setShininess(30).setKs(0.8).setKd(0.1).setKr(0.2);
      //  scene.lights.add(
        //        new SpotLight(new Color(700, 400, 400), new Point(-79,-20, 40), new Vector(79, 20, -40)) //
          //              .setKl(4E-4).setKq(2E-5));
        scene.lights.add(
                new PointLight(new Color(700, 400, 400), new Point(-79,-20, 40))
                        .setKl(4E-4).setKq(2E-5));//.setLengthOfTheSide(2).setSoftShadowsRays(15));
        Material material= new Material().setKd(0.3).setKs(0.5).setShininess(50).setKr(0.3);

        scene.geometries.add(
                /**-------משטח2-------**/
             //   new Sphere(new Point(0,0,0), 5)
               //         .setEmission(new Color(207, 37, 203)).setMaterial(material),
                //new Sphere(new Point(10,0,0), 5)
                    //    .setEmission(new Color(GREEN)).setMaterial(material),
                //new Sphere(new Point(-10,0,0), 5)
                  //      .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,0,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,0,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                 //new Sphere(new Point(0,10,0), 5)
                //.setEmission(new Color(GREEN)).setMaterial(material),
             //   new Sphere(new Point(10,10,0), 5)
                  //      .setEmission(new Color(GREEN)).setMaterial(material),
                //new Sphere(new Point(-10,10,0), 5)
                  //      .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(20,10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                new Sphere(new Point(-20,10,0), 5)
                        .setEmission(new Color(GREEN)).setMaterial(material),
                // new Sphere(new Point(0,-10,0), 5)
                //.setEmission(new Color(GREEN)).setMaterial(material),
                //new Sphere(new Point(10,-10,0), 5)
                  //      .setEmission(new Color(GREEN)).setMaterial(material),
                //new Sphere(new Point(-10,-10,0), 5)
                 //       .setEmission(new Color(GREEN)).setMaterial(material),
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
               /** new Sphere(new Point(0,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),**/
                new Sphere(new Point(20,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,0,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                /**new Sphere(new Point(0,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),**/
                new Sphere(new Point(20,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-20,10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
               /** new Sphere(new Point(0,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(10,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),
                new Sphere(new Point(-10,-10,-10), 5)
                        .setEmission(new Color(BLUE)).setMaterial(material),**/
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
             /**   new Sphere(new Point(0,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),**/
                new Sphere(new Point(20,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,0,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
              /**  new Sphere(new Point(0,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),**/
                new Sphere(new Point(20,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
               /** new Sphere(new Point(0,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,-10,-40), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),**/
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
               /** new Sphere(new Point(0,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),**/
                new Sphere(new Point(20,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,0,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
           /**     new Sphere(new Point(0,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),**/
                new Sphere(new Point(20,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-20,10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                /**new Sphere(new Point(0,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(10,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),
                new Sphere(new Point(-10,-10,-20), 5)
                        .setEmission(new Color(127,0,255)).setMaterial(material),**/
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
                        .setEmission(new Color(160,82,45))
                        .setMaterial(new Material()
                                .setKd(0.1)  // Diffuse reflection
                                .setKs(0.8)  // Specular reflection
                                .setShininess(60)), // Shininess
                new Plane(new Point(-8, -13, 50), new Point(-8, 13, 50), new Point(1, 1, 50))
                        .setEmission(new Color(160,82,45))
                        .setMaterial(new Material()
                                .setKd(0.1)  // Diffuse reflection
                                .setKs(0.8)  // Specular reflection
                                .setShininess(100)), // Shininess


            //    new Plane(new Point(-80, -8, -4), new Point(-80, -80, -3), new Point(-80, -10, -5))
              //          .setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.6).setShininess(6).setKr(0.8)),

        new Plane(new Point(-8, -80, -4), new Point(-4, -80, -3), new Point(1, -80, -5))
                        .setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.6).setShininess(6).setKr(0.8))

        );

        cameraBuilder
                .setLocation(new Point(100, 100, 30))
                .setVpDistance(80)
                .setVpSize(80, 80)
                .setImageWriter(new ImageWriter("mp1", 500, 500))
                .build()
                .renderImage()
                .writeToImage();
    }
}
