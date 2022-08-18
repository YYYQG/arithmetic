import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @date 2022/08/18 14:37
 */
public class MaxEqualFreq {

    public int maxEqualFreq(int[] nums) {

        int max = 0;
        int maxFreq = 0;
        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            Integer count = countMap.get(nums[i]);
            if (count == null) {
                count = 0;
            }
            countMap.put(nums[i], count + 1);

            maxFreq = Math.max(maxFreq, count + 1);

            Integer freq = freqMap.get(count);
            if (freq != null) {
                freqMap.put(count, freq - 1);
            }
            Integer freq1 = freqMap.get(count + 1);
            if (freq1 == null) {
                freq1 = 0;
            }
            freqMap.put(count +1 , freq1 + 1);

            if (maxFreq == 1
                    || freqMap.get(maxFreq) * maxFreq + freqMap.get(maxFreq-1) * (maxFreq-1) == i+1 && freqMap.get(maxFreq) == 1
                    || freqMap.get(1) == 1 && freqMap.get(maxFreq) * maxFreq == i) {
                max = Math.max(max, i + 1);
            }


        }
        return max;

    }


}
