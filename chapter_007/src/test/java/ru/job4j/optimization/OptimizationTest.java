package ru.job4j.optimization;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class OptimizationTest {
    private String home = "database/";

    @Test
    public void whenGenerateThanTableExists() {
        StoreSQL storeSQL = new StoreSQL(new Config());
        int size = 10;
        int result = -1;
        try {
            Connection conn = storeSQL.init();
            conn.setAutoCommit(false);
            storeSQL.generate(size);
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("SELECT count(*) FROM entry");
            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }
            conn.rollback();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertThat(size, is(result));
    }


    @Test
    public void whenListThanXML() {
//        String target = "D:\\GoogleDrive\\database\\file.xml";
        String target = home + "file.xml";
        int size = 5;
        StringBuilder result = new StringBuilder();
        String expected = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("<entries>")
                .append("    <entry>")
                .append("        <field>1</field>")
                .append("    </entry>")
                .append("    <entry>")
                .append("        <field>2</field>")
                .append("    </entry>")
                .append("    <entry>")
                .append("        <field>3</field>")
                .append("    </entry>")
                .append("</entries>")
                .toString();
        StoreSQL storeSQL = new StoreSQL(new Config());
        StoreXML storeXML = new StoreXML(target);
        try {
            Connection conn = storeSQL.init();
            conn.setAutoCommit(false);
            storeSQL.generate(size);
            List<XMLUsage.Entry> list = storeSQL.load();
            storeXML.save(list);
            conn.rollback();
            conn.close();

            FileReader reader = new FileReader(target);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.lines().forEach(string -> result.append(string));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result.toString(), is(expected));
    }

    @Test
    public void whenOldXMLThanNewXML() {
//        String source = "D:\\GoogleDrive\\database\\file.xml";
        String source = home + "file.xml";
//        String dest = "D:\\GoogleDrive\\database\\newFile.xml";
        String dest = home + "newFile.xml";
//        String scheme = "D:\\GoogleDrive\\database\\template.xsl";
        String scheme = home + "template.xsl";
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries>"
                + "\t<entry href=\"1\"/>"
                + "\t<entry href=\"2\"/>"
                + "\t<entry href=\"3\"/></entries>";
        StringBuilder result = new StringBuilder();
        int size = 5;
        StoreSQL storeSQL = new StoreSQL(new Config());
        StoreXML storeXML = new StoreXML(source);
        ConvertXSQT convertXSQT = new ConvertXSQT();
        try {
            Connection conn = storeSQL.init();
            conn.setAutoCommit(false);
            storeSQL.generate(size);
            List<XMLUsage.Entry> list = storeSQL.load();
            storeXML.save(list);
            conn.rollback();
            conn.close();
            convertXSQT.convert(new File(source), new File(dest), new File(scheme));
            FileReader reader = new FileReader(dest);
            BufferedReader bufferedReader = new BufferedReader(reader);
            bufferedReader.lines().forEach(string -> result.append(string));
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(result.toString(), is(expected));
    }
    @Test
    public void whenParserBySAXThenSum() throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXCount count = new SAXCount();
//        String fileName = "D:\\GoogleDrive\\database\\newFile.xml";
        String fileName = home + "newFile.xml";
        File file = new File(fileName);
        parser.parse(file, count);
        assertThat(count.getSum(), is(15));
    }
}