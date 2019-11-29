package ru.job4j.download;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * the Downloader downloads files on link fileURL to saveDir with speed limitation.
 */
public class Downloader implements Runnable {
    /**
     * Buffer size.
     */
    private static final int BUFFER_SIZE = 4096;
    /**
     * the URL of the downloading file.
     */
    private String fileURL;
    /**
     * the directory for saving.
     */
    private String saveDir;
    /**
     * the required maximum of the speed in bytes per sec.
     */
    private int requiredSpeed;
    /**
     * the downloader thread.
     */
    protected final Thread t;
    /**
     * the all downloaded bytes.
     */
    private int allBytes;
    /**
     * the quantity of the bytes downloaded at the previous speed checking.
     */
    private int previousBytes = 0;
    /**
     * the time of the previous speed checking.
     */
    private long previousTime;
    /**
     * the time which this thread should sleep before continues downloading because it download too many bytes before.
     */
    private volatile long sleepTime = 0;
    /**
     * Logger.
     */
    private static final Logger LOG = LogManager.getLogger(Downloader.class.getName());


    /**
     * the main constructor.
     *
     * @param fileURL - the url of the file.
     * @param saveDir - the directory for file saving.
     * @param speed   - the maximum of the speed.
     */
    public Downloader(String fileURL, String saveDir, int speed) {
        this.fileURL = fileURL;
        this.saveDir = saveDir;
        this.requiredSpeed = speed * 1024;
        this.t = new Thread(this, "Downloader");
        this.t.start();
    }


    /**
     * the run method of the class.
     * the method downloads file. Timer once pre second checks how many bytes have been downloaded.
     * if the average speed for the last second was more then the required speed,
     * the sleep time is calculated and the thread sleeps required time.
     */
    @Override
    public void run() {
        try {
            URL url = new URL(this.fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = this.fileURL.substring(this.fileURL.lastIndexOf("/") + 1);
                InputStream inputStream = httpConn.getInputStream();
                String saveFilePath = this.saveDir + File.separator + fileName;
                FileOutputStream outputStream = new FileOutputStream(saveFilePath);
                byte[] buffer = new byte[BUFFER_SIZE];
                long startTime = System.currentTimeMillis();
                this.previousTime = startTime;
                Timer timer = new Timer(1000, actionEvent -> calcSleepTime());
                timer.start();
                int bytesRead = inputStream.read(buffer);
                while (bytesRead != -1) {
                    this.allBytes += bytesRead;
                    outputStream.write(buffer, 0, bytesRead);
                    bytesRead = inputStream.read(buffer);
                    if (this.sleepTime > 0) {
                        try {
                            Thread.sleep(sleepTime);
                        } catch (InterruptedException ie) {
                            LOG.error(ie.getMessage(), ie);
                        }
                        this.sleepTime = 0;
                    }
                }
                outputStream.close();
                inputStream.close();
                timer.stop();
                int time = (int) ((System.currentTimeMillis() - startTime) / 1000);
                System.out.println(String.format("Downloading complete. Total time: %d sec.", time));
                System.out.println("All bytes: " + this.allBytes);
            } else {
                System.out.println("No file. Response code: " + responseCode);
            }
            httpConn.disconnect();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * the method for calculating the sleep time.
     * if the sleep time is less than zero, the thread does not need to sleep,
     * because the downloading speed is less than max.
     */
    private void calcSleepTime() {
        int downloadedBytes = this.allBytes - this.previousBytes;
        this.previousBytes = this.allBytes;
        long now = System.currentTimeMillis();
        long cycleTime = now - this.previousTime;
        this.previousTime = now;
        this.sleepTime = 1000 * downloadedBytes / this.requiredSpeed - cycleTime;
    }
}
