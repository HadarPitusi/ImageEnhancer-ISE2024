package geometries;

import primitives.*;
import java.util.*;


/**
 * Abstract class representing an intersectable object in a geometric space.
 */
public abstract class Intersectable {

    /**
     * Helper method to find the intersection points with a given ray.
     *
     * @param ray the ray to intersect with.
     * @return a list of intersection points as {@code GeoPoint} objects.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * Finds the intersection points with a given ray.
     *
     * @param ray the ray to intersect with.
     * @return a list of intersection points as {@code Point} objects, or {@code null} if there are no intersections.
     */
    public List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds the intersection points with a given ray.
     *
     * @param ray the ray to intersect with.
     * @return a list of intersection points as {@code GeoPoint} objects, or {@code null} if there are no intersections.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * Class representing a point of intersection between a ray and a geometry.
     */
    public static class GeoPoint {
        /**
         * The geometry that was intersected.
         */
        public Geometry geometry;

        /**
         * The point of intersection.
         */
        public Point point;

        /**
         * Constructs a {@code GeoPoint} with the given geometry and point.
         *
         * @param geometry the geometry that was intersected.
         * @param point the point of intersection.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "(" + geometry +','+ point + ')';
        }
    }
}
