package ru.job4j.parser;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import java.util.Calendar;

public class ParserJob implements Job {

    public void execute(JobExecutionContext context) {
        System.out.println("starting");
        JobDataMap dataMap = context.getJobDetail().getJobDataMap();
        System.out.println("1");
        String url = dataMap.getString("url");
        String username = dataMap.getString("username");
        String password = dataMap.getString("password");
        String website = dataMap.getString("website");
        System.out.println("2");
        SQLManager sqlManager = new SQLManager(url, username, password);
        System.out.println("3");
        sqlManager.init();
        System.out.println("4");
        Parser parser = new Parser(sqlManager.getLastTime());
        Calendar now = Calendar.getInstance();
        boolean result;
        int i = 1;
        String link;
        System.out.println("5");
        do {
            link = website + "/" + i;
            result = parser.parse(link);
            i++;
            System.out.println("6." + i);
        } while (result);
        System.out.println("7");
        sqlManager.addNewVacations(parser.getVacations());
        System.out.println("8");
        sqlManager.writeLastTime(now.getTimeInMillis());
        System.out.println("9");
    }
}
