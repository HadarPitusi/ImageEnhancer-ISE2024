package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    /**
     * Unit tests for the {@code findIntersections} method in the {@code Geometries} class.
     */
    @Test
    void testFindIntersections() {
        Point p1 = new Point(1, 0, 0);
        Point p2 = new Point(0, 1, 0);
        Point p3 = new Point(2, 3, 0);
        Point p4 = new Point(1, 0, 1);
        Point p5 = new Point(0, 1, 1);
        Point p6 = new Point(2, 3, 1);
        Point p7 = new Point(1, 1, -1);
        Vector v1 = new Vector(0, 0, 1);
        Ray ray1 = new Ray(new Point(0, 4, -1), v1);
        Ray ray2 = new Ray(p7, v1);
        Geometries emptyGeo = new Geometries();
        Geometries geo = new Geometries(
                new Plane(p1, p2, p3),
                new Triangle(p4, p5, p6),
                new Sphere(5, new Point(0, 0, 7))
        );

        // ============ Equivalence Partitions Tests ==============
        //TC01: Some shapes are intersected, but not all (plane(1), sphere(2))
        assertEquals(3, geo.findIntersections(ray1).size(), "ERROR: wrong result");

        // =============== Boundary Values Tests ==================
        //TC10: Empty collection of geometries.
        assertNull(emptyGeo.findIntersections(ray1), "Error: the collection is empty");

        //TC11: No shapes are intersected
        assertNull(geo.findIntersections(new Ray(new Point(6, 1, 1), v1)), "Error: no point should be returned");

        //TC12: Only one shape is intersected (plane(1))
        assertEquals(1, geo.findIntersections(new Ray(new Point(6, 1, -1), v1)).size(), "ERROR: wrong result");

        //TC13: All shapes are intersected
        assertEquals(4, geo.findIntersections(ray2).size(), "ERROR: wrong result");
    }
}