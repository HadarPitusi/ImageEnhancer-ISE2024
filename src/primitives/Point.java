package primitives;

import java.util.Objects;

/**
 * A static field initializing a point at the beginning of the axes
 */
public class Point {

    /**A static field initializing a point at the beginning of the axes*/
    public static final Point ZERO = new Point(Double3.ZERO);
    /** Field representing a point */
    protected final Double3 xyz;

    /**
     * Constructor to initialize Point based three number values
     * @param x position on the x-axis
     * @param y position on the x-axis
     * @param z position on the x-axis
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor to initialize Point based doubl3 value's
     * @param xyz the values for the new Point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Calculates subtraction.
     * @param point the point we want to sub
     * @return the Vector result
     * @throws IllegalArgumentException in case of point-itself(vector=(0,0,0))
     */
    public Vector subtract(Point point) {
        if (xyz.subtract(point.xyz).equals(Double3.ZERO))
            throw new IllegalArgumentException("ERROR: (point - itself) it's Illegal");
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Calculates an addition
     * @param vector the vector that represented by the point we want to add
     * @return new Point that we get from the adding
     */
    public Point add(Vector vector) {
        return new Point(this.xyz.add(vector.xyz));
    }

    /**
     * Calculates distance Squared
     * @param point the point from which we want to check the distance.
     * @return the distance squared
     */
    public double distanceSquared(Point point) {
        double x = xyz.d1 - point.xyz.d1;
        double y = xyz.d2 - point.xyz.d2;
        double z = xyz.d3 - point.xyz.d3;
        return ((x * x) + (y * y) + (z * z));
    }

    /**
     * Calculates distance
     * @param point the point from which we want to check the distance.
     * @return the distance.
     */
    public double distance(Point point) {
        return (Math.sqrt(distanceSquared(point)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Point other) && this.xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return this.xyz.toString();
    }
}
