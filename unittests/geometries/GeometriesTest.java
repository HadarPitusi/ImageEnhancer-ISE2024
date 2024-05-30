package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testFindIntersections() {
        Point p1= new Point(1,0,0);
        Point p2= new Point(0,1,0);
        Point p3= new Point(2,3,0);
        Point p4= new Point(1,0,1);
        Point p5= new Point(0,1,1);
        Point p6= new Point(2,3,1);
        Point p7= new Point(1,1,-1);
        Vector v1=new Vector(0,0,1);
        Ray ray1= new Ray(new Point(5,6,-7), v1);
        Ray ray2=new Ray(p7, v1);
        Geometries emptyGeo= new Geometries();
        //לא הוספנו ספרה לבדיקה כי היא לא עובדת
        Geometries geo=new Geometries(new Plane(p1,p2,p3), new Triangle(p4,p5,p6));
        //List<Intersectable> geometrie=new LinkedList<>();
        // ============ Equivalence Partitions Tests ==============
        //TC01: כמה צורות נחתכות אך לא כולן
        assertEquals(1, geo.findIntersections(ray1).size(), "ERROR: wrong result");
        // =============== Boundary Values Tests ==================
        // TC10: אוסף גופים ריק
        assertNull(emptyGeo.findIntersections(ray1),"Error: the collection is empty");
        //TC11: אף צורה לא נחתכת
        assertNull(geo.findIntersections(new Ray(new Point(1,1,2),v1)),"Error: no point should be returned");
        //TC12: צורה אחת בלבד נחתכת
        assertEquals(1, geo.findIntersections(ray1).size(), "ERROR: wrong result");
        //TC13: כל הצורות נחתכות
        assertEquals(2, geo.findIntersections(ray2).size(), "ERROR: wrong result");
    }
}