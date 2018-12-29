import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Valid_Parentheses {

    public boolean isValid(String s) {


        Stack<Character> stack = new Stack<Character>();
        Map<Character,Character> map = new HashMap<Character, Character>();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                stack.push(c);
            }else {
                if(stack.empty()){
                    return false;
                }
                char b= stack.pop();
                if(c!=map.get(b)){
                    return false;
                }
            }
        }
        if(!stack.empty()){
            return false;
        }

        return true;
    }

    @Test
    public void test(){

        String s = "({}";
        System.out.println(isValid(s));

    }
}
