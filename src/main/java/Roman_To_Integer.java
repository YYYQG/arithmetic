import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Roman_To_Integer {
    public int romanToInt(String s) {

        int result = 0;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        for (int i = 0; i < s.length(); i++) {
            int x = map.get(s.charAt(i));
            if (i+1<s.length()&&x < map.get(s.charAt(i + 1))) {
                result = result - x;
            } else {
                result = result + x;
            }
        }
        return result;
    }

    @Test
    public void test() {

        String s = "III";
        System.out.println(romanToInt(s));

    }
}
