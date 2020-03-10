package com.javabase.dsandalgo.tree.binary.graph;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Prim {

    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));

        QueueNode mst = new QueueNode(0, 0);
        Map<Integer, QueueNode> vertex2QueueNode = new HashMap<>();
        // insert all vertex into priority Queue with infinite priority
        PriorityQueue<QueueNode> queue = new PriorityQueue<>(Comparator.comparingInt(QueueNode::getPriority));
        queue.add(mst);
        for (int i = 1; i < graph.getV(); i++) {
            queue.add(new QueueNode(i, Integer.MAX_VALUE));
        }

        // prim greedy choice
        while (!queue.isEmpty()) {
            QueueNode min = queue.remove();
            vertex2QueueNode.put(min.vertex, min);
            // prim算法当节点被出队时，其父亲必然是已经最终确定了的
            if (!min.equals(mst)) {
                vertex2QueueNode.keySet().stream()
                        .filter(vertex -> vertex.equals(min.parent.vertex))
                        .findFirst()
                        .ifPresent(parentVertex -> vertex2QueueNode.get(parentVertex).getChildren().add(min));
            }
            graph.getAdj()[min.vertex].forEach((v, weight) -> {
                // 仅更新在V-A里的节点的优先级
                Optional<QueueNode> nodeOptional = queue.stream().filter(node -> node.getVertex() == v).findFirst();
                nodeOptional.ifPresent(node -> {
                    // 更新时需要注意：在prim算法过程中，一个节点在未取出之前，其优先级和其MST父节点可能会被更新多次，以最短的为准
                    if (weight < node.getPriority()) {
                        node.setPriority(weight);
                        node.setParent(min);
                        // 调整优先级
                        queue.add(queue.remove());
                    }
                });
            });
        }
        System.out.println(JSON.toJSONString(mst));
    }

    private static final class QueueNode {
        private int vertex;
        private int priority;
        private QueueNode parent;
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

        public QueueNode setParent(QueueNode parent) {
            this.parent = parent;
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
