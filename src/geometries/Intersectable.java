package geometries;

import primitives.*;
import java.util.*;

/**
 * This interface represents objects that can be intersected by a ray.
 */
public interface Intersectable {

    /**
     * Finds all intersection points between the ray and the object implementing this interface.
     *
     * @param ray the ray for which intersections are to be found
     * @return a list of points where the ray intersects the object.
     *         if there are no intersections, return null
     */
    List<Point> findIntersections(Ray ray);
}
