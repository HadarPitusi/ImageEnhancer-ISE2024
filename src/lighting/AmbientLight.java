package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    private final Color intensity;
    static public AmbientLight NONE=new AmbientLight(new Color(java.awt.Color.BLACK), Double3.ZERO);

    public AmbientLight(Color intensity, Double3 kA) {
        this.intensity = intensity.scale(kA);
    }

    public AmbientLight(Color intensity, double kA) {
        this.intensity = intensity.scale(kA);
    }

    public Color getIntensity() {
        return intensity;
    }

}
