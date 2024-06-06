package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    private final double DELTA = 0.000001;

    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point  p1 = new Point(1, 2, 3);
        Point  p2 = new Point(2, 4, 6);
        //Makes sure the value of the subtraction is correct
        assertEquals(new Vector(-1,-2,-3),
                p1.subtract(p2),
                "ERROR: (point2 - point1) does not work correctly");
        //Verifies that the value of vector subtraction itself is correct
        assertThrows(IllegalArgumentException.class,
                () -> p1.subtract(p1),
                "ERROR: (point - itself) does not throw an exception");
    }

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point  point = new Point(1, 2, 3);
        Vector  vector = new Vector(2, 4, 6);
        //Makes sure the value of the connection is correct
        assertEquals(new Point(3,6,9),
                point.add(vector),
                "ERROR: (point + vector) = other point does not work correctly");
       //Connecting a vector with its opposite vector
        assertEquals(new Point(0,0,0),
                point.add(new Vector(-1,-2,-3)),
                "ERROR: (point + vector) = center of coordinates does not work correctly");
    }

    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(2, 4, 6);
        //Makes sure that the value of the distance between a point and itself in the square is correct
        assertEquals(0,
                p1.distanceSquared(p1),
                DELTA,
                "ERROR: point squared distance to itself is not zero");
        //Makes sure that the value of the distance between two points in the square is correct
        assertEquals(14,
                p2.distanceSquared(p1),
                DELTA,
                "ERROR: squared distance between points is wrong");
    }

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(4, 5, 6);
        //Makes sure that the value of the distance between a point and itself is correct
        assertEquals(0,
                p1.distance(p1),
                DELTA,
                "ERROR: point distance to itself is not zero");
        //Makes sure that the value of the distance between two points is correct
        assertEquals(sqrt(27),
                p2.distance(p1),
                DELTA,
                "ERROR: squared distance between points is wrong");
    }
}