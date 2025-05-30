package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Integration tests for intersection calculations between rays constructed by a camera and geometric shapes.
 */
class IntegrationTest {

    /**
     * Camera builder with default settings.
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(new Scene("Test")))
            .setImageWriter(new ImageWriter("Test", 1, 1))
            .setLocation(Point.ZERO)
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpDistance(1);

    /**
     * Default camera with a view plane size of 3x3.
     */
    Camera camera = cameraBuilder.setVpSize(3, 3).build();

    /**
     * Counts the number of intersections between rays constructed by the camera and the given shape.
     *
     * @param shape  the geometric shape to intersect with
     * @param camera the camera constructing the rays
     * @param nX
     * @param nY
     * @return the count of intersection points
     */
    public int countOfIntersection(Intersectable shape, Camera camera, int nX, int nY) {
        int counter = 0;
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                var result = shape.findIntersections(camera.constructRay(nX, nY, j, i));
                if (result != null)
                    counter += result.size();
            }
        }
        return counter;
    }

    /**
     * Tests intersections with a sphere.
     */
    @Test
    void testSphere() {
        final String IncorrectNumOfPoints = "ERROR: Incorrect number of intersection points at Sphere";
        Sphere sphere = new Sphere(new Point(0, 0, -3), 1);
        // TC01: The sphere is after the view plane (2 points)
        assertEquals(
                2,
                countOfIntersection(sphere, camera, 3, 3),
                IncorrectNumOfPoints
        );
        // TC02: The sphere contains the view plane (18 points)
        assertEquals(
                18,
                countOfIntersection(new Sphere(new Point(0, 0, -2.5), 2.5),
                        cameraBuilder.setLocation(new Point(0, 0, 0.5)).build(), 3, 3),
                IncorrectNumOfPoints
        );
        // TC03: The sphere intersects with the view plane (10 points)
        assertEquals(
                10,
                countOfIntersection(new Sphere(new Point(0, 0, -2), 2),
                        cameraBuilder.setLocation(new Point(0, 0, 0.5)).build(), 3, 3),
                IncorrectNumOfPoints
        );
        // TC04: The sphere contains the camera (9 points)
        assertEquals(
                9,
                countOfIntersection(new Sphere(new Point(0, 0, -1), 4), camera, 3, 3),
                IncorrectNumOfPoints
        );
        // TC05: The sphere is behind the camera (0 points)
        assertEquals(
                0,
                countOfIntersection(new Sphere(new Point(0, 0, 1), 0.5), camera, 3, 3),
                IncorrectNumOfPoints
        );
    }

    /**
     * Tests intersections with a plane.
     */
    @Test
    void testPlane() {
        final String IncorrectNumOfPoints = "ERROR: Incorrect number of intersection points at Plane";
        // TC01: The plane is parallel to the view plane (9 points)
        assertEquals(9,
                countOfIntersection(new Plane(
                                new Point(0, 0, -3),
                                new Point(0, 1, -3),
                                new Point(1, 0, -3)),
                        camera, 3, 3),
                IncorrectNumOfPoints
        );
        // TC02: The plane is at a small angle and visible by all pixels (9 points)
        assertEquals(9,
                countOfIntersection(new Plane(
                                new Point(0, 0, -3),
                                new Point(0, 1, -2.8),
                                new Point(1, 0, -3)),
                        camera, 3, 3),
                IncorrectNumOfPoints
        );
        // TC03: The plane is at a big angle and not visible by the bottom pixels (6 points)
        assertEquals(6,
                countOfIntersection(new Plane(
                                new Point(0, 0, -9.5),
                                new Point(0, 1, -2.005),
                                new Point(1, 0, -3)),
                        camera, 3, 3),
                IncorrectNumOfPoints
        );
    }

    /**
     * Tests intersections with a triangle.
     */
    @Test
    void testTriangle() {
        final String IncorrectNumOfPoints = "ERROR: Incorrect number of intersection points at Triangle";
        // TC01: Only one ray intersects with the triangle (1 point)
        assertEquals(1,
                countOfIntersection(new Triangle(
                                new Point(0, 1, -2),
                                new Point(-1, -1, -2),
                                new Point(1, -1, -2)),
                        camera, 3, 3),
                IncorrectNumOfPoints
        );

        // TC02: More than one ray intersects with the triangle (2 points)
        assertEquals(2,
                countOfIntersection(new Triangle(
                                new Point(0, 20, -2),
                                new Point(-1, -1, -2),
                                new Point(1, -1, -2)),
                        camera, 3, 3),
                IncorrectNumOfPoints
        );
    }
}
