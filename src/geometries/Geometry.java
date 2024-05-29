package geometries;

import primitives.Vector;
import primitives.Point;

public interface Geometry extends Intersectable {

    /**
     * Returns vertical.
     * @param point The point from which the normal comes out.
     * @return the normal vector.
     */
    public Vector getNormal(Point point);
}
