package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.*;

public class Plane extends Geometry {

    private final Point q;
    private final Vector normal;

    /**
     * Constructor to initialize Plane based on 3 points.
     *
     * @param p1 the first point.
     * @param p2 the second point.
     * @param p3 the third point.
     */
    public Plane(Point p1, Point p2, Point p3) {
        //normal = (p1.subtract(p2).crossProduct(p3.subtract(p2))).normalize(); //חישוב מסובך מדי?
        Vector v1 = p1.subtract(p2);
        Vector v2 = p1.subtract(p3);
        normal = v1.crossProduct(v2).normalize();
        q = p1;
    }

    /**
     * Constructor to initialize Plane based on point and vector
     *
     * @param q      point on the plane
     * @param normal the normal of the plane
     */
    public Plane(Point q, Vector normal) {
        this.q = q;
        this.normal = normal.normalize();
    }

    /**
     * Returns vertical.
     *
     * @return the normal vector.
     */
    public Vector getNormal() {
        return this.normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return this.normal;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        // The starting point of the ray coincides with the representation point
        if (q.equals(ray.getHead()))
            return null;
        Vector directionRay = ray.getDirection();
        Vector q_q0= q.subtract(ray.getHead());
        double n_qp = normal.dotProduct(q_q0);
        // The starting point of the ray is on the plane
        if (isZero(n_qp))
            return null;
        double nv = normal.dotProduct(directionRay);
        // The ray is parallel to the plane
        if (isZero(nv))
            return null;
        double t = alignZero(n_qp / nv);
        // The ray start after the plane
        if (t <= 0)
            return null;
        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }
}
