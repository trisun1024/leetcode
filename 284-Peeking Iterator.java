
import java.util.*;
import java.util.NoSuchElementException;

// Method 1: save the peek value;
// Method 2: save the next value;
class PeekingIterator implements Iterator<Integer> {

    private Iterator<Integer> iter;
    private Integer peeked = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iter = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (peeked == null) {
            if (!iter.hasNext()) {
                throw new NoSuchElementException();
            }
            peeked = iter.next();
        }
        return peeked;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (peeked != null) {
            Integer toReturn = peeked;
            peeked = null;
            return toReturn;
        }
        if (!iter.hasNext()) {
            throw new NoSuchElementException();
        }
        return iter.next();
    }

    @Override
    public boolean hasNext() {
        return peeked != null || iter.hasNext();
    }
}