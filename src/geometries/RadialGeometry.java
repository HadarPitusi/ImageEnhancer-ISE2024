package geometries;

public abstract class RadialGeometry extends Geometry {
    /** radius */
    protected final double radius;

    /**
     * constructor
     * @param radius the radius of the geometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
