package stack;

import java.util.NoSuchElementException;

/**
 * An array-backed implementation of the Stack ADT. The top of the stack is at
 * the end of the array (index size - 1), so push and pop touch only the last
 * slot and never shift elements. The backing array doubles its capacity when
 * full, making push amortized O(1).
 *
 * @param <T> the type of elements in this stack.
 */
public class ArrayStack<T> implements Stack<T> {

  private T[] arr;   // the backing array; the top is at index size - 1
  private int size;  // how many items are on the stack

  // arr is private and only ever holds T (added via push), so casting the
  // Object[] to T[] is safe even though the compiler cannot verify it; there
  // is no way to create an array of a type parameter directly.
  @SuppressWarnings("unchecked")
  public ArrayStack() {
    arr = (T[]) new Object[10];  // start with room for 10 items
    size = 0;                    // the stack starts empty
  }

  @Override
  public void push(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    if (size == arr.length) {
      grow();  // out of room: make the backing array bigger first
    }
    arr[size] = item;  // place the item in the next free slot
    size++;            // the new item is now the top
  }

  @Override
  public void pop() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    size--;
    arr[size] = null;  // clear the slot so the object can be garbage collected
  }

  @Override
  public T top() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return arr[size - 1];  // the top is at the end of the array
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  private void grow() {
    // Same cast rationale as the constructor: a fresh Object[] viewed as a T[].
    @SuppressWarnings("unchecked")
    T[] bigger = (T[]) new Object[arr.length * 2];
    for (int i = 0; i < size; i++) {
      bigger[i] = arr[i];
    }
    arr = bigger;
  }
}
