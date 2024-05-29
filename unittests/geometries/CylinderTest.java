package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void testGetNormal() {
        Ray ray = new Ray(new Point(0,1,0), new Vector(0,1,0));
        Cylinder  cylinder = new Cylinder(2, ray ,4);
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        assertEquals(new Vector(1,0,0),cylinder.getNormal(new Point(2,2,0)) ,
                "Normal abnormality");
        // TC02: Makes sure to return correct values for a point on the bottom base
        assertEquals(new Vector(0,-1,0), cylinder.getNormal(new Point(1,1,0)),
                "Normal abnormality");
        // TC03: Ensures that correct values are returned for a point on the top base
        assertEquals(new Vector(0,1,0), cylinder.getNormal(new Point(1,5,0)),
                "Normal abnormality");
        // =============== Boundary Values Tests ==================
        // TC10: The point in the center of the lower base
        assertEquals(new Vector(0,-1,0),cylinder.getNormal(new Point(0,1,0)),
                "Normal abnormality");
        // TC11: The point in the center of the upper base
        assertEquals(new Vector(0,1,0), cylinder.getNormal(new Point(0,5,0)),
                "Normal abnormality");
    }
}