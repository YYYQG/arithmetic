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

}
