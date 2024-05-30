package primitives;

import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {


    private final Point head;
    private final Vector direction;

    /**
     * Constructor to initialize Ray based on Point and Vector.
     * @param head      Point of the ray's head.
     * @param direction The direction vector of the Ray.
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Returns the head point of the vector.
     * @return the head point
     */
    public Point getHead() {
        return head;
    }

    /**
     * Returns the direction vector.
     * @return the direction vector
     */
    public Vector getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return (obj instanceof Ray other) && this.head.equals(other.head) && this.direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "head=" + head +
                ", direction=" + direction +
                '}';
    }
    /**
     * Getter for point at a given distance from ray origin
     *
     * @param t distance from ray origin
     * @return point
    */
    public Point getPoint(double t)
    {
        return isZero(t)? head: head.add(direction.scale(t));
    }
}
