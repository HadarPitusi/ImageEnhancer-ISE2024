package geometries;

import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Geometries extends Intersectable {

    private final List<Intersectable> geometries = new LinkedList<>();

    /**
     * Default constructor that initializes an empty collection of geometric objects.
     */
    public Geometries() {
    }

    /**
     * Constructor that initializes the collection with a given set of geometric objects.
     *
     * @param geometries Varargs parameter representing the geometric objects
     *                   to be added to the collection.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * Adds one or more geometric objects to the collection.
     *
     * @param geometries Varargs parameter representing the geometric objects
     *                   to be added to the collection.
     */
    public void add(Intersectable... geometries) {
        this.geometries.addAll(Arrays.asList(geometries));
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> intersections = new LinkedList<>();
        for (Intersectable geometry : geometries) {
            List<GeoPoint> tempIntersections = geometry.findGeoIntersections(ray, maxDistance);
            if (tempIntersections != null) {
                intersections.addAll(tempIntersections);
            }
        }
        return intersections.isEmpty() ? null : intersections;
    }
}
