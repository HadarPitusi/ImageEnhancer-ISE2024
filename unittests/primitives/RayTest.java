package primitives;

import org.junit.jupiter.api.Test;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void testGetPoint() {
        Point point = new Point(0, 0, 1);
        Ray ray = new Ray(point, new Vector(1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        //TC01: negative distance from the head
        assertEquals(new Point(-1, 0, 1), ray.getPoint(-1), "ERROR: wrong result- negative distance");
        //TC02: positive distance from the head
        assertEquals(new Point(2, 0, 1), ray.getPoint(2), "ERROR: wrong result- positive distance");
        // =============== Boundary Values Tests ==================
        //TC10: The head of the ray and the point are same
        assertEquals(point, ray.getPoint(0), "Error: The head of the ray on the plane");
    }

    @Test
    void testFindClosestPoint() {
        List<Point> points1 = new ArrayList<>();
        Ray ray = new Ray(new Point(1, 0, 0), new Vector(1, 0, 0));
        Point p102 = new Point(1, 0, 0.2);
        Point p359 = new Point(3, 5, 9);
        Point p987 = new Point(9, 8, 7);
        points1.add(p987);
        points1.add(p102);
        points1.add(p359);
        List<Point> points2 = new ArrayList<>();
        points2.add(p102);
        points2.add(p987);
        points2.add(p359);
        List<Point> points3 = new ArrayList<>();
        points3.add(p987);
        points3.add(p359);
        points3.add(p102);

        // ============ Equivalence Partitions Tests ==============
        //TC01: Closest point is in the middle of the list
        assertEquals(p102, ray.findClosestPoint(points1), "Error: The closest point is not correct");
        // =============== Boundary Values Tests ==================
        //TC10: Empty list
        assertNull(ray.findClosestPoint(new ArrayList<>()), "Error: The closest point is null");
        //TC11: Closest point is first in the list
        assertEquals(p102, ray.findClosestPoint(points2), "Error: The closest point is not correct- first");
        //TC12: closest point is last in the list
        assertEquals(p102, ray.findClosestPoint(points3), "Error: The closest point is not correct- last");
    }
}