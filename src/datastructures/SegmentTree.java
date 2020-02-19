package datastructures;

import java.util.ArrayList;
import java.util.List;

public class SegmentTree<T> {
    public static interface Operator<X> {
        public X accept(X t, X u);
    }

    private int leftChild(int node) {
        return node * 2 + 1;
    }

    private int rightChild(int node) {
        return node * 2 + 2;
    }

    private List<T> data;
    private List<T> tree;
    private Operator<T> operator;

    public SegmentTree(List<T> data, Operator<T> operator) {
        this.data = data;
        this.operator = operator;
        int n = data.size();
        tree = new ArrayList<>();
        for (int i = 0; i < 4 * n; i++)
            tree.add(null);
        buildTree(0, data.size() - 1, 0);
    }

    private void buildTree(int start, int end, int node) {
        if (start == end) {
            tree.set(node, data.get(start));
            return;
        }

        int mid = start + (end - start) / 2;
        buildTree(start, mid, leftChild(node));
        buildTree(mid + 1, end, rightChild(node));
        tree.set(node, operator.accept(tree.get(leftChild(node)), tree.get(rightChild(node))));
    }

    public void update(int index, T value) {
        updateUtil(0, data.size() - 1, 0, index, value);
    }

    private void updateUtil(int start, int end, int node, int index, T value) {
        if (start == end) {
            tree.set(node, value);
            return;
        }
        int mid = start + (end - start) / 2;
        if (index <= mid)
            updateUtil(start, mid, node, index, value);
        else
            updateUtil(mid + 1, end, node, index, value);
        tree.set(node, operator.accept(tree.get(leftChild(node)), tree.get(rightChild(node))));
    }

    public T query(int l, int r) {
        return queryUtil(0, data.size() - 1, 0, l, r);
    }

    private T queryUtil(int start, int end, int node, int query_start, int query_end) {
        if ((start > query_end || end < query_start))
            return null;
        if (start >= query_start && end <= query_end)
            return tree.get(node);

        int mid = start + (end - start) / 2;
        T left = queryUtil(start, mid, leftChild(node), query_start, query_end);
        T right = queryUtil(mid + 1, end, rightChild(node), query_start, query_end);
        if (left == null) return right;
        if (right == null) return left;
        return operator.accept(left, right);
    }
}



