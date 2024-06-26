package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

import java.util.List;

import static java.lang.Math.sqrt;

//אצל יאיר בסרטון מופיע ש Sphere implements Gieometry
public class Sphere extends RadialGeometry {

    private final Point center;

    /**
     * Constructor to initialize Sphere based on radius and center point.
     *
     * @param center center point.
     * @param radius sphere's radius.
     */
    public Sphere(Point center, double radius) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return (point.subtract(center).normalize());
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getHead();
        Vector center_p0;
        //p0 in center
        try {
            center_p0 = (center.subtract(p0));
        } catch (IllegalArgumentException error) {
            return List.of(new GeoPoint(this, p0.add(ray.getDirection().scale(radius))));
        }
        double tm = alignZero(ray.getDirection().dotProduct(center_p0));
        double d = alignZero(sqrt(center_p0.lengthSquared() - tm * tm));

        //Ray is tangent to or outside the circle - no intersection point.
        if (d >= radius)
            return null;
        double th = alignZero(sqrt((radius * radius - d * d)));

        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 > 0 && t2 > 0 && alignZero(t1 - maxDistance) <= 0 && alignZero(t2 - maxDistance) <= 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)),
                    new GeoPoint(this, ray.getPoint(t2)));
        if (t2 > 0 && alignZero(t2 - maxDistance) <= 0)
            return List.of(new GeoPoint(this, ray.getPoint(t2)));
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        return null;
    }
}