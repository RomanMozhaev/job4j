package ru.job4j.optimization;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SAXCount extends DefaultHandler {

    private int sum;
    private final static String ENTRY = "entry";

    public int getSum() {
        return sum;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        if (ENTRY.equals(qName)) {
            this.sum += Integer.parseInt(attributes.getValue("href"));
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endDocument() {
    }
}