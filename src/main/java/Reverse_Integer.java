import org.junit.Test;

public class Reverse_Integer {

    public int reverse(int x) {

        int end = 0;
        int result = 0;
        String num = String.valueOf(x);
        StringBuilder stringBuilder = new StringBuilder();
        if(x<0){
            end = 1;
        }
        for (int i=num.length()-1;i>=end;i--){
            stringBuilder.append(num.charAt(i));
        }

        if(x<0&&-Long.parseLong(stringBuilder.toString())>Integer.MIN_VALUE){
            result = -Integer.parseInt(stringBuilder.toString());
        }
        if(x>0&&Long.parseLong(stringBuilder.toString())<Integer.MAX_VALUE){
            result = Integer.parseInt(stringBuilder.toString());
        }
        return result;
    }

    public int reverse2(int x) {

        int tem = Math.abs(x);
        long last = 0;
        long result = 0;
        while (tem >0){
            result = result*10+tem%10;
            tem = tem/10;
        }

        if(x<0&&-result>Integer.MIN_VALUE){
            last = -result;
        }else {
            last = 0;
        }
        if(x>0&&result<Integer.MAX_VALUE){
            last = result;
        }
        return (int)last;
    }

    public int reverse3(int x){

        int tem = x;
        int result = 0;
        while(tem !=0){
            int newRe = 0;
            newRe = result*10+tem%10;
            if((newRe-tem%10)/10!=result){
                return 0;
            }
            tem = tem/10;
            result = newRe;
        }
        return result;
    }

    public int reverse4(int x) {
        long result = 0L;

        while (x != 0) {
            int r = x % 10;
            x = x / 10;
            result = result * 10 + r;
        }

        if (result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE) {
            return 0;
        }

        return (int)result;
    }

    @Test
    public void  test (){

        System.out.println(reverse3(-120));

    }
}
