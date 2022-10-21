import java.awt.Stroke;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Zig_Zag_Conversion {

    //按行排序
    public String convert(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        int cur = 0;
        int going = -1;
        for (char a : s.toCharArray()) {
            list.get(cur).append(a);
            if (cur == 0 || cur == numRows - 1) {
                going = -going;
            }
            cur = cur + going;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            builder.append(list.get(i).toString());
        }
        return builder.toString();
    }

    public String convert2(String s, int numRows) {


        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    @Test
    public void test() {

        String s = "PAYPALISHIRING";
        System.out.println(convert2(s, 4));

    }

    public String longestPalindrome(String s) {

        int length = s.length();
        if (length < 2 ) {
            return s;
        }

        boolean[][] dp = new boolean[length][length];

        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }

        int maxLen = 0;
        int startIndex = 0;

        for (int l = 2; l < length; l++) {

            for (int i = 0; i < length; i++) {

                int j = i + l -1;
                if (j >= length) {
                    continue;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (dp[i+1][j-1]) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                }

                if (dp[i][j] && j - i +1 > maxLen) {
                    maxLen = j - i +1;
                    startIndex = i;
                }
            }
        }

        return s.substring(startIndex, maxLen);

    }

    class MyCircularDeque {

        private int front;

        private int trail;

        private int capacity;

        private int[] values;

        public MyCircularDeque(int k) {
            front = 0;
            trail = 0;
            capacity = k + 1;
            values = new int[k + 1];
        }

        public boolean insertFront(int value) {
            if (isFull()) {
                return false;
            }
            front = (front -1 + capacity) % capacity;
            values[front] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) {
                return false;
            }
            values[trail] = value;
            trail = (trail + 1) % capacity;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) {
                return false;
            }
            front = (front + 1) % capacity;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) {
                return false;
            }
            trail = (trail - 1 + capacity) % capacity;
            return true;
        }

        public int getFront() {
            if (isEmpty()) {
                return -1;
            }
            return values[front];
        }

        public int getRear() {
            if (isEmpty()) {
                return -1;
            }
            return values[(trail - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return front == trail;
        }

        public boolean isFull() {
            return (trail + 1) % capacity == front;
        }
    }

    public int reverse(int x) {

        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = -x;
        }

        String v = String.valueOf(x);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < v.length(); i++) {
            builder.append(v.charAt(v.length() - 1 - i));
        }

        try {
            return Integer.parseInt(builder.toString()) * sign;
        } catch (NumberFormatException e) {
            return 0;
        }
    }


    public int myAtoi(String s) {

        int result = 0;
        int operator = 1;
        int x = 1;
        for (int i = 0; i < s.length(); i++) {

            if (i==0) {
                if (s.charAt(i) == '+') {
                    continue;
                } else if (s.charAt(i) == '-') {
                    operator = -1;
                    continue;
                }
            }

            if (Character.isDigit(s.charAt(i)) && Integer.MAX_VALUE - result > s.charAt(i) - '0'){
                result = result * x + s.charAt(i) - '0';
                x = x * 10;
            } else {
                break;
            }
        }

        return result * operator;
    }


    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int maxEndTime = Arrays.stream(endTime).max().getAsInt();
        if (queryTime > maxEndTime) {
            return 0;
        }
        int[] cnt = new int[maxEndTime + 2];
        for (int i = 0; i < n; i++) {
            cnt[startTime[i]]++;
            cnt[endTime[i] + 1]--;
        }
        int ans = 0;
        for (int i = 0; i <= queryTime; i++) {
            ans += cnt[i];
        }
        return ans;
    }

    public List<List<String>> printTree(TreeNode root) {
        int deep = deep(root, 0);
        int m = deep + 1;
        int n = (1 << (deep + 1)) - 1;
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> s = new ArrayList<>();
            for (int i1 = 0; i1 < n; i1++) {
                s.add("");
            }
            res.add(s);
        }


        return res;
    }

    public void des(List<List<String>> res,TreeNode root , int r, int c, int deep) {
        if (root != null) {
            res.get(r).add(c, String.valueOf(root.val));
        }
        if (root.left != null) {
            des(res, root.left, r+1, c- (1<<deep-r-1), deep);
        }
        if (root.right != null) {
            des(res, root.right, r+1, c+ (1<<deep-r-1), deep);
        }
    }

    public int deep(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        deep++;
        int left = deep(root.left, deep);
        int right = deep(root.right, deep);
        return Math.max(left, right);
    }


    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int right = binarySearch(arr, k);
        int left = right - 1;

        while (k-- > 0) {
            if (left < 0) {
                right ++;
            } else if (right >= arr.length) {
                left --;
            } else if (x - arr[left] <= arr[right] - x) {
                left --;
            } else {
                right ++;
            }
        }

        List<Integer> a = new ArrayList<>();

        for (int i = left + 1 ;i < right ;i++) {
            a.add(arr[i]);
        }
        return a;
    }

    public int binarySearch(int[] arr, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;

            if (x <= arr[mid]) {
                right = mid;
            } else if (x > arr[mid]) {
                left = mid + 1;
            }
        }
        return  left;
    }

    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val > root.val) {
            TreeNode node = new TreeNode(val);
            node.left = root;
            return root;
        }

        TreeNode x = root;

        while (x != null) {
            if (x.right == null) {
                x.right = new TreeNode(val);
                break;
            }
            if (x.right.val < val) {
                TreeNode node = new TreeNode(val);
                node.left = x.right;
                x.right = node;
                break;
            }
            x = x.right;
        }

        return root;
    }
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000

    public String intToRoman(int num) {

        Map<Integer,String> map = new HashMap<>();
        map.put(1, "I");
        map.put(5, "V");
        map.put(10, "X");
        map.put(50, "L");
        map.put(100, "C");
        map.put(500, "D");
        map.put(1000, "M");

        StringBuilder result = new StringBuilder();

        int re = num/1000;
        num = num%1000;
        if (re != 0) {
            for (int i = 0; i < re; i++) {
                result.append("M");
            }
        }

        re = num/100;
        num = num%100;
        if (re != 0) {
            if (re == 5) {
                result.append("D");
            } else if (re == 4) {
                result.append("CD");
            } else if (re == 9) {
                result.append("CM");
            } else {
                for (int i = 0; i < re; i++) {
                    result.append("C");
                }
            }
        }

        re = num/10;
        num = num%10;
        if (re != 0) {
            if (re == 5) {
                result.append("L");
            } else if (re == 4) {
                result.append("XL");
            } else if (re == 9) {
                result.append("XC");
            } else {
                for (int i = 0; i < re; i++) {
                    result.append("x");
                }
            }
        }

        if (num != 0) {
            if (re == 5) {
                result.append("V");
            } else if (re == 4) {
                result.append("IV");
            } else if (re == 9) {
                result.append("IX");
            } else {
                for (int i = 0; i < re; i++) {
                    result.append("I");
                }
            }
        }
        return  result.toString();
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {

        Stack<Integer> stack = new Stack<>();

        int j = 0;

        for (int i = 0; i < pushed.length; i++) {

            stack.push(pushed[i]);

            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }

        }

        if (j == popped.length) {
            return true;
        } else {
            return false;
        }

    }

    //652


    public boolean canFormArray(int[] arr, int[][] pieces) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < pieces.length; i++) {
            int[] a = pieces[i];
            if (a != null) {
                map.put(a[0], i);
            }
        }
        for (int i = 0; i < arr.length; ) {
            int a = arr[i];
            Integer idx = map.get(a);
            if (idx == null) {
                return false;
            }
            int[] b = pieces[idx];
            for (int j = 0; j < b.length; j++) {
                if (b[j] == arr[i]) {
                    i++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public int numComponents(ListNode head, int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, num);
        }

        int num = 0;
        boolean connect = false;
        while (head != null) {

            int val = head.val;
            if (map.containsKey(val)) {
                connect = true;
            } else if (connect) {
                num++;
                connect = false;
            }
            head = head.next;
        }
        return num;
    }



    public static int distinctSubseqII(String s) {
        final int MOD = 1000000007;
        int[] last = new int[26];
        Arrays.fill(last, -1);

        int n = s.length();
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 26; ++j) {
                if (last[j] != -1) {
                    f[i] = (f[i] + f[last[j]]) % MOD;
                }
            }
            last[s.charAt(i) - 'a'] = i;
        }

        int ans = 0;
        for (int i = 0; i < 26; ++i) {
            if (last[i] != -1) {
                ans = (ans + f[last[i]]) % MOD;
            }
        }
        return ans;
    }

    public static int totalFruit(int[] fruits) {
        int type = -1;
        int type2 = -1;
        int max = 0;
        if (fruits.length == 1) {
            return 1;
        }
        for (int i = 0; i < fruits.length; i++) {
            type = fruits[i];
            int res = 1;
            type2 = -1;
            for (int j = i+1; j < fruits.length; j++) {

                if (fruits[j] == type || fruits[j] == type2) {
                    res = res +1;
                } else if (type2 == -1){
                    type2 = fruits[j];
                    res = res +1;
                } else {
                    break;
                }
                max = Math.max(res, max);
            }

        }
        return max;
    }


  //  第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。 4  0110 1001  5 01101001 10010110

    public int kthGrammar(int n, int k) {
        String x = "0";
        String temp = "";

        for (int i = 1; i < n; i++) {
            char[] chars = x.toCharArray();
            for (int i1 = 0; i1 < chars.length; i1++) {
                if (chars[i1] == '0') {
                    temp = temp + "01";
                }
                if (chars[i1] == '1') {
                    temp = temp + "10";
                }
                if (temp.length() >= k) {
                    break;
                }
            }
            if (temp.length() >= k) {
                x = temp;
                break;
            }
            x = temp;
            temp = "";
        }

        return x.charAt(k - 1);
    }

    public static void main(String[] args) {
        int x[] = {0,1,2,2};
        totalFruit(x);
    }


}

class StockSpanner {

    Deque<Map<String, Integer>> deque = new LinkedList<>();

    public StockSpanner() {

    }

    public int next(int price) {
        int count = 1;
        while (!deque.isEmpty() && deque.getFirst().get("price") <= price) {
            count = count + deque.getFirst().get("count");
            deque.removeFirst();
        }
        Map<String, Integer> a = new HashMap<>();
        a.put("price", price);
        a.put("count", count);
        deque.addFirst(a);
        return count;
    }
}

