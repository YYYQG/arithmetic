import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        int start = 0;
        int end = 0;
        for (int i = 0; i < length; i++) {
            int len1 = expre(s, i, i);
            int len2 = expre(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    public int expre(String s, int left, int right) {

        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;

    }

    //manacher算法O(n)
    /*
        1.将原字符串S的每个字符串都插入一个永远不会在S中出现的字符（本例中用#表示），在S的首尾也插入该字符，使得
          新的字符串s1长度为s.length()+1，保证len的长度为奇数;
          s: abb
          s1: #a#b#b#
        2.根据s1求出以每一个字符串为中心的最长回文子串的最右端字符距离该字符的距离，存入len数组中，即
          s1[i]-s1[r]为s1[i]的最长回文子串的右段（s1[2i-r]—s1[r]为以s1[i]为中心的最长回文子串）；Len[i] = r - i + 1;

          s1:   #a#b#b#
          len : 1212321
          len数组性质：len[i]-1即为以len[i]为中心的最长回文子串在s中的长度。在s1中，以s1[i]为中心的
          最长回文子串长度为2len[i]-1，由于在s1中是在每个字符两侧都有新字符#,观察可知#的数量一定是比
          原字符串多1的，即有len[i]个，因此真实的回文子串为len[i]-1，最长回文子串长度为math.max（len）-1.
        3.len数组求解
            a.遍历遍历s1数组，i为当前遍历到的位置，即求解以s1[i]为中心的最长回文子串的len[i]，
            b.设置两个参数：sub_midd表示在i之前所得到的len数组中的最大值所在位置，
            sub_side=sub_midd+len[sub_midd]-1表示以sub_midd为中心的最长回文子串的最右端的s1中
            的位置。起始都为哦，从s1的第一个字母开始计算。
            c.当i<sub_side时，取i关于sub_midd的对称点j（j = 2sub_midd - i，由于i <= sub_side，因此2sub_midd - sub_side <= j <= sub_midd);
            当Len[j] < sub_side - i时，即以s1[j]为中心的最长回文子串是在以s1[sub_midd]为中心的最长回文子串的内部，再由于i、j关于sub_midd对称，可知Len[i] = Len[j];
            当len[j]>=sub_side-1时说明以s1[i]为中心的回文串可能延伸到sub_side之外，而大于sub_side的
            部分还没有进行匹配，所以要从sub_side+1位置开始进行匹配，直到匹配失败以后，从而更新sub_side和对应的sub_midd以及Len[i]；
            d.当i>sub_side时，则说明以s[i]为中心的最长回文子串还没有开始匹配寻找，因此需要一个一个
            进行匹配寻找，结束后更新sub_side和对应的sub_midd以及Len[i]。

     */
    public String manacher(String s) {

        List<Character> s1 = new ArrayList<Character>();

        for (int i = 0; i < s.length(); i++) {
            s1.add('#');
            s1.add(s.charAt(i));
        }
        s1.add('#');
        List<Integer> len = new ArrayList<Integer>();
        String sub = "";
        int sub_midd = 0;
        int sub_side = 0;
        for (int i = 0; i < s1.size(); i++) {

            if (i < sub_side) {
                int j = 2 * sub_midd - i;
                if (len.get(j) <= sub_side - i) {
                    len.add(len.get(j));
                } else {
                    len.add(sub_side - i + 1);
                }
            } else {
                len.add(1);
                System.out.println((i - len.get(i) >= 0) && (i + len.get(i) < s1.size()) && (s1.get(i - len.get(i)) == s1.get(i + len.get(i))));
                while ((i - len.get(i) >= 0) && (i + len.get(i) < s1.size()) && (s1.get(i - len.get(i)) == s1.get(i + len.get(i)))) {
                    len.set(i,len.get(i)+1);
                }
                if(len.get(i)>=len.get(sub_midd)){
                    sub_side = len.get(i)+i-1;
                    sub_midd = i;
                }
            }
        }


        return s.substring((2*sub_midd-sub_side)/2,sub_side/2);
    }

    @Test
    public void test() {

        String s = "cssdsc";
        //System.out.println(longestPalindrome2(s));
        System.out.println(manacher(s));
    }

}
