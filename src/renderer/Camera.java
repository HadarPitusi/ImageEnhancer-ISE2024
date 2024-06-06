package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


import java.lang.module.ModuleDescriptor;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

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

        public Builder(Camera my_camera){
           camera.p0=my_camera.p0;
           camera.vTo=my_camera.vTo;
           camera.vUp=my_camera.vUp;
           camera.vRight=my_camera.vRight;
           camera.height=my_camera.height;
           camera.width=my_camera.width;
           camera.distance=my_camera.distance;
        }
        public Builder setLocation(Point point) {
            camera.p0=point;
            return this;
        }

        public Builder setDirection(Vector vTo, Vector vUp){
            if(!isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("The vectors are not vertical");
            camera.vTo=vTo.normalize();
            camera.vUp=vUp.normalize();
            return this;
        }

        public Builder setVpSize(double width, double height){
            if (width<=0)
                throw new IllegalArgumentException("ERROR: width is negative");
            if (height<=0)
                throw new IllegalArgumentException("ERROR: height is negative");
            camera.width=width;
            camera.height=height;
            return this;
        }

        public Builder setVpDistance(double distance){
            camera.distance=distance;
            return this;
        }

        public Camera build() {
            final String missingData1="Missing rendor data";
            final String NameClassOfMissing="camera";
            if (camera.p0==null)
                throw new MissingResourceException(missingData1, NameClassOfMissing,"the point of distance between the camera");
            if (camera.vTo==null)
                throw new MissingResourceException(missingData1, NameClassOfMissing,"vector To");
            if (camera.vUp==null)
                throw new MissingResourceException(missingData1, NameClassOfMissing,"vector up");
            camera.vRight=camera.vTo.crossProduct(camera.vUp).normalize();

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
        //588
        Point pC;
        if(isZero(distance))
            pC=p0;
        else
            pC= p0.add(vTo.scale(distance));
        double rY=height/(double) nY;
        double rX=width/(double)nX;
        double yI=-(i-((double)nY-1)/2)*rY;
        double xJ=(j-((double)nX-1)/2)*rX;
        Point pIJ=pC;
        if (!isZero(xJ))
            pIJ=pIJ.add(vRight.scale(xJ));
        if (!isZero(yI))
            pIJ=pIJ.add(vUp.scale(yI));

        Vector vIJ= pIJ.subtract(p0);
        return new Ray(p0,vIJ);
    }


    public static Builder getBuilder(){
        return new Builder();
    }


    @Override
    protected Object clone(){
        try{
            Camera camera=(Camera) super.clone();
            return camera;
        } catch(CloneNotSupportedException e){
            throw new AssertionError();
        }
    }
}
