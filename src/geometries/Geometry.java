package geometries;

import primitives.Color;
import primitives.Vector;
import primitives.Point;

/**
 * Abstract class representing a geometric shape that can be intersected.
 * Extends {@code Intersectable}.
 */
public abstract class Geometry extends Intersectable {

    /**
     * The emission color of the geometry.
     */
    protected Color emission = Color.BLACK;

    /**
     * Returns the emission color of the geometry.
     *
     * @return the emission color.
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Sets the emission color of the geometry.
     *
     * @param emission the new emission color.
     * @return the current {@code Geometry} instance for chaining.
     */
    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    /**
     * Returns the normal vector at a given point on the geometry.
     *
     * @param point the point from which the normal is calculated.
     * @return the normal vector at the specified point.
     */
    public abstract Vector getNormal(Point point);
}

