package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {
    private final double DELTA = 0.000001;

    @Test
    void Plane() {
        // ============ Boundary Values Tests ==============
        // TC10:2 points converge
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 1), new Point(0, 0, 1), new Point(1, 1, 1)),
                "ERROR: 2 points converge");

        // TC11:The points that are on the same line
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(-3, 0, 0), new Point(0, 0, 4), new Point(3, 0, 8)),
                "ERROR: The points are on the same line");
    }

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Plane plane = new Plane(new Point(0, 2, 3), new Point(0, 1, 0), new Point(1, 1, 1));
        // Make sure the values of the normal are correct
        assertEquals(new Vector(-0.30151134457776363, -0.9045340337332909, 0.30151134457776363), plane.getNormal(), "ERROR: Plane's normal is not a unit vector");
        // ensure |result| = 1
        assertEquals(1, plane.getNormal().length(), DELTA, "ERROR: Plane's normal is not a unit vector");
    }


    @Test
    void testTestGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        //לשאול מה הקטע של הפונקציה הזאת עם הנקודה, הרי אם כבר יש מישור וודאי יש נורמל. האם זה סתם להעתיק מהטסט הקודם?
        //תהיות חשובות
        //יכולה להיות בעיה עם הכיוון? להוסיף בדיקה?
        Point point = new Point(3, 1, 2);
        Plane plane = new Plane(new Point(0, 1, 1), new Point(0, 1, 0), new Point(1, 1, 1));
        // Make sure the values of the normal are correct
        assertEquals(new Vector(0, -1, 0), plane.getNormal(point), "ERROR: wrong normal value");
        // ensure |result| = 1
        assertEquals(1, plane.getNormal(point).length(), DELTA, "ERROR: Plane's normal is not a unit vector");
    }

    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(1, 0, 0), new Point(1, 2, 0), new Point(0, 1, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01 חותך לא מקביל לא מאונך
        final var result1 = plane.findIntersections(new Ray(new Point(0, 0, -2), new Vector(0, 3, 2))).stream().toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0, 3, 0)), result1, "Ray crosses Plane");
        //TC02 לא חותך לא מקביל לא מאונך
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(-2, 7, 1))), "Wrong number of points");

        // =============== Boundary Values Tests ==================
        //TC10 הקרן מונחת על המישור
        assertNull(plane.findIntersections(new Ray(new Point(1, 2, 0), new Vector(1, 0, 0))), "Wrong number of points");
        //TC11 הקרן מקבילה למישור אך לא מונחת עליו
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 0))), "Wrong number of points");
        //TC12 מאונך למישור מתחיל מתחתיו, חיתוך 1
        final var result2 = plane.findIntersections(new Ray(new Point(0, -1, -2), new Vector(0, 0, 1))).stream().toList();
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(List.of(new Point(0, -1, 0)), result2, "Ray crosses Plane");
        //TC13  מאונך למישור מתחיל המישור
        assertNull(plane.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 0, 1))), "Wrong number of points");
        //TC14 מאונך מתחיל אחרי המישור
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1))), "Wrong number of points");
        //TC15 מתחילה בנקודת הייצוג של המישור,לא מאונך ולא מקביל
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-2, 7, 1))), "Wrong number of points");
        //TC16 מתחיל מנקודה רנדומלית על המישור, לא מאונך ולא מקביל
        assertNull(plane.findIntersections(new Ray(new Point(1, 2, 0), new Vector(-2, 7, 1))), "Wrong number of points");
    }
}