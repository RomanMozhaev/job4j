package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            List<String> list = reader.lines().collect(Collectors.toList());
            List<String> time = stringAnalyser(list);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
                int i = 0;
                while (i < time.size()) {
                    writer.write(time.get(i++));
                    writer.write(";");
                    if (i != time.size()) {
                        writer.write(time.get(i++));
                        writer.write(";");
                        writer.write(System.lineSeparator());
                    }
                }
            } catch (IOException ioe) {
                System.out.println("File writing exception");
            }
        } catch (IOException e) {
            System.out.println("File reading exception");
        }
    }

    private List<String> stringAnalyser(List<String> list) {
        List<String> result = new ArrayList<>();
        boolean able = true;
        for (String string : list) {
            String type = string.substring(0, 3);
            if (!able && (type.equals("200") || type.equals("300"))) {
                able = true;
                result.add(string.substring(4));
            }
            if (able && (type.equals("400") || type.equals("500"))) {
                able = false;
                result.add(string.substring(4));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}