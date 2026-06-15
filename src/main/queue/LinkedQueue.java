package queue;

import java.util.NoSuchElementException;

/**
 * A node-backed implementation of the Queue ADT. The front of the queue is
 * the head of a singly linked list and the back is its tail, so front,
 * dequeue, and enqueue all run in O(1) worst-case time. There is no count
 * and no capacity: an empty queue is when both references are null.
 *
 * @param <T> the type of elements in this queue.
 */
public class LinkedQueue<T> implements Queue<T> {

  private Node<T> head;  // the front of the queue, or null when empty
  private Node<T> tail;  // the back of the queue, or null when empty

  private static class Node<T> {
    T value;
    Node<T> next;

    Node(T value) {
      this.value = value;
    }
  }

  public LinkedQueue() {
    head = null;  // the queue starts empty
    tail = null;
  }

  @Override
  public void enqueue(T item) {
    if (item == null) {
      throw new IllegalArgumentException();
    }
    Node<T> newNode = new Node<>(item);
    if (isEmpty()) {
      head = newNode;       // the queue was empty; the new node is the front
    } else {
      tail.next = newNode;  // link the new node after the old back
    }
    tail = newNode;         // either way, the new node is the new back
  }

  @Override
  public void dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    head = head.next;   // unlink the old front; the next node is the new front
    if (head == null) {
      tail = null;      // the queue is now empty; tail must agree
    }
  }

  @Override
  public T front() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }
    return head.value;  // the front item is in the head node
  }

  @Override
  public boolean isEmpty() {
    return head == null;
  }
}
