import org.junit.Test;

public class Longest_Common_Prefix {

    public String longestCommonPrefix(String[] strs) {

        if (strs.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder("");
        int min = Integer.MAX_VALUE;
        for (String s : strs) {
            min = Math.min(min, s.length());
        }

        int flag = 0;
        for (int i = 0; i < min; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {

                if (strs[j].charAt(i) == c) {
                    flag = flag + 1;
                }
            }
            if (flag == strs.length - 1) {
                builder.append(c);
                flag = 0;
            } else {
                break;
            }
        }


        return builder.toString();
    }

    //分治
    public String longestCommonPrefix2(String[] strs) {

        if(strs.length==0)return "";
        return longestCommonPrefix2(strs, 0, strs.length-1);
    }

    private String longestCommonPrefix2(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        int mid = (r + l) / 2;
        String left = longestCommonPrefix2(strs, l, mid);
        String right = longestCommonPrefix2(strs, mid + 1, r);
        return commonPrefix(left,right);
    }

    private String commonPrefix(String left, String right) {

        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            if(left.charAt(i)!=right.charAt(i))
                return left.substring(0,i);
        }
        return left.substring(0,min);
    }

    @Test
    public void test() {

        String[] s = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix2(s));

    }
}
