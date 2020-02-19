package datastructures;

import java.util.ArrayList;
import java.util.List;

public class LazyPropagation<T> {
    public static interface Operation<X> {
        public X accept(X u, X v);
    }

    private List<T> data;
    private List<T> tree;
    private List<T> lazy;
    private Operation<T> operation;

    private int leftChild(int node) {
        return 2 * node + 1;
    }

    private int rightChild(int node) {
        return 2 * node + 2;
    }


    LazyPropagation(List<T> data,Operation<T> operation) {
        this.data = data;
        this.operation = operation;
        int n = 4 * data.size();
        tree = new ArrayList<>();
        lazy = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(null);
            lazy.add(null);
        }
        LazyTree(0, data.size() - 1, 0);
    }

    public void LazyTree(int start, int end, int node) {
        if (start == end) {
            tree.set(node, data.get(start));
            return;
        }
        int mid = start + (end - start) / 2;
        LazyTree(start, mid, leftChild(node));
        LazyTree(mid + 1, end, rightChild(node));
        tree.set(node, operation.accept(tree.get(leftChild(node)), tree.get(rightChild(node))));
    }

    public void updateQuery(int left, int right, T value) {
        updatequeryutil(0, data.size() - 1, 0, left, right, value);
    }

    private void updatequeryutil(int start, int end, int node, int l, int r, T value) {
        if (lazy.get(node) != null) {

           // tree.set(node, (end - start + 1) * lazy.get(node) + tree.get(node));
            if (start != end) {
                lazy.set(leftChild(node), lazy.get(node));
                lazy.set(rightChild(node), lazy.get(node));
            }
            lazy.set(node, null);
        }
        if (start > r || end < l)
            return;
        if (start >= l && end <= r) {

            if (start != end) {
                lazy.set(leftChild(node), lazy.get(node));
                lazy.set(rightChild(node), lazy.get(node));
            }
            return;
        }
        int mid = start + (end - start) / 2;
        updatequeryutil(start, mid, node, l, r, value);
        updatequeryutil(mid + 1, end, node, l, r, value);


    }


}
