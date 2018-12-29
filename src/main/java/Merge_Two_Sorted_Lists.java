import org.junit.Test;

import java.util.List;

public class Merge_Two_Sorted_Lists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {


        ListNode result = new ListNode(0);
        ListNode real = result;
        while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    real.next = l1;
                    l1 = l1.next;
                    real = real.next;
                } else {
                    real.next = l2;
                    l2 = l2.next;
                    real = real.next;
                }
        }
        if(l1!=null){
            real.next = l1;
        }else {
            real.next = l2;
        }
        return result.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {

        if(l1==null) return l2;
        if(l2==null) return l1;
        if (l1.val<l2.val){
            l1.next = mergeTwoLists2(l1.next,l2);
            return l1;
        }else {
            l2.next = mergeTwoLists2(l1,l2.next);
            return l2;
        }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(5);
        ListNode l2 = new ListNode(5);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(8);
        l1.next = n1;
        n1.next = n2;
        l2.next = n3;
        n3.next = n4;
        ListNode re = mergeTwoLists2(l1, l2);

        System.out.println(re.val);
        while (re != null) {
            System.out.println(re.val + "---");
            re = re.next;
        }
    }


}

