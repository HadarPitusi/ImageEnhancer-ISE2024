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
    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
     * @param ray      the ray being traced
     * @return the calculated color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(geoPoint, ray));
    }

    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }


    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Material material = geoPoint.geometry.getMaterial();
        return calcGlobalEffect(constructRefractedRay(geoPoint, ray), material.Kt, level, k)
                .add(calcGlobalEffect(constructReflectedRay(geoPoint, ray), material.Kr, level, k));
    }

    private Ray constructReflectedRay(GeoPoint geoPoint, Ray ray) {
        //r=v-2*(vn)n
        Vector n=geoPoint.geometry.getNormal(geoPoint.point);
        Vector v=ray.getDirection();

    }

    private Ray constructRefractedRay(GeoPoint gp, Ray ray) {

    }

    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersections(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level-1, kkx))
                .scale(kx);
    }

    private GeoPoint findClosestIntersections(Ray ray){
        List <GeoPoint> list= scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(list);
    }

    /**
     * Calculates the local effects on the color at a given point in the scene.
     *
     * @param gp  the point for which to calculate the local effects
     * @param ray the ray being traced
     * @return the color with local effects applied
     */
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
            if ((nl * nv > 0) && unshaded(gp, lightSource, l, n, nl)) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(
                        iL.scale(calcDiffusive(material, nl)
                                .add(calcSpecular(material, n, l, nl, v))));
            }
        }
        return color;
    }

    /**
     * Calculates the diffusive component of the color.
     *
     * @param material the material of the object
     * @param nl       the dot product of the normal and the light direction
     * @return the diffusive component of the color
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.Kd.scale(abs(nl));
    }

    /**
     * Calculates the specular component of the color.
     *
     * @param material the material of the object
     * @param n        the normal vector at the point
     * @param l        the light direction vector
     * @param nl       the dot product of the normal and the light direction
     * @param v        the view direction vector
     * @return the specular component of the color
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector reflectVector = l.subtract(n.scale(nl * 2));
        double max0_vr = max(0, v.scale(-1).dotProduct(reflectVector));
        return material.Ks.scale(pow(max0_vr, material.Shininess));
    }

    /**
     * Determines if a point is unshaded by other objects in the scene.
     *
     * @param gp    the point in the scene
     * @param light the light source
     * @param l     the light direction vector
     * @param n     the normal vector at the point
     * @param nl    the dot product of the normal and the light direction
     * @return true if the point is unshaded, false otherwise
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);
        Point point = gp.point.add(epsVector);
        Ray ray = new Ray(point, lightDirection);

        // Find intersections with the scene
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray, light.getDistance(gp.point));

        // If no intersections found, the point is unshaded
        return intersections == null || intersections.isEmpty();
    }

    @Override
    public Color tracerRay(Ray ray) {
        List<GeoPoint> intersection = scene.geometries.findGeoIntersections(ray);
        if (intersection == null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(intersection), ray);
    }
}