import org.junit.Test;


/**
 * @description
 * @date 2022/06/10 14:53
 */
public class RedBlackTree<K extends Comparable<K> ,V extends Comparable<V>> {


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

    /*private void flipColors(Node<K,V> h){
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }*/

    public Integer size(Node<K,V> node) {
         if (node == null) return 0;
         return node.n;
    }

    public void put(K key, V value) {
        head = put(head, key, value);
        head.color = BLACK;
    }

    public boolean isEmpty() {
         return head == null;
    }

    public Node<K,V> put(Node<K,V> h, K key, V value) {
        if (h == null) {
            return new Node<>(key, value, RED);
        }
        int i = key.compareTo(h.key);
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


    public void deleteMin() {
         if (!isRed(head.left) && !isRed(head.right)) {
             head.color = RED;
         }
         head = deleteMin(head);
        if (!isEmpty()) head.color = BLACK;
    }

    public Node<K,V> deleteMin(Node<K,V> h) {
         if (h.left == null)
             return null;
         if (!isRed(h.left) && !isRed(h.left.left))
             h = moveRedLeft(h);

        h.left = deleteMin(h.left);

        return balance((h));
    }

    private Node<K,V> moveRedLeft(Node<K,V> h) {
        deleteFlipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    public Node<K,V> balance(Node<K,V> h) {

        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h= rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.n = 1 + size(h.left) + size(head.right);
        return h;
    }

    public void deleteFlipColors(Node<K,V> h) {
        h.color = BLACK;
        h.left.color = RED;
        h.right.color = RED;
    }

    public void deleteMax() {
        if (!isRed(head.left) && !isRed(head.right)) {
            head.color = RED;
        }
        head = deleteMax(head);
        if (!isEmpty()) head.color = BLACK;
    }

    private Node<K,V> moveRedRight(Node<K,V> h) {
        deleteFlipColors(h);
        //算法四 有问题
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node<K,V> deleteMax(Node<K,V> h) {
         if (isRed(h.left)) {
             h = rotateRight(h);
         }
        if (h.right == null) {
            return null;
        }
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(K key) {
        if (!isRed(head.left) && !isRed(head.right))
            head.color = RED;
        head = delete(head, key);
        if (!isEmpty()) head.color = BLACK;
    }

    private Node<K,V> delete(Node<K,V> h, K key){
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && h.right == null)
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                h.value = get(h.right, min(h.right).key);
                h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }

        }
        return balance(h);
    }

    public V get(K key) {
        return get(head, key);
    }

    private V get(Node<K,V> x, K key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.value;
    }

    public K min() {
        return min(head).key;
    }

    private Node<K,V> min(Node<K,V> x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    //打印

    @Test
    public void test() {



    }


}
