import org.junit.Test;

public class Longest_Palindomic_Substring {

    //动态规划
    public String longestPalindrome(String s) {

        if (s.equals("")) {
            return "";
        }
        String result = s;
        int start = 0;
        int end = 0;
        int size = s.length();
        int[][] is = new int[size][size];
        for (int i = 0; i < size - 1; i++) {
            is[i][i] = 1;
            if (s.charAt(i) == s.charAt(i + 1)) {
                is[i][i + 1] = 1;
                start = i;
                end = i + 1;
            }
        }

        for (int len = 3; len <= size; len++) {
            for (int i = 0; i <= size - len; i++) {

                int j = i + len - 1;
                if (is[i + 1][j - 1] == 1 && s.charAt(i) == s.charAt(j)) {
                    is[i][j] = 1;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end + 1);
    }

    //中心扩展算法
    public String longestPalindrome2(String s) {


        int length = s.length();
        if (length == 0) {
            return "";
        }
        for (int i = 0; i <= 2 * length-1; i++) {

            int start = 0, end = 0;



        }

        return "";
    }

    public boolean judge(String s) {

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

    @Test
    public void test() {

        String s = "ccc";
        System.out.println(longestPalindrome(s));

    }

}
