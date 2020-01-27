package datastructures;

public class StackLL<T> {
    private StackLL<T> root,next;
    T val;
    StackLL(){}

    StackLL(T val){
        this.val = val;
        next = null;
    }

    public T pop(){
        if(root == null)
            return null;
        else{
            StackLL<T> temp = root;
           root =  root.next;
            return temp.val;
        }
    }

    public void push(T val){
        if(root == null)
            root = new StackLL<>(val);
        else{
            StackLL<T> temp = new StackLL<>(val);
            temp.next = root;
            root = temp;
        }
    }

    public boolean isEmpty(){
        return root == null;
    }

    public T peek(){
        if(root == null)
            return null;
        return root.val;
    }
}
