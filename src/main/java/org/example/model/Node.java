package org.example.model;


public class Node extends Child {
    public Node(boolean isFile) {
        super(isFile);
    }
    @Override
    public boolean isRoot() {
        return true;
    }
}
