package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    private final double DELTA = 0.000001;

    @Test
    void Plane() {
        // ============ Boundary Values Tests ==============
        // TC10:2 points converge
        assertThrows(
                IllegalArgumentException.class,
                () -> new Plane(
                        new Point(0, 0, 1),
                        new Point(0, 0, 1),
                        new Point(1, 1, 1)),
                "ERROR: 2 points converge"
        );

        // TC11:The points that are on the same line
        assertThrows(
                IllegalArgumentException.class,
                () -> new Plane(
                        new Point(-3, 0, 0),
                        new Point(0, 0, 4),
                        new Point(3, 0, 8)),
                "ERROR: The points are on the same line"
        );
    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Plane plane = new Plane(
                new Point(0, 2, 3),
                new Point(0, 1, 0),
                new Point(1, 1, 1)
        );
        // Make sure the values of the normal are correct
        assertEquals(
                new Vector(-0.30151134457776363, -0.9045340337332909, 0.30151134457776363),
                plane.getNormal(),
                "ERROR: Plane's normal is not a unit vector"
        );
        // ensure |result| = 1
        assertEquals(
                1,
                plane.getNormal().length(),
                DELTA,
                "ERROR: Plane's normal is not a unit vector");
    }


    @Test
    void testTestGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point point = new Point(3, 1, 2);
        Plane plane = new Plane(
                new Point(0, 1, 1),
                new Point(0, 1, 0),
                new Point(1, 1, 1)
        );
        // Make sure the values of the normal are correct
        assertEquals(
                new Vector(0, -1, 0),
                plane.getNormal(point),
                "ERROR: wrong normal value"
        );
        // ensure |result| = 1
        assertEquals(
                1,
                plane.getNormal(point).length(),
                DELTA,
                "ERROR: Plane's normal is not a unit vector"
        );
    }


    /**
     * Tests the findIntersections method of the Plane class.
     */
    @Test
    void testFindIntersections() {
        final String wrongNum = "Wrong number of points";
        Plane plane = new Plane(
                new Point(1, 0, 0),
                new Point(1, 2, 0),
                new Point(0, 1, 0)
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: A cutting ray, neither oblique nor perpendicular
        final var result1 = plane.
                findIntersections(new Ray(new Point(0, 0, -2), new Vector(0, 3, 2)))
                .stream().toList();
        assertEquals(1, result1.size(), wrongNum);
        assertEquals(List.of(new Point(0, 3, 0)), result1, "wrong point found");

        // TC02: A ray that does not intersect the plane, not parallel, and not perpendicular
        assertNull(
                plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(-2, 7, 1))),
                wrongNum
        );

        // =============== Boundary Values Tests ==================
        // TC10: Ray lies on the plane
        assertNull(
                plane.findIntersections(new Ray(new Point(1, 2, 0), new Vector(1, 0, 0))),
                wrongNum
        );

        // TC11: Ray is parallel to the plane but not lying on it
        assertNull(
                plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 0))),
                wrongNum
        );

        // TC12: Ray is perpendicular to the plane and starts below it, intersects once
        final var result2 = plane.
                findIntersections(new Ray(new Point(0, -1, -2), new Vector(0, 0, 1)))
                .stream().toList();
        assertEquals(1, result2.size(), wrongNum);
        assertEquals(List.of(new Point(0, -1, 0)), result2, "wrong point found");

        // TC13: Ray is perpendicular to the plane and starts on the plane
        assertNull(
                plane.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 0, 1))),
                wrongNum
        );

        // TC14: Ray is perpendicular to the plane and starts above it
        assertNull(
                plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1))),
                wrongNum
        );

        // TC15: Ray starts at the reference point of the plane, neither perpendicular nor parallel
        assertNull(
                plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-2, 7, 1))),
                wrongNum
        );

        // TC16: Ray starts at a random point on the plane, neither perpendicular nor parallel
        assertNull(
                plane.findIntersections(new Ray(new Point(1, 2, 0), new Vector(-2, 7, 1))),
                wrongNum
        );
    }

    /**
     * Tests the {@code findGeoIntersections} method of the {@code Plane} class.
     */
    @Test
    public void testFindGeoIntersections() {
        Plane plane = new Plane(
                new Point(1, 0, 0),
                new Point(1, 2, 0),
                new Point(0, 1, 0)
        );

        // ============ Equivalence Partitions Tests ==============
        // TC01: A cutting ray, neither oblique nor perpendicular
        final var result1 = plane.
                findGeoIntersections(new Ray(new Point(0, 0, -2), new Vector(0, 3, 2)), 4)
                .stream().toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new GeoPoint(plane, new Point(0, 3, 0))), result1, "wrong point found");

        //TC02: The point is far away from the head of ray.
        assertNull(
                plane.
                        findGeoIntersections(new Ray(new Point(0, 0, -2), new Vector(0, 3, 2)),
                                0.5),
                "max distance"
        );

        // =============== Boundary Values Tests ==================
        //TC10: maxDistance == distance between point and head of the ray
        final var result2 = plane.
                findGeoIntersections(new Ray(new Point(0, 0, -2), new Vector(0, 3, 2)), sqrt(13))
                .stream().toList();
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(List.of(new GeoPoint(plane, new Point(0, 3, 0))), result2, "wrong point found");

    }
}