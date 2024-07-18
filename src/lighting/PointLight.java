package lighting;

import geometries.Plane;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.random;


/**
 * Represents a point light source in a scene.
 * Point light emits light from a single point in all directions.
 *
 * <p>This class extends the {@link Light} class and implements the {@link LightSource} interface.</p>
 *
 * @see Light
 * @see LightSource
 */
public class PointLight extends Light implements LightSource {

    /**
     * The position of the point light in the scene.
     */
    protected Point position;

    /**
     * The constant attenuation factor.
     */
    private double kC = 1;

    /**
     * The linear attenuation factor.
     */
    private double kL = 0;

    /**
     * The quadratic attenuation factor.
     */
    private double kQ = 0;
    public static int softShadowsRays;
    private int lengthOfTheSide;


    public PointLight setLengthOfTheSide(int lengthOfTheSide) {
        if (lengthOfTheSide < 0)
            throw new IllegalArgumentException("LengthOfTheSide must be greater then 0");
        this.lengthOfTheSide = lengthOfTheSide;
        return this;
    }

    /**
     * Constructs a point light with the specified intensity and position.
     *
     * @param intensity the color intensity of the point light
     * @param position  the position of the point light in the scene
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setSoftShadowsRays(int numOfRays) {
        if (numOfRays < 0)
            throw new IllegalArgumentException("numOfRays must be greater then 0!");
        softShadowsRays = numOfRays;
        return this;
    }

    /**
     * Sets the constant attenuation factor.
     *
     * @param kC the constant attenuation factor
     * @return the current instance of {@code PointLight}
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * Sets the linear attenuation factor.
     *
     * @param kL the linear attenuation factor
     * @return the current instance of {@code PointLight}
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor.
     *
     * @param kQ the quadratic attenuation factor
     * @return the current instance of {@code PointLight}
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point point) {
        double distanceSquare = position.distanceSquared(point);
        return intensity.scale(1d / (kC + kL * Math.sqrt(distanceSquare) + kQ * distanceSquare));
    }

    @Override
    public Vector getL(Point point) {
        return point.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point point) {
        return this.position.distance(point);
    }


    @Override
    public List<Vector> getLBeam(Point p) {
        if (lengthOfTheSide == 0) return List.of(getL(p));

        List<Vector> vectors = new LinkedList<>();
        // help vectors
        Vector v0, v1;

        // A variable that tells how many divide each side

        double divided = Math.sqrt(softShadowsRays);

        // plane of the light
        Plane plane = new Plane(position, getL(p));

        // vectors of the plane
        List<Vector> vectorsOfThePlane = plane.findVectorsOfPlane();

        // Starting point of the square around the lighting
        Point startPoint = position.add(vectorsOfThePlane.get(0).normalize().scale(-lengthOfTheSide / 2.0))
                .add(vectorsOfThePlane.get(1).normalize().scale(-lengthOfTheSide / 2.0));

        // A loop that runs as the number of vectors and in each of its runs it brings a vector around the lamp
        for (double i = 0; i < lengthOfTheSide; i += lengthOfTheSide / divided) {
            for (double j = 0; j < lengthOfTheSide; j += lengthOfTheSide / divided) {
                v0 = vectorsOfThePlane.get(0).normalize()
                        .scale(random(i, i + lengthOfTheSide / divided));
                v1 = vectorsOfThePlane.get(1).normalize()
                        .scale(random(j, j + lengthOfTheSide / divided));
                vectors.add(p.subtract(startPoint.add(v0).add(v1)).normalize());
            }
        }
        return vectors;
    }
}
