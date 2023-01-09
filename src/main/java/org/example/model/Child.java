package org.example.model;

import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Child implements Element{
    private final boolean isFile;
    private String text;

    public Child(boolean isFile) {
        this.isFile = isFile;
    }

    @Override
    public boolean isRoot() {
        return false;
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
        if (checkCondition(pattern)){
            String path = stack.stream()
                    .filter(a ->!a.isRoot())
                    .filter(a -> !a.getText().isEmpty())
                    .flatMap(a-> Stream.of(a.getText()))
                    .collect(Collectors.joining("/"));
            System.out.println(stack.get(0).getText() + path);
        }
        stack.pop();
    }
    private boolean checkCondition(Pattern pattern){
        return pattern.matcher(text).matches() && isFile;
    }
}
