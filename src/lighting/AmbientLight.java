package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * Class representing ambient light in a scene.
 */
public class AmbientLight {

    /**
     * The intensity of the ambient light.
     */
    private final Color intensity;

    /**
     * A constant representing no ambient light.
     */
    public static final AmbientLight NONE = new AmbientLight(new Color(java.awt.Color.BLACK), Double3.ZERO);

    /**
     * Constructs an ambient light with the given color intensity and attenuation factor.
     *
     * @param intensity the color intensity of the ambient light.
     * @param kA the attenuation factor as a {@code Double3}.
     */
    public AmbientLight(Color intensity, Double3 kA) {
        this.intensity = intensity.scale(kA);
    }

    /**
     * Constructs an ambient light with the given color intensity and attenuation factor.
     *
     * @param intensity the color intensity of the ambient light.
     * @param kA the attenuation factor as a {@code double}.
     */
    public AmbientLight(Color intensity, double kA) {
        this.intensity = intensity.scale(kA);
    }

    /**
     * Returns the intensity of the ambient light.
     *
     * @return the color intensity of the ambient light.
     */
    public Color getIntensity() {
        return intensity;
    }
}

