package primitives;

import geometries.Polygon;
import org.junit.jupiter.api.Test;

import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    private final double DELTA = 0.000001;

    @Test
    void testVector() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        //Checking that the constructor does not throw an exception
        assertDoesNotThrow(()->new Vector(1,2,3),
                "Throwing an unnecessary exception");
        //Checking that the constructor does not throw an exception
        assertDoesNotThrow(()->new Vector(new Double3(1,2,3)),
                "Throwing an unnecessary exception");
        //Checking that the constructor throws a correct exception
        assertThrows(IllegalArgumentException.class,
                ()->new Vector(0,0,0),
                "ERROR: Vector is zero");
        //Checking that the constructor throws a correct exception
        assertThrows(IllegalArgumentException.class,
                ()->new Vector(new Double3(0,0,0)),
                "ERROR: Vector is zero");
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector vector=new Vector(1,2,3);
        //Makes sure that the calculation of the length of the squared section is correct
        assertEquals(14,
                vector.lengthSquared(),
                DELTA,
                "ERROR: lengthSquared() wrong value");
    }

    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector vector= new Vector(2,3,4);
        //Verifies correct scalar multiplication calculation
        assertEquals(new Vector(-4,-6,-8),
                vector.scale(-2),
                "ERROR: scale() wrong value");
        // =============== Boundary Values Tests ==================
        // TC10: Scalar multiplication by 0
        assertThrows(IllegalArgumentException.class,
                ()->vector.scale(0),
                "ERROR: scale() by 0 does not throw an exception");
    }

    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        //Ensures correct vector length calculation
        Vector vector=new Vector(1,2,3);
        assertEquals(sqrt(14),
                vector.length(),
                DELTA,
                "ERROR: length() wrong value");
    }

    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(2,3,4);
        //Make sure that the computation of adding a vector to a vector does not throw an exception
        assertDoesNotThrow(()->v1.add(v2),
                "Throwing an unnecessary exception");
        //Makes sure that the calculation of adding vector to vector is correct
        assertEquals(new Vector(3,5,7),
                v1.add(v2),
                "ERROR: Vector + Vector does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC10: Equal and opposite vectors
        assertThrows(IllegalArgumentException.class,
                ()->v1.add(new Vector(-1,-2,-3)),
                "ERROR: Vector + -itself does not throw an exception");
    }

    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(2,3,4);
        //Ensures that scalar multiplication returns correct values
        assertEquals(20,
                v1.dotProduct(v2),
                DELTA,
                "ERROR: dotProduct() wrong value");
        //Ensures that scalar product returns 0 for perpendicular vectors
        assertEquals(0,
                v1.dotProduct(new Vector(0,3,-2)),
                DELTA,
                "ERROR: dotProduct() for orthogonal vectors is not zero");
    }

    @Test
    void testCrossProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        //Ensures that the vector product returns a correct vector
        assertEquals(new Vector(-13,2,3),
                v1.crossProduct(v3),
                "ERROR: crossProduct() wrong result");
        //התלבטנו לגבי הבדיקות, נראה לנו מיותר לפרט יותר את מקרי הקצה למנוגדים, שווים, מנוגדים ושווים וכו, הכל מתבסס בעצם על אותה נקודה והיא שהם מקבילים
        // =============== Boundary Values Tests ==================
        // TC10: parallel vectors
        assertThrows(IllegalArgumentException.class,
                ()->v1.crossProduct(v2),
                "ERROR: crossProduct() for parallel vectors does not throw an exception");
    }

    @Test
    void testNormalize() {
        //אולי צריך לשנות
        //לבדוק מה הכוונה שהוקטור בכיוון הנגדי- להוסיף בדיקה?
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector vector = new Vector(1, 2, 3);
        //Checks that the normalization brings correct values
        assertEquals(new Vector(0.2672612419124244,0.5345224838248488,0.8017837257372732),
                vector.normalize(),
                "ERROR: normalize() wrong value");
    }

    @Test
    void testSubstruct(){
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Vector v1=new Vector(1,2,3);
        Vector v2=new Vector(2,3,4);
        //Checks that there is no unnecessary abnormal injection
        assertDoesNotThrow(()->v1.subtract(v2),
                "Throwing an unnecessary exception");
        //Checks that vector subtraction returns correct values
        assertEquals(new Vector(-1,-1,-1),
                v1.subtract(v2),
                "ERROR: Vector - Vector does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC10: Subtracting a vector from itself
        assertThrows(IllegalArgumentException.class,
                ()->v1.subtract(v1),
                "ERROR: Vector - itself does not throw an exception");
    }
}