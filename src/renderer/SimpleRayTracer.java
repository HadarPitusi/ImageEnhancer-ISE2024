package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import java.util.List;

import static java.lang.Math.*;
import static primitives.Util.alignZero;

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
    private Color calcColor(GeoPoint geoPoint, Ray ray){
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }

    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return null;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(
                        iL.scale(calcDiffusive(material, nl)
                                .add(calcSpecular(material, n, l, nl, v))));
            }
        }
        return color;
    }


    private Double3 calcDiffusive(Material material, double nl){
        return material.Kd.scale(abs(nl));
    }

    private Double3 calcSpecular(Material material, Vector n,Vector l, double nl, Vector v){
        Vector reflectVector=(l).subtract(n.scale(nl*2));
        double max0_vr=max(0,v.scale(-1).dotProduct(reflectVector));
        return material.Ks.scale(pow(max0_vr, material.Shininess));
    }

    private boolean unshaded(GeoPoint gp, Vector l) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray ray = new Ray(gp.point, lightDirection);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        return (intersections.isEmpty());
    }

    @Override
    public Color tracerRay(Ray ray) {
        List<GeoPoint> intersection=scene.geometries.findGeoIntersections(ray);
        if(intersection==null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(intersection), ray);
    }

}
