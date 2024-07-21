package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // The starting point of the ray coincides with the representation point
        if (q.equals(ray.getHead()))
            return null;
        Vector directionRay = ray.getDirection();
        Vector q_q0 = q.subtract(ray.getHead());
        double n_qp = normal.dotProduct(q_q0);
        // The starting point of the ray is on the plane
        if (isZero(n_qp))
            return null;
        double nv = normal.dotProduct(directionRay);
        // The ray is parallel to the plane
        if (isZero(nv))
            return null;
        double t = alignZero(n_qp / nv);
        // The ray start after the plane or far away
        if ((t <= 0) || alignZero(t - maxDistance) > 0)
            return null;

        return List.of(new GeoPoint(this, ray.getPoint(t)));
    }

    /**
     * Finds vectors lying on the plane.
     *
     * @return a list of vectors lying on the plane.
     */
    public List<Vector> findVectorsOfPlane() {
        List<Vector> vectors = new LinkedList<>();
        double nX = this.getNormal().getX(), nY = this.getNormal().getY(), nZ = this.getNormal().getZ();
        double pX = q.getX(), pY = q.getY(), pZ = q.getZ();
        double d = -(nX * pX + nY * pY + nZ * pZ);
        Point p0 = q;
        int amount = 0;
        //calculate a point on the plane, and create a vector with the point
        if (nX != 0) {
            double x1 = (d / nX);
            vectors.add((new Point(x1, 0, 0)).subtract(p0));
            amount++;
        }
        if (nY != 0) {
            double y2 = (d / nY);
            vectors.add((new Point(0, y2, 0)).subtract(p0));
            amount++;
        }
        if (nZ != 0 && amount < 2) {
            double z3 = (d / nZ);
            vectors.add((new Point(0, 0, z3)).subtract(p0));
        }
        return vectors;
    }
}
