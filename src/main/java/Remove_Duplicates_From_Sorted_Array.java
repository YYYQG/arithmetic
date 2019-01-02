import org.junit.Test;

public class Remove_Duplicates_From_Sorted_Array {


    public int removeDuplicates(int[] nums) {

        int i = 0;

        for(int j=1;j<nums.length;j++){
            if(nums[i]!=nums[j]){
                nums[i+1]=nums[j];
                i++;
            }
        }
        return i+1;
    }
    @Test
    public void test(){

        int[] a = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(a));
    }
}
