package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetPoint() {
        Point point=new Point(0,0,1);
        Ray ray=new Ray(point, new Vector(1,0,0));
        // ============ Equivalence Partitions Tests ==============
        //TC01: negative distance from the head
        assertEquals(new Point(-1,0,1), ray.getPoint(-1), "ERROR: wrong result- negative distance");
        //TC02: positive distance from the head
        assertEquals(new Point(2,0,1), ray.getPoint(2), "ERROR: wrong result- positive distance");
        // =============== Boundary Values Tests ==================
        //TC10: The head of the ray and the point are same
        assertEquals(point, ray.getPoint(0), "Error: The head of the ray on the plane");
    }
}