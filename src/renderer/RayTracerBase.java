package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Abstract base class for ray tracing.
 */
public abstract class RayTracerBase {
    /**
     * The scene to be used for ray tracing.
     */
    protected Scene scene;

    /**
     * Constructor for RayTracerBase.
     *
     * @param scene the scene to be used for ray tracing
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Traces a ray and returns the color at the intersection point.
     *
     * @param ray the ray to be traced
     * @return the color at the intersection point
     */
    public abstract Color tracerRay(Ray ray);
}