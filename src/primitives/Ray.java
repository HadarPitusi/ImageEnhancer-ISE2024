package primitives;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.isZero;

public class Ray {

    private static final double DELTA = 0.1;

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
     * Constructs a new Ray.
     *
     * @param head      the origin point of the ray
     * @param direction the direction vector of the ray
     * @param normal    the normal vector used to adjust the origin point
     */
    public Ray(Point head, Vector direction, Vector normal) {
        //Checking the direction and size of the displacement on top of the normal
        double direction_normal = direction.dotProduct(normal);
        this.head = isZero(direction_normal) ? head :
                direction_normal > 0 ? head.add(normal.scale(DELTA)) : head.add(normal.scale(-DELTA));
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
     * @param geoPointList a list of GeoPoint objects to search from.
     * @return the closest GeoPoint to the head, or null if the list is empty.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPointList) {
        if (geoPointList == null)
            return null;

        GeoPoint closestPoint = geoPointList.getFirst();
        double minDistance = closestPoint.point.distanceSquared(this.head);
        double distance = 0;

        for (int i = 1; i < geoPointList.size(); i++) {
            distance = geoPointList.get(i).point.distanceSquared(this.head);
            if (minDistance > distance) {
                minDistance = distance;
                closestPoint = geoPointList.get(i);
            }
        }
        return closestPoint;
    }

    /**
     * Finds the closest Point from the list to the head of this object.
     *
     * @param pointList a list of Point objects to search from.
     * @return the closest Point to the head, or null if the list is null or empty.
     */
    public Point findClosestPoint(List<Point> pointList) {
        return pointList == null || pointList.isEmpty() ? null
                : findClosestGeoPoint(pointList.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

}
