package renderer;

import geometries.Intersectable;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;
import lighting.LightSource;

import static lighting.PointLight.softShadowsRays;
import static primitives.Util.alignZero;
import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;

/**
 * SimpleRayTracer is a class that extends RayTracerBase and provides basic
 * ray tracing functionality.
 */
public class SimpleRayTracer extends RayTracerBase {

    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * Constructs a SimpleRayTracer with the specified scene.
     *
     * @param scene the scene to be rendered
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }


    @Override
    public Color tracerRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Calculates the diffusive component of the color.
     *
     * @param material the material of the intersected geometry
     * @param nl       the dot product of the normal and the light direction
     * @return the diffusive component of the color
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.Kd.scale(abs(nl));
    }

    /**
     * Calculates the specular component of the color.
     *
     * @param material       the material of the intersected geometry
     * @param normal         the normal vector at the intersection point
     * @param lightDirection the light direction vector
     * @param nl             the dot product of the normal and the light direction
     * @param v              the view direction vector
     * @return the specular component of the color
     */
    private Double3 calcSpecular(Material material, Vector normal, Vector lightDirection, double nl, Vector v) {
        Vector reflectVector = lightDirection.subtract(normal.scale(nl * 2));
        double max0_vr = max(0, v.scale(-1).dotProduct(reflectVector));
        return material.Ks.scale(pow(max0_vr, material.Shininess));
    }

    /**
     * Calculates the color at a given intersection point.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray that caused the intersection
     * @return the calculated color at the intersection point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Recursively calculates the color at a given intersection point considering global and local effects.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray that caused the intersection
     * @param level    the recursion level
     * @param k        the attenuation factor
     * @return the calculated color at the intersection point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(geoPoint, ray, k);
        return level == 1 ? color : color.add(calcGlobalEffects(geoPoint, ray, level, k));
    }

    /**
     * Constructs a reflected ray from a given intersection point.
     *
     * @param geoPoint the intersection point
     * @param v        the view direction vector
     * @param normal   the normal vector at the intersection point
     * @return the constructed reflected ray or null if the ray cannot be constructed
     */
    private Ray constructReflectedRay(GeoPoint geoPoint, Vector v, Vector normal) {
        double nv = normal.dotProduct(v);
        if (nv == 0)
            return null;

        Vector vec = v.subtract(normal.scale(2 * nv));
        return new Ray(geoPoint.point, vec, normal);
    }

    /**
     * Constructs a refracted ray from a given intersection point.
     *
     * @param geoPoint the intersection point
     * @param v        the view direction vector
     * @param normal   the normal vector at the intersection point
     * @return the constructed refracted ray
     */
    private Ray constructRefractedRay(GeoPoint geoPoint, Vector v, Vector normal) {
        return new Ray(geoPoint.point, v, normal);
    }

    /**
     * Finds the closest intersection point of a given ray with the scene geometries.
     *
     * @param ray the ray to be traced
     * @return the closest intersection point or null if no intersection is found
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> intersectionsList = scene.geometries.findGeoIntersections(ray);
        return ray.findClosestGeoPoint(intersectionsList);
    }

    /**
     * Calculates the global effects (reflection and refraction) at a given intersection point.
     *
     * @param ray   the ray causing the global effect
     * @param kx    the attenuation factor of the effect
     * @param level the recursion level
     * @param k     the accumulated attenuation factor
     * @return the calculated color of the global effect
     */
    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;

        GeoPoint geoPoint = findClosestIntersection(ray);
        return geoPoint == null ? scene.background : calcColor(geoPoint, ray, level - 1, kkx).scale(kx);
    }

    /**
     * Calculates the global effects (reflection and refraction) at a given intersection point.
     *
     * @param geoPoint the intersection point
     * @param ray      the ray causing the intersection
     * @param level    the recursion level
     * @param k        the accumulated attenuation factor
     * @return the calculated color considering global effects
     */
    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Material material = geoPoint.geometry.getMaterial();
        Vector direction = ray.getDirection();
        Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
        return calcGlobalEffect(constructRefractedRay(geoPoint, direction, normal), material.Kt, level, k)
                .add(calcGlobalEffect(constructReflectedRay(geoPoint, direction, normal), material.Kr, level, k));
    }

    /**
     * Calculates the local lighting effects at a given point.
     * This method considers both diffuse and specular reflections,
     * as well as the effects of transparency and shadows.
     *
     * @param geoPoint the point at which the lighting effects are calculated.
     * @param ray      the ray that intersects the geometry at the geoPoint.
     * @param k        the attenuation factor for recursive reflections.
     * @return the color resulting from the local lighting effects at the given point.
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDirection();
        Color color = geoPoint.geometry.getEmission();
        Color tempColor = Color.BLACK;
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;

        for (LightSource lightSource : scene.lights) {
          //  List<Vector> vectors = (softShadowsRays == 0) ? List.of(lightSource.getL(geoPoint.point))
            //        : lightSource.getLBeam(geoPoint.point);
            List<Vector> vectors =List.of(lightSource.getL(geoPoint.point));

            Material material = geoPoint.geometry.getMaterial();
            for (Vector l : vectors) {
                double nl = alignZero(n.dotProduct(l));
                if (nl * nv > 0) {
                    Double3 ktr = transparency(geoPoint, lightSource, l, n);
                    if (!ktr.product(k).lowerThan(MIN_CALC_COLOR_K)) {
                        Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
                        tempColor = tempColor.add(iL.scale(calcDiffusive(material, nl).add(calcSpecular(material, n, l, nl, v))));
                    }
                }
            }
            int reduceBy = vectors.size();
            color = color.add((softShadowsRays == 0) ? tempColor :
                    tempColor.reduce(reduceBy > 0 ? reduceBy : 1));
        }
        return color;

    }

    /**
     * Calculates the transparency attenuation factor for a given intersection point.
     *
     * @param geoPoint    the intersection point
     * @param lightSource the light source causing the shadow
     * @param l           the light direction vector
     * @param n           the normal vector at the intersection point
     * @return the calculated transparency attenuation factor
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1);
        Ray ray = new Ray(geoPoint.point, lightDirection, n);
        Double3 ktr = Double3.ONE;
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return ktr;

        double distanceToLight = lightSource.getDistance(geoPoint.point);

        for (GeoPoint intersection : intersections) {
            if (distanceToLight > intersection.point.distance(geoPoint.point)) {
                ktr = ktr.product(intersection.geometry.getMaterial().Kt);
            }
        }
        return ktr;
    }

    /**
     * Performs adaptive supersampling to compute the color of a pixel based on rays.
     * This method calculates the color of a pixel using a set of rays, performing recursive
     * adaptive supersampling to refine the color calculation based on differences between
     * the center and corner rays.
     *
     * @param rays              List of rays used for sampling the pixel.
     * @param levelOfAdaptive The current level of adaptive sampling.
     * @param topRightIndex    The index of the top-right corner ray.
     * @param topLeftIndex     The index of the top-left corner ray.
     * @param bottomLeftIndex  The index of the bottom-left corner ray.
     * @param bottomRightIndex The index of the bottom-right corner ray.
     * @param numOfSampleRays   The number of rays used for sampling.
     * @return The computed color for the pixel.
     */
    public Color adaptiveSuperSampling(List<Ray> rays, int levelOfAdaptive,
                                       int topRightIndex, int topLeftIndex, int bottomLeftIndex, int bottomRightIndex,
                                       int numOfSampleRays) {
        int numOfAdaptiveRays = 5;

        Ray centerRay = rays.get(rays.size() - 1);
        Color centerColor = tracerRay(centerRay);
        Ray topRightCorner = rays.get(topRightIndex);
        Color topRightColor = tracerRay(topRightCorner);
        Ray topLeftCorner = rays.get(topLeftIndex);
        Color topLeftColor = tracerRay(topLeftCorner);
        Ray bottomLeftCorner = rays.get(bottomLeftIndex);
        Color bottomLeftColor = tracerRay(bottomLeftCorner);
        Ray bottomRightCorner = rays.get(bottomRightIndex);
        Color bottomRightColor = tracerRay(bottomRightCorner);

        if (levelOfAdaptive == 0) {
            //Calculate the average color of the corners and the center
            centerColor = centerColor.add(topRightColor, topLeftColor, bottomLeftColor, bottomRightColor);
            return centerColor.reduce(numOfAdaptiveRays);
        }

        //If the corner color is the same as the center color, returns the center color
        if (topRightColor.equalsDelta(centerColor) &&
                topLeftColor.equalsDelta(centerColor) &&
                bottomLeftColor.equalsDelta(centerColor) &&
                bottomRightColor.equalsDelta(centerColor)) {
            return centerColor;
        } else {
            //for each color that is different from the center, the recursion goes down to the depth of the pixel and sums up
            // the colors until it gets the same color as the center color,
            if (!topRightColor.equalsDelta(centerColor)) {
                Color color=adaptiveSuperSampling(
                        rays,
                        levelOfAdaptive - 1,
                        topRightIndex - (numOfSampleRays + 1),
                        topLeftIndex,
                        bottomLeftIndex,
                        bottomRightIndex,
                        numOfSampleRays);
                topRightColor = topRightColor.add(color).reduce(2);
            }
            if (!topLeftColor.equalsDelta(centerColor)) {
                Color color=adaptiveSuperSampling(
                        rays,
                        levelOfAdaptive - 1,
                        topRightIndex,
                        topLeftIndex - (numOfSampleRays - 1),
                        bottomLeftIndex,
                        bottomRightIndex,
                        numOfSampleRays);
                topLeftColor = topLeftColor.add(color).reduce(2);
            }
            if (!bottomLeftColor.equalsDelta(centerColor)) {
                Color color=adaptiveSuperSampling(
                        rays,
                        levelOfAdaptive - 1,
                        topRightIndex,
                        topLeftIndex,
                        bottomLeftIndex + (numOfSampleRays + 1),
                        bottomRightIndex,
                        numOfSampleRays);
                bottomLeftColor = bottomLeftColor.add(color).reduce(2);
            }
            if (!bottomRightColor.equalsDelta(centerColor)) {
                Color color=adaptiveSuperSampling(
                        rays,
                        levelOfAdaptive - 1,
                        topRightIndex,
                        topLeftIndex,
                        bottomLeftIndex,
                        bottomRightIndex + (numOfSampleRays - 1),
                        numOfSampleRays);
               // bottomRightColor = bottomRightColor.add();
                bottomRightColor = bottomRightColor.add(color).reduce(2);
            }

            //Calculate and return the average color
            centerColor = centerColor.add(topRightColor, topLeftColor, bottomLeftColor, bottomRightColor);
            return centerColor.reduce(numOfAdaptiveRays);
        }
    }

    @Override
    public Color adaptiveTraceRays(List<Ray> rays) {
        int numOfSampleRays = (int) sqrt(rays.size());
        int topRightIndex = (numOfSampleRays - 1) * numOfSampleRays + (numOfSampleRays - 1);
        int topLeftIndex = (numOfSampleRays - 1) * numOfSampleRays;
        int bottomLeftIndex = 0;
        int bottomRightIndex = (numOfSampleRays - 1);

        return adaptiveSuperSampling(
                rays, 3, topRightIndex, topLeftIndex, bottomLeftIndex, bottomRightIndex, numOfSampleRays);
    }
}
