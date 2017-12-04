package Project;

import Core.Point3D;

import java.util.ArrayList;

/**
 * Created by Erik on 12/2/2017.
 */
public class Model {
    public ArrayList<Point3D> vertices;
    public ArrayList<Point3D> normals;
    public ArrayList<Face> faces;

    public int verticesCount;
    public int normalsCount;

    public Model() {
        vertices = new ArrayList<>();
        normals = new ArrayList<>();
        faces = new ArrayList<>();

        verticesCount = 0;
        normalsCount = 0;
    }
}
