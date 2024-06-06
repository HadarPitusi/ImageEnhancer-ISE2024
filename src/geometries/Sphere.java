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
        double maxDistance = radius * 2;
        if (d >= radius)//הקרן משיקה או מחוץ למעגל- אין נק חיתוך
            return null;
        double th = alignZero(sqrt((radius * radius - d * d)));

        double t1 = tm - th;
        double t2 = tm + th;

         if (t1>0 && t2>0)
             return List.of(ray.getPoint(t1),ray.getPoint(t2));
         if (t2>0)
             return List.of(ray.getPoint(t2));
         if (t1>0)
             return List.of(ray.getPoint(t1));
         return null;


       /** Point p0 = ray.getHead();
        Vector dir = ray.getDirection();
        double maxDistance = radius * 2;
        // Deals with case where ray starts from the center of the sphere
        if (p0.equals(center))
            return List.of(p0.add(ray.getDirection().scale(radius)));

        // Finding the hypotenuse, base and perpendicular of the triangle formed by
        // ray's starting point, the center of the sphere and the intersection point of
        // the ray and the perpendicular line crosing the sphere's center.
        Vector hypotenuse = this.center.subtract(p0);
        double base = dir.dotProduct(hypotenuse);
        double perpendicular = hypotenuse.lengthSquared() - base * base;
        double insideSquared = radius*radius- perpendicular;

        // Dealing with a case in which the ray is perpendicular to the sphere at the
        // intersection point, or passes outside the Sphere.
        if (alignZero(insideSquared) <= 0)
            return null;

        // Returning intersection points, ensuring that only those intersected by the
        // ray are returned.
        double inside = Math.sqrt(insideSquared);
        double t2 = base + inside;
        double t1 = base - inside;
        if (alignZero(t2) <= 0 || alignZero(maxDistance - t1) <= 0)
            return null;

        boolean t2NotInRange = alignZero(maxDistance - t2) <= 0;

        if (alignZero(t1) <= 0) //
            return t2NotInRange //
					? null //
					: List.of(ray.getPoint(t2));

        return t2NotInRange //
                ? List.of(ray.getPoint(t1)) //
                : List.of(ray.getPoint(t1), ray.getPoint(t2));**/

    }
}