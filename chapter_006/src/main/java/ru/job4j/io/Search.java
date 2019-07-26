package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

    public List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        File file = new File(parent);
        if (file.isDirectory()) {
            File[] fileArray = file.listFiles();
            if (fileArray != null) {
                List<File> list = new ArrayList<>(Arrays.asList(fileArray));
                int i = 0;
                while (i != list.size()) {
                    File f = list.get(i++);
                    if (f.isFile()) {
                        String name = f.getName();
                        for (String ext : exts) {
                            if (name.endsWith("." + ext)) {
                                result.add(f);
                                break;
                            }
                        }
                    } else {
                        fileArray = f.listFiles();
                        if (fileArray != null) {
                            list.addAll(Arrays.asList(fileArray));
                        }
                    }
                }
            }
        }
        return result;
    }
}
