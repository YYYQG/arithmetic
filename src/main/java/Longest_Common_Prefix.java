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

    @Test
    public void test() {

        String[] s = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(s));

    }
}
