package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;


public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {



    private class PriorityNode {
        private T item;
        private double priority;

        PriorityNode(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }
    }
    private ArrayList<PriorityNode> pq; //the list of items
    private HashMap<T, Integer> pqIndex;//the index of items
    private int n; // number of items

    public ArrayHeapMinPQ() {
        pq = new ArrayList<>(5);
        pqIndex = new HashMap<>();
        pq.add(null);
        n = 0;
    }


    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new IllegalArgumentException();

        if (n+1 == pq.toArray().length) resize(2*n);
        pq.add(n, new PriorityNode(item, priority));
        pqIndex.put(item, n);
        swim(n++);
    }

    @Override
    public boolean contains(T item) {
        if (isEmpty()) {
            return false;
        }
        return pqIndex.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return pq.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (isEmpty()) throw new NoSuchElementException();
        T smallestItem = pq.get(0).item;
        exch(0, n-1);
        sink(0);
        pq.set(n-1, null);
        n = n-1;
        pqIndex.remove(smallestItem);
        if ((n > 0) && n/pq.toArray().length < 0.25) resize(pq.toArray().length);
        return smallestItem;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void changePriority(T item, double priority) {
        int index = pqIndex.get(item);
        double oldPriority = pq.get(index).priority;
        pq.get(index).setPriority(priority);
        if (oldPriority > priority) swim(index);
        else sink(index);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void swim(int k) {
        while(k > 0 && greater((k-1)/2, k)){
            exch((k-1)/2, k);
            k = (k-1)/2;
        }
    }

    private void sink(int k) {
        while (2*k+1 < n) {
            int i = 2*k+1;
            if (i+1 < n && greater(i, i+1) ) i = i + 1;
            exch(k, i);
            k = i;
        }

    }

    private void exch(int i, int j) {
        PriorityNode tempA = pq.get(i);
        pq.set(i,pq.get(j));
        pq.set(j, tempA);
        pqIndex.put(pq.get(i).item, i);
        pqIndex.put(pq.get(j).item, j);
    }

    private boolean greater(int i, int j) {
        double priA = pq.get(i).priority;
        double priB = pq.get(j).priority;
        return priA > priB;
    }

    private void resize(int size) {
        ArrayList<PriorityNode> temp = new ArrayList<>(size);
        for (int i = 0; i < n; i ++) {
            temp.add(i, pq.get(i));
        }
        pq = temp;
    }
}
