import org.junit.Test;

public class Longest_Palindomic_Substring {

    public String longestPalindrome(String s) {

        String result = s;
        int start = 0;
        int end = 0;
        int size = s.length();
        int[][] is = new int[size][size];
        for (int i =0;i<size-1;i++){
            if(s.charAt(i)==s.charAt(i+1))
                is[i][i+1]=1;
        }

        for(int len = 3;len<size-1;len++){

        }




        return null;
    }

    public boolean judge(String s) {

        int front = 0, rear = s.length() - 1;

        while (front <= rear) {
            if (s.charAt(front) == s.charAt(rear)) {
                front = front+1;
                rear = rear-1;
            }else{
                return false;
            }
        }
        return true;
    }

    @Test
    public void test(){

        String s = "a";
        System.out.println(judge(s));

    }

}
