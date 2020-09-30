import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {
    static class Node{
        int from;
        int to;
        double weight;
        Node(int x, int y, double w){
            from = x;
            to = y;
            weight = w;
        }
        public String toString(){
            return "["+from + "," + to + "," + weight + "]";
        }
    }

    private static int find(int a, int[] parents){
        if( parents[a] < 0){
            return a;
        }
        return parents[a] = find(parents[a], parents);
    }

    private static double computeEdge(int x1, int y1, int x2, int y2){
        double x = Math.pow(Math.abs(x1 - x2), 2);
        double y = Math.pow(Math.abs(y1 - y2), 2);
        return Math.sqrt(x + y);
    }

    private static double clustering(int[] x, int[] y, int k) {
        double result = Double.MAX_VALUE;
        PriorityQueue<Node> p = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.weight > o2.weight){
                    return 1;
                }
                else if (o1.weight < o2.weight){
                    return -1;
                }
                return 0;
            }
        });
        int[] parents = new int[x.length];
        Arrays.fill(parents, -1);

        for(int i =0; i < x.length; i++){
            for(int j =i+1; j < x.length; j++){
                double weight = computeEdge(x[i], y[i], x[j], y[j]);
                p.add(new Node(i , j , weight));
            }
        }
        while(!p.isEmpty()){
            Node temp = p.poll();
            int a = find(temp.from, parents);
            int b = find(temp.to, parents);
            if( a != b ){
                if(total_cluster(parents, k) == k)
                    result = Math.min(result, temp.weight);
                else  parents[a] = b;
            }
        }
        return result;
    }

    private static int total_cluster(int[] parent, int k) {
        int res = 0;
        for(int i = 0; i < parent.length; i++) if( parent[i] < 0) res++;
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(clustering(x, y, k));
    }
}

