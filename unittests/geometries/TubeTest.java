package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point point=new Point(5,2,3);
       Tube tube=new Tube(4, new Ray(new Point(1,2,3),new Vector(1,2,3)));
        // Make sure the values of the normal are correct
        assertEquals(new Vector(0.9636241116594316,-0.1482498633322203,-0.22237479499833038),
                tube.getNormal(point),
                "ERROR: wrong normal value");
        // ============ Boundary Values Tests==============
        // TC10: Error throwing test that a vector is perpendicular to the ray
       assertThrows(IllegalArgumentException.class,
               ()->tube.getNormal(new Point(2,0,4)),
               "ERROR: the vector is perpendicular to the ray");
    }
}