import java.util.*;

public class StronglyConnected {
    public static void DFS(Deque<Integer> st, ArrayList<Integer>[] adj, boolean[] vis, int i){
        vis[i] = true;
        for(int child : adj[i]){
            if( !vis[child]) DFS(st, adj, vis, child);
        }
        st.offerLast(i);
    }
    public static void DFS2(ArrayList<Integer>[] adj, boolean[] vis, int i){
        vis[i] = true;
        for(int child : adj[i]){
            if( !vis[child]) DFS2(adj, vis, child);
        }
    }
    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        boolean[] vis = new boolean[adj.length];
        Deque<Integer> st = new LinkedList<>();
        int scc = 0;

        for(int i = 0; i < adj.length; i++){
            if(!vis[i]){
                DFS(st, adj, vis, i);
            }
        }
        ArrayList<Integer>[] tr = new ArrayList[adj.length];
        for (int i = 0; i < adj.length; i++) {
            tr[i] = new ArrayList<Integer>();
        }
        for(int i =0; i < adj.length; i++){
            for(int node : adj[i]){
                tr[node].add(i);
            }
        }
        Arrays.fill(vis, false);
        while(!st.isEmpty()){
            int node = st.pollLast();
            if(!vis[node]){
                scc++;
                DFS2(tr, vis, node);
            }
        }
        return scc;
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
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }
}

