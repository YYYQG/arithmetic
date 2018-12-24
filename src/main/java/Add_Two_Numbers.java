import org.junit.Test;

public class Add_Two_Numbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = null;
        int tem = 0;
        int flag = 0;
        int re = l1.val + l2.val;
        ListNode res = null;
        if (re >= 10) {
            tem = 1;
            re = re - 10;
        }
        result = new ListNode(re);
        res = result;
        if (l1.next == null && l2.next == null && tem == 1) {
            result.next = new ListNode(tem);
        }
        while (l1.next != null) {

            if (l2.next == null) {
                flag = 1;
                break;
            }
            l1 = l1.next;
            l2 = l2.next;
            re = l1.val + l2.val;
            if (tem == 1) {
                re = re + 1;
                tem = 0;
            }
            if (re >= 10) {
                tem = 1;
                re = re - 10;
            }
            res.next = new ListNode(re);
            res = res.next;
        }
        if (flag == 1) {
            res.next = l1.next;
        } else if (l2.next != null) {
            res.next = l2.next;
        }
        return result;

    }


    @Test
    public void test() {

        ListNode l1 = new ListNode(5);
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(3);
        ListNode l2 = new ListNode(5);
        ListNode n3 = new ListNode(6);
        ListNode n4 = new ListNode(4);
       /* l1.next = n1;
        n1.next = n2;
        l2.next = n3;
        n3.next = n4;*/
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