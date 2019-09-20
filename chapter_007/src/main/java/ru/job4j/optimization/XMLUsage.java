package ru.job4j.optimization;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

public class XMLUsage {

    @XmlRootElement
    public static class Entries {

        private List<Entry> values;

        public Entries() {
        }

        public Entries(List<Entry> values) {
            this.values = values;
        }

        public List<Entry> getValues() {
            return this.values;
        }
        @XmlElement (name = "entry")
        public void setValues(List<Entry> values) {
            this.values = values;
        }
    }

    @XmlRootElement
    public static class Entry {

        int field;

        public Entry() {
        }
        public Entry(int field) {
            this.field = field;
        }
        public int getField() {
            return field;
        }
        @XmlElement
        public void setField(int field) {
            this.field = field;
        }
    }
}
