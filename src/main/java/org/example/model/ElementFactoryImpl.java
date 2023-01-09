package org.example.model;

public class ElementFactoryImpl implements ElementFactory{

    @Override
    public Element createElement(String tag, boolean isFile) {
        Tags eTag = Tags.valueOf(tag);
        return switch (eTag) {
            case child -> new Child(isFile);
            case children-> new Children();
            case name -> new Name();
            case node -> new Node(isFile);
        };
    }
}
