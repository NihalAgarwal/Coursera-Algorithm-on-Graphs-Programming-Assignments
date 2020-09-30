import java.util.*;

public class ShortestPaths {

    private static void shortestPaths(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, long[] distance, int[] reachable, int[] shortest) {
        distance[s] = 0;
        reachable[s] = 1;
        Queue < Integer > queue = new LinkedList <> ();
        for (int i = 0; i < adj.length; i++) {
            for (int u = 0; u < adj.length; u++) {
                for (int v = 0; v < adj[u].size(); v++) {
                    if (distance[u] != Long.MAX_VALUE && distance[adj[u].get(v)] > distance[u] + cost[u].get(v)) {
                        distance[adj[u].get(v)] = distance[u] + cost[u].get(v);
                        reachable[adj[u].get(v)] = 1;
                        if (i == adj.length - 1) {
                            queue.add(adj[u].get(v));
                        }
                    }
                }
            }
        }
//        System.out.println(queue);
//        System.out.println(Arrays.toString(reachable));
//        System.out.println(Arrays.toString(shortest));
//        System.out.println(Arrays.toString(distance));

        int[] visited = new int[adj.length];
        while (!queue.isEmpty()) {
            int u = queue.poll();
            visited[u] = 1;
            shortest[u] = 0;
            for (int v: adj[u]) {
                if (visited[v] == 0) {
                    queue.add(v);
                    visited[v] = 1;
                    shortest[v] = 0;
                }
            }
        }
//        System.out.println(Arrays.toString(reachable));
//        System.out.println(Arrays.toString(shortest));
//        System.out.println(Arrays.toString(distance));
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
        int s = scanner.nextInt() - 1;
        long distance[] = new long[n];
        int reachable[] = new int[n];
        int shortest[] = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Long.MAX_VALUE;
            reachable[i] = 0;
            shortest[i] = 1;
        }
        shortestPaths(adj, cost, s, distance, reachable, shortest);
        for (int i = 0; i < n; i++) {
            if (reachable[i] == 0) {
                System.out.println('*');
            } else if (shortest[i] == 0) {
                System.out.println('-');
            } else {
                System.out.println(distance[i]);
            }
        }
    }

}

