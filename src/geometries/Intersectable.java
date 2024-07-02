package geometries;

import primitives.*;

import java.util.*;


/**
 * Abstract class representing an intersectable object in a geometric space.
 */
public abstract class Intersectable {


    /**
     * Finds intersections between the given ray and geometries within the scene, up to a maximum distance.
     *
     * @param ray         the ray to check for intersections
     * @param maxDistance the maximum distance to check for intersections
     * @return a list of GeoPoint objects representing the intersection points, or null if no intersections are found
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * Finds intersections between the given ray and geometries within the scene.
     *
     * @param ray the ray to check for intersections
     * @return a list of Points representing the intersection points, or null if no intersections are found
     */
    public List<Point> findIntersections(Ray ray) {
        List<GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * Finds intersections between the given ray and geometries within the scene.
     *
     * @param ray the ray to check for intersections
     * @return a list of GeoPoint objects representing the intersection points, or null if no intersections are found
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * Finds intersections between the given ray and geometries within the scene, up to a maximum distance.
     *
     * @param ray         the ray to check for intersections
     * @param maxDistance the maximum distance to check for intersections
     * @return a list of GeoPoint objects representing the intersection points, or null if no intersections are found
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
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
         * @param point    the point of intersection.
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (!(object instanceof GeoPoint geoPoint)) return false;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "(" + geometry + ',' + point + ')';
        }
    }
}
