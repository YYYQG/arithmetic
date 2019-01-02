import org.junit.Test;

public class Maximum_Subarray {


    public int maxSubArray2(int[] nums) {

        int res = nums[0];
        int sum = 0;
        for (int a : nums) {
            if (sum > 0)
                sum = sum + a;
            else
                sum = a;
            res = Math.max(res, sum);
        }
        return res;

    }

    public int maxSubArray(int[] nums) {

        int result = nums[0];
        int[] maxValue = new int[nums.length];
        maxValue[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {

            maxValue[i] = (maxValue[i - 1] + nums[i]) > nums[i] ? maxValue[i - 1] + nums[i] : nums[i];
            if (maxValue[i] > result) {
                result = maxValue[i];
            }
        }
        return result;
    }

    @Test
    public void test() {

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

}
