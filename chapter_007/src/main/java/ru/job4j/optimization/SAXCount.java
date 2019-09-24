package ru.job4j.optimization;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

public class SAXCount extends DefaultHandler {

    private int sum;
    private int i = 0;
    private String element = "";

    public int getSum() {
        return sum;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        this.element = qName;
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (this.element.equals("entry")) {
            String num = "";
            char nextCh = ch[i];
            while (i < start) {
                if (nextCh >= '0' && nextCh <= '9') {
                    num += nextCh;
                }
                nextCh = ch[i++];
            }
            if (!num.equals("")) {
                sum += Integer.parseInt(num);
            }
        }
    }

    @Override
    public void endDocument() {
    }
}