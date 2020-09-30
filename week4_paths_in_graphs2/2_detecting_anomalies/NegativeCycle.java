import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NegativeCycle {
    private static int negativeCycle(ArrayList<ArrayList<Integer>> adj, int n, int m) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for(int i = 1; i < n; i++) {
            for (ArrayList<Integer> edge : adj) {
                int from = edge.get(0);
                int to = edge.get(1);
                int weight = edge.get(2);
                if (dist[from] != Integer.MAX_VALUE && dist[to] > dist[from] + weight) {
                    dist[to] = dist[from] + weight;
                }
            }
        }

        for (ArrayList<Integer> edge : adj) {
            int from = edge.get(0);
            int to = edge.get(1);
            int weight = edge.get(2);
            if (dist[from] != Integer.MAX_VALUE &&  dist[to] > dist[from] + weight) {
                return 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj.add(new ArrayList<>(Arrays.asList(--x, --y, w)));
        }
//        System.out.println(adj);
        System.out.println(negativeCycle(adj, n, m));
    }
}

