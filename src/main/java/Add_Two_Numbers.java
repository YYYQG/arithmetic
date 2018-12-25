import org.junit.Test;

public class Add_Two_Numbers {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode pre = result;
        int tem = 0;
        if(l1==null){
            return l2;
        }else if(l2==null){
            return l1;
        }
        while (l1 != null || l2 != null || tem != 0) {//循环条件

            int sum = 0;
            sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + tem;//l1 或 l2为空则值为0；
            sum = sum % 10;
            tem = sum / 10;
            pre.next = new ListNode(sum);
            pre = pre.next;
            l1=l1!=null?l1.next:l1;
            l2=l2!=null?l2.next:l2;
        }
        return result.next;
    }

    @Test
    public void test() {

        ListNode l1 = new ListNode(2);
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(3);
        ListNode l2 = new ListNode(5);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(4);
        l1.next = n1;
        n1.next = n2;
        l2.next = n3;
        n3.next = n4;
        ListNode re = addTwoNumbers(l1, l2);

        System.out.println(re.val);
        while (re.next != null) {
            re = re.next;
            System.out.println(re.val + "---");
        }
    }

}

class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }

}