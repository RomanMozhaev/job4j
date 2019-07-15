package ru.job4j.analize;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Analise {


    public Info diff(List<User> previous, List<User> current) {
        Info result;
        if (previous == null) {
            if (current == null) {
                result = new Info(0, 0, 0);
            } else {
                result = new Info(current.size(), 0, 0);
            }
        } else {
            if (current == null) {
                result = new Info(0, 0, previous.size());
            } else {
                int changed = 0;
                int added = 0;
                Map<Integer, User> map = previous.stream()
                        .flatMap(Stream:: ofNullable)
                        .collect(Collectors.toMap(
                                e -> e.getId(),
                                e -> e
                        ));
                for (User user : current) {
                    User mapUser = map.get(user.getId());
                    if (mapUser != null) {
                        if (!mapUser.equals(user)) {
                            changed++;
                        }
                    } else {
                        added++;
                    }
                }
                int deleted = previous.size() - current.size() + added;
                result = new Info(added, changed, deleted);
            }
        }

        return result;

    }
}