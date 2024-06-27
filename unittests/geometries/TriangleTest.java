package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    private final double DELTA = 0.000001;

    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Triangle(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0)),
                "Failed constructing a correct Triangle");

        // =============== Boundary Values Tests ==================
        // TC10: The vertices are on the same line
        assertThrows(IllegalArgumentException.class, //
                () -> new Triangle(new Point(0, 0, 1),
                        new Point(0, 0, 2),
                        new Point(0, 0, 3)),
                "Constructed a Triangle with vertix on the same line");

        // TC11: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Triangle(new Point(0, 0, 1),
                        new Point(0, 1, 0),
                        new Point(0, 1, 0)),
                "Constructed a Triangle with vertix on a side");
    }

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Triangle triangle = new Triangle(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0)
        );
        // ensure there are no exceptions
        assertDoesNotThrow(() -> triangle.getNormal(new Point(0, 0, 1)), "");
        // ensure |result| = 1
        assertEquals(1,
                triangle.getNormal(new Point(0, 0, 1)).length(),
                DELTA,
                "Polygon's normal is not a unit vector");
    }

    /**
     * Tests the {@code findIntersections} method of the {@code Triangle} class.
     */
    @Test
    public void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: The ray intersects with the triangle.
        Triangle triangle = new Triangle(
                new Point(3, 0, 0),
                new Point(-3, 0, 0),
                new Point(0, 3, 0)
        );
        final var result1 = triangle.findIntersections(new Ray(new Point(0, 1, -1), new Vector(0, 0, 1)))
                .stream().toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0, 1, 0)), result1, "Ray crosses triangle");

        // TC02: The ray misses the triangle, passing outside near an edge.
        assertNull(
                triangle.findIntersections(new Ray(new Point(2, 2, -1), new Vector(0, 0, 1))),
                "Ray's line out of triangle"
        );

        // TC03: The ray misses the triangle, passing outside near a vertex.
        assertNull(
                triangle.findIntersections(new Ray(new Point(0, 4, -1), new Vector(0, 0, 1))),
                "Ray's line out of triangle"
        );

        // =============== Boundary Values Tests ==================
        // TC10: The ray lies on an edge of the triangle.
        assertNull(
                triangle.findIntersections(new Ray(new Point(1, 0, -1), new Vector(0, 0, 1))),
                "Ray's line on the triangle's side"
        );

        // TC11: The ray starts at a vertex of the triangle.
        assertNull(
                triangle.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 0, 1))),
                "Ray's line on triangle's vertex"
        );

        // TC12: The ray lies on the continuation of an edge of the triangle.
        assertNull(
                triangle.findIntersections(new Ray(new Point(4, 0, -1), new Vector(0, 0, 1))),
                "Ray's line on the continuation of a side"
        );
    }

    /**
     * Tests the {@code findGeoIntersections} method of the {@code Triangle} class.
     */
    @Test
    public void testFindGeoIntersections() {
        Triangle triangle = new Triangle(
                new Point(3, 0, 0),
                new Point(-3, 0, 0),
                new Point(0, 3, 0)
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: The ray intersects with the triangle.
        final var result1 = triangle.
                findGeoIntersections(new Ray(new Point(0, 1, -1), new Vector(0, 0, 1)), 10)
                .stream().toList();
        assertEquals(1, result1.size(), "Wrong number of points -geoIntersection");
        assertEquals(List.of(
                        new GeoPoint(triangle, new Point(0, 1, 0))),
                result1,
                "Ray crosses triangle -geoIntersection"
        );
        //TC02: The point is far away from the head of ray.
        assertNull(
                triangle.findGeoIntersections(new Ray(new Point(0, 1, -1), new Vector(0, 0, 1)),
                        0.5),
                "max Distance"
        );
        // =============== Boundary Values Tests ==================
        //TC11: maxDistance == distance between point and head of the ray
        final var result2 = triangle.
                findGeoIntersections(new Ray(new Point(0, 1, -1), new Vector(0, 0, 1)), 1)
                .stream().toList();
        assertEquals(1, result2.size(), "Wrong number of points -geoIntersection");
        assertEquals(List.of(
                        new GeoPoint(triangle, new Point(0, 1, 0))),
                result2,
                "Ray crosses triangle -geoIntersection"
        );
    }
}

