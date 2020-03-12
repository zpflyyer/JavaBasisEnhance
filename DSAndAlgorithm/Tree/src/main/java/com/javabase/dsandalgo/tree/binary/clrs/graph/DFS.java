package com.javabase.dsandalgo.tree.binary.clrs.graph;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// DFS搜索时，呈现括号化关系：节点的<发现，处理>时间区间d1位于父节点的<发现，处理>时间区间d2内
public class DFS {

    private Graph graph;
    private DFSNode[] dfsNodes;
    private int time = 0;

    public DFS(Graph graph, DFSNode[] dfsNodes, int time) {
        this.graph = graph;
        this.dfsNodes = dfsNodes;
        this.time = time;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(new Scanner(System.in));
        DFSNode[] dfsNodes = new DFSNode[graph.getV()];
        for (int i = 0; i < dfsNodes.length; i++) {
            dfsNodes[i] = new DFSNode(i, Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
        DFS dfs = new DFS(graph, dfsNodes, 0);
        dfs.dfsVisit();
    }

    private void dfsVisit() {
        this.dfsVisit(this.dfsNodes[0]);
        System.out.println(JSON.toJSONString(this.dfsNodes[0]));
    }

    // 入调用栈标记，出调用栈完成
    private void dfsVisit(DFSNode node) {
        // 访问该节点
        node.setMarked(true);

        node.setDiscover(++this.time);

        // 访问该节点的所有子节点
        this.graph.getAdj()[node.getVertex()].keySet()
                .forEach(vertex -> {
                    DFSNode aja = dfsNodes[vertex];
                    if (!aja.isMarked()) {
                        node.getChildren().add(dfsNodes[vertex]);
                        this.dfsVisit(dfsNodes[vertex]);
                    }
                });

        node.setFinish(++this.time);
    }

    private static final class DFSNode {
        private int vertex;
        private int discover;
        private int finish;
        private boolean marked;
        private List<DFSNode> children = new ArrayList<>();

        public DFSNode(int vertex, int discover, int finish) {
            this.vertex = vertex;
            this.discover = discover;
            this.finish = finish;
        }

        public int getVertex() {
            return vertex;
        }

        public DFSNode setVertex(int vertex) {
            this.vertex = vertex;
            return this;
        }

        public int getDiscover() {
            return discover;
        }

        public DFSNode setDiscover(int discover) {
            this.discover = discover;
            return this;
        }

        public int getFinish() {
            return finish;
        }

        public DFSNode setFinish(int finish) {
            this.finish = finish;
            return this;
        }

        public boolean isMarked() {
            return marked;
        }

        public DFSNode setMarked(boolean marked) {
            this.marked = marked;
            return this;
        }

        public List<DFSNode> getChildren() {
            return children;
        }

        public DFSNode setChildren(List<DFSNode> children) {
            this.children = children;
            return this;
        }

        @Override
        public String toString() {
            return "DFSNode{" +
                    "vertex=" + vertex +
                    ", discover=" + discover +
                    ", finish=" + finish +
                    ", marked=" + marked +
                    ", children=" + children +
                    '}';
        }
    }

}
