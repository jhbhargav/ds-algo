package datastructures;
import java.util.*;

public class DisjointSet<T> {
        private HashMap<T, T> parent;
        private HashMap<T, Integer> size;

        public DisjointSet(){
           parent = new HashMap<>();
           size = new HashMap<>();
        }

        public T findRoot(T x) {
            if(!parent.containsKey(x))
                return addElement(x);
            if(parent.get(x).equals(x))
                return x;

            T root = findRoot(parent.get(x));
            parent.put(x, root); // path compression
            return root;
        }

        private T addElement(T x){
            parent.put(x, x);
            size.put(x, 1);
            return x;
        }

       public void union(T x, T y){
            T parentX = findRoot(x);
            T parentY = findRoot(y);
            if(parentX == parentY)
                return;
            if(size.get(parentX) < size.get(parentY)){
                parent.put(parentX, parentY);
                size.put(parentY, size.get(parentY) + size.get(parentX));
            }
            else {
                parent.put(parentY, parentX);
                size.put(parentX, size.get(parentY) + size.get(parentX));
            }
       }

       public static void main(String[] args){
            DisjointSet<Integer> dj = new DisjointSet<>();
            dj.union(1,2);
            dj.union(1,3);
            dj.union(3,4);

            System.out.println(dj.findRoot(4));
            System.out.println(dj.findRoot(3));
       }

}
