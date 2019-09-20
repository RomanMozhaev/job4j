package ru.job4j.optimization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.sqlstorage.UsageLog4j2;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class ConvertXSQT {
    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());
    public void convert(File source, File dest, File scheme) {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(
                    new StreamSource(
                            new ByteArrayInputStream(new FileInputStream(scheme).readAllBytes()))
            );
            transformer.transform(new StreamSource(
                            new ByteArrayInputStream(new FileInputStream(source).readAllBytes())),
                    new StreamResult(new FileOutputStream(dest))
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
