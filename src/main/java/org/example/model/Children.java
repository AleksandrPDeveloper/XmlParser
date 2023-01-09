package org.example.model;

import java.util.Stack;
import java.util.regex.Pattern;

public class Children implements Element{
    @Override
    public boolean isRoot() {
        return false;
    }
    @Override
    public String getText() {
        return "";
    }
    @Override
    public void setText(String text) {
    }
    @Override
    public void tegClose(Pattern pattern, String name, Stack<Element> stack) {
        stack.pop();
    }
}
