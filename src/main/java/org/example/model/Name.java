package org.example.model;

import java.util.Stack;
import java.util.regex.Pattern;

public class Name implements Element{
    static final String tag = "name";
    private String text;

    @Override
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String getText() {
        return text;
    }
    @Override
    public void tegClose(Pattern pattern, String name, Stack<Element> stack) {
        text = name;
        stack.pop();
        if (!stack.empty()) {
            Element element= stack.peek();
            element.setText(text);
        }
    }
}
