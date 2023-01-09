package org.example;

import org.example.model.Element;
import org.example.model.ElementFactory;
import org.example.model.ElementFactoryImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;
import java.util.regex.Pattern;


public class Parser extends DefaultHandler {
    private final Pattern pattern;
    ElementFactory elementFactory = new ElementFactoryImpl();
    Stack<Element> stack = new Stack<>();
    StringBuilder stringBuilder;

    public Parser(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            Element element = elementFactory.createElement(qName, isFile(attributes));
            Element.tegOpen(element, stack);
            stringBuilder = new StringBuilder();
        } catch (Exception e) {
            throw new SAXException();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        try {
            Element element = stack.peek();
            var text = stringBuilder.toString().trim();
            element.tegClose(pattern, text, stack);
        } catch (Exception e) {
            throw new SAXException();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        try {
            stringBuilder.append(String.valueOf(ch, start, length));
        } catch (Exception e) {
            throw new SAXException();
        }
    }

    private boolean isFile(Attributes attributes) {
        boolean isFile = false;
        String isFileAtr = "is-file";
        var atr = attributes.getValue(isFileAtr);

        if (atr != null) {
            String stringTrue = "true";
            isFile = atr.equalsIgnoreCase(stringTrue);
        }
        return isFile;
    }
}
