package Core;

import java.awt.*;

public class Point3D extends Point {
    private int z;

    /**
     * Constructs and initializes a point at the origin (0, 0, 0) of the coordinate space.
     */
    public Point3D() {
        super();
        z = 0;
    }

    /**
     * Constructs and initializes a point at the specified (x,y,z) location in the coordinate space.
     * @param x the X coordinate of the newly constructed Point
     * @param y the Y coordinate of the newly constructed Point
     * @param z the Z coordinate of the newly constructed Point
     */
    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Point3D(Point3D p) {
        super(p.x, p.y);
        this.z = p.z;
    }

    /**
     * Returns the Z coordinate of this Point3D in double precision.
     * @return the Z coordinate of this Point3D.
     */
    public double getZ() {
        return z;
    }

    /**
     * Returns the location of this point. This method is included for completeness, to parallel the getLocation method of Component.
     * @return a copy of this point, at the same location
     */
    public Point3D getLocation() {
        return new Point3D(this.x, this.y, this.z);
    }

    /**
     * Changes the point to have the specified location.
     * This method is included for completeness, to parallel the setLocation method of Component. Its behavior is identical with move(int, int, int).
     * @param x the X coordinate of the new location
     * @param y the Y coordinate of the new location
     * @param z the Z coordinate of the new location
     */
    public void setLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Sets the location of this point to the specified double coordinates. The double values will be rounded to integer values.
     * Any number smaller than Integer.MIN_VALUE will be reset to MIN_VALUE, and any number larger than Integer.MAX_VALUE will be reset to MAX_VALUE.
     *
     * @param x the X coordinate of the new location
     * @param y the Y coordinate of the new location
     * @param z the Z coordinate of the new location
     */
    public void setLocation(double x, double y, double z) {
        this.x = (int)Math.round(x);
        this.y = (int)Math.round(y);
        this.z = (int)Math.round(z);
    }

    /**
     * Moves this point to the specified location in the (x,y,z) coordinate plane. This method is identical with setLocation(int, int, int).
     * @param x the X coordinate of the new location
     * @param y the Y coordinate of the new location
     * @param z the Z coordinate of the new location
     */
    public void move(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Translates this point, at location (x,y,z), by dx along the x axis, dy
     * along the y axis and dz along the z axis so that it now represents the point (x+dx,y+dy,z+dz).
     *
     * @param dx he distance to move this point along the X axis
     * @param dy he distance to move this point along the Y axis
     * @param dz he distance to move this point along the Z axis
     */
    public void translate(int dx, int dy, int dz) {
        this.x += dx;
        this.y += dy;
        this.z += dz;
    }

    /**
     * Determines whether or not two points are equal.
     * Two instances of Point2D are equal if the values
     * of their x, y and z member fields, representing their
     * position in the coordinate space, are the same.
     *
     * @param obj an object to be compared with this Point2D
     * @return true if the object to be compared is an instance of Point2D and has the same values; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if(obj instanceof Point3D) {
            if((this.x == ((Point3D) obj).x) &&
                    (this.y == ((Point3D) obj).y) &&
                    (this.z == ((Point3D) obj).z)) {
                equals = true;
            }
        }
        return equals;
    }

    /**
     * Returns a string representation of this point and its location in the (x,y,z) coordinate space.
     * This method is intended to be used only for debugging purposes, and the content and format of
     * the returned string may vary between implementations. The returned string may be empty but may not be null.
     * @return
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

}
