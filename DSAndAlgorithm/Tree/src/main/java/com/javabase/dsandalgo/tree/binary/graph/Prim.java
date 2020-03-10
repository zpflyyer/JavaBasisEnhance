package com.javabase.dsandalgo.tree.binary.graph;

import java.util.*;

public class Prim {

    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));

        QueueNode mst = null;

        // insert all vertex into priority Queue with infinite priority
        PriorityQueue<QueueNode> queue = new PriorityQueue<>(Comparator.comparingInt(QueueNode::getPriority));
        for (int i = 0; i < graph.getV(); i++) {
            queue.add(new QueueNode(i, Integer.MAX_VALUE));
        }

        // prim greedy choice
        while (!queue.isEmpty()) {
            QueueNode min = queue.remove();
            if (mst == null) {
                mst = min;
            }
            graph.getAdj()[min.vertex].forEach((v, weight) -> {
                // 仅更新在V-A里的节点的优先级
                Optional<QueueNode> nodeOptional = queue.stream().filter(node -> node.getVertex() == v).findFirst();
                nodeOptional.ifPresent(node -> {
                    // 更新时需要注意：在prim算法过程中，一个节点在未取出之前，其优先级和其MST父节点可能会被更新多次
                    if (weight < node.getPriority()) {
                        node.setPriority(weight);
                        node.setParent(min);
                        // 调整优先级
                        queue.add(queue.remove());
                    }
                });
            });
        }
    }

    private static final class QueueNode {
        private int vertex;
        private int priority;
        private QueueNode parent;

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

        public QueueNode setParent(QueueNode parent) {
            this.parent = parent;
            return this;
        }

        @Override
        public String toString() {
            return "QueueNode{" +
                    "vertex=" + vertex +
                    ", priority=" + priority +
                    ", parent=" + parent +
                    '}';
        }
    }
}
