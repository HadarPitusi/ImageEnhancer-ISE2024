package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.lang.module.ModuleDescriptor;
import java.util.MissingResourceException;

public class Camera implements Cloneable{
    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double height=0.0;
    private double width=0.0;
    private double distance=0.0;

    public static class Builder{
        final private Camera camera=new Camera();

        public Builder() {
        }

        public Builder setLocation(Point point) {
            camera.p0=point;
            return this;
        }

        public Builder setDirection(Vector vTo, Vector vUp){
            if(vTo.dotProduct(vUp)==0)
                throw new IllegalArgumentException("The vectors are not vertical");
            camera.vTo=vTo.normalize();
            camera.vUp=vUp.normalize();
            return this;
        }

        public Builder setVpSize(double width, double height){
            camera.width=width;
            camera.height=height;
            return this;
        }

        public Builder setVpDistance(double distance){
            camera.distance=distance;
            return this;
        }

        public Camera build() throws CloneNotSupportedException {
           final String missingData1="Missing rendor data";
            final String NameClassOfMissing="camera";
            if (camera.p0==null)
                throw new MissingResourceException(missingData1, NameClassOfMissing,"the point of distance between the camera");
            if (camera.vTo==null)
                throw new MissingResourceException(missingData1, NameClassOfMissing,"vector To");
            if (camera.vUp==null)
                throw new MissingResourceException(missingData1, NameClassOfMissing,"vector up");
            camera.vRight=camera.vTo.crossProduct(camera.vUp);

            return (Camera) camera.clone();
        }
    }

    private Camera() {
    }

    public Double getHeight() {
        return height;
    }

    public Double getWidth() {
        return width;
    }

    public Double getDistance() {
        return distance;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }


}
