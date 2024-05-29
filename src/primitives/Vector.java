package primitives;

public class Vector extends Point {

    /**
     * Constructor to initialize Vector based three number values.
     * @param x position on the x-axis
     * @param y position on the x-axis
     * @param z position on the x-axis
     * @throws IllegalArgumentException in case of zero vector
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        Double3 newD = new Double3(x, y, z);
        if (newD.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector is zero");
    }

    /**
     * Constructor to initialize Vector based double3 value's.
     * @param double3 the values for the new Vector(and Point..)
     * @throws IllegalArgumentException in case of zero vector
     */
    public Vector(Double3 double3) {
        super(double3);
        if (double3.equals(Double3.ZERO))
            throw new IllegalArgumentException("Vector is zero");
    }

    /**
     * Calculation of the squared length.
     * @return the squared length.
     */
    public double lengthSquared() {
     //   return (this.distanceSquared(ZERO)); //Probably not effective
        return(this.xyz.d1*this.xyz.d1+this.xyz.d2*this.xyz.d2+this.xyz.d3*this.xyz.d3);
    }

    /**
     * Calculate the length.
     * @return the result
     */
    public double length() {
        return (Math.sqrt(this.lengthSquared()));
    }

    /**
     * Multiplication by scalar
     * @param num the scalar
     * @return the result vector
     * @throws IllegalArgumentException in case of multiplies by zero.
     */
    public Vector scale(double num) {
        if (num == 0)
            throw new IllegalArgumentException("ERROR: scale Vector in Zero");
        return new Vector(this.xyz.d1 * num, this.xyz.d2 * num, this.xyz.d3 * num);
    }

    /**
     * Addition a vector.
     * @param vector2 the vector we want to add.
     * @return the result Vector.
     * @throws IllegalArgumentException in case of Vector-itself
     */
    public Vector add(Vector vector2) {
        if (this.xyz.add(vector2.xyz).equals(Double3.ZERO))
            throw new IllegalArgumentException("ERROR: vector - itself");
        return new Vector(this.xyz.add(vector2.xyz));
    }

    /**
     * Scalar multiplication with a vector
     * @param vector2 The vector we want to multiply.
     * @return the result.
     */
    public double dotProduct(Vector vector2) {
        return (this.xyz.d1 * vector2.xyz.d1 + this.xyz.d2 * vector2.xyz.d2 + this.xyz.d3 * vector2.xyz.d3);
    }

    /**
     * Cross product between 2 vectors.
     * @param vector2 the vector we want to multiply.
     * @return the result Vector.
     */
    public Vector crossProduct(Vector vector2) {
        Vector vec = new Vector(this.xyz.d2 * vector2.xyz.d3 - this.xyz.d3 * vector2.xyz.d2,
                this.xyz.d3 * vector2.xyz.d1 - this.xyz.d1 * vector2.xyz.d3,
                this.xyz.d1 * vector2.xyz.d2 - this.xyz.d2 * vector2.xyz.d1
                 );
        return vec;
    }

    /**
     * Vector normalization.
     * @return unit vector in the same direction as the original vector.
     */
    public Vector normalize() {
        double length=this.length(); //for efficiency reasons
        return new Vector(xyz.reduce(length));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Vector other) && super.equals(obj);
    }

    @Override
    public String toString() {
        return "{" +
                "(0, 0, 0) " +
                super.toString() +
                "}";
    }
}
