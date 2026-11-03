public class List {
    private Node head;
    private Node tail;
    private String Name;

    public List() {
        this("No Name");
    }

    public List(String name) {
        Name = name;
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtFront(Object obj) {
        Node newnode = new Node(obj);
        if (isEmpty()) {
            head = tail = newnode;
        } else {
            newnode.setNext(head);
            head = newnode;
        }
    }

    public void insertAtBack(Object obj) {
        Node newnode = new Node(obj);
        if (isEmpty()) {
            head = tail = newnode;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newnode);
            tail = newnode;
        }
    }

    public void insertAtMiddle(Object o, int index) {
        if (index <= 0 || isEmpty()) {
            insertAtFront(o);
            return;
        }
        Node newNode = new Node(o);
        Node current = head;
        for (int i = 0; i < index - 1 && current.getNext() != null; i++) {
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (newNode.getNext() == null) tail = newNode;
    }

    public Object removeFromFront() {
        if (isEmpty()) return null;
        Object removedData = head.getData();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
        }
        return removedData;
    }

    public Object removeFromBack() {
        if (isEmpty()) return null;
        Object removedData = tail.getData();
        if (head == tail) {
            head = tail = null;
        } else {
            Node current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            current.setNext(null);
            tail = current;
        }
        return removedData;
    }

    public Object removeFromMiddle(int index) {
        if (isEmpty() || index < 0) return null;
        if (index == 0) return removeFromFront();
        Node current = head;
        Node previous = null;
        for (int i = 0; i < index && current.getNext() != null; i++) {
            previous = current;
            current = current.getNext();
        }
        Object data = current.getData();
        if (current == tail) return removeFromBack();
        previous.setNext(current.getNext());
        return data;
    }

    public int size() {
        if (isEmpty()) return 0;
        Node current = head;
        int c = 1;
        while (current.getNext() != null) {
            current = current.getNext();
            c++;
        }
        return c;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println(Name + " is empty.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public Node getHead() {
        return head;
    }
}