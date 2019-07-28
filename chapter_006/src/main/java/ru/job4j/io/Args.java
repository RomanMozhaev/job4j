package ru.job4j.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Args {

    private String dir;
    private List<String> exclude;
    private String output;
//-d /home/roman/Projects/job4j -e *.java -o project.zip
    public Args(String[] args) {
        this.dir = "";
        this.exclude = new ArrayList<>();
        this.output = "";
        int size = args.length;
        int i = 0;
        if (i < size - 1 && args[i].equals("-d")) {
            i++;
            this.dir = args[i++];
        }
        if (i < size - 1 && args[i].equals("-e")) {
            i++;
            while (i < size && !args[i].equals("-o")) {
                this.exclude.add(args[i++]);
            }
        }
        if (i < size - 1 && args[i].equals("-o")) {
            i++;
            this.output = args[i];
        }
    }

    public String getDir() {
        return dir;
    }

    public List<String> getExclude() {
        return exclude;
    }

    public File getOutput() {
        return new File(dir + "/" + output);
    }
}
