package structures;

/** A single link in a queue. */
class Node<T> {
    final T data;
    Node<T> next;

    Node(T data) {
        this.data = data;
    }
}
