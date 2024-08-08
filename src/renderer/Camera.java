package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.IntStream;

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
    private int antiAliasingRays = 1;
    private boolean multiThreading = false;
    private boolean superSempling = false;


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
         * @param camera the Camera to copy
         */
        public Builder(Camera camera) {
            this.camera.p0 = camera.p0;
            this.camera.vTo = camera.vTo;
            this.camera.vUp = camera.vUp;
            this.camera.vRight = camera.vRight;
            this.camera.height = camera.height;
            this.camera.width = camera.width;
            this.camera.distance = camera.distance;
            this.camera.antiAliasingRays = camera.antiAliasingRays;
            this.camera.multiThreading = camera.multiThreading;
            this.camera.superSempling = camera.superSempling;
        }

        /**
         * Sets the location of the camera.
         *
         * @param point the location point
         * @return the Builder instance
         */
        public Builder setLocation(Point point) {
            this.camera.p0 = point;
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
            this.camera.vTo = vTo.normalize();
            this.camera.vUp = vUp.normalize();
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
            this.camera.width = width;
            this.camera.height = height;
            return this;
        }

        /**
         * Sets the distance from the camera to the view plane.
         *
         * @param distance the distance to the view plane
         * @return the Builder instance
         */
        public Builder setVpDistance(double distance) {
            this.camera.distance = distance;
            return this;
        }

        /**
         * Sets the ray tracer for the camera.
         *
         * @param rayTracer the ray tracer
         * @return the Builder instance
         */
        public Builder setRayTracer(RayTracerBase rayTracer) {
            this.camera.rayTracer = rayTracer;
            return this;
        }

        /**
         * Sets the image writer for the camera.
         *
         * @param imageWriter the image writer
         * @return the Builder instance
         */
        public Builder setImageWriter(ImageWriter imageWriter) {
            this.camera.imageWriter = imageWriter;
            return this;
        }

        /**
         * Sets the number of rays for antialiasing.
         *
         * @param antiAliasingRays the number of rays to set for anti-aliasing. Must be greater than 0.
         * @return the current Builder instance for method chaining.
         */
        public Builder setAntiAliasingRays(int antiAliasingRays) {
            this.camera.antiAliasingRays = antiAliasingRays;
            return this;
        }

        /**
         * Enables or disables multi threading.
         *
         * @param multiThreading true to enable multi threading, false to disable.
         * @return the current Builder instance for method chaining.
         */
        public Builder setMultiThreading(boolean multiThreading) {
            this.camera.multiThreading = multiThreading;
            return this;
        }

        /**
         * Enables or disables super sampling.
         *
         * @param superSempling true to enable super sampling, false to disable.
         * @return the current Builder instance for method chaining.
         */
        public Builder setSuperSempling(boolean superSempling) {
            this.camera.superSempling = superSempling;
            return this;
        }


        /**
         * Builds and returns the Camera instance.
         *
         * @return the built Camera instance
         * @throws MissingResourceException if any required field is missing
         */
        public Camera build() {
            final String missingData = "Missing render data";
            final String NameClassOfMissing = "camera";
            if (this.camera.p0 == null)
                throw new MissingResourceException(
                        missingData,
                        NameClassOfMissing,
                        "the point of distance between the camera"
                );
            if (this.camera.vTo == null)
                throw new MissingResourceException(
                        missingData,
                        NameClassOfMissing,
                        "vector To"
                );
            if (this.camera.vUp == null)
                throw new MissingResourceException(
                        missingData,
                        NameClassOfMissing,
                        "vector up"
                );
            if (this.camera.imageWriter == null)
                throw new MissingResourceException(
                        missingData,
                        NameClassOfMissing,
                        "imageWriter"
                );
            if (this.camera.rayTracer == null)
                throw new MissingResourceException(
                        missingData,
                        NameClassOfMissing,
                        "rayTracer"
                );
            this.camera.vRight = this.camera.vTo.crossProduct(this.camera.vUp).normalize();

            try {
                return (Camera) this.camera.clone();
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
        if (isZero(this.distance))
            pC = this.p0;
        else
            pC = this.p0.add(this.vTo.scale(this.distance));
        double rY = this.height / (double) nY;
        double rX = this.width / (double) nX;
        double yI = -(i - ((double) nY - 1) / 2) * rY;
        double xJ = (j - ((double) nX - 1) / 2) * rX;
        Point pIJ = pC;
        if (!isZero(xJ))
            pIJ = pIJ.add(this.vRight.scale(xJ));
        if (!isZero(yI))
            pIJ = pIJ.add(this.vUp.scale(yI));

        Vector vIJ = pIJ.subtract(this.p0);
        return new Ray(this.p0, vIJ);
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
     * Casts a ray through a specific pixel on the image grid, traces its color, and writes the result to the image.
     *
     * @param nx     the number of columns in the image grid
     * @param ny     the number of rows in the image grid
     * @param column the column index of the pixel through which the ray is cast
     * @param row    the row index of the pixel through which the ray is cast
     */
    private void castRay(int nx, int ny, int column, int row) {
        Ray ray = constructRay(nx, ny, column, row);
        Color color = this.rayTracer.tracerRay(ray);
        this.imageWriter.writePixel(column, row, color);
    }

    /**
     * Renders the image by casting rays through each pixel and computing the color
     * based on the scene's objects and lights. This method supports anti-aliasing and multi threading.
     *
     * @return the current Camera instance for method chaining.
     */
    public Camera renderImage() {
        int nx = imageWriter.getNx();
        int ny = imageWriter.getNy();

        if (!multiThreading) {
            if (antiAliasingRays == 1) {
                if (!superSempling) {
                    //Basic image without enhancements in camera.
                    for (int i = 0; i < nx; i++) {
                        for (int j = 0; j < ny; j++) {
                            castRay(nx, ny, i, j);
                        }
                    }
                }
                //There can't be super sampling without anti-aliasing, because only one beam is sent.

            } else {    //with anti-aliasing
                if (!superSempling) {
                    //without anti-aliasing and multi threading
                    for (int i = 0; i < nx; i++) {
                        for (int j = 0; j < ny; j++) {
                            List<Ray> rays = this.constructRays(nx, ny, i, j);
                            Color color = average(rays);
                            this.imageWriter.writePixel(i, j, color);
                        }
                    }
                } else {  //with super sempling
                    for (int i = 0; i < nx; i++) {
                        for (int j = 0; j < ny; j++) {
                            // construct a ray through the current pixel
                            List<Ray> rays = this.constructRays(nx, ny, i, j);
                            // get the  color of the point from trace ray
                            Color color = this.rayTracer.adaptiveTraceRays(rays);
                            imageWriter.writePixel(i, j, color);
                        }
                    }
                }
            }
        } else {     //with multi threading
            if (antiAliasingRays == 1) {  //without super sempling
                IntStream.range(0, ny).parallel()
                        .forEach(i -> IntStream.range(0, nx).parallel()
                                .forEach(j -> castRay(nx, ny, i, j)));
            } else {  //with anti-aliasing
                if (!superSempling) {
                    IntStream.range(0, ny).parallel()
                            .forEach(i -> IntStream.range(0, nx).parallel()
                                    .forEach(j -> {
                                        List<Ray> rays = this.constructRays(nx, ny, i, j);
                                        Color color = average(rays);
                                        this.imageWriter.writePixel(i, j, color);
                                    }));
                } else { //all of the enhancements in camera.
                    IntStream.range(0, ny).parallel()
                            .forEach(i -> IntStream.range(0, nx).parallel()
                                    .forEach(j -> {
                                        List<Ray> rays = this.constructRays(nx, ny, i, j);
                                        // get the  color of the point from trace ray
                                        Color color = this.rayTracer.adaptiveTraceRays(rays);
                                        // write the pixel color to the image
                                        imageWriter.writePixel(i, j, color);
                                    }));
                }
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
        for (int i = 0; i < this.imageWriter.getNx(); i += interval) {
            for (int j = 0; j < this.imageWriter.getNy(); j++) {
                this.imageWriter.writePixel(i, j, color);
            }
        }

        for (int j = 0; j < this.imageWriter.getNy(); j += interval) {
            for (int i = 0; i < this.imageWriter.getNx(); i++) {
                this.imageWriter.writePixel(i, j, color);
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

    /**
     * Constructs a list of rays through a specified pixel.
     *
     * @param nX the number of pixels in the x direction
     * @param nY the number of pixels in the y direction
     * @param i  the x coordinate of the pixel
     * @param j  the y coordinate of the pixel
     * @return a list of rays through the specified pixel
     */
    public List<Ray> constructRays(int nX, int nY, int i, int j) {
        List<Ray> rays = new LinkedList<>();
        Point centralPixel = findPixelLocation(nX, nY, i, j);
        double rY = height / nY / antiAliasingRays;
        double rX = width / nX / antiAliasingRays;
        double x, y;

        for (int rowNumber = 0; rowNumber < antiAliasingRays; rowNumber++) {
            for (int colNumber = 0; colNumber < antiAliasingRays; colNumber++) {
                y = -(rowNumber - (antiAliasingRays - 1d) / 2) * rY;
                x = (colNumber - (antiAliasingRays - 1d) / 2) * rX;
                Point pIJ = centralPixel;
                if (y != 0) pIJ = pIJ.add(vUp.scale(y));
                if (x != 0) pIJ = pIJ.add(vRight.scale(x));
                rays.add(new Ray(p0, pIJ.subtract(p0)));
            }
        }
        return rays;
    }

    /**
     * Finds the location of the central point of a specified pixel.
     *
     * @param numXPixels the number of pixels along the horizontal axis (width) of the screen.
     * @param numYPixels the number of pixels along the vertical axis (height) of the screen.
     * @param i          the x coordinate of the pixel
     * @param j          the y coordinate of the pixel
     * @return the location of the central point of the specified pixel
     */
    private Point findPixelLocation(int numXPixels, int numYPixels, int i, int j) {
        // Calculate the size of each pixel along the X and Y axes
        double pixelHeight = height / numYPixels;
        double pixelWidth = width / numXPixels;

        // Calculate the offset of the pixel from the center of the screen
        double offsetY = -(j - (numYPixels - 1d) / 2) * pixelHeight;
        double offsetX = (i - (numXPixels - 1d) / 2) * pixelWidth;

        // Compute the point at the center of the screen based on the camera's view direction
        Point pixelPoint = p0.add(vTo.scale(distance));

        // Adjust the center point by the calculated offsets to get the exact pixel location
        if (offsetY != 0) pixelPoint = pixelPoint.add(vUp.scale(offsetY));
        if (offsetX != 0) pixelPoint = pixelPoint.add(vRight.scale(offsetX));

        return pixelPoint;
    }

    /**
     * Calculates the average color of a list of rays.
     *
     * @param rays the list of rays
     * @return the average color of the rays
     */
    private Color average(List<Ray> rays) {
        Color colorOfPixel = Color.BLACK;
        for (Ray ray : rays) {
            Color colorOfRay = this.rayTracer.tracerRay(ray);
            colorOfPixel = colorOfPixel.add(colorOfRay);
        }
        return colorOfPixel.reduce(rays.size());
    }

}

