package queue;

import java.util.NoSuchElementException;

/**
 * A Queue is a collection that supports first-in-first-out (FIFO) access.
 * Items enter at the back and leave from the front: the first item
 * enqueued is the first one dequeued.
 *
 * @param <T> the type of elements in this queue.
 */
public interface Queue<T> {

  /**
   * Adds an item to the back of this queue.
   *
   * @param item the item to be added to this queue.
   * @throws IllegalArgumentException if the item is null.
   */
  void enqueue(T item);

  /**
   * Removes the item at the front of this queue.
   *
   * @throws NoSuchElementException if this queue is empty.
   */
  void dequeue();

  /**
   * Returns the item at the front of this queue without removing it.
   *
   * @return the item at the front of this queue.
   * @throws NoSuchElementException if this queue is empty.
   */
  T front();

  /**
   * Returns true if this queue contains no elements.
   *
   * @return true if this queue is empty, false otherwise.
   */
  boolean isEmpty();
}
