package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

/**
 * A simple implementation of the RayTracerBase class.
 */
public class SimpleRayTracer extends RayTracerBase {
    /**
     * Constructor for SimpleRayTracer.
     *
     * @param scene the scene to be used for ray tracing
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Calculates the color at a given point in the scene.
     *
     * @param geoPoint the point for which to calculate the color
     * @return the calculated color
     */
    private Color calcColor(GeoPoint geoPoint){
        return scene.ambientLight.getIntensity().add(geoPoint.geometry.getEmission());
    }

    @Override
    public Color tracerRay(Ray ray) {
        List<GeoPoint> intersection=scene.geometries.findGeoIntersections(ray);
        if(intersection==null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(intersection));
    }

}
