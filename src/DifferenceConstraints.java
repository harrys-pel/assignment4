
import java.util.*;
import java.io.*;
 
public class DifferenceConstraints {  
 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner iFile = new Scanner(new FileReader("example.txt"));
        int K = iFile.nextInt();
 
        for(int c = 1; c <= K; c++) {
            int N = iFile.nextInt();
            int M = iFile.nextInt();
            int[] d = new int[N];
            Edge[] edges = new Edge[M];
 
            System.out.printf("Case #%d:\n", c);
 
            // initialization
            for(int i = 1; i < N; i++)
                d[i] = Integer.MAX_VALUE;
 
            // read all edges
            for(int i = 0; i < M; i++)
                edges[i] = new Edge(iFile.nextInt(), iFile.nextInt(), iFile.nextInt());
 
            // relaxation
            for(int i = 0; i < N-1; i++) {
                for(int j = 0; j < M; j++) {
                    Edge e = edges[j];
                    if(d[e.d] > d[e.s] + e.w)
                        d[e.d] = d[e.s] + e.w;
                }
            }
 
            boolean flag = false;
 
            // reporting
            for(int j = 0; j < M; j++) {
                Edge e = edges[j];
                if(d[e.d] > d[e.s] + e.w) {
                    System.out.println("  No solution -- graph has a negative cycle.");
                    flag = true;
                }
            }
 
            for(int i = 0; !flag && i < N; i++)
                System.out.printf("  %d --> %d : %d\n", 0, i, d[i]);
 
            System.out.println();
        }
 
        iFile.close();
    }
 
}
 
class Edge {
 
    public int s, d, w;
 
    public Edge(int s, int d, int w) {
        this.s = s;
        this.d = d;
        this.w = w;
    }
 
}