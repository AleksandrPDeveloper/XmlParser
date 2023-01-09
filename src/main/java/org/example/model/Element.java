package org.example.model;

import java.util.Stack;
import java.util.regex.Pattern;

public interface Element {
      void setText(String name);
      String getText();
      boolean isRoot();
      void tegClose(Pattern pattern, String name, Stack<Element> stack);
      static void tegOpen(Element element, Stack<Element> stack){
          stack.add(element);
      }
}
