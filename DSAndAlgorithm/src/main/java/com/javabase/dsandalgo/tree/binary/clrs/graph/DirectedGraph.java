package com.javabase.dsandalgo.tree.binary.clrs.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectedGraph {

    private Vertex[] vertices;

    private Map<Integer, List<Integer>> edges = new HashMap<>();

    private int time;

    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(new Scanner(System.in));
        String[] result = graph.topologicalSort();
        System.out.println(Arrays.toString(result));
    }

    public DirectedGraph(Scanner sc) {
        int v = sc.nextInt();
        this.vertices = new Vertex[v];
        for (int i = 0; i < v; i++) {
            this.vertices[i] = new Vertex(i, sc.next());
            this.edges.put(i, new ArrayList<>());
        }

        int e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            edges.get(from).add(to);
        }
    }

    private String[] topologicalSort() {
        this.dfs();
        String[] result = new String[this.vertices.length];
        return Stream.of(this.vertices)
                .sorted(Comparator.comparing(Vertex::getFinish).reversed())
                .map(Vertex::getAlias)
                .collect(Collectors.toList())
                .toArray(result);
    }

    private void dfs() {
        for (Vertex vertex : this.vertices) {
            if (!vertex.marked) {
                this.dfsVisit(vertex.vertex);
            }
        }
    }

    private void dfsVisit(int start) {
        Vertex vertex = this.vertices[start];
        vertex.find = ++this.time;
        vertex.marked = true;

        for (Integer v : this.edges.get(start)) {
            if (!this.vertices[v].marked) {
                this.dfsVisit(v);
            }
        }

        vertex.finish = ++this.time;
    }

    private static final class Vertex {
        private int vertex;
        private String alias;
        private boolean marked;
        private int find;
        private int finish;

        public Vertex(int vertex, String alias) {
            this.vertex = vertex;
            this.alias = alias;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex1 = (Vertex) o;
            return vertex == vertex1.vertex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
        }

        public int getFinish() {
            return finish;
        }

        public String getAlias() {
            return alias;
        }
    }
}
