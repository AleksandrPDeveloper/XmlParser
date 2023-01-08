package org.example.model;

import java.util.Stack;
import java.util.regex.Pattern;

public class Node implements Element{
    static final String tag = "node";
    private boolean isFile;
    private final boolean root = true;
    private String text;

    public Node(boolean isFile) {
        this.isFile = isFile;
    }

    public boolean isFile() {
        return isFile;
    }
    @Override
    public String getText() {
        return text;
    }
    @Override
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public void tegClose(Pattern pattern, String name, Stack<Element> stack) {
        stack.pop();
    }
}
