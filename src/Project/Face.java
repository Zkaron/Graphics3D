package Project;

import Core.Point3D;

/**
 * Created by Erik on 12/2/2017.
 */
public class Face {
    public Index vertex;
    public Index normal;

    public Face(Index vertex, Index normal) {
        this.vertex = vertex;
        this.normal = normal;
    }
}
