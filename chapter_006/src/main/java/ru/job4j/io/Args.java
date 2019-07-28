package ru.job4j.io;

public class Args {

    private String dir;
    private String exclude;
    private String output;

    public Args(String dir, String exclude, String output) {
        this.dir = dir;
        this.exclude = exclude;
        this.output = output;
    }

    public String getDir() {
        return dir;
    }

    public String getExclude() {
        return exclude;
    }

    public String getOutput() {
        return output;
    }
}
