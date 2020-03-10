package com.javabase.dsandalgo.tree.binary.graph;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class BFS {

    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));

        QueueNode bfs = new QueueNode(0, 0);
        PriorityQueue<QueueNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(QueueNode::getPriority));
        priorityQueue.add(bfs);

        for (int i = 1; i < graph.getV(); i++) {
            priorityQueue.add(new QueueNode(i, Integer.MAX_VALUE));
        }

        while (!priorityQueue.isEmpty()) {
            QueueNode node = priorityQueue.remove();
            graph.getAdj()[node.vertex].keySet().forEach(vertex -> {
                // 如果该邻接节点未被标记过: 标记之
                priorityQueue.stream()
                        .filter(v -> v.vertex == vertex && v.priority > node.priority + 1)
                        .findFirst()
                        .ifPresent(v -> {
                            v.setPriority(node.priority + 1);
                            node.getChildren().add(v);
                            priorityQueue.add(priorityQueue.remove());
                        });
            });
        }
        System.out.println(JSON.toJSONString(bfs));
    }

    private static final class QueueNode {
        private int vertex;
        private int priority;
        private List<QueueNode> children = new ArrayList<>();

        public QueueNode(int v, int priority) {
            this.vertex = v;
            this.priority = priority;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            QueueNode queueNode = (QueueNode) o;
            return vertex == queueNode.vertex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex);
        }

        public int getVertex() {
            return vertex;
        }

        public QueueNode setVertex(int vertex) {
            this.vertex = vertex;
            return this;
        }

        public int getPriority() {
            return priority;
        }

        public QueueNode setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        public List<QueueNode> getChildren() {
            return children;
        }

        public QueueNode setChildren(List<QueueNode> children) {
            this.children = children;
            return this;
        }

        @Override
        public String toString() {
            return "QueueNode{" +
                    "vertex=" + vertex +
                    ", priority=" + priority +
                    ", children=" + children +
                    '}';
        }
    }
}
