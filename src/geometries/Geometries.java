package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
     private final List<Intersectable> geometries=new LinkedList<>();
    // בנאי ברירת מחדל ריק
    public Geometries() {   }

    // בנאי שמקבל אוסף של גופים גאומטריים
    public Geometries(Intersectable... geometries) {
        addGeometries(geometries);
    }
    // מתודה פרטית להוספת גופים גאומטריים לרשימה
    private void addGeometries(Intersectable... geometries) {
        for (Intersectable geometry : geometries) {
            this.geometries.add(geometry);
        }
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
