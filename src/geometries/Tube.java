package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

public class Tube extends RadialGeometry {

    /** Radius of the main axis of the cylinder. */
    protected final Ray axis;

    /**
     * Constructor to initialize Tube based on radius and ray of the main axis.
     * @param radius base's radius.
     * @param axis   main axis's Ray.
     */
    public Tube(double radius, Ray axis) {
        super(radius);
        this.axis = axis;
    }

    @Override
    public Vector getNormal(Point point) {
        double t=point.subtract(axis.getHead()).dotProduct(axis.getDirection());
        if(isZero(t))
            throw new IllegalArgumentException("ERROR: the vector is perpendicular to the ray");
        Point projection=axis.getPoint(t);
        return point.subtract(projection).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        return null;
    }
}
