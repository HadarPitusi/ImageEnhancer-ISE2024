package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;
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
     * Finds the closest GeoPoint from the list to the head of this object.
     *
     * @param listPoints a list of GeoPoint objects to search from.
     * @return the closest GeoPoint to the head, or null if the list is empty.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> listPoints) {
        if (listPoints.isEmpty())
            return null;

        GeoPoint closestPoint = listPoints.getFirst();
        double minDistance = closestPoint.point.distanceSquared(this.head);
        double distance = 0;

        for (int i = 1; i < listPoints.size(); i++) {
            distance = listPoints.get(i).point.distanceSquared(this.head);
            if (minDistance > distance) {
                minDistance = distance;
                closestPoint = listPoints.get(i);
            }
        }
        return closestPoint;
    }

    /**
     * Finds the closest Point from the list to the head of this object.
     *
     * @param points a list of Point objects to search from.
     * @return the closest Point to the head, or null if the list is null or empty.
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

}
