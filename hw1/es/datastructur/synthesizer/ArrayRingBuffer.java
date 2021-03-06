package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T>  implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    public int plusOne(int index) {
        if (index == rb.length-1) {
            return 0;
        } else {
            return index + 1;
        }
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T []) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }



    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public int capacity() {
        return rb.length;
    }

    public int fillCount() {
        return fillCount;
    }
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = plusOne(last);
        fillCount += 1;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow!");
        }
        T res = rb[first];
        rb[first] = null;
        first = plusOne(first);
        fillCount -= 1;
        return res;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (fillCount() == 0) {
            throw new RuntimeException("Ring buffer underflow!");
        }
        T res = rb[first];
        return res;

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wizPos;
        public ArrayRingBufferIterator() {
            wizPos = first;
        }

        @Override
        public boolean hasNext() {
            return wizPos == last;
        }

        @Override
        public T next() {
            T returnItem = rb[wizPos];
            wizPos = plusOne(wizPos);
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        BoundedQueue<T> obj = (BoundedQueue<T>) o;
        return false;
    }

}
    // TODO: Remove all comments that say TODO when you're done.
