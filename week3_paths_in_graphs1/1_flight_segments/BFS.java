import java.util.*;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        Deque<Integer> q = new LinkedList<>();
        int[] dist =new int[adj.length];
        boolean[] vis= new boolean[adj.length];

        q.add(s);
        dist[s] = 0;
        vis[s] = true;

        while( !q.isEmpty()){
            int temp = q.poll();

            for(int i  : adj[temp]){
                if(!vis[i]){
                    vis[i] = true;
                    dist[i] = dist[temp] + 1;
                    q.add(i);
                }
            }
        }
        if(dist[t] != 0) return dist[t];
        return -1;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}

