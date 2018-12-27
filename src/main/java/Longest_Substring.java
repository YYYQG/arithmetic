import org.junit.Test;

public class Longest_Substring {


    //动态规划
    public String longestSubstring(String s1, String s2) {

        int result = 0;
        int end = 0;
        int len1 = s1.length();
        int len2 = s2.length();
        int[][] is = new int[len1+1][len2+1];

        for (int i = 1; i <=len1; i++) {
            for (int j = 1; j <=len2; j++) {
                if (s1.charAt(i-1)==s2.charAt(j-1)) {
                    is[i][j] = is[i-1][j-1]+1;
                    end = i;
                    result = Math.max(result,is[i][j]);
                }
            }
        }
        for(int i=0;i< len1+1;i++){
            for(int j=0;j<len2+1;j++){
                System.out.print(is[i][j]);
            }
            System.out.println();
        }
        return s1.substring(end-result,end);
    }

    @Test
    public void test(){

        String s1 = "swasf";
        String s2 = "dsdwf";
        System.out.println(longestSubstring(s1,s2));

    }

}
