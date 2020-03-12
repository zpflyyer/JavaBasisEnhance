package com.javabase.dsandalgo.tree.binary.clrs.graph;

import com.alibaba.fastjson.JSON;

import java.util.*;

// BFS得到的是以起始点s为圆心的同心圆：每个节点a处于以<s,a>的最短距离r为半径的圆环上
public class BFS {

    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));

        Queue<BFSNode> priorityQueue = new LinkedList<>();
        // 图的节点数组：便于标记
        BFSNode[] bfsNodes = new BFSNode[graph.getV()];
        for (int i = 0; i < bfsNodes.length; i++) {
            bfsNodes[i] = new BFSNode(false, i, Integer.MAX_VALUE);
        }
        //添加一个初始节点作为圆心，标记、入队
        priorityQueue.add(bfsNodes[0].setMarked(true).setRadius(0));

        // 入队标记，出队完成
        while (!priorityQueue.isEmpty()) {
            BFSNode node = priorityQueue.remove();
            graph.getAdj()[node.vertex].keySet().forEach(vertex -> {
                // 如果该邻接节点未被标记过: 标记之并入队列，代表该节点已经被发现
                BFSNode aja = bfsNodes[vertex];
                if (!aja.isMarked()) {
                    aja.setMarked(true);
                    aja.setRadius(node.getRadius() + 1);
                    priorityQueue.add(aja);
                }
            });
        }
        System.out.println(JSON.toJSONString(bfsNodes));
    }

    private static final class BFSNode {
        private boolean marked;
        private int vertex;
        private int radius;

        public BFSNode(boolean marked, int vertex, int radius) {
            this.marked = marked;
            this.vertex = vertex;
            this.radius = radius;
        }

        public boolean isMarked() {
            return marked;
        }

        public BFSNode setMarked(boolean marked) {
            this.marked = marked;
            return this;
        }

        public int getVertex() {
            return vertex;
        }

        public BFSNode setVertex(int vertex) {
            this.vertex = vertex;
            return this;
        }

        public int getRadius() {
            return radius;
        }

        public BFSNode setRadius(int radius) {
            this.radius = radius;
            return this;
        }

        @Override
        public String toString() {
            return "BFSNode{" +
                    "marked=" + marked +
                    ", vertex=" + vertex +
                    ", radius=" + radius +
                    '}';
        }
    }
}
