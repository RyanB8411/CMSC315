package Non_Projects;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

public class Exercise24_03 {
  public static void main(String[] args) {
    new Exercise24_03();
  }
  
  public Exercise24_03() {
    TwoWayLinkedList<Double> list = new TwoWayLinkedList<>();
    System.out.print("Enter five numbers: ");
    Scanner input = new Scanner(System.in);
    double[] v = new double[5];
    for (int i = 0; i < 5; i++)
      v[i] = input.nextDouble();

    list.addFirst(v[1]);
    list.addLast(v[2]);
    list.addLast(v[3]);
    list.add(v[4]);
    list.add(0, v[0]);
    list.add(2, 10.55);
    list.remove(3);

    java.util.ListIterator<Double> iterator1 = list.listIterator();
    while (iterator1.hasNext())
      System.out.print(iterator1.next() + " ");

    java.util.ListIterator<Double> iterator2 = list.listIterator(list.size() - 1);
    System.out.println();
    while (iterator2.hasPrevious())
      System.out.print(iterator2.previous() + " ");
  }
}

interface MyList<E> extends java.util.Collection<E> {
  /** Add a new element at the specified index in this list */
  public void add(int index, E e);

  /** Return the element from this list at the specified index */
  public E get(int index);

  /** Return the index of the first matching element in this list.
   *  Return -1 if no match. */
  public int indexOf(Object e);

  /** Return the index of the last matching element in this list
   *  Return -1 if no match. */
  public int lastIndexOf(E e);

  /** Remove the element at the specified position in this list
   *  Shift any subsequent elements to the left.
   *  Return the element that was removed from the list. */
  public E remove(int index);

  /** Replace the element at the specified position in this list
   *  with the specified element and returns the new set. */
  public E set(int index, E e);
  
  @Override /** Add a new element at the end of this list */
  public default boolean add(E e) {
    add(size(), e);
    return true;
  }

  @Override /** Return true if this list contains no elements */
  public default boolean isEmpty() {
    return size() == 0;
  }

  @Override /** Remove the first occurrence of the element e 
   *  from this list. Shift any subsequent elements to the left.
   *  Return true if the element is removed. */
  public default boolean remove(Object e) {
    if (indexOf(e) >= 0) {
      remove(indexOf(e));
      return true;
    }
    else
      return false;
  }

  @Override
  public default boolean containsAll(Collection<?> c) {
    // Left as an exercise
    return true;
  }

  @Override
  public default boolean addAll(Collection<? extends E> c) {
    // Left as an exercise
    return true;
  }

  @Override
  public default boolean removeAll(Collection<?> c) {
    // Left as an exercise
    return true;
  }

  @Override
  public default boolean retainAll(Collection<?> c) {
    // Left as an exercise
    return true;
  }

  @Override
  public default Object[] toArray() {
    // Left as an exercise
    return null;
  }

  @Override
  public default <T> T[] toArray(T[] array) {
    // Left as an exercise
    return null;
  }
}

class TwoWayLinkedList<E> implements MyList<E> {
  private Node<E> head, tail;
  private int size;

  /** Create a default list */
  public TwoWayLinkedList() {
  }

  /** Create a list from an array of objects */
  public TwoWayLinkedList(E[] objects) {
    for (E e : objects)
      add(e);
  }

  /** Return the head element in the list */
  public E getFirst() {
    if (size == 0) {
      return null;
    } else {
      return head.element;
    }
  }

  /** Return the last element in the list */
  public E getLast() {
    if (size == 0) {
      return null;
    } else {
      return tail.element;
    }
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[");

    Node<E> current = head;
    for (int i = 0; i < size; i++) {
      result.append(current.element);
      current = current.next;
      if (current != null) {
        result.append(", "); // Separate two elements with a comma
      } else {
        result.append("]"); // Insert the closing ] in the string
      }
    }

    return result.toString();
  }

  /** Clear the list */
  public void clear() {
    head = tail = null;
  }

  /** Return true if this list contains the element o */
  public boolean contains(Object e) {
    System.out.println("Implementation left as an exercise");
    return true;
  }

  /** Return the element from this list at the specified index */
  public E get(int index) {
    System.out.println("Implementation left as an exercise");
    return null;
  }

  /**
   * Return the index of the head matching element in this list. Return -1 if
   * no match.
   */
  public int indexOf(Object e) {
    System.out.println("Implementation left as an exercise");
    return 0;
  }

  /**
   * Return the index of the last matching element in this list Return -1 if
   * no match.
   */
  public int lastIndexOf(Object e) {
    System.out.println("Implementation left as an exercise");
    return 0;
  }

  /**
   * Replace the element at the specified position in this list with the
   * specified element.
   */
  public E set(int index, E e) {
    System.out.println("Implementation left as an exercise");
    return null;
  }

  private class LinkedListIterator implements java.util.ListIterator<E> {
    private Node<E> current = head; // Current index

    public LinkedListIterator() {
    }
    
    public LinkedListIterator(int index) {
      if (index < 0 || index >= size)
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
          + size);
      for (int nextIndex = 0; nextIndex < index; nextIndex++)
        current = current.next;
    }
    
    public void setLast() {
      current = tail;
    }
    
    @Override
    public boolean hasNext() {
      return (current != null);
    }

    @Override
    public E next() {
      E e = current.element;
      current = current.next;
      return e;
    }

    @Override
    public void set(E e) {
      current.element = e; // TODO Auto-generated method stub
    }
  }

  private class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E o) {
      element = o;
    }
  }

  @Override
  public int size() {
    return size;
  }

  public ListIterator<E> listIterator() {
    return new LinkedListIterator(); 
  }
  
  public ListIterator<E> listIterator(int index) {
    return new LinkedListIterator(index); 
  }

  @Override
  public Iterator<E> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

  /** Add an element to the beginning of the list */
  public void addFirst(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new node
    newNode.next = head; // Link the new node with the head
    head = newNode; // head points to the new node
    size++; // Increase list size

    if (tail == null) // the new node is the only node in list
      tail = head;

    if (head != tail)
      head.next.previous = head; // For a two-way linked list
  }

  /** Add an element to the end of the list */
  public void addLast(E e) {
    Node<E> newNode = new Node<E>(e); // Create a new for element e

    Node<E> temp = tail; // For a two-way linked list

    if (tail == null) {
      head = tail = newNode; // The new node is the only node in list
    } else {
      tail.next = newNode; // Link the new with the last node
      tail = tail.next; // tail now points to the last node
    }

    size++; // Increase size

    tail.previous = temp; // For a two-way linked list
  }

  /**
   * Add a new element at the specified index in this list The index of the
   * head element is 0
   */
  public void add(int index, E e) {
    if (index == 0) {
      addFirst(e);
    }
    else if (index >= size) {
      addLast(e);
    } 
    else {
      Node<E> current = head;
      for (int i = 1; i < index; i++) {
        current = current.next;
      }
      Node<E> temp = current.next;
      current.next = new Node<E>(e);
      (current.next).next = temp;
      size++;

      // For a two-way linked list
      temp.previous = current.next;
      current.next.previous = current;
    }
  }

  /**
   * Remove the head node and return the object that is contained in the
   * removed node.
   */
  public E removeFirst() {
    if (size == 0) {
      return null;
    } else {
      Node<E> temp = head;
      head = head.next;
      size--;
      if (head == null) {
        tail = null;
      }
      if (head != null)
        head.previous = null; // For two-way linked list
      return temp.element;
    }
  }

  /**
   * Remove the last node and return the object that is contained in the
   * removed node.
   */
  public E removeLast() {
    if (size == 0) {
      return null;
    } else if (size == 1) {
      return removeFirst();
    } else {
      Node<E> temp = tail;
      tail = tail.previous; // For two-way linked list
      tail.next = null;
      size--;
      return temp.element;
    }
  }

  /**
   * Remove the element at the specified position in this list. Return the
   * element that was removed from the list.
   */
  public E remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    } else if (index == 0) {
      return removeFirst();
    } else if (index == size - 1) {
      return removeLast();
    } else {
      Node<E> previous = head;

      for (int i = 1; i < index; i++) {
        previous = previous.next;
      }

      Node<E> current = previous.next;
      previous.next = current.next;
      current.next.previous = previous; // For a two-way linked list
      size--;
      return current.element;
    }
  }
}