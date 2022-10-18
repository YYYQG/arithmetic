
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Random;


/**
 * @description
 * @date 2022/06/22 9:57
 */

public class Sort {


    public boolean less(Integer v, Integer w) {
        return v.compareTo(w) < 0;
    }

    private void exch(Integer[] a, int i, int j) {
        Integer t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void show(Integer[] a) {
        for (Integer t : a) {
            System.out.println(t);
        }
    }

    public void selectSort(Integer[] a) {
        int j;
        int min;
        for (int i = 0; i < a.length; i++) {
            min = i;
            for (j = i+1; j < a.length; j++) {
                if (less(a[j],a[min])) {
                    min = j;
                }
            }
            exch(a, i ,min);
        }
    }

    public void bubbleSort(Integer[] a) {

        for (int i = 0; i < a.length; i++) {

            for (int j = 0; j < a.length - i -1 ; j++) {

                if (less(a[j+1], a[j])) {
                    exch(a, j, j+1);
                }
            }
        }
    }

    public void insertSort(Integer[] a) {

        for (int i = 1; i < a.length ; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]) ; j--) {
                    exch(a, j-1, j);
            }
        }
    }


    public void shellSort(Integer[] a) {

        for (int h = a.length/2; h > 0; h = h/2 ) {

            for (int i = h; i < a.length; i++) {

                for (int j = i; j >= h && less(a[j], a[j-h]); j = j - h) {
                    exch(a, j-h, j);
                }
            }
        }
    }

    private Integer[] temp;

    //归并排序
    public void mergeSort(Integer[] a) {
        temp = new Integer[a.length];
        mergeSort(a, 0, a.length - 1);
    }

    public void mergeSort(Integer[] a, Integer low, Integer high) {
        if (high <= low) return;
        Integer mid = low + (high - low) / 2;
        mergeSort(a, low, mid);
        mergeSort(a, mid + 1, high);
        merge(a, low, mid, high);
    }

    public void merge(Integer[] a, Integer low, Integer mid, Integer high) {
        Integer i = low;
        Integer j = mid + 1;

        for (int k = low; k<= high; k++) {
            temp[k] = a[k];
        }

        for (int k = low; k<= high; k ++ ) {
            if (i > mid) {
                a[k] = temp[j];
                j++;
            } else if (j > high) {
                a[k] = temp[i];
                i++;
            } else if (temp[i] < temp[j]) {
                a[k] = temp[i];
                i++;
            } else if (temp[j] < temp[i]) {
                a[k] = temp[j];
                j++;
            }
        }
    }

    //快速排序

    public void quickSort(Integer[] a) {
        StdRandom.shuffle(a);


    }

    private void quickSort(Integer[] a, Integer low, Integer high) {
        if (high <= low) return;;
        int j = partition(a, low, high);
        quickSort(a, low, j-1);
        quickSort(a, j+1, high);
    }

    private Integer partition(Integer[] a, Integer low, Integer high) {

        int i = low;
        int j = high + 1;
        int v = a[low];
        while (true) {
            while (less(a[++i], v)) if (i == high) break;
            while (less(v, a[--j])) if (j == low) break;
            if (i >= j ) break;
            exch(a, i, j);
        }
        exch(a, low, j);
        return j;
    }


    public double time(String alg, Integer [] a)
    {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("InsertionSort"))    insertSort(a);
        if (alg.equals("SelectSort"))   selectSort(a);
        if (alg.equals("ShellSort"))    shellSort(a);

        return  timer.elapsedTime();
    }

    public double timeRandomInput(String alg, int N, int T)
    { // 使用算法 alg 将T个长度为N的数组排序
        double total = 0.0;
        Integer [] a = new Integer[N];
        for (int t = 0 ; t < T ; t++)
        { // 进行一次测试（生成一个数组并排序）
            for (int i = 0; i < N ; i++)
            {
                a[i] = new Random().nextInt();
            }
            total += time(alg, a);
        }
        return total;
    }


    public static void main(String[] args) {
        Integer[] a = new Integer[5];
        a[0] = 5;
        a[1] = 8;
        a[2] = 2;
        a[3] = 7;
        a[4] = 0;

        Sort integerSort = new Sort();

        //integerSort.selectSort(a);
        //integerSort.bubbleSort(a);
        //integerSort.insertSort(a);
        //integerSort.shellSort(a);
        integerSort.mergeSort(a);
        integerSort.show(a);

    }

}
/*Sort integerSort = new Sort();

        int N = 1000;   // 被排序的数组长度
        int T = 100;    // 排序次数
        double t1 = integerSort.timeRandomInput("ShellSort", N, T);
        double t2 = integerSort.timeRandomInput("SelectSort", N, T);
        double t3 = integerSort.timeRandomInput("InsertionSort", N, T);
        System.out.println("ShellSort:" + t1);
        System.out.println("SelectSort" + t2);
        System.out.println("InsertionSort" + t3);*//*




    }







}
*/
