import org.junit.Test;

public class Remove_Element {

    public int removeElement(int[] nums, int val) {


        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if(nums[j]!=val){
                nums[i]=nums[j];
                i++;
            }
        }
        return i;
    }

    //双指针--当要删除的元素很少时
    public int removeElement2(int[] nums,int val){

        int i = 0;
        int n = nums.length;
        while (i<n){

            if(nums[i]==val){
                nums[i] = nums[n-1];
                n=n-1;
            }else {
                i++;
            }

        }

        return i;
    }
    @Test
    public void test(){

        int[] a = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement2(a,2));
    }

}
