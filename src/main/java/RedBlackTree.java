import org.junit.Test;

/**
 * @description
 * @date 2022/06/10 14:53
 */
public class RedBlackTree {


    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node head;

     static class Node {
        private Integer key;
        private Integer value;
        private Node left, right;
        private Integer n; //子树节点总数
        private boolean color;
    }

    public boolean isRed(Node node) {
         if (node == null) return false;
         return node.color == RED;
    }

    public Node rotateLeft(Node node) {
         Node x = node.right;
         node.right = x.left;
         x.left = node;
         x.color = node.color;
         node.color = RED;
         x.n = node.n;
         node.n = 1 + size(node.left) + size(head.right);
         return x;
    }

    public Node rotateRight(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.n = node.n;
        node.n = 1 + size(node.left) + size(head.right);
        return x;
    }

    public Integer size(Node node) {
         if (node == null) return 0;
         Integer count = 1;

         return count;
    }


    @Test
    public void test() {

    }


}
