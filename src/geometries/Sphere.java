package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.*;

import java.util.List;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

//אצל יאיר בסרטון מופיע ש Sphere implements Gieometry
public class Sphere extends RadialGeometry {

    private final Point center;

    /**
     * Constructor to initialize Sphere based on radius and center point.
     *
     * @param radius sphere's radius.
     * @param center center point.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return (point.subtract(center).normalize());
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getHead();
        Vector center_p0;
        try {//p0 במרכז
            center_p0 = (center.subtract(p0));
        } catch (IllegalArgumentException error) {
            return List.of(p0.add(ray.getDirection().scale(radius)));
        }
        double tm = alignZero(ray.getDirection().dotProduct(center_p0));
        double d = alignZero(sqrt(center_p0.lengthSquared() - tm * tm));

        //Ray is tangent to or outside the circle - no intersection point.
        if (d >= radius)
            return null;
        double th = alignZero(sqrt((radius * radius - d * d)));

        double t1 = tm - th;
        double t2 = tm + th;

         if (t1>0 && t2>0)
             return List.of(ray.getPoint(t1),ray.getPoint(t2)).stream().toList();
         if (t2>0)
             return List.of(ray.getPoint(t2));
         if (t1>0)
             return List.of(ray.getPoint(t1));
         return null;
    }
}