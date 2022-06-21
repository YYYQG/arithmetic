/**
 * @description
 * @date 2022/06/21 16:53
 */
public class UF {

    private int[] id;

    private int count;

    UF(int n) {
        count = n;
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    /*public int find(int i) {
        return id[i];
    }

    public void union(int p, int q) {

        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        for (int i = 0; i < id.length; i++) {
            if (id[i] == p) id[i] = q;
        }
        count --;
    }*/

    public int find(int i) {
        while (i != id[i]) i = id[i];
        return id[i];
    }

    public void union(int p, int q) {

        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        id[rootP] = rootQ;
        count --;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    public static void main(String[] args) {



    }


}
