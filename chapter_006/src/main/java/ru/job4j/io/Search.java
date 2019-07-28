package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

    public List<File> exists(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        List<File> files = files(parent);
        if (!files.isEmpty()) {
            int i = 0;
            int size = files.size();
            while (i < size) {
                File f = files.get(i++);
                String name = f.getName();
                for (String ext : exts) {
                    if (name.endsWith("." + ext)) {
                        result.add(f);
                    }
                }
            }
        }
        return result;
    }

    public List<File> files(String parent) {
        List<File> result = new ArrayList<>();
        List<File> list = new ArrayList<>();
        File file = new File(parent);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                list.addAll(Arrays.asList(files));
                int i = 0;
                while (i < list.size()) {
                    file = list.get(i++);
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