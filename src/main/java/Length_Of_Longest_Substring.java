import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Length_Of_Longest_Substring {


    public int lengthOfLongestSubstring(String s) {

        int p = 0;
        int q = 1;
        int max = 0;
        if (s.length() == 0) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }
        while (q <= s.length() - 1) {

            int contain = s.substring(p, q).indexOf(s.charAt(q) + "");
            if (contain != -1) {
                p = p + contain + 1;
            }
            if (max < q - p + 1) {
                max = q - p + 1;
            }
            q = q + 1;
        }
        return max;
    }

    //滑动窗口HashMap
    public int lengthOfLongestSubstring2(String s) {

        int n = s.length();
        int max = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();

        for (int j = 0, i = 0; j < n; j++) {

            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            max = Math.max(max, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return max;

    }

    //假设字符集合为ASCII 128
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    @Test
    public void test() {

        String s = "\\n 2@@你好";
        System.out.println(lengthOfLongestSubstring3(s));

    }

}
