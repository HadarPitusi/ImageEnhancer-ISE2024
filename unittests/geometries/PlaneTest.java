package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    private final double DELTA = 0.000001;

    @Test
    void Plane(){
        // ============ Boundary Values Tests ==============
        // TC10:2 points converge
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0,0,1), new Point(0,0,1),new Point(1,1,1)),
                "ERROR: 2 points converge");

        // TC11:The points that are on the same line
        assertThrows(IllegalArgumentException.class,
                ()-> new Plane(new Point(-3,0,0),new Point(0,0,4), new Point(3,0,8)),
                "ERROR: The points are on the same line");
    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Plane p=new Plane (new Point(0,2,3), new Point(0,1,0), new Point(1,1,1));
        // Make sure the values of the normal are correct
        assertEquals(new Vector(0.30151134457776363,0.9045340337332909,-0.30151134457776363),p.getNormal(),"ERROR: Plane's normal is not a unit vector");
        // ensure |result| = 1
        assertEquals(1, p.getNormal().length(), DELTA,"ERROR: Plane's normal is not a unit vector");
    }


    @Test
    void testTestGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        //לשאול מה הקטע של הפונקציה הזאת עם הנקודה, הרי אם כבר יש מישור וודאי יש נורמל. האם זה סתם להעתיק מהטסט הקודם?
        //תהיות חשובות
        //יכולה להיות בעיה עם הכיוון? להוסיף בדיקה?
        Point point= new Point(3,1,2);
        Plane p=new Plane (new Point(0,1,1), new Point(0,1,0), new Point(1,1,1));
        // Make sure the values of the normal are correct
        assertEquals(new Vector(0,-1,0), p.getNormal(point), "ERROR: wrong normal value");
        // ensure |result| = 1
        assertEquals(1, p.getNormal(point).length(), DELTA, "ERROR: Plane's normal is not a unit vector");
    }

    @Test
    void testFindIntersections() {
    }
}