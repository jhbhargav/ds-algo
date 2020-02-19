package problems;
import java.util.*;

public class FallingSquares {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a[][] = new int[n][2];
        for(int i = 0; i < n; i++){
            a[i][0] = sc.nextInt();
            a[i][1] = sc.nextInt();
        }
        int res[] = solve(a);
        for(int i = 0; i < res.length; i++)
            System.out.print(res[i]+" ");

        System.out.println();
    }
    public static int[] solve(int a[][]){
        int maxnodes = Integer.MIN_VALUE;
        int n = a.length;

        for(int i = 0; i < n; i++){
            if(maxnodes < a[i][0] + a[i][1])
                maxnodes = a[i][0] + a[i][1];
        }
        int tree[] = new int[4*maxnodes + 1];
        int res[] = new int[n];

        for(int i = 0; i < n; i++){

        }
        return res;
    }

}
