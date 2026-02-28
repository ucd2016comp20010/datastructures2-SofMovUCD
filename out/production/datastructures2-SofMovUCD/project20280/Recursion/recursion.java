public class recursion{
    public static void main(String args[]){
        ninetyone(80);
    }

    public static int ninetyone(int n){
        System.out.println(n);
        return n> 100?  (n-10): ninetyone(ninetyone(n+11));
    }
}