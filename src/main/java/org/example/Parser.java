package org.example;

import org.example.model.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;
import java.util.regex.Pattern;


public class Parser extends DefaultHandler {
    private final Pattern pattern;
    Stack<Element> stack = new Stack<>();
    StringBuilder stringBuilder;
    public Parser(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       Element.tegOpen(qName, isFile(attributes), stack);
       stringBuilder = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        Element element = stack.peek();
        var text =  stringBuilder.toString().trim();
        element.tegClose(pattern, text, stack);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(String.valueOf(ch, start, length));
    }

    private boolean isFile(Attributes attributes){
        boolean isFile = false;
        String isFileAtr = "is-file";
        var atr = attributes.getValue(isFileAtr);

        if (atr != null){
            String stringTrue = "true";
            isFile = atr.equalsIgnoreCase(stringTrue);
        }
        return isFile;
    }
}
