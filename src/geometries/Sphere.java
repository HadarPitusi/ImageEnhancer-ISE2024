package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

//אצל יאיר בסרטון מופיע ש Sphere implements Gieometry
public class Sphere extends RadialGeometry {

    private final Point center;

    /**
     * Constructor to initialize Sphere based on radius and center point.
     * @param radius sphere's radius.
     * @param center center point.
     */
    public Sphere(double radius, Point center) {
        super(radius);
        this.center = center;
    }

    @Override
    public Vector getNormal(Point point) {
        return(point.subtract(center).normalize());
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}