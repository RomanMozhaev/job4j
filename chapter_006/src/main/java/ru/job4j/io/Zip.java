package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public List<File> seekBy(String root, List<String> ext) {
        Search search = new Search();
        List<File> list = search.files(root);
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String name = list.get(i).getName();
            for (String extension : ext) {
                if (name.endsWith(extension.substring(1))) {
                    list.remove(i);
                    size--;
                    i--;
                    break;
                }
            }
        }
        return list;
    }

    public void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            if (sources != null) {
                for (File file : sources) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Args a = new Args(args);
        Zip zip = new Zip();
        zip.pack(zip.seekBy(a.getDir(), a.getExclude()), a.getOutput());
    }
}