import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static void DFS(ArrayList<Integer>[] adj, boolean[] vis, int x){
        vis[x] = true;
        for( int i : adj[x]){
            if(!vis[i]) DFS(adj, vis, i);
        }
    }
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        boolean[] vis = new boolean[adj.length];
        for(int i =0; i < adj.length; i++){
            if(!vis[i]){
                result++;
                DFS(adj, vis, i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}

