package datastructures;

public class Tree<T> {
    T val;
    Tree<T> left;
    Tree<T> right;

    Tree(T val){
        this.val = val;
    }

    public static void dfs(Tree<Integer> node){
          if(node == null)
              return;
          dfs(node.left);
          System.out.print(node.val+" ");
          dfs(node.right);
    }

    public static void main(String args[]){
            Tree<Integer> root = new Tree<>(0);
            root.left = new Tree<>(1);
            root.right = new Tree<>(7);
            root.left.left = new Tree<>(3);
            root.left.right = new Tree<>(6);
            root.left.left.left = new Tree<>(4);
            root.left.left.right = new Tree<>(5);

            root.dfs(root);
    }

}
