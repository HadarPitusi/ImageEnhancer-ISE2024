package geometries;

public abstract class RadialGeometry implements Geometry {
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
