package org.example.model;

public interface ElementFactory {
    Element createElement(String tag, boolean isFile);
}
