import org.junit.Test;


/**
 * @description
 * @date 2022/06/10 14:53
 */
public class RedBlackTree<K ,V extends Comparable<V>> {


    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node<K,V> head;

     static final class Node<K ,V extends Comparable<V>> {
        private K key;
        private V value;
        private Node<K,V> left, right;
        private Integer n; //子树节点总数
        private boolean color;

         public Node(K key, V value, boolean color) {
             this.key = key;
             this.value = value;
             this.color = color;
         }
     }

    public boolean isRed(Node<K,V> node) {
         if (node == null) return false;
         return node.color == RED;
    }

    public Node<K,V> rotateLeft(Node<K,V> node) {
         Node<K,V> x = node.right;
         node.right = x.left;
         x.left = node;
         x.color = node.color;
         node.color = RED;
         x.n = node.n;
         node.n = 1 + size(node.left) + size(head.right);
         return x;
    }

    public Node<K,V> rotateRight(Node<K,V> node) {
        Node<K,V> x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        x.n = node.n;
        node.n = 1 + size(node.left) + size(head.right);
        return x;
    }

    public void flipColors(Node<K,V> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public Integer size(Node<K,V> node) {
         if (node == null) return 0;
         Integer count = 1;

         return count;
    }

    public void put(K key, V value) {
        head = put(head, key, value);
        head.color = BLACK;
    }

    public Node<K,V> put(Node<K,V> h, K key, V value) {
        if (h == null) {
            return new Node<>(key, value, RED);
        }
        int i = value.compareTo(h.value);
        if (i < 0) {
            h = put(h.left, key, value);
        } else if (i > 0) {
            h = put(h.right, key, value);
        } else {
            h.value = value;
        }
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h= rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.n = 1 + size(h.left) + size(head.right);
        return h;
    }

    @Test
    public void test() {

    }


}
