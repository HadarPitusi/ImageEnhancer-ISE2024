package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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

    @Test
    public void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: חותך באמצע
        Triangle triangle = new Triangle(
                new Point(3,0,0),
                new Point(-3, 0, 0),
                new Point(0, 3, 0)
        );
        final var result1 = triangle.findIntersections(new Ray(new Point(0,1,-1), new Vector(0,0,1))).stream().toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0,1,0)), result1, "Ray crosses sphere");
        //TC02: בחוץ מול הצלע
        assertNull(triangle.findIntersections(new Ray(new Point(2,2,-1), new Vector(0,0,1))), "Ray's line out of sphere");
        //TC03: בחוץ מול קודקוד
        assertNull(triangle.findIntersections(new Ray(new Point(0,4,-1), new Vector(0,0,1))), "Ray's line out of sphere");

        // =============== Boundary Values Tests ==================
        //TC10 על הצלע
        assertNull(triangle.findIntersections(new Ray(new Point(1,0,-1), new Vector(0,0,1))), "Ray's line out of sphere");
        //TC11 על הקודקוד
        assertNull(triangle.findIntersections(new Ray(new Point(3,0,0), new Vector(0,0,1))), "Ray's line out of sphere");
        //TC12 על המשך צלע
        assertNull(triangle.findIntersections(new Ray(new Point(4,0,-1), new Vector(0,0,1))), "Ray's line out of sphere");
    }

}

