import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    /* Returns true if this map contains a mapping for the specified key. */
    public Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int size;

        public Node(K k, V v, int s) {
            key = k;
            value = v;
            size =s;
        }

    }

    public BSTMap() {

    }


    @Override
    public void clear() {
        root = null;

    }

    /** containsKey method's helper method. */
    private boolean containsKey(Node x, K key) {
        if (x == null) {
            return false;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return true;
        } else if ( cmp < 0) {
            return containsKey(x.left, key);
        } else {
            return containsKey(x.right, key);
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        return containsKey(root, key);
    }

    /** get method's helper method */
    private V get(Node x, K key) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x.value;
        else if (cmp < 0) return get(x.left, key);
        else return get(x.right, key);
    }


    @Override
    public V get(K key) {
        return get(root, key);
    }

    /** size(Node x)
     * get x's size
     * @param x
     * @return
     */
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    @Override
    public int size() {
        return size(root);
    }

    /** put(K key, V value)'s helper method. */
    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.value = value;
        } else if (cmp < 0) {
            x.left =  put(x.left, key, value);
        } else {
            x.right = put(x.right, key, value);
        }
        x.size = 1 + size(x.right) + size(x.left);
        return x;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key can not be null");
        root = put(root, key, value);

    }

    /** printInOrder()'s helper method. get the Node whose key is the kth smallest */
    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (k < t) {
            return select(x.left, k);
        } else if (k > t) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }


    /** printInOrder()'s helper method. get the Node whose key is the kth smallest */
    public Node select(int k) {
        if (k > 0 || k >= size()) {
            throw new IllegalArgumentException();
        }
        return select(root, k);
    }


    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (int i = 0; i < size(); i += 1) {
            keys.add(select(i).key);
        }
        return null;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("null");

    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("null");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("null");
    }

    /** print the <key, value> pairs in the order of keys */
    public void printInOrder() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(select(i).key + " " + select(i).value);
        }
    }
}
