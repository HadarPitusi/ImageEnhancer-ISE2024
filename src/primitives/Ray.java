package primitives;

import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

public class Ray {


    private final Point head;
    private final Vector direction;

    /**
     * Constructor to initialize Ray based on Point and Vector.
     *
     * @param head      Point of the ray's head.
     * @param direction The direction vector of the Ray.
     */
    public Ray(Point head, Vector direction) {
        this.head = head;
        this.direction = direction.normalize();
    }

    /**
     * Returns the head point of the vector.
     *
     * @return the head point
     */
    public Point getHead() {
        return head;
    }

    /**
     * Returns the direction vector.
     *
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
    public Point getPoint(double t) {
        return isZero(t) ? head : head.add(direction.scale(t));
    }

    /**
     * Finds the closest point to the reference point (head) from a list of points.
     *
     * @param listPoints the list of points to search from.
     * @return the closest point to the reference point, or {@code null} if the list is empty.
     */
    public Point findClosestPoint(List<Point> listPoints) {
        if (listPoints.isEmpty())
            return null;

        Point closestPoint = listPoints.getFirst();
        double minDistance = closestPoint.distanceSquared(this.head);
        double distance = 0;

        for (int i = 1; i < listPoints.size(); i++) {
            distance = listPoints.get(i).distanceSquared(this.head);
            if (minDistance > distance) {
                minDistance = distance;
                closestPoint = listPoints.get(i);
            }
        }
        return closestPoint;
    }
}
