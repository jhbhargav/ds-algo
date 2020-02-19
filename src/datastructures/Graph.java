package datastructures;

import java.util.*;

public class Graph<T> {
    private HashMap<T, HashMap<T, Integer>> adjacencyList = new HashMap<>();
    private Integer edgeCount = 0;
    public  interface VisitorFunction<T>{
        T accept(T u);
    }

    public void addVertex(T source) {
        adjacencyList.put(source, new HashMap<>());
    }

    public void addEdge(T source, T dest, boolean bi, int weight) {
        if (!adjacencyList.containsKey(source))
            addVertex(source);
        if (!adjacencyList.containsKey(dest))
            addVertex(dest);
        adjacencyList.get(source).put(dest, weight);
        edgeCount++;
        if (bi) {
            adjacencyList.get(dest).put(source, weight);
            edgeCount++;
        }
    }

    public int getVertexCount() {
        return adjacencyList.keySet().size();
    }

    public boolean hasVertex(T x) {
        return adjacencyList.containsKey(x);
    }

    public boolean hasEdge(T from, T to) {
        return hasVertex(from) && hasVertex(to) && adjacencyList.get(from).containsKey(to);
    }

    public void bfs(T source, VisitorFunction<T> func) {
        HashSet<T> visited = new HashSet<>();
        Queue<T> q = new Queue<>();
        q.enqueue(source);
        visited.add(source);
        while (!q.isEmpty()) {
            T node = q.dequeue();
            func.accept(node);
            for (T key : adjacencyList.get(source).keySet()) {
                if (!visited.contains(node)) {
                    q.enqueue(key);
                    visited.add(key);
                }
            }
        }
    }

    public void dfs(T source, VisitorFunction<T> func) {
        HashSet<T> visited = new HashSet<>();
        dfsUtil(source, visited, func);
        for (T e : adjacencyList.keySet()) {
            if (!visited.contains(e)) {
                dfsUtil(e, visited, func);
            }
        }
    }

    private void dfsUtil(T source, HashSet<T> visited, VisitorFunction<T>func) {
        if (!visited.contains(source)) {
            func.accept(source);
            visited.add(source);
            for (Map.Entry<T, Integer> entry : adjacencyList.get(source).entrySet()) {
                if (!visited.contains(entry.getKey()))
                    dfsUtil(entry.getKey(), visited,func);
            }
        }
    }

    public int diameterOfGraph(T source) {
        if (source == null)
            return 0;

        int max1 = 0;
        int max2 = 0;

        for (Map.Entry<T, Integer> entry : adjacencyList.get(source).entrySet()) {
            int height = depthTree(entry.getKey());
            if (max1 < height) {
                max2 = max1;
                max1 = height;
            } else if (max2 < height)
                max2 = height;
        }
        int maxdia = 0;
        for (Map.Entry<T, Integer> entry : adjacencyList.get(source).entrySet()) {
            maxdia = Math.max(maxdia, diameterOfGraph(entry.getKey()));
        }

        return Math.max(maxdia, max1 + max2 + 1);
    }

    public int depthTree(T source) {
        if (source == null)
            return 0;
        int maxDepth = 0;
        for (Map.Entry<T, Integer> entry : adjacencyList.get(source).entrySet())
            maxDepth = Math.max(maxDepth, depthTree(entry.getKey()));

        return maxDepth + 1;
    }

    public HashMap<T, Integer> shortestPath(T source) {
        HashMap<T, Integer> dist = new HashMap<>();
        for (T x : adjacencyList.keySet())
            dist.put(x, Integer.MAX_VALUE);
        HashSet<T> visited = new HashSet<>();
        PriorityQueue<Node> explored = new PriorityQueue<>(getVertexCount(), new Node(null, 0));
        explored.add(new Node(source, 0));
        dist.put(source, 0);
        while (!explored.isEmpty()) {
            Node temp = explored.poll();
            T u = temp.node;
            for (Map.Entry<T, Integer> entry : adjacencyList.get(u).entrySet()) {
                if (dist.get(u) != Integer.MAX_VALUE && !visited.contains(entry.getKey()) && entry.getValue() != null && dist.get(entry.getKey()) >= dist.get(u) + entry.getValue()) {
                    visited.add(entry.getKey());
                    dist.put(entry.getKey(), dist.get(u) + entry.getValue());
                    explored.add(new Node(entry.getKey(), dist.get(entry.getKey())));
                }
            }
        }
        for (Map.Entry<T, Integer> entry : dist.entrySet()) {
            if (entry.getValue() == Integer.MAX_VALUE)
                dist.put(entry.getKey(), -1);
        }
        return dist;
    }

    public Integer getEdgeCount() {
        return edgeCount;
    }

    class Node implements Comparator<Node> {
        T node;
        int cost;


        public Node(T x, int y) {
            node = x;
            cost = y;
        }

        public int compare(Node a, Node b) {
            return Integer.compare(a.cost, b.cost);
        }
    }

    public static void main(String[] args) {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2, false, 1);
        g.addEdge(2, 3, false, 1);
        g.addEdge(2, 6, false, 1);
        g.addEdge(1, 7, false, 1);
        g.addEdge(3, 4, false, 1);
        g.addEdge(3, 5, false, 1);

        System.out.println(g.getVertexCount());
        System.out.println(g.getEdgeCount());
        g.bfs(1, u -> u);
        System.out.println();
        g.dfs(1,u -> u);
        System.out.println();
        System.out.println(g.diameterOfGraph(1));
        HashMap<Integer, Integer> res = g.shortestPath(1);
        for (Map.Entry<Integer, Integer> entry : res.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());
    }

}
