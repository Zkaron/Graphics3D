package Project;

/**
 * Created by Erik on 12/3/2017.
 */
public class Index {
    public int index1;
    public int index2;
    public int index3;

    public Index() {
        index1 = Integer.MAX_VALUE;
        index2 = Integer.MAX_VALUE;
        index3 = Integer.MAX_VALUE;
    }

    public Index(int index1, int index2) {
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = Integer.MAX_VALUE;
    }

    public Index(int index1, int index2, int index3) {
        this.index1 = index1;
        this.index2 = index2;
        this.index3 = index3;
    }
}
