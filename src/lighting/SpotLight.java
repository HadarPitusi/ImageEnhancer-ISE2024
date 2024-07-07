package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;
import static java.lang.Math.pow;

/**
 * Represents a spot light source in a scene.
 * Spot light emits light from a single point in a specific direction,
 * typically with a focus area and fall-off.
 *
 * <p>This class extends the {@link PointLight} class.</p>
 *
 * @see PointLight
 */
public class SpotLight extends PointLight {

    private Vector direction;
    private double narrowness = 1;

    /**
     * Constructs a spot light with the specified intensity, position, and direction.
     *
     * @param intensity the color intensity of the spot light
     * @param position  the position of the spot light in the scene
     * @param direction the direction of the light beam
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize(); // Ensure direction is normalized
    }

    /**
     * Increases the intensity of the light according to the narrowness of the beam.
     *
     * @param point the point in the scene where the light intensity is calculated
     * @return the color intensity of the light at the given point
     */
    @Override
    public Color getIntensity(Point point) {
        double projection = direction.dotProduct(super.getL(point));
        return super.getIntensity(point).scale(pow(max(0, projection), narrowness));
    }

    @Override
    public SpotLight setKc(double Kc) {
        super.setKc(Kc);
        return this;
    }

    @Override
    public SpotLight setKl(double Kl) {
        super.setKl(Kl);
        return this;
    }

    @Override
    public SpotLight setKq(double Kq) {
        super.setKq(Kq);
        return this;
    }

    /**
     * Sets the narrowness of the light beam.
     *
     * @param narrowness the narrowness value
     * @return the current SpotLight instance (for method chaining)
     */
    public SpotLight setNarrowBeam(double narrowness) {
        this.narrowness = narrowness;
        return this;
    }
}
