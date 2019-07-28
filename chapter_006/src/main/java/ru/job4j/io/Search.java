package ru.job4j.io;

import java.io.File;
import java.util.*;

public class Search {

    public List<File> exists(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        List<File> files = files(parent);
        if (!files.isEmpty()) {
            int i = 0;
            int size = files.size();
            while (i < size) {
                File f = files.get(i++);
                if (checkExtends(f.getName(), exts)) {
                    result.add(f);
                }
            }
        }
        return result;
    }

    private boolean checkExtends(String fileName, List<String> exts) {
        boolean result = false;
        for (String ext : exts) {
            if (fileName.endsWith("." + ext)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public List<File> files(String parent) {
        List<File> result = new ArrayList<>();
        Queue<File> list = new LinkedList<>();
        File file = new File(parent);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                list.addAll(Arrays.asList(files));
                while (!list.isEmpty()) {
                    file = list.poll();
                    if (file.isFile()) {
                        result.add(file);
                    } else {
                        files = file.listFiles();
                        if (files != null) {
                            list.addAll(Arrays.asList(files));
                        }
                    }
                }
            }
        }
        return result;
    }
}