package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void load() {
        try (BufferedReader bReader = new BufferedReader(new FileReader(this.path))) {
            this.values.putAll(bReader.lines()
                    .flatMap(string -> stringAnalyser(string))
                    .flatMap(Stream :: ofNullable)
                    .collect(Collectors.toMap(string -> string[0], string -> string[1])));
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }

    public Stream<String[]> stringAnalyser(String s) {
        Stream<String[]> result = Stream.empty();
        int length = s.length();
        int start;
        if (length > 0) {
            char[] chars = s.toCharArray();
            int i = 0;
            while (length > i && chars[i] == ' ') {
                i++;
            }
            start = i;
            if (i < length && ((chars[i] >= 'a' && chars[i] <= 'z') || (chars[i] >= 'A' && chars[i] <= 'Z'))) {
                while (length - 1 > i) {
                    if (chars[i] == '=') {
                        break;
                    }
                    i++;
                }
                if (i - start > 0 && length - i - 1 > 0) {
                    String[] temp = new String[2];
                    String[] tempNull = null;
                    temp[0] = String.copyValueOf(chars, start, i - start);
                    temp[1] = String.copyValueOf(chars, i + 1, length - i - 1);
                    result = Stream.of(temp, tempNull);
                }
            }
        }
        return result;
    }

    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}