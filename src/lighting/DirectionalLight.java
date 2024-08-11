package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * Represents a directional light source in a scene.
 * Directional light has a specific direction and is considered to be at an infinite distance,
 * causing the light rays to be parallel.
 *
 * <p>This class extends the {@link Light} class and implements the {@link LightSource} interface.</p>
 *
 * @see Light
 * @see LightSource
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a directional light with the specified intensity and direction.
     *
     * @param intensity the color intensity of the directional light
     * @param direction the direction of the light rays
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point point) {
        return intensity;
    }

    @Override
    public Vector getL(Point point) {
        return direction;
    }

    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }

   /** @Override
    public List<Vector> getLBeam(Point p) {
        return List.of(getL(p));
    }**/
}

