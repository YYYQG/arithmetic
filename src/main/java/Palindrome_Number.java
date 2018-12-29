import org.junit.Test;

public class Palindrome_Number {


    public boolean isPalindrome(int x) {

        String s = String.valueOf(x);
        int front = 0, rear = s.length() - 1;
        while (front <= rear) {
            if (s.charAt(front) == s.charAt(rear)) {
                front = front + 1;
                rear = rear - 1;
            } else {
                return false;
            }
        }
        return true;

    }

    //反转一半数
    public boolean isPalindrome2(int x){

        if(x<0||(x%10==0&&x!=0)){
            return false;
        }
        int reverse = 0;
        while(reverse<x){
            reverse = reverse*10+x%10;
            x=x/10;
        }
        return x==reverse||x==reverse/10;

    }
    @Test
    public void test(){

        int x=1;
        System.out.println(isPalindrome(x));

    }
}
