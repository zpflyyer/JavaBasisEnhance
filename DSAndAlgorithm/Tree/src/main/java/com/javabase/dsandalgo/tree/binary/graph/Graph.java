package com.javabase.dsandalgo.tree.binary.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Graph {

    private int v;
    private int e;
    private Map<Integer, Integer>[] adj;

    public Graph(Scanner scanner) {
        this(scanner.nextInt());
        int e = scanner.nextInt();
        for (int i = 0; i < e; i++) {
            this.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
    }

    @SuppressWarnings("unchecked")
    private Graph(int v) {
        this.v = v;
        this.e = 0;
        this.adj = (Map<Integer, Integer>[]) new Map[v];
        for (int i = 0; i < adj.length; i++) {
            this.adj[i] = new HashMap<>();
        }
    }

    private void addEdge(int u, int v, int weight) {
        this.adj[u].put(v, weight);
        this.adj[v].put(u, weight);
        this.e++;
    }

    public int getV() {
        return v;
    }

    public int getE() {
        return e;
    }

    public Map<Integer, Integer>[] getAdj() {
        return adj;
    }
}
