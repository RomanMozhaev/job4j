package ru.job4j.download;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * the starting class.
 */
public class Starter {

    private static final Logger LOG = LogManager.getLogger(Starter.class.getName());

    public static void main(String[] args) {
        Starter starter = new Starter();
        String url = "https://f.vividscreen.info/soft/25dd54eb81f5ed241345cea9ab9fd85d/Green-Landscape-2880x1920.jpg";
        String saveDir = "/home/romanm/Downloads";
        int speed = 100;
        starter.start(url, saveDir, speed);
    }

    private void start(String url, String saveDir, int speed) {
        Downloader downloader = new Downloader(url, saveDir, speed);
        try {
            downloader.t.join();
        } catch (InterruptedException ie) {
            LOG.error(ie.getMessage(), ie);
        }
    }
}
