import java.awt.Stroke;
import java.util.HashMap;
import java.util.Map;
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

    public static void main(String[] args) {
        int x=  -32;

        System.out.println(x % 10);
        x = x/10;
        System.out.println(x);
        System.out.println(x % 10);
    }


}
