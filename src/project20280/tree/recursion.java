package project20280.tree;

import java.io.FilterOutputStream;

public class recursion {
    static void main() {
        System.out.println(ninetyone(87));
        System.out.println(Fibonacci(6));
        foo(2468);
    }
    public static int ninetyone(int n){
        return n>100? n-10:ninetyone(ninetyone(n+11));
    }
    public static int Fibonacci(int i){
        if(i == 2) return 1;
        if(i == 1) return 0;
        return Fibonacci(i-1) + Fibonacci(i-2);
    }
    public static int foo(int x){
        if(x/2 == 0) {
            System.out.print(x);
            return 0;
        }
        foo(x/2);
        System.out.print(x%2);
        return 0;
    }

}
