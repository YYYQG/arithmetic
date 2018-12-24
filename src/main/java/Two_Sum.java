import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_Sum {

    public int[] twoSum(int[] nums, int target) {


        int[] result = new int[2];
        Map<Integer,Integer> num = new HashMap<Integer, Integer>();

        for (int i=0;i<nums.length;i++){

            if(num.containsKey(target-nums[i])){

                result[0]=num.get(target-nums[i]);
                result[1]=i;

            }
            num.put(nums[i],i);
        }

        return result;
    }

    @Test
    public void test() {

        int a[] = {2,7,11,15};
        int target = 4;
        int[] re = twoSum(a, 6);
        System.out.println(re[0] + " " + re[1]);
    }

}
