package Project;

import Core.Point3D;

import java.io.*;

/**
 * Created by Erik on 12/2/2017.
 */
public class ObjLoader {

    public ObjLoader() {

    }

    public Model load(File f) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Model m = new Model();
        String line;
        while((line = reader.readLine()) != null) {
            if(line.startsWith("v ")) {
                double x = Double.parseDouble(line.split(" ")[1]);
                double y = Double.parseDouble(line.split(" ")[2]);
                double z = Double.parseDouble(line.split(" ")[3]);
                m.vertices.add(new Point3D(x, y, z));
                m.verticesCount++;
            } else if(line.startsWith("vn ")) {
                double x = Double.parseDouble(line.split(" ")[1]);
                double y = Double.parseDouble(line.split(" ")[2]);
                double z = Double.parseDouble(line.split(" ")[3]);
                m.normals.add(new Point3D(x, y, z));
                m.normalsCount++;
            } else if(line.startsWith("f ")) {
                String[] splittedSecondArgument = line.split(" ")[1].split("/");
                Index vertexIndices = null, normalIndices = null;
                if(splittedSecondArgument.length == 1) {   //File just contains vertices
                    if(line.split(" ").length == 4) {
                        vertexIndices = new Index(Integer.parseInt(line.split(" ")[1]),
                                Integer.parseInt(line.split(" ")[2]),
                                Integer.parseInt(line.split(" ")[3]));
                    } else if (line.split(" ").length == 5) {
                        vertexIndices = new Index(Integer.parseInt(line.split(" ")[1]),
                                Integer.parseInt(line.split(" ")[2]));
                        m.faces.add(new Face(vertexIndices, null));  //We add the first relation
                        vertexIndices = new Index(Integer.parseInt(line.split(" ")[3]),
                                Integer.parseInt(line.split(" ")[4]));
                    }
                    m.faces.add(new Face(vertexIndices, null));

                } else if(splittedSecondArgument.length == 2) {   //File contains vertices and vertices normals
                    vertexIndices = new Index(Integer.parseInt(line.split(" ")[1].split("/")[0]),
                            Integer.parseInt(line.split(" ")[2].split("/")[0]),
                            Integer.parseInt(line.split(" ")[3].split("/")[0]));

                    normalIndices = new Index(Integer.parseInt(line.split(" ")[1].split("/")[1]),
                            Integer.parseInt(line.split(" ")[2].split("/")[1]),
                            Integer.parseInt(line.split(" ")[3].split("/")[1]));
                    m.faces.add(new Face(vertexIndices, normalIndices));

                } else if(splittedSecondArgument.length == 3) {   //File contains vertices,vertices textures and vertices normals.
                    //Not supported yet
                    vertexIndices = new Index(Integer.parseInt(line.split(" ")[1].split("/")[0]),
                            Integer.parseInt(line.split(" ")[2].split("/")[0]),
                            Integer.parseInt(line.split(" ")[3].split("/")[0]));

                    normalIndices = new Index(Integer.parseInt(line.split(" ")[1].split("/")[2]),
                            Integer.parseInt(line.split(" ")[2].split("/")[2]),
                            Integer.parseInt(line.split(" ")[3].split("/")[2]));
                    m.faces.add(new Face(vertexIndices, normalIndices));
                }
            }
        }
        return m;
    }
}
