import org.junit.Test;

public class Climbing_Stairs {

    /*
    假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
    每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */
    //记忆搜索法
    public int climbStairs(int n) {

        int[] me = new int[n + 1];
        return getClimbStairs(n, me);
    }

    public int getClimbStairs(int n, int[] me) {

        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (me[n] == 0) {
            me[n] = getClimbStairs(n - 1, me) + getClimbStairs(n - 2, me);
        }
        return me[n];
    }

    //动态规划
    public int climbStairs2(int n) {

        int[] me = new int[n+1];

        me[1]=1;
        me[0]=1;
        for(int i=2;i<=n;i++){
            me[i] = me[i-1]+me[i-2];
        }
        return me[n];
    }

    //Fibonacci 公式
    public int climbStairs3(int n){
        double sqrt5=Math.sqrt(5);
        double fibn=Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
        return (int)(fibn/sqrt5);
    }

    @Test
    public void test() {

        System.out.println(climbStairs2(3));

    }
}
