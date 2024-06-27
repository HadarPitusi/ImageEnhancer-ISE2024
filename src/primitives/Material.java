package primitives;

/**
 * Class representing the material properties of a surface.
 * The material determines how the surface interacts with light,
 * specifically its specular reflection, diffuse reflection, and shininess.
 */
public class Material {

    /**
     * The specular reflection coefficient.
     */
    public Double3 Ks = Double3.ZERO;

    /**
     * The diffuse reflection coefficient.
     */
    public Double3 Kd = Double3.ZERO;

    public Double3 Kt= Double3.ZERO;

    public Double3 Kr= Double3.ZERO;
    /**
     * The shininess level of the material.
     */
    public int Shininess = 0;

    /**
     * Sets the specular reflection coefficient with a {@code Double3} value.
     *
     * @param Ks the specular reflection coefficient
     * @return the current instance of {@code Material}
     */
    public Material setKs(Double3 Ks) {
        this.Ks = Ks;
        return this;
    }

    /**
     * Sets the specular reflection coefficient with a double value.
     *
     * @param Ks the specular reflection coefficient
     * @return the current instance of {@code Material}
     */
    public Material setKs(double Ks) {
        this.Ks = new Double3(Ks);
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient with a {@code Double3} value.
     *
     * @param Kd the diffuse reflection coefficient
     * @return the current instance of {@code Material}
     */
    public Material setKd(Double3 Kd) {
        this.Kd = Kd;
        return this;
    }

    /**
     * Sets the diffuse reflection coefficient with a double value.
     *
     * @param Kd the diffuse reflection coefficient
     * @return the current instance of {@code Material}
     */
    public Material setKd(double Kd) {
        this.Kd = new Double3(Kd);
        return this;
    }

    public Material setKt(Double3 Kt) {
        this.Kt = Kt;
        return this;
    }

    public Material setKt(double Kt) {
        this.Kt = new Double3(Kt);
        return this;
    }

    public Material setKr(Double3 Kr) {
        this.Kr = Kr;
        return this;
    }

    public Material setKr(double Kr) {
        this.Kr = new Double3(Kr);
        return this;
    }

    /**
     * Sets the shininess level of the material.
     *
     * @param Shininess the shininess level
     * @return the current instance of {@code Material}
     */
    public Material setShininess(int Shininess) {
        this.Shininess = Shininess;
        return this;
    }
}
