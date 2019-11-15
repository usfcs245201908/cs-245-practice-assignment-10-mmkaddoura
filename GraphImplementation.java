import java.util.List;
import java.util.ArrayList;

public class GraphImplementation implements Graph{

    private int[][] matrx;
    private int vers;

    public GraphImplementation(int vers){
        this.vers = vers;
        matrx = new int[vers][vers];
    }

    public void addEdge(int v1, int v2) throws Exception{
        if ((v1 < 0 || v1 >= vers) || (v2 < 0 || v2 >= vers))
            throw new Exception("Invalid verticies");
        matrx[v1][v2] = 1;
    }

    public List<Integer> topologicalSort(){
        List<Integer> list = new ArrayList<Integer>();
        int [] sum = new int[vers];
        for (int i=0; i < vers; i++){
            for (int j=0; j < vers; j++)
                sum[i]+=matrx[j][i];
        }
        for (int i=0; i < vers; i++){
            int next=zero(sum);
            if (next == -1){
                return list;
            }
            list.add(next);
            System.out.println(next);
            sum[next]=-1;
            for (int j=0; j<vers; j++)
                sum[j]-=matrx[next][j];
        }
        return list;
    }

    public List<Integer> neighbors(int vertex) throws Exception{
        if (vertex < 0 || vertex >= vers)
            throw new Exception("Invalid vertex");

        List<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<vers; i++){
            if (matrx[vertex][i] == 1)
                list.add(i);
        }
        return list;
    }

    public int zero(int [] a){
        for (int i=0; i<a.length; i++){
            if (a[i]==0)
                return i;
        }
        return -1;
    }
}