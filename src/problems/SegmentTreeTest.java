package problems;

import datastructures.Pair;
import datastructures.SegmentTree;

import java.util.*;


public class SegmentTreeTest {

    public static Pair<Integer, Integer> find2ndMax(Pair<Integer, Integer> left, Pair<Integer, Integer> right) {
        int max;
        int max2;

        if (left.getFirst() > right.getFirst()) {
            max = left.getFirst();
            max2 = left.getSecond() > right.getFirst() ? left.getSecond() : right.getFirst();
        } else {
            max = right.getFirst();
            max2 = left.getFirst() > right.getSecond() ? left.getFirst() : right.getSecond();
        }

        return new Pair<>(max, max2);
    }

    public static void test(List<Integer> A, SegmentTree.Operator<Integer> op) {
        SegmentTree<Integer> tree = new SegmentTree<>(A, op);
        for (int s = 0; s < A.size() - 1; s++)
            for (int e = s; e < A.size(); e++) {
                int expected = A.get(s);
                for (int i = s + 1; i <= e; i++)
                    expected = op.accept(expected, A.get(i));
                int qValue = tree.query(s, e);
                if (qValue != expected) {
                    System.out.println("Failed for range: " + s + " - " + e);
                    return;
                }
            }
        System.out.println("All Pass :)");
    }

    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(9999, 8888, 7777, 66666, 55555);
        Map<String, SegmentTree.Operator<Integer>> operators = new HashMap<>();
        operators.put("sum", Integer::sum);
        operators.put("max", (x, y) -> x > y ? x : y);
        operators.put("mul", (x, y) -> (x % 20 * y % 20)%20);
        operators.put("xor", (x, y) -> x ^ y);
        for (Map.Entry<String, SegmentTree.Operator<Integer>> entry : operators.entrySet()) {
            System.out.println("Testing operator: " + entry.getKey());
            test(A, entry.getValue());
        }

        List<Pair<Integer, Integer>> A2 = new ArrayList<>();
        for (int x : A) A2.add(new Pair<>(x, Integer.MIN_VALUE));
        SegmentTree<Pair<Integer, Integer>> range2Max = new SegmentTree<>(A2, SegmentTreeTest::find2ndMax);
    }
}
