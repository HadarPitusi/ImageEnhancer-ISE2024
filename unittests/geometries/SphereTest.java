package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
//import primitives.Vector;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    private final double DELTA = 0.000001;

    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Sphere s = new Sphere(4, new Point(0, 0, 2));
        // Make sure the values of the normal are correct
        assertEquals(new Vector(0, 0, 1),
                s.getNormal(new Point(0, 0, 6)),
                "ERROR: wrong normal value");
        // ensure |result| = 1
         assertEquals(1,
                 s.getNormal(new Point(0,0,6)).length(),
                 DELTA,
                 "ERROR: Sphere's normal is not a unit vector");
    }

    private final Point p001 = new Point(0, 0, 1);
    private final Point p100 = new Point(1, 0, 0);
    private final Vector v001 = new Vector(0, 0, 1);

    /**
     * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(1d, p100);
        final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
        final var exp = List.of(gp1, gp2);
        final Vector v310 = new Vector(3, 1, 0);
        final Vector v110 = new Vector(1, 1, 0);
        final Vector v912 = new Vector(1.93, -0.1, 0.29);
        final Vector v100 = new Vector(1, 0, 0);
        final Vector v1 = new Vector(1, 0, 0);
        final Vector v0 = new Vector(0, 0, 1);
        final Point p01 = new Point(-1, 0, 0);
        final Point p285 = new Point(1.2, -0.85, 0.49);
        final Point p912 = new Point(1.93, -0.1, 0.29);
        final Point p310 = new Point(3, 1, 0);
        final Point p840 = new Point(1.89, 0.45, 0);
        final Point p140 = new Point(0.11, -0.45, 0);
        final Point p710 = new Point(0.7, -0.15, 0);
        final Point p0 = new Point(0, 0, -2);
        final Point p000 = new Point(0, 0, 0);
        final var exp1 = List.of(p840, p140);
        final var exp2 = List.of(new Point(2, 0, 0), new Point(0,0,0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        final var result1 = sphere.findIntersections(new Ray(p01, v310))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(p01))).toList();
        assertEquals(2, result1.size(), "Wrong number of points");
         assertEquals(exp, result1, "Ray crosses sphere");
        // TC03: Ray starts inside the sphere (1 point)
        final var result2 = sphere.findIntersections(new Ray(new Point(1.5,0,0), new Vector(0.5,0,0))).stream().toList();
        assertEquals(1, result2.size(), "Wrong number of points");
        assertEquals(List.of(new Point(2,0,0)), result2, "Ray crosses sphere");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(p01, v310.scale(-1))), "Ray's line out of sphere");
        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        final var result3 = sphere.findIntersections(new Ray(new Point(2,0,0), new Vector(-1,1,0))).stream().toList();
        assertEquals(1, result3.size(), "Wrong number of points");
        assertEquals(List.of(new Point(1, 1, 0)), result3, "Ray crosses sphere");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1,1,0), new Vector(1,1,0))), "Ray's line out of sphere");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        final var result4 = sphere.findIntersections(new Ray(new Point(3,0,0), new Vector(-2,0,0)))
                .stream().sorted(Comparator.comparingDouble(p -> p.distance(new Point(3,0,0)))).toList();
        assertEquals(2, result4.size(), "Wrong number of points");
        assertEquals(exp2, result4, "Ray crosses sphere");
        // TC14: Ray starts at sphere and goes inside (1 points)
        final var result5 = sphere.findIntersections(new Ray(p840, v100)).stream().toList();
        assertEquals(1, result5.size(), "Wrong number of points");
        assertEquals(List.of(p140), result5, "Ray crosses sphere");
        // TC15: Ray starts inside (1 points)
        final var result6 = sphere.findIntersections(new Ray(p710, v100)).stream().toList();
        assertEquals(1, result6.size(), "Wrong number of points");
        assertEquals(List.of(p140), result6, "Ray crosses sphere");
        // TC16: Ray starts at the center (1 points)
        final var result7 = sphere.findIntersections(new Ray(p100, v100)).stream().toList();
        assertEquals(1, result7.size(), "Wrong number of points");
        assertEquals(List.of(p140), result7, "Ray crosses sphere");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(p840, v100.scale(-1))), "Ray's line out of sphere");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-0.3, -0.65, 0), v100)),"Ray's line out of sphere");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(p0,v0)),"Ray's line is tangent to the sphere");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(p000,v0)),"Ray's line is tangent to the sphere");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(p001,v0)),"Ray's line is tangent to the sphere");

        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(p01,v0)),"Ray's line is tangent to the sphere");
    }
}