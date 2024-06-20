package lighting;

import primitives.Color;

/**
 * Abstract class representing a light source.
 * All light sources in the scene should extend from this class.
 *
 * @see Color
 */
public abstract class Light {

    /**
     * The intensity (color) of the light.
     */
    protected Color intensity;

    /**
     * Constructs a light source with the specified intensity.
     *
     * @param intensity the color intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Gets the intensity of the light.
     *
     * @return the color intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
