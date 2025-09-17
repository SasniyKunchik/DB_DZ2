package generics;

import org.w3c.dom.Node;

public class LinkedList<C> implements List<C> {

    private Node<C> first;

    private static class Node<D> {
        D value;
        Node<D> next;
    }
    @Override
    public void add(C c) {

    }

    @Override
    public C get(int index) {
        return null;
    }
}
