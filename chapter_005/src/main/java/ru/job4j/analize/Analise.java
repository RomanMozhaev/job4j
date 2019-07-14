package ru.job4j.analize;

import java.util.*;

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
                List<User> listCrnt = new LinkedList<>();
                List<User> listPrev = new LinkedList<>();
                listCrnt.addAll(current);
                listPrev.addAll(previous);
                for (User userPrev : previous) {
                    for (User userCrnt : current) {
                        if (userPrev.equals(userCrnt)) {
                            listCrnt.remove(userCrnt);
                            listPrev.remove(userPrev);
                            break;
                        }
                        if (userPrev.equalsId(userCrnt)) {
                            changed++;
                            listCrnt.remove(userCrnt);
                            listPrev.remove(userPrev);
                            break;
                        }
                    }
                }
                int added = listCrnt.size();
                int deleted = listPrev.size();
                result = new Info(added, changed, deleted);
            }
        }

        return result;

    }
}