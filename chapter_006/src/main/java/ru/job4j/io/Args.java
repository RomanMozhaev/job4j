package ru.job4j.io;

import java.io.File;
import java.util.List;

public class Args {

    private String dir;
    private List<String> exclude;
    private File output;

    public Args(String dir, List<String> exclude, String output) {
        this.dir = dir;
        this.exclude = exclude;
        this.output = new File(output);
    }

    public String getDir() {
        return dir;
    }

    public List<String> getExclude() {
        return exclude;
    }

    public File getOutput() {
        return output;
    }
}
