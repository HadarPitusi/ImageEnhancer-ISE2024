package geometries;

import primitives.*;


public class Box {

    public Point minimums;
    public Point maximums;

    public Box(Point minimums, Point maximums) {
        this.minimums = minimums;
        this.maximums = maximums;
    }

    public boolean intersectingTheBox(Ray ray) {
        Vector direction = ray.getDirection();
        Point head = ray.getHead();

        // Calculate the intersection intervals on the x-axis
        double tXmin = (minimums.getX() - head.getX()) / direction.getX();
        double tXmax = (maximums.getX() - head.getX()) / direction.getX();

        // Ensure xMin is smaller than xMax
        if (tXmin > tXmax) {
            double temp = tXmin;
            tXmin = tXmax;
            tXmax = temp;
        }

        // Calculate the intersection intervals on the y-axis
        double tYmin = (minimums.getY() - head.getY()) / direction.getY();
        double tYmax = (maximums.getY() - head.getY()) / direction.getY();

        // Ensure yMin is smaller than yMax
        if (tYmin > tYmax) {
            double temp = tYmin;
            tYmin = tYmax;
            tYmax = temp;
        }

        // Check for non-overlapping intervals on the x-axis and y-axis
        if ((tXmin > tYmax) || (tYmin > tXmax))
            return false;

        // Update xMin to the maximum of yMin and xMin
        if (tYmin > tXmin)
            tXmin = tYmin;

        // Update xMax to the minimum of yMax and xMax
        if (tYmax < tXmax)
            tXmax = tYmax;

        // Calculate the intersection intervals on the z-axis
        double tZmin = (minimums.getZ() - head.getZ()) / direction.getZ();
        double tZmax = (maximums.getZ() - head.getZ()) / direction.getZ();

        // Ensure tZmin is smaller than tZmax
        if (tZmin > tZmax) {
            double temp = tZmin;
            tZmin = tZmax;
            tZmax = temp;
        }

        // Check for non-overlapping intervals on the x-axis and z-axis
        if ((tXmin > tZmax) || (tZmin > tXmax))
            return false;

        // Update xMin to the maximum of tZmin and xMin
        if (tZmin > tXmin)
            tXmin = tZmin;

        // Update xMax to the minimum of zMax and xMax
        if (tZmax < tXmax)
            tXmax = tZmax;

        return true;
    }


}
