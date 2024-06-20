package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a point light source in a scene.
 * Point light emits light from a single point in all directions.
 *
 * <p>This class extends the {@link Light} class and implements the {@link LightSource} interface.</p>
 *
 * @see Light
 * @see LightSource
 */
public class PointLight extends Light implements LightSource {

    /**
     * The position of the point light in the scene.
     */
    protected Point position;

    /**
     * The constant attenuation factor.
     */
    private double kC = 1;

    /**
     * The linear attenuation factor.
     */
    private double kL = 0;

    /**
     * The quadratic attenuation factor.
     */
    private double kQ = 0;

    /**
     * Constructs a point light with the specified intensity and position.
     *
     * @param intensity the color intensity of the point light
     * @param position  the position of the point light in the scene
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    /**
     * Sets the constant attenuation factor.
     *
     * @param kC the constant attenuation factor
     * @return the current instance of {@code PointLight}
     */
    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation factor.
     *
     * @param kL the linear attenuation factor
     * @return the current instance of {@code PointLight}
     */
    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor.
     *
     * @param kQ the quadratic attenuation factor
     * @return the current instance of {@code PointLight}
     */
    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point point) {
        double distanceSquare = position.distanceSquared(point);
        return intensity.scale(1d / (kC + kL * Math.sqrt(distanceSquare) + kQ * distanceSquare));
    }

    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }
}
