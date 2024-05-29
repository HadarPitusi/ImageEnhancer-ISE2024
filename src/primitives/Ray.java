package primitives;

import java.util.Objects;

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
}
