import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Graph {
    
    Map<Integer, List<Edge>> edgesMap;
    int[][] dists;
    Map<Integer, List<Integer>> incoming;
    int n;

    public Graph(int n, int[][] edges) {
        this.n = n;
        edgesMap = new HashMap<>();
        incoming = new HashMap<>();
        dists = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dists[i][j] = 0;
                } else {
                    dists[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < edges.length; i++) {
            int v = edges[i][0];
            int w = edges[i][1];
            int weight = edges[i][2];
            if (edgesMap.get(v) == null) {
                edgesMap.put(v, new ArrayList<>());
            }
            if (edgesMap.get(w) == null) {
                edgesMap.put(w, new ArrayList<>());
            }
            edgesMap.get(v).add(new Edge(v, w, weight));
            if (incoming.get(v) == null) {
                incoming.put(v, new ArrayList<>());
            }
            if (incoming.get(w) == null) {
                incoming.put(w, new ArrayList<>());
            }
            incoming.get(w).add(v);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dists[i][j] = dist(i, j);
            }
        }
    }
    
    private int dist(int v, int t) {
        if (dists[v][t] < Integer.MAX_VALUE) {
            return dists[v][t];
        }
        int min = Integer.MAX_VALUE;
        for (Edge e: edgesMap.get(v)) {
            int w = e.w;
            int weight = e.weight;
            min = Math.min(min, dist(w, t) + weight);
        }
        dists[v][t] = min;
        return min;
    }
    
    public void addEdge(int[] edge) {
        int v = edge[0];
        int w = edge[1];
        int weight = edge[2];
        if (edgesMap.get(v) == null) {
            edgesMap.put(v, new ArrayList<>());
        }
        if (edgesMap.get(w) == null) {
            edgesMap.put(w, new ArrayList<>());
        }
        edgesMap.get(v).add(new Edge(v, w, weight));
        if (incoming.get(v) == null) {
            incoming.put(v, new ArrayList<>());
        }
        if (incoming.get(w) == null) {
            incoming.put(w, new ArrayList<>());
        }
        incoming.get(w).add(v);
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {v, w});
        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            int x = next[0];
            int y = next[1];
            Edge e = getEdge(x, y);
            boolean changed = false;
            for (int i = 0; i < n; i++) {
                int newSum = Math.min(dists[x][i], e.weight + dists[y][i]);
                if (newSum < dists[x][i]) {
                    changed = true;
                    dists[x][i] = newSum;
                }
            }
            if (changed) {
                for (Integer neigh: incoming.get(x)) {
                    queue.add(new int[] {neigh, x});
                }
            }
        }
    }
    
    private Edge getEdge(int v, int w) {
        List<Edge> edgs = edgesMap.get(v);
        for (Edge e: edgs) {
            if (e.w == w) {
                return e;
            }
        }
        return null;
    }
    
    public int shortestPath(int node1, int node2) {
        return dists[node1][node2];
    }
    
    class Edge {
        int v;
        int w;
        int weight;
        
        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */