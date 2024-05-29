package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube {
    private final double height;
    
     /**
     * Constructor to initialize Cylinder based on radius, height and axis.
     * @param radius base's radius.
     * @param axis   main axis's Ray.
     * @param height Cylinder's height
     */
    public Cylinder(double radius, Ray axis, double height) {
        super(radius, axis);
        this.height = height;
    }


    @Override
    public Vector getNormal(Point point) {
        Vector vectorP0=axis.getDirection();
        Point centerOfBottom=axis.getHead();
        Point centerOfTop=axis.getHead().add(axis.getDirection().scale(height));

        // If the point is at the center of the bottom base
        if(point.equals(centerOfBottom))
            return vectorP0.scale(-1);
        // If the point is at the center of the top base
        if(point.equals(centerOfTop))
            return vectorP0;

        // If the point is on the top base but not at the center
        Vector vectorP_p0=point.subtract(centerOfTop);
        if(vectorP_p0.dotProduct(vectorP0) ==0)
            return vectorP0;

        // If the point is on the bottom base but not at the center
        vectorP_p0=point.subtract(centerOfBottom);
        if(vectorP_p0.dotProduct(vectorP0) ==0)
            return vectorP0.scale(-1);

        // If the point is on the lateral surface
        Vector normalScale=vectorP0.scale(vectorP0.dotProduct(vectorP_p0));
        Vector normalCasing=vectorP_p0.subtract(normalScale);
        if(normalCasing.length()==radius)
            return normalCasing.normalize();

        return vectorP0;
    }
}
