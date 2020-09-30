import java.util.*;

public class Bipartite {
    private static boolean DFS(ArrayList<Integer>[] adj, int[] colors, int i, int color){
        colors[i] = color;
        for(int child : adj[i]){
            if(colors[child] == -1){
                if( !DFS(adj, colors, child, 1- color) ) return false;
            }
            else if(colors[child] == colors[i]){
                    return false;
            }
        }
        return true;
    }
    private static int bipartite_(ArrayList<Integer>[] adj) {
        int[] colors = new int[adj.length];
        Arrays.fill(colors, -1);

        for(int i =0; i < adj.length; i++){
            if(colors[i] == -1){
                if( !DFS(adj, colors, i, 1)) return 0;
            }
        }
        return 1;
    }
    private static int bipartite(ArrayList<Integer>[] adj) {
        //write your code here
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Boolean> black = new HashMap<>();

        for (int i = 0; i < adj.length; i++) {
            if (black.containsKey(i)) continue;

            queue.add(i);
            black.put(i, true);

            while (!queue.isEmpty()) {
                int parent = queue.remove();
                boolean isParentBlack = black.get(parent);

                for (int child : adj[parent]) {
                    Boolean isChildBlack = black.get(child);

                    if (isChildBlack == null) { //unexplored
                        black.put(child, !isParentBlack);
                        queue.add(child);
                        continue;
                    }

                    //if colour is same
                    if (isChildBlack == isParentBlack) return 0;
                }
            }
        }

        return 1;
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
        System.out.println(bipartite(adj));
    }
}

