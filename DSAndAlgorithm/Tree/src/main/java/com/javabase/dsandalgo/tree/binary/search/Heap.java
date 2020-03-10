package com.javabase.dsandalgo.tree.binary.search;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Heap<T> {
    public static void main(String[] args) {
        Heap<Integer> solution = new Heap<>();
        Integer[] array = {1, 3, 2, 6, 4, 5, 10, 9, 11, 14, 12};
        List<Integer> integerList = Arrays.asList(array);
        System.out.println(Arrays.toString(solution.heapSort(integerList)));
        int[] result = solution.topM(5);
        System.out.println(Arrays.toString(result));
    }

    private int[] topM(int m) {
        Scanner scanner = new Scanner(System.in);
        // 小根堆，多分配一个位置，每次淘汰root
        int capacity = m + 1;
        int[] queue = new int[capacity + 1];
        int N = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            if (s.equalsIgnoreCase("exit") || s.equalsIgnoreCase("quit")) {
                System.out.println("---------bye--------");
                break;
            } else if (s.startsWith("q")) {
                System.out.println(Arrays.toString(Arrays.copyOfRange(queue, 1, N + 1)));
                continue;
            }
            int i = 0;
            try {
                i = Integer.parseInt(s);
            } catch (NumberFormatException nfe) {
                System.out.println("---------bye--------");
                break;
            }
            // 插入新元素
            queue[++N] = i;
            this.swim1(queue, N, N);

            // 队列已满时，删除小根堆堆顶，将队列尾部替换到堆顶，并恢复最小堆性质，空出堆尾
            if (N == queue.length - 1) {
                // 删除小根堆堆顶,将队尾元素调入堆顶
                this.exchange(queue, 1, N, N);
                // 堆的size - 1
                N--;
                // 恢复最小堆性质
                this.sink1(queue, 1, N);
            }
        }
        // 循环结束时，最小堆里共有N个元素（N <= m）
        Stack<Integer> topMStack = new Stack<>();
        int heapSize = N;
        for (int i = 1; i <= heapSize; i++) {
            topMStack.push(queue[1]);
            if (N > 1) {
                this.exchange(queue, 1, N, N);
                N--;
                this.sink1(queue, 1, N);
            }
        }
        int[] result = new int[m];
        int idx = 0;
        while (!topMStack.isEmpty()) {
            result[idx++] = topMStack.pop();
        }
        return result;
    }

    // 正序排序：使用大根堆，每次deleteMax, 之后按N-1的size sink(1)
    private int[] heapSort(List<Integer> list) {
        int N = list.size();
        int[] queue = new int[N + 1];
        // store queue in array from idx 1 to N
        for (int i = 0; i < N; i++) {
            queue[i + 1] = list.get(i);
        }
        // build heap
        for (int i = N / 2; i >= 1; i--) {
            this.sink(queue, i, N);
        }
        // sort
        while (N > 1) {
            this.exchange(queue, 1, N, N);
            N--;
            this.sink(queue, 1, N);
        }
        return queue;
    }

    private void sink(int[] queue, int k, int N) {
        if (k > N || k <= 0) {
            throw new IndexOutOfBoundsException("队列数组访问越界,合法范围[1, N]");
        }
        // sink until reach leaf node or bigger than all child node(s)
        while (2 * k <= N) {
            int j = 2 * k;
            if (j + 1 <= N && this.less(queue, j, j + 1, N)) {
                j++;
            }
            if (!this.less(queue, k, j, N)) {
                break;
            }
            this.exchange(queue, k, j, N);
            k = j;
        }
    }

    private void sink1(int[] queue, int k, int N) {
        if (k > N || k <= 0) {
            throw new IndexOutOfBoundsException("队列数组访问越界,合法范围[1, N]");
        }
        // sink until reach leaf node or bigger than all child node(s)
        while (2 * k <= N) {
            int j = 2 * k;
            if (j + 1 <= N && this.less(queue, j + 1, j, N)) {
                j++;
            }
            if (!this.less(queue, j, k, N)) {
                break;
            }
            this.exchange(queue, k, j, N);
            k = j;
        }
    }

    private void swim(int[] queue, int k, int N) {
        if (k > N || k <= 0) {
            throw new IndexOutOfBoundsException("队列数组访问越界,合法范围[1, N]");
        }
        while (k > 1 && this.less(queue, k / 2, k, N)) {
            this.exchange(queue, k / 2, k, N);
            k /= 2;
        }
    }

    private void swim1(int[] queue, int k, int N) {
        if (k > N || k <= 0) {
            throw new IndexOutOfBoundsException("队列数组访问越界,合法范围[1, N]");
        }
        while (k > 1 && this.less(queue, k, k / 2, N)) {
            this.exchange(queue, k / 2, k, N);
            k /= 2;
        }
    }

    private boolean less(int[] queue, int i, int j, int N) {
        if (i > N || j > N || i <= 0 || j <= 0) {
            throw new IndexOutOfBoundsException("队列数组访问越界,合法范围[1, N]");
        }
        return queue[i] < queue[j];
    }

    private void exchange(int[] queue, int i, int j, int N) {
        if (i > N || j > N || i <= 0 || j <= 0) {
            throw new IndexOutOfBoundsException("队列数组访问越界,合法范围[1, N]");
        }
        int tmp = queue[i];
        queue[i] = queue[j];
        queue[j] = tmp;
    }

}
