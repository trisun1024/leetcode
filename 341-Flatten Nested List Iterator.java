import java.util.*;
import extensions.*;

class FlattenNestedListIterator {

    // One Stack. Time = O(N + L); Space = O(N + L);
    public class NestedIterator implements Iterator<Integer> {

        private Deque<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.stack = new ArrayDeque<>(nestedList);
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                return null;
            }
            return stack.pollFirst().getInteger();
        }

        @Override
        public boolean hasNext() {
            makeStackTopAnInteger();
            return !stack.isEmpty();
        }

        private void makeStackTopAnInteger() {
            while (!stack.isEmpty() && !stack.peekFirst().isInteger()) {
                List<NestedInteger> list = stack.pollFirst().getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.offerFirst(list.get(i));
                }
            }
        }
    }

    // Two Stack. Time = O(1);
    public class NestedIteratorI implements Iterator<Integer> {

        private Deque<List<NestedInteger>> listStack = new ArrayDeque<>();
        private Deque<Integer> indexStack = new ArrayDeque<>();

        public NestedIteratorI(List<NestedInteger> nestedList) {
            listStack.addFirst(nestedList);
            indexStack.addFirst(0);
        }

        @Override
        public Integer next() {
            if (!hasNext())
                throw new NoSuchElementException();
            int currentPosition = indexStack.removeFirst();
            indexStack.addFirst(currentPosition + 1);
            return listStack.peekFirst().get(currentPosition).getInteger();
        }

        @Override
        public boolean hasNext() {
            makeStackTopAnInteger();
            return !indexStack.isEmpty();
        }

        private void makeStackTopAnInteger() {

            while (!indexStack.isEmpty()) {

                // If the top list is used up, pop it and its index.
                if (indexStack.peekFirst() >= listStack.peekFirst().size()) {
                    indexStack.removeFirst();
                    listStack.removeFirst();
                    continue;
                }

                // Otherwise, if it's already an integer, we don't need to do anything.
                if (listStack.peekFirst().get(indexStack.peekFirst()).isInteger()) {
                    break;
                }

                // Otherwise, it must be a list. We need to update the previous index
                // and then add the new list with an index of 0.
                listStack.addFirst(listStack.peekFirst().get(indexStack.peekFirst()).getList());
                indexStack.addFirst(indexStack.removeFirst() + 1);
                indexStack.addFirst(0);
            }
        }
    }

    // Stack of Iterator
    public class NestedIteratorII implements Iterator<Integer> {

        // This time, our stack will hold list iterators instead of just lists.
        private Deque<ListIterator<NestedInteger>> stackOfIterators = new ArrayDeque<>();
        private Integer peeked = null;

        public NestedIteratorII(List<NestedInteger> nestedList) {
            // Make an iterator with the input and put it on the stack.
            // Note that creating a list iterator is an O(1) operation.
            stackOfIterators.addFirst(nestedList.listIterator());
        }

        private void setPeeked() {

            // If peeked is already set, there's nothing to do.
            if (peeked != null)
                return;

            while (!stackOfIterators.isEmpty()) {

                // If the iterator at the top of the stack doesn't have a next,
                // remove that iterator and continue on.
                if (!stackOfIterators.peekFirst().hasNext()) {
                    stackOfIterators.removeFirst();
                    continue;
                }

                // Otherwise, we need to check whether that next is a list or
                // an integer.
                NestedInteger next = stackOfIterators.peekFirst().next();

                // If it's an integer, set peeked to it and return as we're done.
                if (next.isInteger()) {
                    peeked = next.getInteger();
                    return;
                }

                // Otherwise, it's a list. Create a new iterator with it, and put
                // the new iterator on the top of the stack.
                stackOfIterators.addFirst(next.getList().listIterator());
            }
        }

        @Override
        public Integer next() {

            // As per Java specs, throw an exception if there are no further elements.
            if (!hasNext())
                throw new NoSuchElementException();

            // hasNext() called setPeeked(), which ensures peeked has the next integer
            // in it. We need to clear the peeked field so that the element is returned
            // again.
            Integer result = peeked;
            peeked = null;
            return result;
        }

        @Override
        public boolean hasNext() {

            // Try to set the peeked field. If any integers are remaining, it will
            // contain the next one to be returned after this call.
            setPeeked();

            // There are elements remaining iff peeked contains a value.
            return peeked != null;
        }
    }
}
