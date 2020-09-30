import java.util.*;

public class Dijkstra {
    static class pair{
        int to ,weight;
        pair(int x, int y){
            to =  x;
            weight = y;
        }
    }
    private static long distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        PriorityQueue<pair> p = new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return o1.weight - o2.weight;
            }
        });
        boolean[] vis = new boolean[adj.length];
        int[] dist = new int[adj.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        p.add(new pair(s, 0));
        dist[s] = 0;

        while( !p.isEmpty()){
            int node = p.peek().to;
            int weight = p.peek().weight;
            p.poll();
            if(vis[node]) continue;
            vis[node] = true;

            for(int i =0 ; i < adj[node].size(); i++){
                int new_node = adj[node].get(i);
                int new_weight = cost[node].get(i);

                if( !vis[new_node] && dist[new_node] > weight + new_weight){
                    dist[new_node] = weight + new_weight;
                    p.add(new pair(new_node, dist[new_node]));
                }
            }
        }
        return dist[t] == Integer.MAX_VALUE ? -1 : dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[])new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
            cost[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
//        System.out.println(Arrays.toString(adj));
//        System.out.println(Arrays.toString(cost));
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}

