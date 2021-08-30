import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K,V> implements Map61B<K,V>{
    private static final int INITIAL_SIZE = 4;
    private static final double LOAD_FACTOR = 0.75;

    private int m;  //hash table size
    private int n;  // number of key-value pairs
    private double threshold;
    private Node[] st;

    private static class Node<K, V> {
        private K key;
        private V val;
        private Node next;



        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }



    public MyHashMap() {
        m = INITIAL_SIZE;
        st = (Node<K,V>[]) new Object[m];
        threshold = LOAD_FACTOR;
        n = 0;
    }

    public MyHashMap(int init) {
        m = init;
        st = (Node<K,V>[]) new Object[m];
        threshold = LOAD_FACTOR;
        n = 0;

    }

    public MyHashMap(int init, double lF) {
        m = init;
        st = (Node<K,V>[]) new Object[m];
        threshold = lF;
        n = 0;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m; //get from https://algs4.cs.princeton.edu/34hash/SeparateChainingLiteHashST.java.html
    }

    private void resize(int chains) {
        Node<K,V>[] temp = (Node<K,V>[]) new Object[chains];
        for(int i = 0; i < m; i += 1) {
            temp[i] = st[i];
        }
        m = chains;
        st = temp;
    }
    @Override
    public void clear() {
        st = (Node<K,V>[]) new Object[m];
        n = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        for (Node x = st[index]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return (V) x.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void put(K key, V value) {
        if (value == null) {
            delete(key);
        }
        if (n > m*threshold) {
            resize(2*m);
        }

        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = value;
            }
        }
        st[i] = new Node(key, value, st[i]);
        n += 1;
    }

    public void delete(K key) {
        throw new UnsupportedOperationException();
    }
    @Override
    public Set<K> keySet() {
        Set<K> allKeys = new HashSet<>();
        for (int i = 0; i < m; i += 1) {
            for (Node x = st[i]; x != null; x = x.next) {
                allKeys.add((K) x.key);
            }
        }
        return allKeys;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
