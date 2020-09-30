import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    private static boolean DFS(ArrayList<Integer>[] adj, boolean[] vis, int x){
        vis[x] = true;
        for(int i : adj[x]){

            if(!vis[i]){
                boolean temp = DFS(adj, vis, i);
                if(temp) return true;
            }
            else{
                return true;
            }
        }
        vis[x] = false;
        return false;
    }
    private static int acyclic(ArrayList<Integer>[] adj) {
        boolean[] vis = new boolean[adj.length];
        for(int i =0; i < adj.length; i++){
            if(!vis[i]){
                boolean temp = DFS(adj, vis, i);
                if(temp) return 1;
            }
        }
        return 0;
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
        }
        System.out.println(acyclic(adj));
    }
}

