package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.util.MissingResourceException;

import static primitives.Util.isZero;

/**
 * Class representing a camera in a 3D scene.
 */
public class Camera implements Cloneable {
    private Point p0;
    private Vector vTo;
    private Vector vUp;
    private Vector vRight;
    private double height = 0.0;
    private double width = 0.0;
    private double distance = 0.0;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * Builder class for constructing Camera instances.
     */
    public static class Builder {
        final private Camera camera = new Camera();

        /**
         * Default constructor for Builder.
         */
        public Builder() {
        }

        /**
         * Constructor for Builder that copies an existing Camera.
         *
         * @param my_camera the Camera to copy
         */
        public Builder(Camera my_camera) {
            camera.p0 = my_camera.p0;
            camera.vTo = my_camera.vTo;
            camera.vUp = my_camera.vUp;
            camera.vRight = my_camera.vRight;
            camera.height = my_camera.height;
            camera.width = my_camera.width;
            camera.distance = my_camera.distance;
        }

        /**
         * Sets the location of the camera.
         *
         * @param point the location point
         * @return the Builder instance
         */
        public Builder setLocation(Point point) {
            camera.p0 = point;
            return this;
        }

        /**
         * Sets the direction of the camera.
         *
         * @param vTo the direction vector
         * @param vUp the up vector
         * @return the Builder instance
         * @throws IllegalArgumentException if the vectors are not orthogonal
         */
        public Builder setDirection(Vector vTo, Vector vUp) {
            if (!isZero(vTo.dotProduct(vUp)))
                throw new IllegalArgumentException("The vectors are not vertical");
            camera.vTo = vTo.normalize();
            camera.vUp = vUp.normalize();
            return this;
        }

        /**
         * Sets the size of the view plane.
         *
         * @param width  the width of the view plane
         * @param height the height of the view plane
         * @return the Builder instance
         * @throws IllegalArgumentException if width or height is non-positive
         */
        public Builder setVpSize(double width, double height) {
            if (width <= 0)
                throw new IllegalArgumentException("ERROR: width is negative");
            if (height <= 0)
                throw new IllegalArgumentException("ERROR: height is negative");
            camera.width = width;
            camera.height = height;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         *
         * @param distance the distance to the view plane
         * @return the Builder instance
         */
        public Builder setVpDistance(double distance) {
            camera.distance = distance;
            return this;
        }

        /**
         * Sets the ray tracer for the camera.
         *
         * @param rayTracer the ray tracer
         * @return the Builder instance
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Sets the image writer for the camera.
         *
         * @param imageWriter the image writer
         * @return the Builder instance
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Builds and returns the Camera instance.
         *
         * @return the built Camera instance
         * @throws MissingResourceException if any required field is missing
         */
        public Camera build() {
            final String missingData1 = "Missing render data";
            final String NameClassOfMissing = "camera";
            if (camera.p0 == null)
                throw new MissingResourceException(
                        missingData1,
                        NameClassOfMissing,
                        "the point of distance between the camera"
                );
            if (camera.vTo == null)
                throw new MissingResourceException(
                        missingData1,
                        NameClassOfMissing,
                        "vector To"
                );
            if (camera.vUp == null)
                throw new MissingResourceException(
                        missingData1,
                        NameClassOfMissing,
                        "vector up"
                );
            if (camera.imageWriter == null)
                throw new MissingResourceException(
                        missingData1,
                        NameClassOfMissing,
                        "imageWriter"
                );
            if (camera.rayTracer == null)
                throw new MissingResourceException(
                        missingData1,
                        NameClassOfMissing,
                        "rayTracer"
                );
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();

            try {
                return (Camera) camera.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Camera() {
    }

    /**
     * Gets the height of the view plane.
     *
     * @return the height of the view plane
     */
    public Double getHeight() {
        return height;
    }

    /**
     * Gets the width of the view plane.
     *
     * @return the width of the view plane
     */
    public Double getWidth() {
        return width;
    }

    /**
     * Gets the distance from the camera to the view plane.
     *
     * @return the distance to the view plane
     */
    public Double getDistance() {
        return distance;
    }

    /**
     * Constructs a ray through a pixel on the view plane.
     *
     * @param nX number of horizontal pixels
     * @param nY number of vertical pixels
     * @param j  horizontal index of the pixel
     * @param i  vertical index of the pixel
     * @return the constructed ray
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC;
        if (isZero(distance))
            pC = p0;
        else
            pC = p0.add(vTo.scale(distance));
        double rY = height / (double) nY;
        double rX = width / (double) nX;
        double yI = -(i - ((double) nY - 1) / 2) * rY;
        double xJ = (j - ((double) nX - 1) / 2) * rX;
        Point pIJ = pC;
        if (!isZero(xJ))
            pIJ = pIJ.add(vRight.scale(xJ));
        if (!isZero(yI))
            pIJ = pIJ.add(vUp.scale(yI));

        Vector vIJ = pIJ.subtract(p0);
        return new Ray(p0, vIJ);
    }

    /**
     * Gets a new Builder instance for constructing Camera objects.
     *
     * @return a new Builder instance
     */
    public static Builder getBuilder() {
        return new Builder();
    }

    /**
     * Casts a ray through a specific pixel and writes the color to the image.
     *
     * @param Nx     the number of horizontal pixels
     * @param Ny     the number of vertical pixels
     * @param column the column index of the pixel
     * @param row    the row index of the pixel
     */
    private void castRay(int Nx, int Ny, int column, int row){
        Ray ray=constructRay(column, row, Nx, Ny);
        imageWriter.writePixel(Nx,Ny, rayTracer.tracerRay(ray));
    }


    /**
     * Renders the image.
     * Currently not implemented.
     */
    public Camera renderImage() {
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                castRay(i,j, imageWriter.getNx(), imageWriter.getNy());
            }
        }
        return this;
    }

    /**
     * Prints a grid on the image with the specified interval and color.
     *
     * @param interval the interval between grid lines
     * @param color    the color of the grid lines
     */
    public Camera printGrid(int interval, Color color) {
        for (int i = 0; i < imageWriter.getNx(); i += interval) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                imageWriter.writePixel(i, j, color);
            }
        }

        for (int j = 0; j < imageWriter.getNy(); j += interval) {
            for (int i = 0; i < imageWriter.getNx(); i++) {
                imageWriter.writePixel(i, j, color);
            }
        }
        return this;
    }

    /**
     * Writes the rendered image to a file.
     */
    public void writeToImage() {
        this.imageWriter.writeToImage();
    }

}

