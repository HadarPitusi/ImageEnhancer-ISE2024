package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationTest {

    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setLocation(Point.ZERO)
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1);

    Camera camera = cameraBuilder.setVpSize(3, 3).build();

    public int countOfIntersection(Intersectable shape, Camera camera) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                var result = shape.findIntersections(camera.constructRay(3, 3, j, i));
                if (result != null)
                    counter += result.size();
            }
        }
        return counter;
    }

    @Test
    void testSphere(){
        Sphere sphere = new Sphere(1, new Point(0, 0, -3));
        // // TC01: The sphere is after the view plane (2 points)
        assertEquals(
                2,
                countOfIntersection(sphere, camera),
                "ERROR: Incorrect number of intersection points"
        );
        // TC02: The sphere contains the view plane (18 points)
        assertEquals(
                18,
                countOfIntersection(new Sphere(2.5, new Point(0,0,-2.5)),
                cameraBuilder.setLocation(new Point(0,0,0.5)).build()),
                "ERROR: Incorrect number of intersection points"
        );
        // TC03: The sphere intersects with the view plane (10 points)
        assertEquals(
                10,
                countOfIntersection(new Sphere(2, new Point(0,0,-2)),
                        cameraBuilder.setLocation(new Point(0,0,0.5)).build()),
                "ERROR: Incorrect number of intersection points"
        );
        // TC04: The sphere contains the camera (9 points)
        assertEquals(
                9,
                countOfIntersection(new Sphere(4, new Point(0,0,-1)), camera),
                "ERROR: Incorrect number of intersection points"
        );
        // TC05: The sphere is behind the camera (0 points)
        assertEquals(
                0,
                countOfIntersection(new Sphere(0.5, new Point(0,0,1)), camera),
                "ERROR: Incorrect number of intersection points"
        );
    }

    @Test
    void testPlane() {
        // TC01: The plane is parallel to the view plane (9 points)
        assertEquals(9,
                countOfIntersection(new Plane(
                                        new Point(0, 0, -3),
                                        new Point(0, 1, -3),
                                        new Point(1, 0, -3)),
                                    camera),
                "gg"
        );
        // TC02: The plane is at a small angle and visible by all pixels (9 points)
        assertEquals(9,
                countOfIntersection(new Plane(
                                        new Point(0, 0, -3),
                                        new Point(0, 1, -2.8),
                                        new Point(1, 0, -3)),
                                    camera),
                "gg"
        );
        // TC03: The plane is at a big angle and not visible by the bottom pixels (6 points)
        assertEquals(6,
                countOfIntersection(new Plane(
                                        new Point(0, 0, -9.5),
                                        new Point(0, 1, -2.005),
                                        new Point(1, 0, -3)),
                                    camera),
                "gg"
        );
    }

    @Test
    void testTriangle() {
        // TC01: Only one ray intersects with the triangle (1 point)
        assertEquals(1,
                countOfIntersection(new Triangle(
                                        new Point(0,1,-2),
                                        new Point(-1,-1,-2),
                                        new Point(1,-1,-2)),
                                    camera),
                "Wrong number of intersections"
        );

        // TC02: More than one ray intersects with the triangle (2 points)
        assertEquals(2,
                countOfIntersection(new Triangle(
                                        new Point(0,20,-2),
                                        new Point(-1,-1,-2),
                                        new Point(1,-1,-2)),
                                    camera),
                "Wrong number of intersections"
        );
    }

}
