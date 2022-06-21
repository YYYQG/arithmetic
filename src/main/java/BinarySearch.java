/**
 * @description
 * @date 2022/06/21 14:35
 */
public class BinarySearch {


    public Integer binarySearch(Integer[] a, Integer n, Integer x) {

        Integer low = 0;
        Integer high = n - 1;
        while (low <= high) {
            Integer mid = (low + high) / 2;
            if (x < a[mid]) {
                high = mid -1;
            }
            if (x > a[mid]) {
                low = mid + 1;
            } else
            {
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {

    }
    //130  归并排序 快排  nlogN
}
