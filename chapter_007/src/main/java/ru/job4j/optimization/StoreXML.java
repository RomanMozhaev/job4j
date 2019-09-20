package ru.job4j.optimization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.sqlstorage.UsageLog4j2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class StoreXML {

    private String target;
    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    public StoreXML(String target) {
        this.target = target;
    }

    public void save(List<XMLUsage.Entry> list) {
        XMLUsage.Entries entries = new XMLUsage.Entries(list);
        try {
            File file = new File(this.target);
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLUsage.Entries.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entries, file);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
