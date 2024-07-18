package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * This interface represents a light source in a scene.
 * Any light source in the scene should implement this interface.
 *
 * <p>Implementing classes should provide specific behaviors for how light interacts with points in the scene.</p>
 */
public interface LightSource {

    /**
     * Gets the intensity of the light at a specific point in the scene.
     *
     * @param point the point in the scene where the light intensity is being calculated
     * @return the color intensity of the light at the specified point
     */
    public Color getIntensity(Point point);

    /**
     * Gets the direction of the light from the light source to a specific point in the scene.
     *
     * @param point the point in the scene to which the direction vector is being calculated
     * @return the direction vector from the light source to the specified point
     */
    public Vector getL(Point point);

    /**
     * Returns distance of lightsource from a given point
     * *
     *
     * @param point from which the distance is calculated
     * @return the distance
     */
    public double getDistance(Point point);

    List<Vector> getLBeam(Point point);
}
