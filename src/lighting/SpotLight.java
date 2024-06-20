package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import static java.lang.Math.max;

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

    @Override
    public Color getIntensity(Point point) {
        return super.getIntensity(point).scale(max(0, direction.dotProduct(super.getL(point))));
    }

    //לבדוק אם צריך. עשינו רק סופר אז למה לדרוס?
    @Override
    public Vector getL(Point point) {
        return super.getL(point);
    }

    @Override
    public SpotLight setkC(double kC) {
        super.setkC(kC);
        return this;
    }

    @Override
    public SpotLight setkL(double kL) {
        super.setkL(kL);
        return this;
    }

    @Override
    public SpotLight setkQ(double kQ) {
        super.setkQ(kQ);
        return this;
    }
}
