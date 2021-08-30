import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LinearProbingHashST<Key, Value> implements Map61B<Key, Value>{
    private static final int INIT_CAPACITY = 4;

    private int n; //number of key-value pairs
    private int m;
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        n = 0;
        m = capacity;
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
    }
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < m; i += 1) {
            if (keys[i] != null){
                temp.put(keys[i], vals[i]); //不是所有key都会被复制，因为put会对key进行限制。
            }
        }
        this.keys = temp.keys;
        this.vals = temp.vals;
        this.m = temp.m;
    }

    @Override
    public void clear() {
        keys = (Key[]) new Object[m];
        vals = (Value[]) new Object[m];
        n = 0;
    }

    @Override
    public boolean containsKey(Key key) {
        return get(key) != null;
    }

    //以hash值为起始点，比较key和key[i]，直到遇到keys[i] == null，相当于缩小范围。
    @Override
    public Value get(Key key) {
       for (int i = hash(key); keys[i] != null; i = (i+1)%m) {
           if (keys[i].equals(key)) return vals[i];
       }
        return null;
    }

    public int size() {
        return n;
    }

    private void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (!containsKey(key)) return;

        int i = hash(key);
        while(!keys[i].equals(key)) {
            i = (i + 1)%m;
        }
        keys[i] = null;
        vals[i] = null;

        i = (i + 1)%m;
        while (keys[i] != null) {
            Key keytemp = keys[i];
            Value valtemp = vals[i];
            keys[i] = null;
            vals[i] = null;
            n -= 1;
            put(keytemp, valtemp);
        }
        n = n-1;
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException();

        if (value == null) {
            delete(key);
            return;
        }

        if (n >= m/2) resize(2*m);//最好利用率要保持在0-0.5之间，这样效率会高一点。用空间换时间。


        for (int i = hash(key); keys[i] != null; i = (i+1)%m) {
            if (keys[i].equals(key)) vals[i] = value;
        keys[i] = key;
        vals[i] = value;
        n += 1;
        }
    }

    @Override
    public Set<Key> keySet() {
        Set<Key> allKeys = new HashSet<>();
        for (int i = 0; i < m; i += 1) {
            if (keys[i] != null) {
                allKeys.add(keys[i]);
            }
        }

        return allKeys;
    }

    @Override
    public Value remove(Key key) {
        return null;
    }

    @Override
    public Value remove(Key key, Value value) {
        return null;
    }


    @Override
    public Iterator<Key> iterator() {
        return keySet().iterator();
    }
}
