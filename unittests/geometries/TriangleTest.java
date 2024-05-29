package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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

        Triangle tri = new Triangle(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        // ensure there are no exceptions
        assertDoesNotThrow(() -> tri.getNormal(new Point(0, 0, 1)), "");
        // ensure |result| = 1
        assertEquals(1, tri.getNormal(new Point(0, 0, 1)).length(), DELTA, "Polygon's normal is not a unit vector");
    }

}