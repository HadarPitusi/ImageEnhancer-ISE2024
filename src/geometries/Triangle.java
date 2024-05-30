package geometries;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.isZero;

public class Triangle extends Polygon {

    /**
     * Constructor to initialize Triangle based on 3 points.
     * @param p1 the first vertex of the triangle
     * @param p2 the second vertex of the triangle
     * @param p3 the third vertex of the triangle
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> check = plane.findIntersections(ray);//חיתוך עם המישור שהמשולש מוכל בו
        if (check == null)
            return null;
        Point p0 = ray.getHead();
        Vector v = ray.getDirection();
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector v3 = vertices.get(2).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();
        double d1 = v.dotProduct(n1);
        double d2 = v.dotProduct(n2);
        double d3 = v.dotProduct(n3);
        if (isZero(d1) || isZero(d2) || isZero(d3))//לפחות אחת מהמכפלות הסקלריות שווה אפס- אין חיתוך
            return null;
        if (((d1 > 0 && d2 > 0 && d3 > 0) || (d1 < 0 && d2 < 0 && d3 < 0)))// כל המכפלות הסקלריות חיוביות או  כולם שליליות-נקודת החיתוך בתוך המשולש
            return check;
        return null;// הנקודה על הצלע או על הקודקוד

    }
}
