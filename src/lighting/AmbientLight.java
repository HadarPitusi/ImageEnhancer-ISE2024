package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Represents ambient light in a scene.
 * Ambient light is a light that is scattered in all directions and comes from all directions equally.
 * It has a constant intensity throughout the scene.
 *
 * <p>This class extends the abstract class {@link Light}.</p>
 *
 * @see Light
 */
public class AmbientLight extends Light {

    /**
     * A constant representing no ambient light.
     * This can be used to denote the absence of ambient light in a scene.
     */
    public static final AmbientLight NONE = new AmbientLight(new Color(java.awt.Color.BLACK), Double3.ZERO);

    /**
     * Constructs an ambient light with the specified intensity and scaling factor.
     *
     * @param intensity the color intensity of the ambient light
     * @param kA the scaling factor for the intensity
     */
    public AmbientLight(Color intensity, Double3 kA) {
        super(intensity.scale(kA));
    }

    /**
     * Constructs an ambient light with the specified intensity and scaling factor.
     *
     * @param intensity the color intensity of the ambient light
     * @param kA the scaling factor for the intensity
     */
    public AmbientLight(Color intensity, double kA) {
        super(intensity.scale(kA));
    }
}


