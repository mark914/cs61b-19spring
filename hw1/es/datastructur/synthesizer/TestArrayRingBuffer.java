package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertEquals(10, arb.capacity());
        arb.enqueue("china");
        arb.enqueue("china");
        arb.enqueue("china");
        arb.enqueue("china");
        arb.enqueue("china");
        arb.enqueue("china");
        assertEquals(6, arb.fillCount());
        assertEquals("china", arb.dequeue());
        assertEquals(5, arb.fillCount());




    }
}
