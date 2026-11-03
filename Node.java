public class Node {
    private Object data;
    private Node next;

    public Node(Object obj) {
        data = obj;
        next = null;
    }

    public void setNext(Node nextPtr) {
        next = nextPtr;
    }

    public Node getNext() {
        return next;
    }

    public void setData(Object obj) {
        data = obj;
    }

    public Object getData() {
        return data;
    }

    public String toString() {
        return data.toString();
    }
}