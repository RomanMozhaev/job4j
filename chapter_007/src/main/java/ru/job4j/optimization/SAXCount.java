package ru.job4j.optimization;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SAXCount extends DefaultHandler {

    private int sum;
    private char[] chars;
    int startPoint;

    public int getSum() {
        return sum;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
//        this.element = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        String num = "";
        char c;
        if (qName.equals("entry")) {
            do {
                c = this.chars[this.startPoint++];
            } while (c != '"');
            while (c != '/') {
                if (c >= '0' && c <= '9') {
                    num += c;
                }
                c = this.chars[this.startPoint++];
            }
            if (!num.equals("")) {
                this.sum += Integer.parseInt(num);
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        this.chars = ch;
        this.startPoint = start;
    }

    @Override
    public void endDocument() {
    }
}