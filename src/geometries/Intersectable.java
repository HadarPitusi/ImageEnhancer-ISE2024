package geometries;

import primitives.*;
import java.util.*;

public interface Intersectable {
    List<Point> findIntersections(Ray ray);
}
