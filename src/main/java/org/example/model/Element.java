package org.example.model;

import java.util.Stack;
import java.util.regex.Pattern;

public interface Element {
      void setText(String name);
      String getText();
      void tegClose(Pattern pattern, String name, Stack<Element> stack);
      static void tegOpen(String tag, boolean isFile, Stack<Element> stack){

          Element element = switch (tag) {
              case Child.tag -> new Child(isFile);
              case Children.tag -> new Children();
              case Name.tag -> new Name();
              case Node.tag -> new Node(isFile);
              default -> null;
          };
          stack.add(element);
      }
}
